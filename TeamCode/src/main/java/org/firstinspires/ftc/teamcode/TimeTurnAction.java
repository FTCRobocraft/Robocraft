package org.firstinspires.ftc.teamcode;

/**
 * Created by djfig on 12/12/2016.
 */
public class TimeTurnAction implements Action {

    RobotHardware.DIRECTION direction;
    boolean init;
    double endTime;
    double time;
    double speed;

    public TimeTurnAction(RobotHardware.DIRECTION direction, double time, double speed) {
        this.direction = direction;
        this.time = time;
        this.speed = speed;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;
        if (init){
            endTime = System.currentTimeMillis() + time;
            init = false;
        }
        if (System.currentTimeMillis() >= endTime){
            finished = true;
        }else{
            if (direction == RobotHardware.DIRECTION.RIGHT){
                hardware.set_drive_power(this.speed, -this.speed);
            }else{
                hardware.set_drive_power(-this.speed, this.speed);
            }
        }
        return finished;
    }
}
