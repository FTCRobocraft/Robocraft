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

    double gyroX;
    double gyroY;
    double gyroZ;

    @Override public void init() {
        super.init();
    }


    @Override public void loop() {


        gyroX = (int) Math.floor(revIMU.getAngularOrientation().firstAngle);
        gyroY = (int) Math.floor(revIMU.getAngularOrientation().secondAngle);
        gyroZ = (int) Math.floor(revIMU.getAngularOrientation().thirdAngle);

        telemetry.addData("X", "Gyro: " + gyroX);
        telemetry.addData("Y", "Gyro: " + gyroY);
        telemetry.addData("Z", "Gyro: " + gyroZ);




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
