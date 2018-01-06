package org.firstinspires.ftc.teamcode.autonomous.sequences;

import org.firstinspires.ftc.teamcode.action.ImageDetectionAction;
import org.firstinspires.ftc.teamcode.action.MecanumMoveAction;
import org.firstinspires.ftc.teamcode.util.ActionSequence;

/**
 * Created by djfigs1 on 12/23/17.
 */

public class CryptoboxSequence extends ActionSequence {

    enum StartingPosition {
        TOP,
        BOTTOM
    }

    Team team;
    StartingPosition startingPosition;
    ImageDetectionAction imageAction;

    public CryptoboxSequence(Team team, StartingPosition startingPosition) {
        this.team = team;
        this.startingPosition = startingPosition;

        switch (team) {
            case Red:
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
        }
    }
}
