package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 11/18/16.
 */
public class TestActionSequence extends ActionSequence {

    public TestActionSequence() {
        addAction(new WaitForGyroCalibrationAction());
        addAction(new TimeDriveAction(3000, -0.1, true));
    }
}
