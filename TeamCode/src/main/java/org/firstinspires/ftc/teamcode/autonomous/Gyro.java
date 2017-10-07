package org.firstinspires.ftc.teamcode.autonomous;

/**
 * Created by lvern on 9/17/2017.
 */

import org.firstinspires.ftc.teamcode.util.RobotHardware;

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
            moveLeft(0.3f);

        } else {
            moveRight(0.3f);
        }

        if (Math.abs(gyroY) > threshold) {
            moveRight(0.3f);
        } else {
            moveLeft(0.3f);
        }

        if (Math.abs(gyroZ) > threshold) {
            rotateRight(0.3f);
        } else {
            rotateLeft(0.3f);
        }

    }


}
