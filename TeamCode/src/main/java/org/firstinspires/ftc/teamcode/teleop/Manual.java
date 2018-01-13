package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by lvern on 1/6/2018.
 */
@TeleOp(name="Manual")
public class Manual extends RobotHardware {

    float dpadPower = 1f;
    float bumperPower = 1f;
    float dpadGripperPower = 1f;
    double clawOpen = 0;
    double clawClosed = 1;

    double clawElbowUp = 0;
    double clawElbowDown = 1;
    boolean clawUp = false;
    boolean leftStickPressed = false;

    @Override
    public void loop() {
        //region gamepad1

        if (gamepad1.dpad_up) {
            moveForward(dpadPower);
        }
        else if (gamepad1.dpad_down) {
            moveBackward(dpadPower);
        }
        else if (gamepad1.dpad_left) {
            moveLeft(dpadPower);
        }
        else if (gamepad1.dpad_right) {
            moveRight(dpadPower);
        }

        if (gamepad1.right_stick_x > 0 && gamepad1.right_stick_y > 0) { // Forward Right
            moveForwardRight((float) ((gamepad1.right_stick_x + gamepad1.right_stick_y) / 2.0));
        } else if (gamepad1.right_stick_x > 0 && gamepad1.right_stick_y < 0) { // Backward Right
            moveBackwardRight((float) ((gamepad1.right_stick_x + gamepad1.right_stick_y) / 2.0));
        } else if (gamepad1.right_stick_x < 0 && gamepad1.right_stick_y > 0) { // Forward Left
            moveForwardLeft((float) ((gamepad1.right_stick_x + gamepad1.right_stick_y) / 2.0));
        } else if (gamepad1.right_stick_x < 0 && gamepad1.right_stick_y < 0) { // Backward Left
            moveBackwardLeft((float) ((gamepad1.right_stick_x + gamepad1.right_stick_y) / 2.0));
        }


        if (gamepad1.right_trigger > 0) {
            rotateRight(gamepad1.right_trigger);
        }
        else if (gamepad1.left_trigger > 0) {
            rotateLeft(gamepad1.left_trigger);
        }

        if (gamepad1.right_bumper) {
            rotateRight(bumperPower);
        }
        else if (gamepad1.left_bumper) {
            rotateLeft(bumperPower);
        }
        //endregion

        //region gamepad2
        if (gamepad2.dpad_up) {
            lift_verticalServo.setPower(dpadGripperPower);
        } else if (gamepad2.dpad_down) {
            lift_verticalServo.setPower(-dpadGripperPower);
        } else {
            lift_verticalServo.setPower(0);
        }

        if (gamepad2.left_stick_button) {
            if (!leftStickPressed) {
                if (clawUp) {
                    clawUp = false;
                    clawElbowServo.setPosition(clawElbowDown);
                } else {
                    clawUp = true;
                    clawElbowServo.setPosition(clawElbowUp);
                }
                leftStickPressed = true;
            }
        } else {
            leftStickPressed = false;
        }

        if (gamepad2.left_trigger > 0) {

        }

        if (gamepad2.right_trigger > 0) {
            clawElbowServo.setPosition(clawClosed);
        } else {
            clawElbowServo.setPosition(clawOpen);
        }

        clawArmServo.setPower(gamepad2.left_stick_y / 2.0);

        //endregion

    }

}
