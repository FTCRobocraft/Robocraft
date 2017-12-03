package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by lvern on 12/2/2017.
 */

public class ColorDetectionAction implements Action {

    public int r;
    public int g;
    public int b;

    public boolean doAction(RobotHardware hardware) {
        this.r = hardware.jewelSensor.red();
        this.g = hardware.jewelSensor.green();
        this.b = hardware.jewelSensor.blue();
        return true;
    }
}
