package org.firstinspires.ftc.teamcode.teleop;

import org.firstinspires.ftc.teamcode.hardware.RelicRecoveryHardware;

/**
 * Created by lvern on 1/6/2018.
 */

//@TeleOp(name="RelicRecoveryManual")
public class RelicRecoveryManual extends RelicRecoveryHardware {

    float dpadPower = 1f;
    float bumperPower = 1f;
    float dpadGripperPower = 1f;

    float slowModeSpeed = 1f;

    double clawElbowUp = 0;
    double clawElbowDown = 1;
    boolean clawUp = false;
    boolean leftStickPressed = false;
    boolean leftTriggerPressed = false;
    boolean liftClawState = false;
    boolean slowToggle = false;
    boolean slowToggleState = false;
    boolean reverseToggle = false;
    boolean reverseToggleState = false;

    @Override
    public void loop() {
        //region gamepad1

        stopDrive();
        armServo.setPosition(1);

        if (gamepad1.a) {
            if (!slowToggle) {
                if (slowToggleState) {
                    slowToggleState = false;
                    slowModeSpeed = 3f;
                } else {
                    slowToggleState = true;
                    slowModeSpeed = 1f;
                }
                slowToggle = true;
            }
        } else {
            slowToggle = false;
        }

        if (gamepad1.b) {
            if (!reverseToggle) {
                if (reverseToggleState) {
                    reverseToggleState = false;
                } else {
                    reverseToggleState = true;
                }
                reverseToggle = true;
            }
        } else {
            reverseToggle = false;
        }

        if (gamepad1.dpad_up) {
            if (reverseToggleState) {
                moveBackward(dpadPower / slowModeSpeed);
            } else {
                moveForward(dpadPower / slowModeSpeed);
            }
        } else if (gamepad1.dpad_down) {
            if (reverseToggleState) {
                moveForward(dpadPower / slowModeSpeed);
            } else {
                moveBackward(dpadPower / slowModeSpeed);
            }
        } else if (gamepad1.dpad_left) {
            if (reverseToggleState) {
                moveRight(dpadPower / slowModeSpeed);
            } else {
                moveLeft(dpadPower / slowModeSpeed);
            }
        } else if (gamepad1.dpad_right) {
            if (reverseToggleState) {
                moveLeft(dpadPower / slowModeSpeed);
            } else {
                moveRight(dpadPower / slowModeSpeed);
            }
        }

        if (gamepad1.right_stick_x > 0 && gamepad1.right_stick_y > 0) { // Forward Right
            moveForwardRight(dpadPower / slowModeSpeed);
        } else if (gamepad1.right_stick_x > 0 && gamepad1.right_stick_y < 0) { // Backward Right
            moveBackwardRight(dpadPower / slowModeSpeed);
        } else if (gamepad1.right_stick_x < 0 && gamepad1.right_stick_y > 0) { // Forward Left
            moveForwardLeft(dpadPower / slowModeSpeed);
        } else if (gamepad1.right_stick_x < 0 && gamepad1.right_stick_y < 0) { // Backward Left
            moveBackwardLeft(dpadPower / slowModeSpeed);
        }

        if (gamepad1.right_trigger > 0) {
            rotateRight(gamepad1.right_trigger / slowModeSpeed);
        }

        else if (gamepad1.left_trigger > 0) {
            rotateLeft(gamepad1.left_trigger / slowModeSpeed);
        }

        if (gamepad1.right_bumper) {
            rotateRight(bumperPower / slowModeSpeed);
        }
        else if (gamepad1.left_bumper) {
            rotateLeft(bumperPower / slowModeSpeed);
        }
        //endregion

        //region gamepad2
        if (gamepad2.dpad_up) {
            lift_verticalServo.setPower(-dpadGripperPower);
        } else if (gamepad2.dpad_down) {
            lift_verticalServo.setPower(dpadGripperPower);
        } else {
            lift_verticalServo.setPower(0);
        }

        if (gamepad2.a) { // Rest Position
            relicShoulderServo.setPosition(m_relicShoulderDown);
            relicArmServo.setPosition(m_relicArmDown);
        }

        if (gamepad2.x) { //  Position
            relicClawServo.setPosition(m_relicClawClosed);
        }

        if (gamepad2.y) { //
             relicShoulderServo.setPosition(m_relicShoulderUp);
             relicArmServo.setPosition(m_relicArmUp);
        }

        if (gamepad2.b) { //
            relicClawServo.setPosition(m_relicClawOpen);
        }

        if (gamepad2.start) {
            relicShoulderServo.setPosition(m_relicShoulderRetracted);
            relicArmServo.setPosition(m_relicArmUp);
        }

        if (gamepad2.left_trigger > 0) {
            if (!leftTriggerPressed) {
                if (liftClawState) {
                    liftClawState = false;
                    lift_gripServo.setPosition(m_liftGripClosed);
                } else {
                    liftClawState = true;
                    lift_gripServo.setPosition(m_liftGripOpen);
                }
                leftTriggerPressed = true;
            }
        } else {
            leftTriggerPressed = false;
        }


        if (gamepad2.right_trigger > 0) {
            //clawElbowServo.setPosition(clawClosed);
        } else {
            //clawElbowServo.setPosition(clawOpen);
        }

        //endregion

    }

}