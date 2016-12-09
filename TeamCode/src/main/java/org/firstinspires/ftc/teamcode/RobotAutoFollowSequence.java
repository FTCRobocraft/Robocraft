package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 12/9/16.
 */
public class RobotAutoFollowSequence extends ActionSequence {

    public RobotAutoFollowSequence() {
        addAction(new FindLineAction(RobotHardware.VV_LINE_COLOR.WHITE, 0.08));
        addAction(new FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR.WHITE, 3, 0.06));


    }
}
