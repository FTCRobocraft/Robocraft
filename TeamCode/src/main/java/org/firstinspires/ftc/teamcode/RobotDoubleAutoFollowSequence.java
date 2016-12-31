package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 12/9/16.
 */
public class RobotDoubleAutoFollowSequence extends ActionSequence {

    public RobotDoubleAutoFollowSequence(RobotHardware.TEAM team) {
        // Waits for gyro calibration, in case it was started too early.
        addAction(new WaitForGyroCalibrationAction());

        //1st Beacon
        addAction(new FindLineAction(RobotHardware.VV_LINE_COLOR.BLUE, 0.10, true));
        addAction(new FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR.BLUE, 9, 0.06));
        addAction(new PushBeaconAction(team, 0.08));
        addAction(new TimeDriveAction(1000, -0.10, true));
        //addAction(new FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR.BLUE, 8, 0.06));
        //addAction(new TimeDriveAction(1000, -0.08));

        addAction(new ResetBeaconAction());
        addAction(new TimeTurnAction(RobotHardware.DIRECTION.LEFT, 800, 0.20));
        addAction(new TimeDriveAction(500, 0.18, false));
        addAction(new TimeTurnAction(RobotHardware.DIRECTION.RIGHT, 650, 0.20));

        //2nd Beacon
        addAction(new FindLineAction(RobotHardware.VV_LINE_COLOR.BLUE, 0.10, true));
        addAction(new FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR.BLUE, 9, 0.06));
        addAction(new PushBeaconAction(team, 0.08));
        addAction(new TimeDriveAction(1000, -0.08, true));
        // You mother says hi
    }
}
