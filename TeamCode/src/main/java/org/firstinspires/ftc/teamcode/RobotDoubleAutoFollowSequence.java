package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 12/9/16.
 */
public class RobotDoubleAutoFollowSequence extends ActionSequence {

    final double S_findLine = 0.12;
    final double S_followLine = 0.06;
    final double S_align = 0.09;
    final double S_turn = 0.14;

    final double T_back = 850;

    final double D_beacon = 16;
    final double A_1 = 85;
    final double A_2 = 35;

    public RobotDoubleAutoFollowSequence(RobotHardware.TEAM team) {
        switch (team) {
            case RED:
                // Waits for gyro calibration, in case it was started too early.
                addAction(new WaitForGyroCalibrationAction());

                //1st Beacon
                addAction(new FindLineAction(RobotHardware.VV_LINE_COLOR.BLUE, S_findLine, true));
                addAction(new FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR.BLUE, D_beacon, S_followLine));
                addAction(new DuoDistanceAlignAction(0, S_align));
                addAction(new PushBeaconAction(team, 0.08));
                addAction(new TimeDriveAction(T_back, -0.10, true));

                addAction(new ResetBeaconAction());

                addAction(new GyroTurnAction(RobotHardware.DIRECTION.RIGHT, A_1, S_turn));
                addAction(new TimeDriveAction(500, 0.2, false));
                addAction(new GyroTurnAction(RobotHardware.DIRECTION.LEFT, A_2, S_turn));

                //2nd Beacon
                addAction(new FindLineAction(RobotHardware.VV_LINE_COLOR.BLUE, S_findLine, true));
                addAction(new FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR.BLUE, D_beacon, S_followLine));
                addAction(new DuoDistanceAlignAction(0, S_align));
                addAction(new PushBeaconAction(team, 0.08));

                break;
            case BLUE:
                // Waits for gyro calibration, in case it was started too early.
                addAction(new WaitForGyroCalibrationAction());

                //1st Beacon
                addAction(new FindLineAction(RobotHardware.VV_LINE_COLOR.BLUE, S_findLine, true));
                addAction(new FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR.BLUE, D_beacon, S_followLine));
                addAction(new DuoDistanceAlignAction(0, S_align));
                addAction(new PushBeaconAction(team, 0.08));
                addAction(new TimeDriveAction(T_back, -0.10, true));

                addAction(new ResetBeaconAction());
                addAction(new GyroTurnAction(RobotHardware.DIRECTION.LEFT, A_1, S_turn));
                addAction(new TimeDriveAction(500, 0.2, false));
                addAction(new GyroTurnAction(RobotHardware.DIRECTION.RIGHT, A_2, S_turn));

                //2nd Beacon
                addAction(new FindLineAction(RobotHardware.VV_LINE_COLOR.BLUE, S_findLine, true));
                addAction(new FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR.BLUE, D_beacon, S_followLine));
                addAction(new DuoDistanceAlignAction(0, S_align));
                addAction(new PushBeaconAction(team, 0.08));
                break;
        }
    }
}