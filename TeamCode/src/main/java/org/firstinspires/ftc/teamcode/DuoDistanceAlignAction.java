package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 12/14/16.
 */
public class DuoDistanceAlignAction implements Action {

    double alignRange;
    double fixSpeed;
    RobotHardware.DIRECTION direction = RobotHardware.DIRECTION.LEFT;
    final double badReading = 20;
    boolean init = true;
    final double fixTime = 2500;
    double endTime;


    public DuoDistanceAlignAction(double alignRange, double fixSpeed) {
        this.alignRange = alignRange;
        this.fixSpeed = fixSpeed;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;
        double leftCm = hardware.leftRangeSensor.cmUltrasonic();
        double rightCm = hardware.getRightCm();
        if (init) {
            endTime = System.currentTimeMillis() + fixTime;
            init = false;
        }
        if (!(System.currentTimeMillis() >= endTime)) {
            if ((leftCm <= badReading && rightCm <= badReading)){
                if (leftCm == rightCm){
                    finished = true;
                    hardware.stopdrive();
                }else {
                    if (leftCm >= rightCm + alignRange) {
                        hardware.set_drive_power(fixSpeed, -fixSpeed);
                        direction = RobotHardware.DIRECTION.RIGHT;
                    } else {
                        if (rightCm >= leftCm + alignRange) {
                            hardware.set_drive_power(-fixSpeed, fixSpeed);
                            direction = RobotHardware.DIRECTION.LEFT;
                        }
                    }
                }
            } else {
                //hardware.stopdrive();
                switch (direction) {
                    case LEFT:
                        hardware.set_drive_power(fixSpeed, -fixSpeed);
                        break;
                    case RIGHT:
                        hardware.set_drive_power(-fixSpeed, fixSpeed);
                        break;
                }
            }
        }else{
            finished = true;
        }

        return finished;
    }
}