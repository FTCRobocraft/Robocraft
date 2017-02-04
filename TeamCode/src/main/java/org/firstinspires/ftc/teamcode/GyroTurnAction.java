package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.robot.Robot;

/**
 * Created by djfigs1 on 1/13/17.
 */
public class GyroTurnAction implements Action {

    boolean init = true;
    private HeadingNormalizer normalizer;

    private double degrees;
    private double speed;
    private RobotHardware.DIRECTION direction;

    public GyroTurnAction(RobotHardware.DIRECTION direction, double degrees, double speed) {
        this.direction = direction;
        this.degrees = degrees;
        this.speed = speed;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;
        double heading = hardware.gyroSensor.getHeading();
        if (init) {
            normalizer = new HeadingNormalizer(hardware.gyroSensor.getHeading());
            init = false;
        }
        double h = normalizer.normalizeHeading(heading);
        switch (direction) {
            case LEFT:
                if (Math.abs(h) >= degrees) {
                    finished = true;
                } else {
                    hardware.set_drive_power(-speed, speed);
                }
                break;
            case RIGHT:
                if (Math.abs(h) >= degrees) {
                    finished = true;
                } else {
                    hardware.set_drive_power(speed, -speed);
                }
                break;
        }
        return finished;
    }
}
