package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.Action;
import org.firstinspires.ftc.teamcode.RobotHardware;

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
    final private double veerRange = 2;
    final private double fixSpeed = 0.05;


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
        }
        hardware.telemetry.addData("startHeading", startingHeading);
        hardware.telemetry.addData("heading", heading);

        if (heading > startingHeading + veerRange || heading < startingHeading - veerRange && veerControl){
            if (heading > startingHeading){
                hardware.set_drive_power(speed - fixSpeed, speed);
            }else{
                hardware.set_drive_power(speed, speed - fixSpeed);
            }
        }else{
            hardware.set_drive_power(speed, speed);
        }

        if (hardware.getLineFollowState(this.lineColor, this.colorThreshold) != RobotHardware.ROBOT_LINE_FOLLOW_STATE.NONE){
            finished = true;
        }

        return finished;
    }
}
