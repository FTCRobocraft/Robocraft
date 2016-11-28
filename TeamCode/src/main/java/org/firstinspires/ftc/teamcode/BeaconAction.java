package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.Action;
import org.firstinspires.ftc.teamcode.RobotHardware;

/**
 * Created by djfigs1 on 11/19/16.
 */
public class BeaconAction implements Action {

    private RobotHardware.TEAM team;
    private int servoWaitTime;
    private RobotHardware.VV_BEACON_COLOR beaconColor = null;

    public BeaconAction(RobotHardware.TEAM team, int servoWaitTime) {
        this.team = team;
        this.servoWaitTime = servoWaitTime;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        boolean finished = false;
        if (beaconColor == null) {
            this.beaconColor = hardware.getBeaconColor();
        }
        switch (this.beaconColor) {
            case RED:
                if (this.team == RobotHardware.TEAM.RED) {
                    //Push
                } else {
                    hardware.prepareForBeacon(true);
                }
                break;
            case BLUE:
                if (this.team == RobotHardware.TEAM.RED) {
                    hardware.prepareForBeacon(true);
                } else {
                    //push
                }
                break;
        }

        return finished;
    }
}
