package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by djfigs1 on 10/1/17.
 */

public class MecanumRotationAction implements Action {

    private int degrees;
    private float speed;
    boolean init = true;

    float targetDegrees;
    double deadzone = 1;


    public MecanumRotationAction(int degrees, float speed) {
        this.degrees = degrees;
        this.speed = speed;
    }

    public boolean doAction(RobotHardware hardware) {
        if (init) {
            init = false;
            targetDegrees = hardware.revIMU.getAngularOrientation().firstAngle + this.degrees;
        }

        float currentAngle = hardware.revIMU.getAngularOrientation().firstAngle;
        if (Math.abs(targetDegrees - currentAngle) < deadzone) {
            return true;
        } else {
            if (currentAngle < targetDegrees) {
                hardware.rotateRight(speed);
            } else {
                hardware.rotateLeft(speed);
            }
        }
        return false;
    }
}
