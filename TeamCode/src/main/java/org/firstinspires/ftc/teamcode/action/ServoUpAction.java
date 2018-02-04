package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by djfigs1 on 2/4/18.
 */

public class ServoUpAction implements Action {

    boolean init = true;
    double servoUpTime = 1000;
    double endTime;

    @Override
    public boolean doAction(RobotHardware hardware) {
        if (init) {
            endTime = System.currentTimeMillis() + servoUpTime;
            init = false;
        }

        if (System.currentTimeMillis() > endTime) {
            return true;
        }
        return false;
    }
}
