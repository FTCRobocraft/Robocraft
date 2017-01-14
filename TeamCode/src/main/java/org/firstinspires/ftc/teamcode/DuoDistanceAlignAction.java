package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 12/14/16.
 */
public class DuoDistanceAlignAction implements Action {

    double alignRange = 0.05;
    double fixSpeed;


    public DuoDistanceAlignAction(double alignRange, double fixSpeed) {
        this.alignRange = alignRange;
        this.fixSpeed = fixSpeed;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;
        double leftCm = hardware.leftRangeSensor.cmUltrasonic();
        double rightCm = hardware.getRightCm();

        if (leftCm <= rightCm + alignRange && rightCm <= leftCm + alignRange){
            finished = true;
        }else{
            if (leftCm >= rightCm + alignRange){
                hardware.set_drive_power(fixSpeed, -fixSpeed);
            }else{
                if (rightCm >= leftCm + alignRange){
                    hardware.set_drive_power(-fixSpeed, fixSpeed);
                }
            }
    }

    return finished;
    }
}
