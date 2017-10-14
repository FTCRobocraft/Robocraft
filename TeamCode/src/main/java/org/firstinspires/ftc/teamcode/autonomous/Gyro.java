package org.firstinspires.ftc.teamcode.autonomous;

/**
 * Created by lvern on 9/17/2017.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

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

        if (-gyroY > threshold) {moveForward(0.3f);}
        if (gyroY > threshold) {moveBackward(0.3f);}
        if (gyroZ > threshold) {moveLeft(0.3f);}
        if (-gyroZ > threshold) {moveRight(0.3f);}


    }


}
