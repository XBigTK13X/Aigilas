package aigilas.statuses;

import aigilas.creatures.CreatureAction;
import aigilas.creatures.ICreature;
import aigilas.entities.Elements;

import java.util.ArrayList;
import java.util.List;

public class StatusPool {
    private List<IStatus> _statuses = new ArrayList<IStatus>();

    public boolean allows(CreatureAction action) {
        for (int ii = 0; ii < _statuses.size(); ii++) {
            if (_statuses.get(ii).prevents(action)) {
                return false;
            }
        }
        return true;
    }

    public void add(IStatus status) {
        _statuses.add(status);
    }

    public void update() {
        for (int ii = 0; ii < _statuses.size(); ii++) {
            _statuses.get(ii).update();
            if (!_statuses.get(ii).isActive()) {
                _statuses.remove(_statuses.get(ii));
                ii--;
            }
        }
    }

    public void act() {
        for (int ii = 0; ii < _statuses.size(); ii++) {
            _statuses.get(ii).act();
        }
    }

    public void passOn(ICreature target, StatusComponent componentType) {
        for (int ii = 0; ii < _statuses.size(); ii++) {
            _statuses.get(ii).passOn(target, componentType);
        }
    }

    public boolean isElementBlocked(Elements element) {
        for (IStatus status : _statuses) {
            if (status.isElementBlocked(element)) {
                return true;
            }
        }
        return false;
    }
}
