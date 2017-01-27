package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 11/27/16.
 */
public class AccumulatedHeadingTurn implements Action {

    RobotHardware.DIRECTION direction;
    double degrees;

    double previousHeading = 0;
    double totalTurned = 0;

    private static final double speed = 0.1;

    public AccumulatedHeadingTurn(RobotHardware.DIRECTION direction, double degrees) {
        this.direction = direction;
        this.degrees = degrees;
        this.previousHeading = 0;
        this.totalTurned = 0;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;
        if (!hardware.gyroSensor.isCalibrating()){
            double currentHeading = hardware.gyroSensor.getHeading();
            getTotalTurnDistance(currentHeading);

            switch (direction){
                case LEFT:
                    hardware.set_drive_power(-speed, speed);
                    break;
                case RIGHT:
                    hardware.set_drive_power(speed, -speed);
                    break;
            }

            if (Math.abs(totalTurned) > degrees){
                finished = true;
            }

            previousHeading = currentHeading;
        }
        hardware.telemetry.addData("Total", totalTurned);
        return finished;
    }

    public void getTotalTurnDistance(double currentDegrees) {
        totalTurned += currentDegrees - previousHeading;

        switch (direction){
            case LEFT:
                if (currentDegrees < previousHeading){
                    totalTurned += 360;
                }
                break;
            case RIGHT:
                if (currentDegrees > previousHeading){
                    totalTurned -= 360;
                }
                break;
        }
    }
}
