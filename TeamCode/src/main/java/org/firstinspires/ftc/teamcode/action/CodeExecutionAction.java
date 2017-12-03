package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.util.RobotHardware;

import java.util.concurrent.Callable;

/**
 * Created by djfigs1 on 11/11/17.
 */

public class CodeExecutionAction implements Action {

    Callable<RobotHardware> method;

    public CodeExecutionAction(Callable<RobotHardware> method) {
        this.method = method;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        return false;
    }
}
