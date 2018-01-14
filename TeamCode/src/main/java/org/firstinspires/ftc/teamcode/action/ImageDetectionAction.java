package org.firstinspires.ftc.teamcode.action;

import android.media.Image;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by lvern on 12/2/2017.
 */

public class ImageDetectionAction implements Action {

    public RelicRecoveryVuMark vuMark;
    public double timeout;

    public boolean init = true;
    public double endTime;

    public ImageDetectionAction(double timeout) {
        this.timeout = timeout;
    }

    public boolean doAction(RobotHardware hardware) {
        if (init) {
            endTime = System.currentTimeMillis() + timeout;
            init = false;
        }

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
