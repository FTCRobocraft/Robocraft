package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by djfigs1 on 9/23/17.
 */

@TeleOp(name="RevMotorTest")
public class MecanumDrive extends RobotHardware {

    final float dpad_speed = 0.5f;

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

        if (Math.abs(gamepad1.right_stick_x) > 0) {
            rotateRight(gamepad1.right_stick_x);
        }

    }
}
