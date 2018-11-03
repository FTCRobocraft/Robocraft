package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.util.EncoderDrive;
import org.firstinspires.ftc.teamcode.util.RelicRecoveryHardware;

/**
 * Created by djfigs1 on 1/19/18.
 */

public class JewelDecideAction implements Action {

    ColorDetectionAction color;
    RelicRecoveryHardware.Team team;
    EncoderDrive drive;
    boolean init = true;

    final double distance = 3;
    final float speed = 0.15f;
    final double timeout = 2;

    enum JewelStages {
        PUSH,
        BACKUP,
        END
    }

    JewelStages currentStage = JewelStages.PUSH;


    public  JewelDecideAction(ColorDetectionAction colorAction, RelicRecoveryHardware.Team team) {
        this.color = colorAction;
        this.team = team;
    }

    public boolean doAction(RelicRecoveryHardware hardware) {
        switch (currentStage) {
            case PUSH:
                if (init) {
                    init = false;
                    drive = new EncoderDrive(hardware);
                    switch (team) {
                        case Red:
                            if (this.color.r > this.color.b) {
                                drive.setInchesToDrive(RelicRecoveryHardware.RobotMoveDirection.BACKWARD, distance, speed, timeout);
                            } else {
                                drive.setInchesToDrive(RelicRecoveryHardware.RobotMoveDirection.FORWARD, distance, speed, timeout);
                            }
                            break;
                        case Blue:
                            if (this.color.b > this.color.r) {
                                drive.setInchesToDrive(RelicRecoveryHardware.RobotMoveDirection.BACKWARD, distance, speed, timeout);
                            } else {
                                drive.setInchesToDrive(RelicRecoveryHardware.RobotMoveDirection.FORWARD, distance, speed, timeout);
                            }
                            break;
                    }
                }

                drive.run();
                if (!drive.isBusy) {
                    currentStage = JewelStages.BACKUP;
                    init = true;
                }
                break;

            case BACKUP:
                if (init) {
                    init = false;
                    drive = new EncoderDrive(hardware);
                    switch (team) {
                        case Red:
                            if (this.color.r > this.color.b) {
                                drive.setInchesToDrive(RelicRecoveryHardware.RobotMoveDirection.FORWARD, distance, speed, timeout);
                            } else {
                                drive.setInchesToDrive(RelicRecoveryHardware.RobotMoveDirection.BACKWARD, distance, speed, timeout);
                            }
                            break;
                        case Blue:
                            if (this.color.b > this.color.r) {
                                drive.setInchesToDrive(RelicRecoveryHardware.RobotMoveDirection.FORWARD, distance, speed, timeout);
                            } else {
                                drive.setInchesToDrive(RelicRecoveryHardware.RobotMoveDirection.BACKWARD, distance, speed, timeout);
                            }
                            break;
                    }
                }

                drive.run();
                if (!drive.isBusy) {
                    currentStage = JewelStages.END;
                    init = true;
                }
                break;

            case END:
                return true;
        }
        return false;
    }
}