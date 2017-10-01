package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 11/18/16.
 */
public class TestActionSequence extends ActionSequence {

    private final double speed = 0.12;

    public TestActionSequence() {
        addAction(new MecanumMoveAction(RobotHardware.RobotMoveDirection.RIGHT, 60, 0.5f));
    }
}
