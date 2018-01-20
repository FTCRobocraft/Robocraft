package org.firstinspires.ftc.teamcode.action;

import android.media.Image;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by lvern on 12/2/2017.
 */

public class ImageDetectionAction implements Action {

    public RelicRecoveryVuMark vuMark;

    public ImageDetectionAction() {}

    public boolean doAction(RobotHardware hardware) {
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(hardware.relicTemplate);
        if (!(vuMark == RelicRecoveryVuMark.UNKNOWN)) {
            this.vuMark = vuMark;
            return true;
        }
        return false;
    }
}
