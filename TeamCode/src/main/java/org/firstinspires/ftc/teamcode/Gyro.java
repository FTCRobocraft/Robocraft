package org.firstinspires.ftc.teamcode;

/**
 * Created by lvern on 9/17/2017.
 */

import android.os.DropBoxManager;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

public class Gyro extends RobotHardware {

    final static int threshold = 1;

    int gyroX;
    int gyroY;
    int gyroZ;

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

        gyroX = gyroSensor.rawX();
        gyroY = gyroSensor.rawY();
        gyroZ = gyroSensor.rawZ();

        if (Math.abs(gyroX) > threshold) {
            moveLeft(30);

        } else {
            //?
        }

        if (Math.abs(gyroY) > threshold) {
            moveRight(30);
        } else {
            //?
        }

        if (Math.abs(gyroZ) > threshold) {
            moveForward(30);
        } else {
            //?
        }

    }


}
