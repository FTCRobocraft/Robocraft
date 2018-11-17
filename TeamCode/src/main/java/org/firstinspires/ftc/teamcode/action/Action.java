package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.hardware.BaseHardware;
import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;

/**
 * Created by djfigs1 on 11/18/16.
 */
public interface Action {

    /**
     * Function called when the action is first executed
     * @param hardware
     */
    void init(BaseHardware hardware);

    /**
     * Function that is called for every iteration of the OpMode loop
     * @param hardware
     * @return Return true when the action is complete.
     */
    boolean doAction(BaseHardware hardware);
}
