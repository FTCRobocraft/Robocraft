package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Not really created by djfigs1 on 9/23/17. Created when Luke wanted his gyro code tested. lol XD
 */

@TeleOp(name="RevMotorTest")
public class MecanumDrive extends RobotHardware {

    final float dpad_speed =  1f;

    final double leftServoOpen = 0;
    final double rightServoOpen = 0;
    final double leftServoClosed = 0;
    final double rightServoClosed = 0;


    @Override
    public void init() {
        super.init();
    }

    @Override
    public void start() {
        super.init();
    }

    @Override
    public void loop() {
        stopDrive();

        if (gamepad1.dpad_up && gamepad1.dpad_left) {
            moveForwardLeft(dpad_speed);
        } else if (gamepad1.dpad_up && gamepad1.dpad_right) {
            moveForwardRight(dpad_speed);
        } else if (gamepad1.dpad_down && gamepad1.dpad_left) {
            moveBackwardLeft(dpad_speed);
        } else if (gamepad1.dpad_down && gamepad1.dpad_right) {
            moveBackwardRight(dpad_speed);
        } else if (gamepad1.dpad_up) {
            moveForward(dpad_speed);
        } else if (gamepad1.dpad_down) {
            moveBackward(dpad_speed);
        } else if (gamepad1.dpad_left) {
            moveLeft(dpad_speed);
        } else if (gamepad1.dpad_right) {
            moveRight(dpad_speed);
        }

        if (gamepad2.a) {
            lift_leftServo.setPosition(leftServoClosed);
            lift_rightServo.setPosition(rightServoClosed);
        } else if (gamepad2.b) {
            lift_leftServo.setPosition(leftServoOpen);
            lift_rightServo.setPosition(rightServoOpen);
        }

        lift_verticalServo.setPower(gamepad2.left_stick_y);

        if (Math.abs(gamepad1.right_stick_x) > 0) {
            rotateRight(gamepad1.right_stick_x / 2);
        }

        /*fork_leftServo.setPower(0);
        fork_rightServo.setPower(0);

        if (gamepad2.a) {
            fork_leftServo.setPower(1);
            fork_rightServo.setPower(1);
        }

        if (gamepad2.x) {
            fork_leftServo.setPower(-1);
            fork_rightServo.setPower(-1);
        }
        */

    }

}
