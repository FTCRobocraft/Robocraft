package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by lvern on 12/2/2017.
 */

public class ImageDetectionAction implements Action {

    RelicRecoveryVuMark vuMark;

    public boolean doAction(RobotHardware hardware) {
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(hardware.relicTemplate);
        this.vuMark = vuMark;
        return true;
    }
}
