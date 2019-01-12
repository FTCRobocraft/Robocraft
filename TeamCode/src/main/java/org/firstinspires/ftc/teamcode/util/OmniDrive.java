package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class OmniDrive {

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public OmniDrive(DcMotor frontLeft, DcMotor frontRight,
                     DcMotor backLeft, DcMotor backRight) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;

    }

    public void moveForward(float power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }

    public void moveBackward(float power) {
        frontLeft.setPower(-power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(-power);
    }

    public void moveLeft(float power) {
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(-power);
    }

    public void moveRight(float power) {
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(power);
    }

    public void rotateRight(float power) {
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(power);
        backRight.setPower(-power);
    }

    public void rotateLeft(float power) {
            frontLeft.setPower(-power);
            frontRight.setPower(power);
            backLeft.setPower(-power);
            backRight.setPower(power);
    }

    public void moveForwardLeft(float power) {
        frontLeft.setPower(0);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(0);
    }

    public void moveForwardRight(float power) {
        frontLeft.setPower(power);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(power);
    }

    public void moveBackwardLeft(float power) {
        frontLeft.setPower(-power);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(-power);
    }

    public void moveBackwardRight(float power) {
        frontLeft.setPower(0);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(0);
    }

    public void circleMove(float x, float y) {
        frontLeft.setPower(x);
        backRight.setPower(x);
        backLeft.setPower(y);
        frontRight.setPower(y);
    }

    public void stopDrive() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void dpadMove(Gamepad gamepad, float power) {
        if (gamepad.dpad_up && gamepad.dpad_left) {
            moveForwardLeft(power);
        } else if (gamepad.dpad_up && gamepad.dpad_right) {
            moveForwardRight(power);
        } else if (gamepad.dpad_down && gamepad.dpad_left) {
            moveBackwardLeft(power);
        } else if (gamepad.dpad_down && gamepad.dpad_right) {
            moveBackwardRight(power);
        } else if (gamepad.dpad_up) {
            moveForward(power);
        } else if (gamepad.dpad_left) {
            moveLeft(power);
        } else if (gamepad.dpad_right) {
            moveRight(power);
        } else if (gamepad.dpad_down) {
            moveBackward(power);
        }

        if (gamepad.left_trigger > 0) {
            rotateLeft(gamepad.left_trigger);
        } else if (gamepad.right_trigger > 0) {
            rotateRight(gamepad.right_trigger);
        }
    }
}
