package org.firstinspires.ftc.teamcode;

/**
 * Created by djfig on 12/12/2016.
 */
public class TimeBasedDegreeTurn implements Action{

    RobotHardware.DIRECTION direction;
    double degrees;
    double speed;

    public TimeBasedDegreeTurn(RobotHardware.DIRECTION direction, double degrees, double speed) {
        this.direction = direction;
        this.degrees = degrees;
        this.speed = speed;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;


        return finished;
    }
}
