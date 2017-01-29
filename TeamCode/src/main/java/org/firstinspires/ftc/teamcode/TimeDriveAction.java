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
    private double veerRange = 0;
    private double fixSpeed = 0.07;
    private double startingDegrees = 0;
    private HeadingNormalizer normalizer;

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
            normalizer = new HeadingNormalizer(startingDegrees);
            init = false;
        }
        if (!(System.currentTimeMillis() >= endTime)){
            double h = normalizer.normalizeHeading(heading);
            hardware.telemetry.addData("head", heading);
            hardware.telemetry.addData("h", h);
            if (veerControl) {
                /*
                if (heading > startingDegrees + veerRange) {
                    hardware.set_drive_power(this.speed - fixSpeed, this.speed);
                }else{
                    if (heading > startingDegrees - veerRange) {
                        hardware.set_drive_power(this.speed, this.speed - fixSpeed);
                    } else{
                        hardware.set_drive_power(this.speed, this.speed);
                    }
                    */

                if (Math.abs(h) > veerRange) {
                    if (h > 0) {
                        if (speed > 0) {
                            hardware.set_drive_power(speed - fixSpeed, speed);
                        } else {
                            hardware.set_drive_power(speed, speed + fixSpeed);
                        }

                    } else if (h < 0) {
                        if (speed > 0) {
                            hardware.set_drive_power(speed, speed - fixSpeed);
                        } else {
                            hardware.set_drive_power(speed + fixSpeed, speed);
                        }
                    }
                }else{
                    hardware.set_drive_power(this.speed, this.speed);
                }

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