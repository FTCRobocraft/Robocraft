package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Action;
import org.firstinspires.ftc.teamcode.RobotHardware;

/**
 * Created by djfigs1 on 11/18/16.
 */
public class MoveAction implements Action {

    enum Direction {
        FORWARD,
        BACKWARD
    }

    private Direction direction;
    private float distanceInches;
    private double speed;

    private float distanceTravelled;

    public MoveAction(Direction direction, float distanceInches, double speed) {
        this.direction = direction;
        this.distanceInches = distanceInches;
        this.speed = speed;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        while (distanceTravelled < distanceInches){
            hardware.set_drive_power(speed, speed);
            distanceTravelled += hardware.compassSensor.getAcceleration().toUnit(DistanceUnit.INCH).unit.bVal;
        }
        return true;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public float getDistanceInches() {
        return distanceInches;
    }

    public void setDistanceInches(float distanceInches) {
        this.distanceInches = distanceInches;
    }


}