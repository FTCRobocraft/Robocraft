package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.Action;
import org.firstinspires.ftc.teamcode.RobotHardware;

/**
 * Created by djfigs1 on 11/18/16.
 */
public class HeadingTurnAction implements Action {

    public enum Direction {
        LEFT,
        RIGHT
    }

    private Direction direction;
    private float degrees;
    private double targetDegrees = -1;
    private HeadingNormalizer normalizer;
    boolean init = false;

    private static final double targetError = 3;
    private static final double speed = 0.10;

    public HeadingTurnAction(Direction direction, float degrees) {
        this.direction = direction;
        this.degrees = degrees;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;
        if (init) {
            normalizer = new HeadingNormalizer(hardware.gyroSensor.getHeading());
            init = false;
        }
        if (!hardware.gyroSensor.isCalibrating()) {
            double currentDegrees = hardware.gyroSensor.getHeading();
            if (targetDegrees == -1) {
                targetDegrees = currentDegrees + (degrees * (direction.equals(Direction.LEFT) ? -1 : 1));
            }

            currentDegrees = targetDegrees >= 360 ? currentDegrees + 360 : targetDegrees <= 0 ? currentDegrees - 360 : currentDegrees;
            hardware.telemetry.addData("Raw Gyro", hardware.gyroSensor.getHeading());
            hardware.telemetry.addData("Current Degrees", currentDegrees);
            hardware.telemetry.addData("Target Degrees", targetDegrees);
            if (!(Math.abs(currentDegrees - targetDegrees) <= targetError)) {
                switch (direction) {
                    case LEFT:
                        hardware.set_drive_power(-speed, speed);
                        break;
                    case RIGHT:
                        hardware.set_drive_power(speed, -speed);
                        break;
                }
            } else {
                finished = true;
                hardware.set_drive_power(0, 0);
            }
        }
        return finished;
    }

}
