package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.util.RelicRecoveryHardware;

import java.util.concurrent.Callable;

/**
 * Created by djfigs1 on 11/11/17.
 */

public class CodeExecutionAction implements Action {

    Callable<RelicRecoveryHardware> method;

    public CodeExecutionAction(Callable<RelicRecoveryHardware> method) {
        this.method = method;
    }

    @Override
    public boolean doAction(RelicRecoveryHardware hardware) {
        return false;
    }
}
