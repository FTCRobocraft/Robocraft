package org.firstinspires.ftc.teamcode.autonomous.sequences;

import org.firstinspires.ftc.teamcode.action.DumperServoAction;
import org.firstinspires.ftc.teamcode.action.EncoderToPositionAction;
import org.firstinspires.ftc.teamcode.action.WaitAction;
import org.firstinspires.ftc.teamcode.action.WaitForeverAction;
import org.firstinspires.ftc.teamcode.util.ActionSequence;

public class DumpBlockSequence  extends ActionSequence  {

    final int VERTICAL_HEX_UP_POSITION = 980;
    final int TRANSFER_MOTOR_POSITION = 900;
    final double TIMEOUT = 3000;
    final double TRANSFER_SPEED = 0.35f;
    final double VERTICAL_SPEED = 1f;

    public DumpBlockSequence() {
        addAction(new EncoderToPositionAction("scooperTransferMotor", TRANSFER_MOTOR_POSITION, TRANSFER_SPEED, TIMEOUT));
        addAction(new EncoderToPositionAction("dumperVerticalHexMotor", VERTICAL_HEX_UP_POSITION, VERTICAL_SPEED, TIMEOUT));
        addAction(new DumperServoAction(0.6, 250));
        addAction(new WaitAction(400));
        addAction(new DumperServoAction(1, 250));
        addAction(new WaitAction(250));
        addAction(new DumperServoAction(0, 250));
        addAction(new EncoderToPositionAction("dumperVerticalHexMotor", -VERTICAL_HEX_UP_POSITION, VERTICAL_SPEED, TIMEOUT));
        addAction(new EncoderToPositionAction("scooperTransferMotor", -TRANSFER_MOTOR_POSITION, TRANSFER_SPEED, TIMEOUT));
    }
}
