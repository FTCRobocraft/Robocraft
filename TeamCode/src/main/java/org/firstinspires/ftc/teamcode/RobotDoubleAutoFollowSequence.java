package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 12/9/16.
 */
public class RobotDoubleAutoFollowSequence extends ActionSequence {

    public RobotDoubleAutoFollowSequence(RobotHardware.TEAM team) {
        // Waits for gyro calibration, in case it was started too early.
        addAction(new WaitForGyroCalibrationAction());

        //1st Beacon
        addAction(new FindLineAction(RobotHardware.VV_LINE_COLOR.BLUE, 0.15, true));
        addAction(new FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR.BLUE, 18, 0.07));
        addAction(new DuoDistanceAlignAction(0, 0.0925));
        addAction(new PushBeaconAction(team, 0.08));
        addAction(new TimeDriveAction(1000, -0.10, true));

        addAction(new ResetBeaconAction());
        addAction(new GyroTurnAction(RobotHardware.DIRECTION.LEFT, 100, 0.14));
        addAction(new TimeDriveAction(500, 0.2, false));
        addAction(new GyroTurnAction(RobotHardware.DIRECTION.RIGHT, 40, 0.14));

        //2nd Beacon
        addAction(new FindLineAction(RobotHardware.VV_LINE_COLOR.BLUE, 0.15, true));
        addAction(new FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR.BLUE, 18, 0.07));
        addAction(new DuoDistanceAlignAction(0, 0.0925));
        addAction(new PushBeaconAction(team, 0.08));

    }
}
