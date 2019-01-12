package org.firstinspires.ftc.teamcode.autonomous.sequences;

import org.firstinspires.ftc.teamcode.action.BlockPushAction;
import org.firstinspires.ftc.teamcode.action.KeepScooperUnderPowerAction;
import org.firstinspires.ftc.teamcode.action.MecanumMoveAction;
import org.firstinspires.ftc.teamcode.action.MecanumRotationAction;
import org.firstinspires.ftc.teamcode.action.WaitForeverAction;
import org.firstinspires.ftc.teamcode.hardware.BaseHardware;
import org.firstinspires.ftc.teamcode.util.ActionSequence;

public class TestSequence extends ActionSequence {

    public BlockPushAction blockDetectionAction;

    public TestSequence() {
        addAction(new KeepScooperUnderPowerAction());
        addAction(new MecanumMoveAction(BaseHardware.Direction.BACKWARD,
                13, 0.5f, 1000));
        blockDetectionAction = new BlockPushAction(15000);
        addAction(blockDetectionAction);
        addAction(new MecanumMoveAction(BaseHardware.Direction.RIGHT,
                45, 0.5f, 2000));
        addAction(new MecanumRotationAction(45, 0.4f));
        addAction(new WaitForeverAction());
    }


}
