package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by djfigs1 on 11/18/16.
 */

@Autonomous(name = "Auto-Beacons BLUE")
public class BeaconAutoExecutor_Blue extends RobotHardware {

    private ActionSequence actionSequence;
    private Action action = null;

    @Override
    public void init() {
        super.init();
        actionSequence = new RobotAutoFollowSequence(TEAM.BLUE);
    }

    @Override
    public void init_loop(){
        beaconPosition(1);
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

