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
    double timeout;

    private EncoderDrive driver;

    public MecanumMoveAction(RobotHardware.RobotMoveDirection direction, double distance, float speed, double timeout) {
        this.direction = direction;
        this.distance = distance;
        this.speed = speed;
        this.timeout = timeout;
    }

    public boolean doAction(RobotHardware hardware) {
        if (driver == null) {
            driver = new EncoderDrive(hardware);
            driver.setInchesToDrive(direction, distance, speed, timeout);
        }
        driver.run();
        return !driver.isBusy;
    }
}
