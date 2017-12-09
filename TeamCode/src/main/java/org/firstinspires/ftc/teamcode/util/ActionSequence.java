package org.firstinspires.ftc.teamcode.util;

import org.firstinspires.ftc.teamcode.action.Action;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djfigs1 on 11/18/16.
 */
public class ActionSequence extends RobotHardware {

    private List<Action> actions = new ArrayList<Action>();
    private int currentPosition = 0;

    public void addAction(Action action){
        actions.add(action);
    }

    public Action getCurrentAction() {
        Action action = null;
        if (currentPosition < actions.size()) {
            action = actions.get(currentPosition);
        }
        return action;
    }

    public int numberOfActions() {
        return actions.size();
    }

    public void currentActionComplete() {
        currentPosition++;
    }

}