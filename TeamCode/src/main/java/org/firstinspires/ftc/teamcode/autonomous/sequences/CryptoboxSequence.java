package org.firstinspires.ftc.teamcode.autonomous.sequences;

import org.firstinspires.ftc.teamcode.action.Action;
import org.firstinspires.ftc.teamcode.action.MecanumMoveAction;
import org.firstinspires.ftc.teamcode.util.ActionSequence;
import org.firstinspires.ftc.teamcode.util.RelicRecoveryHardware;
import org.firstinspires.ftc.teamcode.util.RelicRecoveryHardware.RobotMoveDirection;
import org.firstinspires.ftc.teamcode.util.RelicRecoveryHardware.Team;


/**
 * Created by djfigs1 on 12/23/17.
 */

public class CryptoboxSequence extends ActionSequence {

    public enum StartingPosition {
        TOP,
        BOTTOM
    }

    Team team;
    StartingPosition startingPosition;
    ImageDetectionAction imageAction;

    public CryptoboxSequence(Team team, StartingPosition startingPosition) {
        this.team = team;
        this.startingPosition = startingPosition;

        //imageAction = new ImageDetectionAction(2000);
        //addAction(imageAction);

        addAction(new ImageAction(imageAction));
        //region stuff
        switch (team) {
            /*case Red:
                switch (startingPosition) {
                    case TOP:
                        addAction(new MecanumMoveAction(RobotMoveDirection.FORWARD, 10, 0.75f, 36));
                        imageAction = new ImageDetectionAction();
                        addAction(imageAction);
                        break;
                    case BOTTOM:
                        addAction(new MecanumMoveAction(RobotMoveDirection.BACKWARD, 10, 0.75f, 36));
                        imageAction = new ImageDetectionAction();
                        addAction(imageAction);
                        break;
                }

                break;

            case Blue:
                switch (startingPosition) {
                    case TOP:
                        addAction(new MecanumMoveAction(RobotMoveDirection.BACKWARD, 10, 0.75f, 36));
                        imageAction = new ImageDetectionAction();
                        addAction(imageAction);
                    case BOTTOM:
                        addAction(new MecanumMoveAction(RobotMoveDirection.FORWARD, 10, 0.75f, 36));
                        imageAction = new ImageDetectionAction();
                        addAction(imageAction);
                        break;
                }

                break;

              */
        }
        //endregion
    }

    class ImageAction implements Action {

        ImageDetectionAction detectionAction;

        public ImageAction(ImageDetectionAction imageDetectionAction) {
            detectionAction = imageDetectionAction;
        }

        @Override
        public boolean doAction(RelicRecoveryHardware hardware) {
            switch (imageAction.vuMark) {
                case LEFT:
                    addAction(new MecanumMoveAction(RobotMoveDirection.FORWARD, 10, 10f,  5));
                    break;
                case CENTER:
                    addAction(new MecanumMoveAction(RobotMoveDirection.FORWARD, 20, 10f,  5));
                    break;
                case RIGHT:
                    addAction(new MecanumMoveAction(RobotMoveDirection.FORWARD, 30, 10f,  5));
                    break;
            }
            return true;
        }
    }
}
