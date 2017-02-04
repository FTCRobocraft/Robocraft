package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 11/18/16.
 */
public class TestActionSequence extends ActionSequence {

    private final double speed = 0.12;

    public TestActionSequence() {
        addAction(new WaitForGyroCalibrationAction());
        addAction(new TimeDriveAction(2000, speed, true));
        addAction(new GyroTurnAction(RobotHardware.DIRECTION.RIGHT, 45, speed));
        addAction(new GyroTurnAction(RobotHardware.DIRECTION.LEFT, 90, speed));
    }
}
