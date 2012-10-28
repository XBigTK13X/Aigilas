package aigilas.reactions;

import aigilas.creatures.BaseCreature;
import aigilas.entities.Elements;
import aigilas.entities.ReactionMarker;
import sps.core.Logger;
import sps.text.ActionText;
import sps.text.TextManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReactionMeter {
    private static final HashMap<Integer, Reaction> __reactions = new HashMap<Integer, Reaction>();


    //TODO Move into config
    static {
        __reactions.put(12, Reaction.Sweat);
        __reactions.put(13, Reaction.Magma);
        __reactions.put(14, Reaction.Explosion);
        __reactions.put(15, Reaction.Scorch);
        __reactions.put(16, Reaction.Blind);
        __reactions.put(17, Reaction.Lactic_Acid);
        __reactions.put(18, Reaction.Mind_Blown);
        __reactions.put(23, Reaction.Vent);
        __reactions.put(24, Reaction.Drown);
        __reactions.put(25, Reaction.Reflect);
        __reactions.put(26, Reaction.Drench);
        __reactions.put(27, Reaction.Pneumonia);
        __reactions.put(28, Reaction.Lobotomy);
        __reactions.put(34, Reaction.Rust);
        __reactions.put(35, Reaction.Purify);
        __reactions.put(36, Reaction.Eclipse);
        __reactions.put(37, Reaction.Respect);
        __reactions.put(38, Reaction.Craftsman);
        __reactions.put(45, Reaction.Flash);
        __reactions.put(46, Reaction.Metabolism);
        __reactions.put(47, Reaction.Fast_Forward);
        __reactions.put(48, Reaction.Blank);
        __reactions.put(56, Reaction.Yin_Yang);
        __reactions.put(57, Reaction.Expose);
        __reactions.put(58, Reaction.Enlighten);
        __reactions.put(67, Reaction.Atrophy);
        __reactions.put(68, Reaction.Neurosis);
        __reactions.put(78, Reaction.Confuse);
    }

    private final BaseCreature _parent;
    private final List<Elements> _elements = new ArrayList<Elements>();
    private final List<ReactionMarker> _markers = new ArrayList<ReactionMarker>();
    private static final int _maxTimer = 120;
    private int _reactionTimer = _maxTimer;

    public ReactionMeter(BaseCreature parent) {
        _parent = parent;
    }

    private void resetComboDisplay() {
        _reactionTimer = _maxTimer;
        for (ReactionMarker _marker : _markers) {
            _marker.setInactive();
        }
        _markers.clear();
        for (Elements element : _elements) {
            _markers.add(new ReactionMarker(_parent, element));
            _markers.get(_markers.size() - 1).loadContent();
        }
    }

    public void draw() {
        for (ReactionMarker marker : _markers) {
            marker.draw();
        }
    }

    public void add(Elements element) {
        if (!_elements.contains(element)) {
            Logger.info(element.toString());
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
            for (Elements el : _elements) {
                Logger.info(el + ";;");
            }
            resetComboDisplay();
        }
    }

    private BaseReaction reaction;

    public void update() {
        for (ReactionMarker marker : _markers) {
            marker.update();
        }
        int key = 0;
        if (_elements.size() == 3) {
            key = _elements.get(0).Value * 100 + _elements.get(1).Value * 10 + _elements.get(2).Value;
        }
        if (_elements.size() == 2) {
            key = _elements.get(0).Value * 10 + _elements.get(1).Value;
        }
        if (_elements.size() == 1) {
            key = _elements.get(0).Value;
        }
        react(key);
    }

    private void react(int reactionId) {
        _reactionTimer--;
        if (_reactionTimer <= 0) {
            if (__reactions.keySet().contains(reactionId)) {
                reaction = ReactionFactory.create(__reactions.get(reactionId));
                if (reaction != null) {
                    reaction.affect(_parent);
                    Logger.gameplay(_parent + " affected by " + __reactions.get(reactionId).toString());
                    TextManager.add(new ActionText(__reactions.get(reactionId).toString(), 10, (int) _parent.getLocation().PosX, (int) _parent.getLocation().PosY));
                }
            }
            _elements.clear();
            resetComboDisplay();
            _reactionTimer = _maxTimer;
        }
    }
}
