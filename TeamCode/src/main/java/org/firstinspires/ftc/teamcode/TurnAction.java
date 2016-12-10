package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.Action;
import org.firstinspires.ftc.teamcode.RobotHardware;

/**
 * Created by djfigs1 on 11/18/16.
 */
public class TurnAction implements Action {

    public enum Direction {
        LEFT,
        RIGHT
    }

    private Direction direction;
    private float degrees;
    private double targetDegrees = -1;

    private static final double targetError = 5;
    private static final double speed = 0.05;

    public TurnAction(Direction direction, float degrees) {
        this.direction = direction;
        this.degrees = degrees;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;
        if (targetDegrees == -1) {
            double directionDegrees = hardware.gyroSensor.getHeading();
            targetDegrees = directionDegrees + (degrees * (direction.equals(Direction.LEFT) ? -1 : 1));
        }

        double currentDegrees = hardware.compassSensor.getDirection();
        currentDegrees = targetDegrees >= 360 ? currentDegrees + 360 : targetDegrees < 0 ? currentDegrees - 360 : currentDegrees;
        hardware.telemetry.addData("Current Degrees", currentDegrees);
        hardware.telemetry.addData("Target Degrees", targetDegrees);
        hardware.telemetry.addData("Raw Compass", hardware.compassSensor.getDirection());
        hardware.telemetry.update();
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
            //region Old
            /*
            double currentError = currentDegrees - targetDegrees;
            Direction newDirrection = direction;
            boolean overshot;

            if (direction.equals(Direction.RIGHT)){

            }

            switch (direction) {
                case LEFT:
                    hardware.set_drive_power(-speed, speed);
                    break;
                case RIGHT:
                    hardware.set_drive_power(speed, -speed);
                    break;
            }
            */
            //endregion

            if (targetError < 0.05){
                finished = true;
            }
            hardware.set_drive_power(0, 0);
        }
        return finished;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public float getDegrees() {
        return degrees;
    }

    public void setDegrees(float degrees) {
        this.degrees = degrees;
    }
}
