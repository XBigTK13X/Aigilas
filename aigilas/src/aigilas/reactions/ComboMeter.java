package aigilas.reactions;

import aigilas.creatures.BaseCreature;
import aigilas.entities.ComboMarker;
import aigilas.entities.Elements;
import sps.text.ActionText;
import sps.text.TextManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ComboMeter {
    private static final HashMap<Integer, ReactionId> __reactions = new HashMap<Integer, ReactionId>();

    static {
        __reactions.put(12, ReactionId.SWEAT);
        __reactions.put(13, ReactionId.MAGMA);
        __reactions.put(14, ReactionId.EXPLOSION);
        __reactions.put(15, ReactionId.SCORCH);
        __reactions.put(16, ReactionId.BLIND);
        __reactions.put(17, ReactionId.LACTIC_ACID);
        __reactions.put(18, ReactionId.MIND_BLOWN);
        __reactions.put(23, ReactionId.VENT);
        __reactions.put(24, ReactionId.DROWN);
        __reactions.put(25, ReactionId.REFLECT);
        __reactions.put(26, ReactionId.DRENCH);
        __reactions.put(27, ReactionId.PNEUMONIA);
        __reactions.put(28, ReactionId.LOBOTOMY);
        __reactions.put(34, ReactionId.RUST);
        __reactions.put(35, ReactionId.PURIFY);
        __reactions.put(36, ReactionId.ECLIPSE);
        __reactions.put(37, ReactionId.RESPECT);
        __reactions.put(38, ReactionId.CRAFTSMAN);
        __reactions.put(45, ReactionId.FLASH);
        __reactions.put(46, ReactionId.METABOLISM);
        __reactions.put(47, ReactionId.FAST_FORWARD);
        __reactions.put(48, ReactionId.BLANK);
        __reactions.put(56, ReactionId.YIN_YANG);
        __reactions.put(57, ReactionId.EXPOSE);
        __reactions.put(58, ReactionId.ENLIGHTEN);
        __reactions.put(67, ReactionId.ATROPHY);
        __reactions.put(68, ReactionId.NEUROSIS);
        __reactions.put(78, ReactionId.CONFUSE);
    }

    private final BaseCreature _parent;
    private final List<Elements> _elements = new ArrayList<Elements>();
    private final List<ComboMarker> _markers = new ArrayList<ComboMarker>();
    private static final int _maxTimer = 120;
    private int _reactionTimer = _maxTimer;

    public ComboMeter(BaseCreature parent) {
        _parent = parent;
    }

    private void resetComboDisplay() {
        _reactionTimer = _maxTimer;
        for (ComboMarker _marker : _markers) {
            _marker.setInactive();
        }
        _markers.clear();
        int jj = 0;
        for (Elements element : _elements) {
            _markers.add(new ComboMarker(_parent, element, jj));
            jj++;
            _markers.get(_markers.size() - 1).loadContent();
        }
    }

    public void draw() {
        for (ComboMarker marker : _markers) {
            marker.draw();
        }
    }

    public void add(Elements element) {
        if (!_elements.contains(element)) {
            if (_elements.size() == 2) {
                if (_elements.get(0).Value > element.Value) {
                    _elements.add(0, element);
                }
                else if (_elements.get(1).Value > element.Value) {
                    _elements.add(1, element);
                }
                else {
                    _elements.add(element);
                }
            }
            else if (_elements.size() == 1) {
                if (_elements.get(0).Value > element.Value) {
                    _elements.add(0, element);
                }
                else {
                    _elements.add(element);
                }
            }
            if (_elements.size() == 0) {
                _elements.add(element);
            }
            resetComboDisplay();
        }
    }

    private IReaction reaction;

    public void update() {
        for (ComboMarker marker : _markers) {
            marker.update();
        }
        int key;
        if (_elements.size() == 3) {
            key = _elements.get(0).Value * 100 + _elements.get(1).Value * 10 + _elements.get(2).Value;
            react(key);
        }
        if (_elements.size() == 2) {
            key = _elements.get(0).Value * 10 + _elements.get(1).Value;
            react(key);
        }
        if (_elements.size() == 1) {
            react(_elements.get(0).Value);
        }
    }

    private void react(int reactionId) {
        _reactionTimer--;
        if (_reactionTimer <= 0) {
            if (__reactions.keySet().contains(reactionId)) {
                reaction = ReactionFactory.create(__reactions.get(reactionId));
                if (reaction != null) {
                    reaction.affect(_parent);
                    TextManager.add(new ActionText(__reactions.get(reactionId).toString(), 10, (int) _parent.getLocation().PosX, (int) _parent.getLocation().PosY));
                }
            }
            _elements.clear();
            resetComboDisplay();
            _reactionTimer = _maxTimer;
        }
    }
}
