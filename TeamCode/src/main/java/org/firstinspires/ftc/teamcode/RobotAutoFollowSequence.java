package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 12/9/16.
 */
public class RobotAutoFollowSequence extends ActionSequence {

    public RobotAutoFollowSequence(RobotHardware.TEAM team) {
        //1st Beacon
        addAction(new FindLineAction(RobotHardware.VV_LINE_COLOR.WHITE, 0.10, true));
        addAction(new FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR.WHITE, 15, 0.06));
        addAction(new TimeDriveAction(500, -0.10));
        addAction(new FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR.WHITE, 8, 0.06));
        addAction(new PushBeaconAction(team, 0.08));
        addAction(new TimeDriveAction(1000, -0.08));
        addAction(new AccumulatedHeadingTurn(RobotHardware.DIRECTION.LEFT, 80));

        //2nd Beacon
        addAction(new FindLineAction(RobotHardware.VV_LINE_COLOR.WHITE, 0.10, true));
        addAction(new AccumulatedHeadingTurn(RobotHardware.DIRECTION.RIGHT, 80));
        addAction(new FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR.WHITE, 8, 0.06));
        addAction(new PushBeaconAction(team, 0.08));
        addAction(new TimeDriveAction(1000, -0.08));
    }
}
