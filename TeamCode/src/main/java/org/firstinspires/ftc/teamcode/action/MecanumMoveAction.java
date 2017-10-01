package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.util.EncoderDrive;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by djfigs1 on 9/30/17.
 */

public class MecanumMoveAction implements Action {

    RobotHardware.RobotMoveDirection direction;
    double distance;
    float speed;

    private EncoderDrive driver;

    public MecanumMoveAction(RobotHardware.RobotMoveDirection direction, double distance, float speed) {
        this.direction = direction;
        this.distance = distance;
        this.speed = speed;
    }

    public boolean doAction(RobotHardware hardware) {
        if (driver == null) {
            driver = new EncoderDrive(hardware);
            driver.setInchesToDrive(direction, distance, speed);
        }
        driver.run();
        return !driver.isBusy;
    }
}
