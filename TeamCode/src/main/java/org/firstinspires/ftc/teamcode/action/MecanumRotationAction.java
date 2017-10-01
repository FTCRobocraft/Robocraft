package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by djfigs1 on 10/1/17.
 */

public class MecanumRotationAction implements Action {

    private int degrees;

    public MecanumRotationAction(int degrees) {
        this.degrees = degrees;
    }

    public boolean doAction(RobotHardware hardware) {
        return false;
    }
}
