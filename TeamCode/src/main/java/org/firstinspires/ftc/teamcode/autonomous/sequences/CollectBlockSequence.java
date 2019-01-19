package org.firstinspires.ftc.teamcode.autonomous.sequences;

import org.firstinspires.ftc.teamcode.action.EncoderToPositionAction;
import org.firstinspires.ftc.teamcode.action.WaitAction;
import org.firstinspires.ftc.teamcode.util.ActionSequence;

public class CollectBlockSequence extends ActionSequence {

    final int VERTICAL_HEX_UP_POSITION = 980;
    final int TRANSFER_MOTOR_POSITION = 950;
    final double TIMEOUT = 1000;
    final double TRANSFER_SPEED = 0.3f;
    final double VERTICAL_SPEED = 0.8f;

    public CollectBlockSequence(boolean deploy) {
        addAction(new EncoderToPositionAction("scooperTransferMotor",
                deploy ? TRANSFER_MOTOR_POSITION : -TRANSFER_MOTOR_POSITION,
                TRANSFER_SPEED,
                TIMEOUT));
    }

}
