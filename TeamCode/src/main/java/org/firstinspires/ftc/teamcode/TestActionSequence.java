package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 11/18/16.
 */
public class TestActionSequence extends ActionSequence {

    public TestActionSequence() {
        //addAction(new MoveAction(MoveAction.Direction.FORWARD, 12, 0.25));
        //addAction(new TurnAction(TurnAction.Direction.RIGHT, 90));
        addAction(new AccumulatedHeadingTurn(RobotHardware.DIRECTION.RIGHT, 85));
        //addAction(new TurnAction2(TurnAction2.Direction.LEFT, 85));
        //addAction(new MoveAction(MoveAction.Direction.FORWARD, 6, 0.25));
        //addAction(new MoveAction(MoveAction.Direction.BACKWARD, 6, 0.25));
        //addAction(new MoveAction(MoveAction.Direction.BACKWARD, 12, 0.25));

        //addAction(new FindLineAction(RobotHardware.VV_LINE_COLOR.WHITE, 0.07));
        //addAction(new FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR.WHITE, 7, 0.1));
    }


}
