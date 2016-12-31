package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.Action;
import org.firstinspires.ftc.teamcode.RobotHardware;

/**
 * Created by djfigs1 on 11/19/16.
 */
public class FollowLineUntilDistance implements Action {

    private RobotHardware.VV_LINE_COLOR lineColor;
    private double distance;
    private double speed;
    final private double steerMultiplier = 2.05;
    private static final int colorThreshold = 5;

    public FollowLineUntilDistance(RobotHardware.VV_LINE_COLOR color, double distance, double speed){
        this.lineColor = color;
        this.distance = distance;
        this.speed = speed;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;
        if (hardware.rangeSensor.cmUltrasonic() >= this.distance){
            switch (hardware.getLineFollowState(lineColor, colorThreshold)){
                case LEFT:
                    hardware.set_drive_power(this.speed * steerMultiplier, -this.speed);
                    break;
                case RIGHT:
                    hardware.set_drive_power(-this.speed, this.speed * steerMultiplier);
                    break;
                case BOTH:
                    hardware.set_drive_power(this.speed, this.speed);
                    break;
            }
        }else{
            finished = true;
            /*
            if (hardware.rangeSensor.cmOptical() >= this.distance){
                hardware.set_drive_power(this.speed / 2, this.speed / 2);
            }else {
                finished = true;
            }
            */
        }
        return finished;
    }
}
