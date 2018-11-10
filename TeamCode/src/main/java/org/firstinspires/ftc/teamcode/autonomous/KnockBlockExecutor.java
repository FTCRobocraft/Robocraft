package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.sequences.KnockBlockSequence;
import org.firstinspires.ftc.teamcode.util.ActionExecutor;

/**
 * Created by djfigs1 on 1/20/18.
 */

@Autonomous(name = "Knock Block")
public class KnockBlockExecutor extends ActionExecutor {

    @Override
    public void init() {
        this.initTFOD = true;
        super.init();
        KnockBlockSequence sequence = new KnockBlockSequence();
        this.actionSequence = sequence;
    }
}
