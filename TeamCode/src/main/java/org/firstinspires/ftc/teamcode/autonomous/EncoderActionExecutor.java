package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.action.MecanumMoveAction;
import org.firstinspires.ftc.teamcode.util.ActionExecutor;
import org.firstinspires.ftc.teamcode.util.ActionSequence;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by djfigs1 on 10/1/17.
 */

@Autonomous(name = "Encoder Auto Test")
public class EncoderActionExecutor extends ActionExecutor {
    class EncoderMecanumTestActionSequence extends ActionSequence {

        private final float distance = 24;
        private final float speed = 0.5f;

        public EncoderMecanumTestActionSequence() {
            addAction(new MecanumMoveAction(RobotHardware.RobotMoveDirection.FORWARD, distance, speed));
            addAction(new MecanumMoveAction(RobotHardware.RobotMoveDirection.LEFT, distance, speed));
            addAction(new MecanumMoveAction(RobotHardware.RobotMoveDirection.BACKWARD_RIGHT, distance, speed));
            addAction(new MecanumMoveAction(RobotHardware.RobotMoveDirection.RIGHT, distance, speed));
            addAction(new MecanumMoveAction(RobotHardware.RobotMoveDirection.BACKWARD_LEFT, distance, speed));
            addAction(new MecanumMoveAction(RobotHardware.RobotMoveDirection.FORWARD_LEFT, distance, speed));
            addAction(new MecanumMoveAction(RobotHardware.RobotMoveDirection.FORWARD_RIGHT, distance, speed));
            addAction(new MecanumMoveAction(RobotHardware.RobotMoveDirection.BACKWARD, distance, speed));
        }
    }

    @Override
    public void init() {
        super.init();
        this.actionSequence = new EncoderMecanumTestActionSequence();
    }
}
