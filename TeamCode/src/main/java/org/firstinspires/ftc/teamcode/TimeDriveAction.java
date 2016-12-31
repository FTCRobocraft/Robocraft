package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 12/9/16.
 */
public class TimeDriveAction implements Action {

    private boolean init = true;
    private double time;
    private double speed;
    private double endTime;
    private boolean veerControl;
    private double veerRange = 2;
    private double fixSpeed = 0.05;
    private double startingDegrees = 0;
    private HeadingNormalizer normalizer = new HeadingNormalizer();

    public TimeDriveAction(double time, double speed, boolean veerControl){
        this.time = time;
        this.speed = speed;
        this.veerControl = veerControl;
    }

    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;
        double heading = hardware.gyroSensor.getHeading();
        if (init){
            startingDegrees = heading;
            endTime = System.currentTimeMillis() + this.time;
            init = false;
        }
        if (!(System.currentTimeMillis() >= endTime)){
            double h = normalizer.normalizeHeaing(heading);
            if (veerControl) {
                if (heading > startingDegrees + veerRange) {
                    hardware.set_drive_power(this.speed - fixSpeed, this.speed);
                }else{
                    if (heading > startingDegrees - veerRange) {
                        hardware.set_drive_power(this.speed, this.speed - fixSpeed);
                    } else{
                        hardware.set_drive_power(this.speed, this.speed);
                    }
                }

                /*
                if (Math.abs(h) > veerRange) {
                    if (h > 0) {
                        hardware.set_drive_power(speed - fixSpeed, speed);
                    } else if (h < 0) {
                        hardware.set_drive_power(speed, speed - fixSpeed);
                    }
                }else{
                    hardware.set_drive_power(this.speed, this.speed);
                }
                */
            }
            else {
                hardware.set_drive_power(this.speed, this.speed);
            }
        }else{
            finished = true;
        }
        return finished;
    }
}