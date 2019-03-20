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

    public DumpBlockSequence(boolean scooperUp) {
        if (scooperUp) {
            addAction(new EncoderToPositionAction("scooperTransferMotor", TRANSFER_MOTOR_POSITION, TRANSFER_SPEED, 1500));
        }
        addAction(new EncoderToPositionAction("dumperVerticalHexMotor", VERTICAL_HEX_UP_POSITION, VERTICAL_SPEED, TIMEOUT));
        addAction(new DumperServoAction(0.7, 250));
        addAction(new WaitAction(800));
        addAction(new DumperServoAction(1, 250));
        addAction(new WaitAction(250));
        addAction(new DumperServoAction(0.2, 250));
        addAction(new EncoderToPositionAction("dumperVerticalHexMotor", -VERTICAL_HEX_UP_POSITION, VERTICAL_SPEED, TIMEOUT));
        addAction(new DumperServoAction(0, 250));
        addAction(new EncoderToPositionAction("scooperTransferMotor", -TRANSFER_MOTOR_POSITION, TRANSFER_SPEED, 1500));
    }
}
