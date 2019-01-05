package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.sequences.CollectBlockSequence;
import org.firstinspires.ftc.teamcode.autonomous.sequences.DumpBlockSequence;
import org.firstinspires.ftc.teamcode.util.ActionExecutor;

/**
 * Created by djfigs1 on 1/20/18.
 */

@Autonomous(name = "Dump Block")
public class DumpBlockExecutor extends ActionExecutor {

    @Override
    public void init() {
        this.initTFOD = false;
        super.init();
        DumpBlockSequence dumpBlockSequence = new DumpBlockSequence();
        this.actionSequence = dumpBlockSequence;
    }
}
