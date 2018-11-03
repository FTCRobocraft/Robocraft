package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.util.RelicRecoveryHardware;

/**
 * Created by lvern on 11/11/2017.
 */

public class ServoAction implements Action {

    double servoPosition;
    Servos servo;

    public enum Servos {
        ARM,
        GRIP_LEFT,
        GRIP_RIGHT,
        CLAW_ELBOW,
        CLAW
    }

    public ServoAction(Servos servo, double servoPosition) {
        this.servoPosition = servoPosition;
        this.servo = servo;
    }

    @Override
    public boolean doAction(RelicRecoveryHardware hardware) {
        switch (this.servo) {
            case ARM:
                hardware.armServo.setPosition(servoPosition);
                break;
        }
        return true;
    }
}
