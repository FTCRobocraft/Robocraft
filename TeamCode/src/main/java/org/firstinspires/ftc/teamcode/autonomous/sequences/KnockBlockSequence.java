package org.firstinspires.ftc.teamcode.autonomous.sequences;

import org.firstinspires.ftc.teamcode.action.Action;
import org.firstinspires.ftc.teamcode.action.CameraBlockDetectionAction;
import org.firstinspires.ftc.teamcode.action.MecanumMoveAction;
import org.firstinspires.ftc.teamcode.action.WaitAction;
import org.firstinspires.ftc.teamcode.hardware.BaseHardware;
import org.firstinspires.ftc.teamcode.util.ActionSequence;
import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;


/**
 * Created by djfigs1 on 10/21/17.
 */
public class KnockBlockSequence extends ActionSequence {

    double lowPos = 0;
    double hiPos = 0.7;

    double downPosition = 0.15;
    double upPosition = 1;

    public KnockBlockSequence() {
        CameraBlockDetectionAction block = new CameraBlockDetectionAction();
        addAction(new WaitAction(5));
        addAction(block);
        addAction(new KnockAction(block));
        addAction(new MecanumMoveAction(BaseHardware.Direction.RIGHT, 39.5, 0.5f, 5));
        addAction(new MecanumMoveAction(BaseHardware.Direction.RIGHT, 39.5, 0.5f, 5));


    }

    private class KnockAction implements Action {

        CameraBlockDetectionAction cameraBlockDetectionAction;

        KnockAction(CameraBlockDetectionAction cameraBlockDetectionAction) {
            this.cameraBlockDetectionAction = cameraBlockDetectionAction;
        }

        @Override
        public boolean doAction(RoverRuckusHardware hardware) {
            if (cameraBlockDetectionAction.object == CameraBlockDetectionAction.DETECTED_OBJECT.CUBE) {
                hardware.omniDrive.moveForward(0.5f);
            }
            return false;
        }
    }
}
