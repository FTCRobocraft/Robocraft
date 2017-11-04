package org.firstinspires.ftc.teamcode.autonomous;

/**
 * Created by lvern on 9/17/2017.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

@Autonomous(name = "Gyro")
public class Gyro extends RobotHardware {

    final static int threshold = 2;

    double gyroX;
    double gyroY;
    double gyroZ;
    float power = 0.2f;

    @Override public void init() {
        super.init();
    }


    @Override public void loop() {
        gyroY = (int) Math.floor(revIMU.getAngularOrientation().secondAngle);
        gyroZ = (int) Math.floor(revIMU.getAngularOrientation().thirdAngle);

        telemetry.addData("Y", "Gyro: " + gyroY);
        telemetry.addData("Z", "Gyro: " + gyroZ);

        stopDrive();

        if (gyroY < -threshold) {moveForward(power);}
        if (gyroY > threshold) {moveBackward(power);}
        if (gyroZ > threshold) {moveLeft(power);}
        if (gyroZ < -threshold) {moveRight(power);}


    }


}
