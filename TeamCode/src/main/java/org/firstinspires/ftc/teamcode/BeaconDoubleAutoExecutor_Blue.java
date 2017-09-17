package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by djfigs1 on 11/18/16.
 */

@Autonomous(name = "BLUE Auto")
public class BeaconDoubleAutoExecutor_Blue extends RobotHardware {

    private ActionSequence actionSequence;
    private Action action = null;
    double time = 0;

    @Override
    public void init() {
        super.init();
        actionSequence = new RobotDoubleAutoFollowSequence(TEAM.BLUE);
    }

    @Override
    public void init_loop(){
        beaconPosition(1);
    }

    @Override
    public void loop() {
        action = actionSequence.getCurrentAction();
        if (action != null) {
            telemetry.addData("Heading", gyroSensor.getHeading());
            telemetry.addData("deltaT", System.currentTimeMillis() - time);
            time = System.currentTimeMillis();
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