package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.sequences.FullAutoSequence;
import org.firstinspires.ftc.teamcode.util.ActionExecutor;

/**
 * Created by djfigs1 on 1/20/18.
 */

@Autonomous(name = "BLUE - Top")
public class AutoBlueTopExec extends ActionExecutor {

    @Override
    public void init() {
        this.initVuforia = true;
        super.init();
        FullAutoSequence sequence = new FullAutoSequence(Team.Blue, Position.Top);
        this.actionSequence = sequence;
        sequence.init(this);
    }

    @Override
    public void stop() {
        FullAutoSequence sequence = (FullAutoSequence) this.actionSequence;
        if (sequence != null) {
            sequence.stop(this);
        }
    }
}
