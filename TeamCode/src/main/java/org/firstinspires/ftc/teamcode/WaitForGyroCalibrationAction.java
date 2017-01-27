package org.firstinspires.ftc.teamcode;

/**
 * Created by djfigs1 on 12/30/16.
 */
public class WaitForGyroCalibrationAction implements Action {

    public WaitForGyroCalibrationAction() {}

    @Override
    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;
        if (!hardware.gyroSensor.isCalibrating()) {
            finished = true;
        }
        return finished;
    }
}
