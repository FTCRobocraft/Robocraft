package org.firstinspires.ftc.teamcode;

import java.text.Normalizer;

/**
 * Created by djfigs1 on 12/30/16.
 */
public class NormalizedTurnAction implements Action {

    double degrees;
    RobotHardware.DIRECTION direction;
    boolean init = true;
    private double initialHeading = 0;
    private HeadingNormalizer normalizer;
    double speed;

    public NormalizedTurnAction(double degrees, RobotHardware.DIRECTION direction, double speed) {
        this.degrees = degrees;
        this.direction = direction;
        this.speed = speed;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;
        double heading = hardware.gyroSensor.getHeading();
        if (init) { initialHeading = heading; normalizer = new HeadingNormalizer(initialHeading); init = false; }
        double nHeading = normalizer.normalizeHeading(heading);

        switch (direction) {
            case LEFT:
                if (nHeading <= degrees) {
                    finished = true;
                }else{
                    hardware.set_drive_power(-speed, speed);
                }
                break;
            case RIGHT:
                if (nHeading >= degrees) {
                    finished = true;
                }else{
                    hardware.set_drive_power(speed, -speed);
                }
                break;
        }

        hardware.telemetry.addData("nHeading", nHeading);

        return finished;
    }
}
