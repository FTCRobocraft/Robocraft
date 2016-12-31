package org.firstinspires.ftc.teamcode;

/**
 * Created by djfig on 12/12/2016.
 */
public class ShimmeyTimeTurnAction implements Action {

    RobotHardware.DIRECTION direction;
    double deadzone;
    double degrees;
    boolean init = true;
    double endTime;
    double time;
    double speed;

    public ShimmeyTimeTurnAction(double degrees, double deadzone, double time, double speed) {
        this.degrees = degrees;
        this.deadzone = deadzone;
        this.time = time;
        this.speed = speed;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;
        double heading = hardware.gyroSensor.getHeading();
        if (init){
            endTime = System.currentTimeMillis() + time;
            if (heading > degrees + deadzone) {
                direction = RobotHardware.DIRECTION.LEFT;
            }else{
                if (heading < degrees - deadzone) {
                    direction = RobotHardware.DIRECTION.RIGHT;
                } else{
                    finished = true;
                }
            }
            init = false;
        }
        if (System.currentTimeMillis() >= endTime || finished){
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
