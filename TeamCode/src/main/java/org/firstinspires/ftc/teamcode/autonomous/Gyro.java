package org.firstinspires.ftc.teamcode.autonomous;

/**
 * Created by lvern on 9/17/2017.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.util.RobotHardware;
import com.*;
import org.*;

@Autonomous(name = "Gyro")
public class Gyro extends RobotHardware {

    final static int threshold = 1;

    int gyroX;
    int gyroY;
    int gyroZ;

    @Override public void init() {
        gyroSensor = hardwareMap.gyroSensor.get("gyroSensor");
        gyroSensor.calibrate();

    }


    @Override public void loop() {
        telemetry.addData("00", "Gyro: " + gyroSensor.rawX());
        telemetry.addData("01", "Gyro: " + gyroSensor.rawY());
        telemetry.addData("02", "Gyro: " + gyroSensor.rawZ());
        telemetry.update();

        gyroX = gyroSensor.rawX();
        gyroY = gyroSensor.rawY();
        gyroZ = gyroSensor.rawZ();


        /*
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
        */



    }


}
