package org.firstinspires.ftc.teamcode.autonomous;

import org.firstinspires.ftc.teamcode.action.MecanumMoveAction;
import org.firstinspires.ftc.teamcode.util.ActionExecutor;
import org.firstinspires.ftc.teamcode.util.ActionSequence;
import org.firstinspires.ftc.teamcode.util.RelicRecoveryHardware;

/**
 * Created by djfigs1 on 10/1/17.
 */

public class EncoderActionExecutor extends ActionExecutor {
    class EncoderMecanumTestActionSequence extends ActionSequence {

        private final float distance = 10;
        private final float speed = 0.25f;
        private final double timeout = 5;

        public EncoderMecanumTestActionSequence() {
            addAction(new MecanumMoveAction(RelicRecoveryHardware.RobotMoveDirection.FORWARD, distance, speed, timeout));
            addAction(new MecanumMoveAction(RelicRecoveryHardware.RobotMoveDirection.LEFT, distance, speed, timeout));
            addAction(new MecanumMoveAction(RelicRecoveryHardware.RobotMoveDirection.BACKWARD_RIGHT, distance, speed, timeout));
            addAction(new MecanumMoveAction(RelicRecoveryHardware.RobotMoveDirection.RIGHT, distance, speed, timeout));
            addAction(new MecanumMoveAction(RelicRecoveryHardware.RobotMoveDirection.BACKWARD_LEFT, distance, speed, timeout));
            addAction(new MecanumMoveAction(RelicRecoveryHardware.RobotMoveDirection.FORWARD_LEFT, distance, speed, timeout));
            addAction(new MecanumMoveAction(RelicRecoveryHardware.RobotMoveDirection.FORWARD_RIGHT, distance, speed, timeout));
            addAction(new MecanumMoveAction(RelicRecoveryHardware.RobotMoveDirection.BACKWARD, distance, speed, timeout));
        }
    }

    @Override
    public void init() {
        super.init();
        this.actionSequence = new EncoderMecanumTestActionSequence();
    }
}
