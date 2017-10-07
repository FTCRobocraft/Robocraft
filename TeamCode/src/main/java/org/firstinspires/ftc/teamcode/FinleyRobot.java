package org.firstinspires.ftc.teamcode;

/**
 * Created by lvern on 9/30/2017.
 */

import com.qualcomm.robotcore.hardware.DcMotor;


public class FinleyRobot extends RobotTelemetry {

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    @Override
    public void init() {}

    @Override
    public void loop() {

        float lX = gamepad1.left_stick_x;
        float lY = gamepad1.left_stick_y;
        float rX = gamepad1.right_stick_x;
        float rY = gamepad1.right_stick_y;

        frontLeft.setPower(lY);
        backLeft.setPower(lY);

        frontRight.setPower(rY);
        backRight.setPower(rY);




    }

}
