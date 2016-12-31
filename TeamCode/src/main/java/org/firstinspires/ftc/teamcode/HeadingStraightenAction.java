package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 12/16/16.
 */
public class HeadingStraightenAction implements Action {

    double degrees;
    double deadzone;
    double speed;

    public HeadingStraightenAction(double degrees, double deadzone, double speed) {
        this.degrees = degrees;
        this.deadzone = deadzone;
        this.speed = speed;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;
        double heading = hardware.gyroSensor.getHeading();
        if (heading > degrees + deadzone) {
            hardware.set_drive_power(-speed, speed);
        }else{
            if (heading < degrees - deadzone) {
                hardware.set_drive_power(speed, -speed);
            } else{
                finished = true;
            }
        }
        return finished;
    }
}
