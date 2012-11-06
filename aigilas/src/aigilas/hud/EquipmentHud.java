package aigilas.hud;

import aigilas.creatures.BaseCreature;
import aigilas.items.Equipment;
import aigilas.items.GenericItem;
import aigilas.items.ItemSlot;
import sps.core.Point2;
import sps.graphics.Renderer;
import sps.text.Text;
import sps.text.TextPool;
import sps.util.StringSquisher;

import java.util.HashMap;

public class EquipmentHud extends BaseHud {
    private final Equipment _equipment;

    public EquipmentHud(BaseCreature owner, Equipment equipment) {
        super(owner, Renderer.VirtualWidth / 2, Renderer.VirtualHeight / 2);
        _equipment = equipment;
    }

    @Override
    public void draw() {
    }

    private static final String sep = ":";
    private String display = "EMPTY";
    private final String title = "Equipped\n";
    private String[] list = new String[10];

    private Text header;
    private Text[] equipList = new Text[10];
    private Point2 headerPosition = new Point2(0, 0);

    public void update(boolean refresh) {
        if (_isVisible) {
            if (refresh) {
                list = new String[10];
                StringSquisher.clear();
                StringSquisher.squish(title);
                display = StringSquisher.flush();
                HashMap<ItemSlot, GenericItem> items = _equipment.getItems();
                int count = 0;
                for (ItemSlot item : items.keySet()) {
                    count++;
                    if (count < 10) {
                        StringSquisher.clear();
                        StringSquisher.squish(item.toString().substring(0, 1), sep, items.get(item).Name);
                        list[count] = StringSquisher.flush();
                    }
                }
                if (header != null) {
                    header.hide();
                }
                headerPosition.reset(getInventoryAnchor().X + (int) (_dimensions.X * .5), getInventoryAnchor().Y + (int) (_dimensions.Y * .9), false);
                header = TextPool.get().write(display, headerPosition);
                for (int ii = 0; ii < 10; ii++) {
                    if (list[ii] != null) {
                        if (equipList[ii] != null) {
                            equipList[ii].hide();
                        }
                        equipList[ii] = TextPool.get().write(list[ii], getInventoryAnchor().add((int) (_dimensions.X * .5), (int) (_dimensions.Y * .9) - 60 * ii));
                    }
                }
            }
        }
        else {
            if (header != null) {
                header.hide();
                for (Text text : equipList) {
                    if (text != null) {
                        text.hide();
                    }
                }
            }
        }
    }
}
