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

    int actionNumber = 1;

    @Override
    public void loop() {
        action = actionSequence.getCurrentAction();
        if (action != null) {
            if (action.doAction(this)) {
                actionSequence.currentActionComplete();
                action = actionSequence.getCurrentAction();
                actionNumber++;
            } else {
                telemetry.addData("Progress", "%d/%d, %d%%", actionNumber, actionSequence.numberOfActions(),
                        (int)((double) actionNumber / (double) actionSequence.numberOfActions() * 100.0));
                telemetry.addData("Current Action", action.getClass().getSimpleName());
            }
        }
        else {
            requestOpModeStop();
        }


    }
}

