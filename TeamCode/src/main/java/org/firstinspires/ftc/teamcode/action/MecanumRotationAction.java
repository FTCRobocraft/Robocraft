package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.hardware.BaseHardware;
import org.firstinspires.ftc.teamcode.util.EncoderDrive;
import org.firstinspires.ftc.teamcode.hardware.RelicRecoveryHardware;
import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;

/**
 * Created by djfigs1 on 10/1/17.
 */

public class MecanumRotationAction implements Action {

    private int degrees;
    private float speed;
    EncoderDrive encoderDrive;

    public final double INCHES_PER_DEGREE = 66.1/360;


    public MecanumRotationAction(int degrees, float speed) {
        this.degrees = degrees;
        this.speed = speed;
    }

    public void init(BaseHardware hardware) {
        double distance = INCHES_PER_DEGREE * degrees;
        encoderDrive = new EncoderDrive(((RoverRuckusHardware) hardware).omniDrive);
        if (distance < 0) {
            distance = -distance;
            encoderDrive.setInchesToDrive(BaseHardware.Direction.ROTATE_LEFT, distance, speed, 1500);
        } else {
            encoderDrive.setInchesToDrive(BaseHardware.Direction.ROTATE_RIGHT, distance, speed, 1500);
        }
    }

    public boolean doAction(BaseHardware hardware) {
        if (hardware instanceof RoverRuckusHardware) {
            encoderDrive.run(hardware);
            return !encoderDrive.isBusy;
        } else {
            return true;
        }
    }
}
