package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by djfigs1 on 9/23/17.
 */

@TeleOp(name="RevMotorTest")
public class OmniDrive extends RobotTelemetry {

    float xDeadband = 0.1f;
    float yDeadband = 0.1f;

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
        float aX = gamepad1.left_stick_x;
        float aY = gamepad1.left_stick_y;
        float bX = gamepad2.left_stick_x;
        float bY = gamepad2.left_stick_y;

        stopDrive();

        if (aY > 0 && Math.abs(aX) < xDeadband) {
            frontLeft.setPower(aY);
            frontRight.setPower(aY);
            backLeft.setPower(aY);
            backRight.setPower(aY);
        } else if (aY < 0 && Math.abs(aX) < xDeadband) {
            frontLeft.setPower(aY);
            frontRight.setPower(aY);
            backLeft.setPower(aY);
            backRight.setPower(aY);
        } else if (aX > 0 && Math.abs(aY) < yDeadband) {
            frontLeft.setPower(aY);
            frontRight.setPower(-aY);
            backLeft.setPower(aY);
            backRight.setPower(-aY);
        } else if (aX < 0 && Math.abs(aY) < yDeadband) {
            frontLeft.setPower(-aY);
            frontRight.setPower(aY);
            backLeft.setPower(-aY);
            backRight.setPower(aY);
        } else if (aX > 0 && aY > 0) {
            frontLeft.setPower(aY);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(aY);
        } else if (aX < 0 && aY > 0) {
            frontLeft.setPower(0);
            frontRight.setPower(aY);
            backLeft.setPower(aY);
            backRight.setPower(0);
        } else if (aX > 0 && aY < 0) {
            frontLeft.setPower(-aY);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(-aY);
        } else if (aX < 0 && aY < 0) {
            frontLeft.setPower(0);
            frontRight.setPower(-aY);
            backLeft.setPower(-aY);
            backRight.setPower(0);
        } else if (bX > 0) {
            frontLeft.setPower(bX);
            backLeft.setPower(bX);
            backRight.setPower(-bX);
            frontRight.setPower(-bX);
        } else if (bX < 0) {
            frontLeft.setPower(-bX);
            backLeft.setPower(-bX);
            backRight.setPower(bX);
            frontRight.setPower(bX);
        }

        super.init();




    }
}
