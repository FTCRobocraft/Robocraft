package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.action.ServoAction;
import org.firstinspires.ftc.teamcode.action.WaitAction;
import org.firstinspires.ftc.teamcode.util.ActionExecutor;
import org.firstinspires.ftc.teamcode.util.ActionSequence;

/**
 * Created by djfigs1 on 12/9/17.
 */

public class GripperClaw extends ActionExecutor {

    public class GripperClawSequence extends ActionSequence {

        double clawGripPosition = 1;
        double clawClose = 1;

        public GripperClawSequence() {
            addAction(new ServoAction(ServoAction.Servos.CLAW_ELBOW, clawGripPosition));
            addAction(new WaitAction(500));
            addAction(new ServoAction(ServoAction.Servos.CLAW, clawClose));
            addAction(new WaitAction(500));
            addAction(new ServoAction(ServoAction.Servos.CLAW_ELBOW, 0.5));
            addAction(new WaitAction(5000));
        }
    }

    public GripperClaw() {
        this.actionSequence = new GripperClawSequence();
    }

}
