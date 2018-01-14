package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by djfigs1 on 1/13/18.
 */

public class CustomCodeExecution implements Action {

    Runnable runnable;

    public CustomCodeExecution(Runnable runnable) {
        this.runnable = runnable;
    }

    public boolean doAction(RobotHardware hardware) {
        runnable.run();
        return true;
    }
}
