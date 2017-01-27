package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.Action;
import org.firstinspires.ftc.teamcode.RobotHardware;

/**
 * Created by djfigs1 on 11/18/16.
 */
public class TurnAction2 implements Action {

    public enum Direction {
        LEFT,
        RIGHT
    }

    private Direction direction;
    private double degrees;
    private Double targetDegrees = null;

    private static final double targetError = 5;
    private static final double speed = 0.10;

    public TurnAction2(Direction direction, double degrees) {
        this.direction = direction;
        this.degrees = degrees;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;
        if (targetDegrees == null) {
            hardware.gyroSensor.getHeading();
            //targetDegrees = currentDegrees + (degrees * (direction.equals(Direction.LEFT) ? -1 : 1));
            targetDegrees = direction.equals(Direction.RIGHT) ? degrees : 360 - degrees;
        }

        if (!hardware.gyroSensor.isCalibrating()){
            double currentDegrees = hardware.gyroSensor.getHeading();
            //currentDegrees = targetDegrees >= 360 ? currentDegrees + 360 : targetDegrees <= 0 ? currentDegrees - 360 : currentDegrees;
            hardware.telemetry.addData("Current Degrees", currentDegrees);
            hardware.telemetry.addData("Target Degrees", targetDegrees);
            hardware.telemetry.addData("Raw Gyro", hardware.gyroSensor.getHeading());
            hardware.telemetry.update();
            //if (!(Math.abs(currentDegrees - targetDegrees) <= targetError)) {
            switch (direction) {
                case LEFT:
                    if (currentDegrees > targetDegrees || currentDegrees < 1) {
                        hardware.set_drive_power(-speed, speed);
                    }else{
                        hardware.set_drive_power(0, 0);
                        finished = true;
                    }
                    break;
                case RIGHT:
                    if (currentDegrees < targetDegrees) {
                        hardware.set_drive_power(speed, -speed);
                    }else{
                        hardware.set_drive_power(0, 0);
                        finished = true;
                    }
                    break;
            }
            //} else {
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
            //}
        }
        return finished;
    }
}
