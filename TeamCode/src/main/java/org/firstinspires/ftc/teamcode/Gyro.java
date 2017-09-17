package org.firstinspires.ftc.teamcode;

/**
 * Created by lvern on 9/17/2017.
 */

import com.qualcomm.robotcore.hardware.GyroSensor;

public class Gyro extends RobotHardware {

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
        telemetry.addData ("00", "Gyro:" +  gyroSensor);
    }

}
