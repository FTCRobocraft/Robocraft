package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 12/9/16.
 */
public class RobotAutoFollowSequence extends ActionSequence {

    public RobotAutoFollowSequence(RobotHardware.TEAM team) {
        //1st Beacon
        addAction(new FindLineAction(RobotHardware.VV_LINE_COLOR.WHITE, 0.08));
        addAction(new FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR.WHITE, 3, 0.06));
        addAction(new PushBeaconAction(team, 0.08));
        addAction(new TimeDriveAction(5500, -0.08));
        addAction(new TurnAction2(TurnAction2.Direction.RIGHT, 80));
        
        //2nd Beacon
        addAction(new FindLineAction(RobotHardware.VV_LINE_COLOR.WHITE, 0.08));
        addAction(new TurnAction2(TurnAction2.Direction.LEFT, 80));
        addAction(new FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR.WHITE, 3, 0.06));
        addAction(new PushBeaconAction(team, 0.08));
        addAction(new TimeDriveAction(5500, -0.08));
    }
}
