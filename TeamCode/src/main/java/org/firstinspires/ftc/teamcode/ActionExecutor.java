package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by djfigs1 on 11/18/16.
 */

@Autonomous(name = "Action Tester")
public class ActionExecutor extends RobotHardware {

    private ActionSequence actionSequence;
    private Action action = null;

    @Override
    public void init() {
        super.init();
        actionSequence = new TestActionSequence();
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

