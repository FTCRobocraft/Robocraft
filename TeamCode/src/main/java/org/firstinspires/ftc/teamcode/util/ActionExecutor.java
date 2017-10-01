package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.action.Action;

/**
 * Created by djfigs1 on 11/18/16.
 */

public class ActionExecutor extends RobotHardware {

    public ActionSequence actionSequence;
    private Action action = null;

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        action = actionSequence.getCurrentAction();
        if (action != null) {
            if (action.doAction(this)) {
                actionSequence.currentActionComplete();
                action = actionSequence.getCurrentAction();
            }
        }
        else {
            requestOpModeStop();
        }
    }
}

