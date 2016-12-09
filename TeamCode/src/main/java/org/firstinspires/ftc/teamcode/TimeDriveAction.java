package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 12/9/16.
 */
public class TimeDriveAction implements Action {

    double time;
    double speed;
    double endTime;

    public TimeDriveAction(double time, double speed){
        this.time = time;
        this.speed = speed;
        endTime = System.currentTimeMillis() + this.time;
    }

    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;
        if (!(System.currentTimeMillis() >= endTime)){
            hardware.set_drive_power(this.speed, this.speed);
        }else{
            finished = true;
        }
        return finished;
    }
}