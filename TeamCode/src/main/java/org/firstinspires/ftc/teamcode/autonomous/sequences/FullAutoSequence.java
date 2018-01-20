package org.firstinspires.ftc.teamcode.autonomous.sequences;

import org.firstinspires.ftc.teamcode.action.ColorDetectionAction;
import org.firstinspires.ftc.teamcode.action.ImageDetectionAction;
import org.firstinspires.ftc.teamcode.action.JewelDecideAction;
import org.firstinspires.ftc.teamcode.action.MecanumMoveAction;
import org.firstinspires.ftc.teamcode.action.MecanumRotationAction;
import org.firstinspires.ftc.teamcode.action.ServoAction;
import org.firstinspires.ftc.teamcode.action.WaitAction;
import org.firstinspires.ftc.teamcode.util.ActionSequence;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by djfigs1 on 1/19/18.
 */

public class FullAutoSequence extends ActionSequence {

    double colorArmDownPosition = 0.15;
    double colorArmUpPosition = 1;

    float s_detectImage = 0.25f;
    float s_rotation = 0.5f;
    float s_moveToCryptobox = 0.25f;
    double m_redTopToCryptobox = 10;

    double t_cryptobox = 5;

    public FullAutoSequence(Team team, Position position) {

        //region Jewels
        addAction(new ServoAction(ServoAction.Servos.ARM, colorArmDownPosition));
        addAction(new WaitAction(1000));
        ColorDetectionAction color = new ColorDetectionAction();
        addAction(new WaitAction(250));
        addAction(color);
        addAction(new JewelDecideAction(color, team));
        addAction(new ServoAction(ServoAction.Servos.ARM, colorArmDownPosition));
        //endregion

        ImageDetectionAction imageDetectionAction = new ImageDetectionAction(
                RobotMoveDirection.FORWARD, s_detectImage);

        if (team == Team.Red) {
            if (position == Position.Top) {
                addAction(new MecanumMoveAction(RobotMoveDirection.FORWARD, m_redTopToCryptobox, s_moveToCryptobox, t_cryptobox));
                addAction(new MecanumRotationAction(90, s_rotation));

            }
        }



    }

}
