package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 12/14/16.
 */
public class DuoDistanceAlignAction implements Action {

    double alignRange;
    double fixSpeed;
    RobotHardware.DIRECTION direction = RobotHardware.DIRECTION.LEFT;
    final double badReading = 20;
    int check = 0;
    boolean init = true;
    final double fixTime = 1500;
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
        hardware.telemetry.addData("leftcm", leftCm);
        hardware.telemetry.addData("rightcm", rightCm);
        hardware.telemetry.addData("checks", check);
        if (!(System.currentTimeMillis() >= endTime)) {
                if (leftCm == rightCm){
                    hardware.stopdrive();
                    check++;
                    if (check >= 5) {
                        finished = true;
                    }
                }else {
                    check = 0;
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
        }else{
            finished = true;
        }

        return finished;
    }
}