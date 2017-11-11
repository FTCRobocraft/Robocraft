package org.firstinspires.ftc.teamcode.autonomous.sequences;

import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.teamcode.action.ServoAction;
import org.firstinspires.ftc.teamcode.action.WaitAction;
import org.firstinspires.ftc.teamcode.util.ActionSequence;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by djfigs1 on 10/21/17.
 */

public class JewelActionSequence extends ActionSequence {

    double lowPos = 0;
    double hiPos = 0.7; //0.??

    public JewelActionSequence(RobotHardware.Team team) {

        switch (team) {
            case Red:
                addAction(new WaitAction(1));
                addAction(new ServoAction(0));
                break;

            case Blue:
                break;

        }
    }
}
