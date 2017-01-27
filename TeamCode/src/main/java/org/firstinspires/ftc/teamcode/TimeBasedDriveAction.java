package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.robot.Robot;

/**
 * Created by djfigs1 on 12/16/16.
 */
public class TimeBasedDriveAction implements Action {

    double distance;
    double speed;

    public TimeBasedDriveAction(double distance, double speed) {
        this.distance = distance;
        this.speed = speed;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;

        return finished;
    }
}
