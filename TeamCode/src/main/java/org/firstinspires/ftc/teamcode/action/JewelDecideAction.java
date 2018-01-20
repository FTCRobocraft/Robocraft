package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.util.EncoderDrive;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by djfigs1 on 1/19/18.
 */

public class JewelDecideAction implements Action {

    ColorDetectionAction color;
    RobotHardware.Team team;
    boolean init = true;

    final double distance = 2;
    final float speed = 0.15f;
    final double timeout = 2;


    public  JewelDecideAction(ColorDetectionAction colorAction, RobotHardware.Team team) {
        this.color = colorAction;
        this.team = team;
    }

    public boolean doAction(RobotHardware hardware) {
        EncoderDrive drive = new EncoderDrive(hardware);
        if (init) {
            init = false;
            switch (team) {
                case Red:
                    if (this.color.r > this.color.b) {
                        drive.setInchesToDrive(RobotHardware.RobotMoveDirection.BACKWARD, distance, speed, timeout);
                    } else {
                        drive.setInchesToDrive(RobotHardware.RobotMoveDirection.FORWARD, distance, speed, timeout);
                    }
                    break;
                case Blue:
                    if (this.color.b > this.color.r) {
                        drive.setInchesToDrive(RobotHardware.RobotMoveDirection.BACKWARD, distance, speed, timeout);
                    } else {
                        drive.setInchesToDrive(RobotHardware.RobotMoveDirection.FORWARD, distance, speed, timeout);
                    }
                    break;
            }
        }

        drive.run();
        return !drive.isBusy;
    }
}