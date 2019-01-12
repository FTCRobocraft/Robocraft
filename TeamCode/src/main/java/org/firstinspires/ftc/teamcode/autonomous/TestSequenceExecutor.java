package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.sequences.DumpBlockSequence;
import org.firstinspires.ftc.teamcode.autonomous.sequences.TestSequence;
import org.firstinspires.ftc.teamcode.util.ActionExecutor;

/**
 * Created by djfigs1 on 1/20/18.
 */

@Autonomous(name = "TestSequence")
public class TestSequenceExecutor extends ActionExecutor {

    TestSequence testSequence;

    @Override
    public void init() {
        this.initVuforia = true;
        this.initTFOD = true;
        this.enableFlashlight = false;
        super.init();
        testSequence = new TestSequence();
        this.actionSequence = testSequence;
    }

    @Override
    public void loop() {
        super.loop();
        telemetry.addData("pos", testSequence.blockDetectionAction.position.toString());
    }
}
