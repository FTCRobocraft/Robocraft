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

    public FindLineAction (RobotHardware.VV_LINE_COLOR color, double speed){
        this.lineColor = color;
        this.speed = speed;
    }

    @Override
    public boolean doAction(RobotHardware hardware){
        boolean finished = false;
        hardware.set_drive_power(this.speed, this.speed);

        if (hardware.getLineFollowState(this.lineColor, this.colorThreshold) != RobotHardware.ROBOT_LINE_FOLLOW_STATE.NONE){
            finished = true;
        }

        return finished;
    }
}
