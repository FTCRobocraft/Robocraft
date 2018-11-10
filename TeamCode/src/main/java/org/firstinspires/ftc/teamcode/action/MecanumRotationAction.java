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
    boolean init = true;
    EncoderDrive encoderDrive;

    public final double INCHES_PER_DEGREE = 66.1/360;


    public MecanumRotationAction(int degrees, float speed) {
        this.degrees = degrees;
        this.speed = speed;
    }

    public boolean doAction(RoverRuckusHardware hardware) {
        if (init) {
            double distance = INCHES_PER_DEGREE * degrees;
            encoderDrive = new EncoderDrive(hardware.omniDrive);
            encoderDrive.setInchesToDrive(BaseHardware.Direction.ROTATE_RIGHT, distance, speed, 1000);
            init = false;
        }
        encoderDrive.run(hardware);
        return !encoderDrive.isBusy;
    }
}
