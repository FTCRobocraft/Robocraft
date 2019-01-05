package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.hardware.BaseHardware;

public class WaitForeverAction implements Action {


    /**
     * Function called when the action is first executed
     *
     * @param hardware
     */
    @Override
    public void init(BaseHardware hardware) {

    }

    /**
     * Function that is called for every iteration of the OpMode loop
     *
     * @param hardware
     * @return Return true when the action is complete.
     */
    @Override
    public boolean doAction(BaseHardware hardware) {
        return false;
    }
}
