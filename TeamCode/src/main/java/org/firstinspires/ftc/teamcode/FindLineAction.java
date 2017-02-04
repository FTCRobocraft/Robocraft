package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 11/19/16.
 */
public class FindLineAction implements Action {

    private RobotHardware.VV_LINE_COLOR lineColor;
    private double speed;
    private static final int colorThreshold = 5;
    private boolean init = true;
    private boolean veerControl = true;
    private double startingHeading;
    final private double veerRange = 0;
    final private double fixSpeed = 0.09;
    private HeadingNormalizer normalizer;


    public FindLineAction (RobotHardware.VV_LINE_COLOR color, double speed, boolean veerControl){
        this.lineColor = color;
        this.speed = speed;
        this.veerControl = veerControl;
    }

    @Override
    public boolean doAction(RobotHardware hardware){
        boolean finished = false;
        double heading = hardware.gyroSensor.getHeading();
        if (init) {
            startingHeading = heading;
            init = false;
            normalizer = new HeadingNormalizer(startingHeading);
        }

        double h = normalizer.normalizeHeading(heading);
        if (Math.abs(h) > veerRange) {
            if (h > 0) {
                hardware.set_drive_power(speed - fixSpeed, speed);
            }else if(h < 0) {
                hardware.set_drive_power(speed, speed - fixSpeed);
            }
        }else{
            hardware.set_drive_power(speed, speed);
        }


        /*
        if (heading > startingHeading + veerRange || heading < startingHeading - veerRange && veerControl){
            if (heading > startingHeading){
                hardware.set_drive_power(speed - fixSpeed, speed);
            }else{
                hardware.set_drive_power(speed, speed - fixSpeed);
            }
        }else{
            hardware.set_drive_power(speed, speed);
        }
        */

        if (hardware.getLineFollowState(this.lineColor, this.colorThreshold) != RobotHardware.ROBOT_LINE_FOLLOW_STATE.NONE){
            finished = true;
        }

        return finished;
    }
}
