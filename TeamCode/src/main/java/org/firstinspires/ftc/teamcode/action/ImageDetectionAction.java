package org.firstinspires.ftc.teamcode.action;

import android.media.Image;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by lvern on 12/2/2017.
 */

public class ImageDetectionAction implements Action {

    public RelicRecoveryVuMark vuMark;
    public RobotHardware.RobotMoveDirection direction;
    public double endTime;
    public float power;

    public ImageDetectionAction(RobotHardware.RobotMoveDirection direction, float power) {
        this.direction = direction;
        this.power = power;
    }

    public boolean doAction(RobotHardware hardware) {
        hardware.moveInDirection(this.direction, this.power);

        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(hardware.relicTemplate);
        if (System.currentTimeMillis() > endTime) {
            if (!(vuMark == RelicRecoveryVuMark.UNKNOWN)) {
                this.vuMark = vuMark;
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
