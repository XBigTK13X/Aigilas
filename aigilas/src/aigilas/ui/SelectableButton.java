package aigilas.ui;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class SelectableButton extends TextButton {
    private boolean isSelected;

    public SelectableButton(String text, TextButtonStyle style) {
        super(text, style);
    }

    @Override
    public boolean isOver() {
        return isSelected || super.isOver();
    }

    public boolean mouseIsOver(){
        return super.isOver();
    }

    public void setSelected(boolean selected){
        isSelected = selected;
    }
}
