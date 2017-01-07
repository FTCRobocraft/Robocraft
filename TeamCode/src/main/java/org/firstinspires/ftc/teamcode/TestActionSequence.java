package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 11/18/16.
 */
public class TestActionSequence extends ActionSequence {

    public TestActionSequence() {
        /*
        addAction(new WaitForGyroCalibrationAction());
        addAction(new NormalizedTurnAction(90, RobotHardware.DIRECTION.RIGHT, 0.1));
        */

        addAction(new DuoDistanceAlignAction(0.1, 0.1));
    }
}
