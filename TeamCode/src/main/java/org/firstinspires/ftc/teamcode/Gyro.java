package org.firstinspires.ftc.teamcode;

/**
 * Created by lvern on 9/17/2017.
 */

import android.os.DropBoxManager;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

public class Gyro extends RobotHardware {

    final static int threshold = 1;

    @Override public void init() {
        try {
            gyroSensor = hardwareMap.gyroSensor.get("gyroSensor");
            gyroSensor.calibrate();
        } catch (Exception e) {
            telemetry.addData("error", "gyro sensor");
            gyroSensor = null;
        }
    }

    @Override public void loop() {
        telemetry.addData("00", "Gyro: " + gyroSensor.rawX());
        telemetry.addData("01", "Gyro: " + gyroSensor.rawY());
        telemetry.addData("02", "Gyro: " + gyroSensor.rawZ());

        int gyroX = gyroSensor.rawX();
        int gyroY = gyroSensor.rawY();

        if (Math.abs(gyroX) > threshold) {
            left.setPower(30);
            right.setPower(30);
        } else {
            left.setPower(-30);
            right.setPower(-30);
        }

        if (Math.abs(gyroY) > threshold) {
            left.setPower(-30);
            right.setPower(30);
        } else {
            left.setPower(30);
            right.setPower(-30);
        }

    }

    public DcMotor left;
    public DcMotor right;


}
