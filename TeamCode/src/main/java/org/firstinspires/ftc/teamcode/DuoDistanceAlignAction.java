package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 12/14/16.
 */
public class DuoDistanceAlignAction implements Action {

    double alignRange;
    double fixSpeed;


    public DuoDistanceAlignAction(double alignRange, double fixSpeed) {
        this.alignRange = alignRange;
        this.fixSpeed = fixSpeed;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;
        double leftCm = hardware.rangeSensor.cmUltrasonic();
        double rightCm = hardware.rangeSensor.cmUltrasonic();

        if (leftCm < rightCm + alignRange && rightCm < leftCm + alignRange){
            finished = true;
        }else{
            if (leftCm > rightCm + alignRange){
                hardware.set_drive_power(fixSpeed, -fixSpeed);
            }else{
                if (rightCm > leftCm + alignRange){
                    hardware.set_drive_power(-fixSpeed, fixSpeed);
                }
            }
        }

        return finished;
    }
}
