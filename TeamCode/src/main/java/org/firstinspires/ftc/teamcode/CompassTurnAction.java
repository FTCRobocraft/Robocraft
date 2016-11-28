package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CompassSensor;

import org.firstinspires.ftc.teamcode.Action;
import org.firstinspires.ftc.teamcode.RobotHardware;

/**
 * Created by djfigs1 on 11/19/16.
 */
public class CompassTurnAction implements Action {

    enum Direction {
        LEFT,
        RIGHT
    }

    private double degreesToTravel;
    private double degreesTravelled = 0;
    private Double startDegrees = null;
    private double previousDegrees;
    private Direction direction;

    public CompassTurnAction(Direction direction, double degrees){
        this.degreesToTravel = degrees;
        this.direction = direction;
    }

    @Override
    public boolean doAction(RobotHardware hardware){
        double currentDirection = hardware.compassSensor.getDirection();
        if (startDegrees == null) {
            startDegrees = currentDirection;
        }
        previousDegrees = currentDirection;
        return true;
    }
}
