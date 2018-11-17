package org.firstinspires.ftc.teamcode.autonomous.sequences;

import org.firstinspires.ftc.teamcode.action.Action;
import org.firstinspires.ftc.teamcode.action.BlockDetectionAction;
import org.firstinspires.ftc.teamcode.action.BlockPushAction;
import org.firstinspires.ftc.teamcode.action.IfAction;
import org.firstinspires.ftc.teamcode.action.MecanumMoveAction;
import org.firstinspires.ftc.teamcode.action.WaitAction;
import org.firstinspires.ftc.teamcode.hardware.BaseHardware;
import org.firstinspires.ftc.teamcode.util.ActionSequence;
import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;

import java.lang.reflect.Array;
import java.util.List;


/**
 * Created by djfigs1 on 10/21/17.
 */
public class KnockBlockSequence extends ActionSequence {
    public KnockBlockSequence() {
        BlockDetectionAction blockDetectionAction = new BlockDetectionAction(5);

        // Detect the block
        addAction(blockDetectionAction);

        // Move left if the gold mineral is on the left
        addAction(new IfAction((hardware) -> blockDetectionAction.position == RoverRuckusHardware.GOLD_MINERAL_POSITION.LEFT,
                leftSequence, null));

        // Move right if the gold mineral is on the right
        addAction(new IfAction((hardware) -> blockDetectionAction.position == RoverRuckusHardware.GOLD_MINERAL_POSITION.RIGHT,
                rightSequence, null));

        // PUSH
        addAction(new MecanumMoveAction(BaseHardware.Direction.FORWARD, 10, 0.5f, 5));

    }

    private ActionSequence leftSequence = new ActionSequence(
            new MecanumMoveAction(BaseHardware.Direction.LEFT, 39.5, 0.5f, 5)
    );

    private ActionSequence rightSequence = new ActionSequence(
            new MecanumMoveAction(BaseHardware.Direction.RIGHT, 39.5, 0.5f, 5)
    );
}
