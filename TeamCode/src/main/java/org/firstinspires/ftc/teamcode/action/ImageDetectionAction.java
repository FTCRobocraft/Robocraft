package org.firstinspires.ftc.teamcode.action;

import android.media.Image;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by lvern on 12/2/2017.
 */

public class ImageDetectionAction implements Action {

    public RelicRecoveryVuMark vuMark;
    final double imageTime = 5000;
    double endTime;
    boolean init = true;

    public ImageDetectionAction() {}

    public boolean doAction(RobotHardware hardware) {
        if (init) {
            endTime = System.currentTimeMillis() + imageTime;
            init = false;
        }

        if (System.currentTimeMillis() > endTime) {
            this.vuMark = RelicRecoveryVuMark.CENTER;
            return true;
        } else {
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(hardware.relicTemplate);
            if (!(vuMark == RelicRecoveryVuMark.UNKNOWN)) {
                this.vuMark = vuMark;
                return true;
            }
        }
        return false;
    }
}
