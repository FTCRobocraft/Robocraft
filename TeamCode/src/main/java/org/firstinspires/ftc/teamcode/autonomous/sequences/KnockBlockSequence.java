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
        addAction(new BlockPushAction(null));
    }
}
