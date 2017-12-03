package org.firstinspires.ftc.teamcode.action;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by lvern on 11/11/2017.
 */

public class ServoAction implements Action {

    double servoPosition;
    Servos servo;

    public enum Servos {
        ARM,
        GRIP_LEFT,
        GRIP_RIGHT
    }

    public ServoAction(Servos servo, double servoPosition) {
        this.servoPosition = servoPosition;
        this.servo = servo;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        switch (this.servo) {
            case ARM:
                hardware.armServo.setPosition(servoPosition);
                break;
            case GRIP_LEFT:
                hardware.lift_leftServo.setPosition(servoPosition);
                break;
            case GRIP_RIGHT:
                hardware.lift_rightServo.setPosition(servoPosition);
                break;
        }
        return true;
    }
}
