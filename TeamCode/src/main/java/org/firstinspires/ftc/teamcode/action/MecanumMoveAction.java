package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.util.EncoderDrive;
import org.firstinspires.ftc.teamcode.util.RelicRecoveryHardware;
import org.firstinspires.ftc.teamcode.util.RoverRuckusHardware;

/**
 * Created by djfigs1 on 9/30/17.
 */

public class MecanumMoveAction implements Action {

    RelicRecoveryHardware.RobotMoveDirection direction;
    double distance;
    float speed;
    double timeout;

    private EncoderDrive driver;

    public MecanumMoveAction(RelicRecoveryHardware.RobotMoveDirection direction, double distance, float speed, double timeout) {
        this.direction = direction;
        this.distance = distance;
        this.speed = speed;
        this.timeout = timeout;
    }

    public boolean doAction(RoverRuckusHardware hardware) {
        if (driver == null) {
            driver = new EncoderDrive(hardware);
            driver.setInchesToDrive(direction, distance, speed, timeout);
        }
        driver.run();
        return !driver.isBusy;
    }
}
