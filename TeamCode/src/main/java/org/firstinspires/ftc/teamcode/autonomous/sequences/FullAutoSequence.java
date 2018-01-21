package org.firstinspires.ftc.teamcode.autonomous.sequences;

import org.firstinspires.ftc.teamcode.action.ColorDetectionAction;
import org.firstinspires.ftc.teamcode.action.GlyphAction;
import org.firstinspires.ftc.teamcode.action.ImageDetectionAction;
import org.firstinspires.ftc.teamcode.action.JewelDecideAction;
import org.firstinspires.ftc.teamcode.action.MecanumMoveAction;
import org.firstinspires.ftc.teamcode.action.MecanumRotationAction;
import org.firstinspires.ftc.teamcode.action.ServoAction;
import org.firstinspires.ftc.teamcode.action.WaitAction;
import org.firstinspires.ftc.teamcode.util.ActionSequence;
import org.firstinspires.ftc.teamcode.util.RobotHardware;
import org.firstinspires.ftc.teamcode.util.RobotHardware.RobotMoveDirection;
import org.firstinspires.ftc.teamcode.util.RobotHardware.Team;
import org.firstinspires.ftc.teamcode.util.RobotHardware.Position;

/**
 * Created by djfigs1 on 1/19/18.
 */

public class FullAutoSequence extends ActionSequence {

    double colorArmDownPosition = 0.15;
    double colorArmUpPosition = 1;

    float s_detectImage = 0.25f;
    float s_rotation = 0.2f;
    float s_imageDetect = 0.1f;
    float s_moveToCryptobox = 0.25f;

    double m_topToCryptobox = 36;
    double m_bottomToCryptobox_1 = 24;
    double m_bottomToCryptobox_2 = 12;
    double m_imageDetect = 10;

    double t_imageDetect = 2;
    double t_cryptobox = 5;
    double t_liftTime = 3500;

    double endInitTime;

    public FullAutoSequence(Team team, Position position) {
        //region Jewels
        addAction(new ServoAction(ServoAction.Servos.ARM, colorArmDownPosition));
        addAction(new WaitAction(1000));
        ColorDetectionAction color = new ColorDetectionAction();
        addAction(new WaitAction(250));
        addAction(color);
        addAction(new JewelDecideAction(color, team));
        addAction(new ServoAction(ServoAction.Servos.ARM, colorArmUpPosition));
        //endregion

        ImageDetectionAction imageDetectionAction = new ImageDetectionAction();

        if (team == Team.Red) {
            if (position == Position.Top) {
                addAction(new MecanumMoveAction(RobotMoveDirection.FORWARD, m_imageDetect, s_imageDetect, t_imageDetect));
                addAction(imageDetectionAction);
                addAction(new MecanumMoveAction(RobotMoveDirection.FORWARD, m_topToCryptobox - m_imageDetect, s_moveToCryptobox, t_cryptobox));
                addAction(new MecanumRotationAction(90, s_rotation));
                addAction(new GlyphAction(imageDetectionAction));
            } else {
                addAction(new MecanumMoveAction(RobotMoveDirection.FORWARD, m_imageDetect, s_imageDetect, t_imageDetect));
                addAction(imageDetectionAction);
                addAction(new MecanumMoveAction(RobotMoveDirection.BACKWARD, m_bottomToCryptobox_1 - m_imageDetect, s_moveToCryptobox, t_cryptobox));
                addAction(new MecanumMoveAction(RobotMoveDirection.RIGHT, m_bottomToCryptobox_2, s_moveToCryptobox, t_cryptobox));
                addAction(new GlyphAction(imageDetectionAction));
            }
        } else {
            if (position == Position.Top) {
                addAction(new MecanumMoveAction(RobotMoveDirection.FORWARD, m_imageDetect, s_imageDetect, t_imageDetect));
                addAction(imageDetectionAction);
                addAction(new MecanumMoveAction(RobotMoveDirection.BACKWARD, m_topToCryptobox + m_imageDetect, s_moveToCryptobox, t_cryptobox));
                addAction(new MecanumRotationAction(90, s_rotation));
                addAction(new GlyphAction(imageDetectionAction));
            } else {
                addAction(new MecanumMoveAction(RobotMoveDirection.FORWARD, m_imageDetect, s_imageDetect, t_imageDetect));
                addAction(imageDetectionAction);
                addAction(new MecanumMoveAction(RobotMoveDirection.BACKWARD, m_bottomToCryptobox_1 + m_imageDetect, s_moveToCryptobox, t_cryptobox));
                addAction(new MecanumRotationAction(180, s_rotation));
                addAction(new MecanumMoveAction(RobotMoveDirection.LEFT, m_bottomToCryptobox_2, s_moveToCryptobox, t_cryptobox));
                addAction(new GlyphAction(imageDetectionAction));
            }
        }
    }

    public void init(RobotHardware hardware) {
        hardware.armServo.setPosition(1);
        hardware.lift_gripServo.setPosition(hardware.m_liftGripClosed);
        endInitTime = System.currentTimeMillis() + t_liftTime;
    }

    public void initLoop(RobotHardware hardware) {
        if (System.currentTimeMillis() < endInitTime) {
            hardware.lift_verticalServo.setPower(-1);
        } else {
            hardware.lift_verticalServo.setPower(0);
        }
    }

}
