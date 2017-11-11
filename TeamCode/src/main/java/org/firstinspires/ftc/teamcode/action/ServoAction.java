package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by lvern on 11/11/2017.
 */

public class ServoAction implements Action {

    double servoPosition;

    public ServoAction(double servoPosition) {
        this.servoPosition = servoPosition;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        hardware.armServo.setPosition(servoPosition);
        return true;
    }
}
