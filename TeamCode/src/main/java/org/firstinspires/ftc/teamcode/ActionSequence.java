package org.firstinspires.ftc.teamcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djfigs1 on 11/18/16.
 */
public class ActionSequence {

    private List<Action> actions = new ArrayList<Action>();
    private int currentPosition = 0;

    public void addAction(Action action){
        actions.add(action);
    }

    public Action getCurrentAction(){
        Action action = null;
        if (currentPosition < actions.size()){
            action = actions.get(currentPosition);
        }
        return action;
    }

    public void currentActionComplete() {
        currentPosition++;
    }

}