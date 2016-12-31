package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.robot.Robot;

/**
 * Created by djfigs1 on 12/30/16.
 */
public class ResetBeaconAction implements Action {

    public ResetBeaconAction() {}

    @Override
    public boolean doAction(RobotHardware hardware) {
        hardware.beaconPosition(0);
        return true;
    }
}
