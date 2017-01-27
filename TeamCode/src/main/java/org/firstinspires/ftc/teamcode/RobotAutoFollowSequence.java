package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 12/9/16.
 */
public class RobotAutoFollowSequence extends ActionSequence {

    public RobotAutoFollowSequence(RobotHardware.TEAM team) {
        addAction(new FindLineAction(RobotHardware.VV_LINE_COLOR.BLUE, 0.10, true));
        addAction(new FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR.BLUE, 10, 0.06));
        //addAction(new TimeDriveAction(1000, -0.08));
        //addAction(new FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR.BLUE, 8, 0.07));
        addAction(new DuoDistanceAlignAction(0, 0.07));

        addAction(new PushBeaconAction(team, 0.08));
        addAction(new TimeDriveAction(500, -0.08, true));
    }
}
// you are having a bad time
// you are lost
// confoozeled
