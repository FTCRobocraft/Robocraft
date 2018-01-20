package org.firstinspires.ftc.teamcode.autonomous.sequences;

import org.firstinspires.ftc.teamcode.action.Action;
import org.firstinspires.ftc.teamcode.action.ColorDetectionAction;
import org.firstinspires.ftc.teamcode.action.ServoAction;
import org.firstinspires.ftc.teamcode.action.WaitAction;
import org.firstinspires.ftc.teamcode.util.ActionSequence;
import org.firstinspires.ftc.teamcode.util.EncoderDrive;
import org.firstinspires.ftc.teamcode.util.RobotHardware;
import org.firstinspires.ftc.teamcode.util.RobotHardware.RobotMoveDirection;
import org.firstinspires.ftc.teamcode.util.RobotHardware.Team;
import org.firstinspires.ftc.teamcode.util.RobotHardware.Position;


/**
 * Created by djfigs1 on 10/21/17.
 */
public class JewelActionSequence extends ActionSequence {

    double lowPos = 0;
    double hiPos = 0.7;

    double downPosition = 0.15;
    double upPosition = 1;
    public JewelActionSequence(RobotHardware.Team team) {

       ColorDetectionAction color;
        switch (team) {
            case Red:
                addAction(new ServoAction(ServoAction.Servos.ARM, downPosition));
                addAction(new WaitAction(1000));
                color = new ColorDetectionAction();
                addAction(new WaitAction(250));
                addAction(color);
                addAction(new JewelAction(color, Team.Red));
                break;

            case Blue:
                addAction(new ServoAction(ServoAction.Servos.ARM, downPosition));
                addAction(new WaitAction(1000));
                color = new ColorDetectionAction();
                addAction(new WaitAction(250));
                addAction(color);
                addAction(new JewelAction(color, Team.Blue));
                break;

        }
    }

    public class JewelAction implements Action {

        ColorDetectionAction color;
        Team team;
        boolean init = true;

        final double distance = 2;
        final float speed = 0.3f;
        final double timeout = 2;


        public JewelAction(ColorDetectionAction colorAction, Team team) {
            this.color = colorAction;
            this.team = team;
        }

        public boolean doAction(RobotHardware hardware) {
            EncoderDrive drive = new EncoderDrive(hardware);
            if (init) {
                init = false;
                switch (team) {
                    case Red:
                        if (this.color.r > this.color.b) {
                            drive.setInchesToDrive(RobotMoveDirection.BACKWARD, distance, speed, timeout);
                        } else {
                            drive.setInchesToDrive(RobotMoveDirection.FORWARD, distance, speed, timeout);
                        }
                        break;
                    case Blue:
                        if (this.color.b > this.color.r) {
                            drive.setInchesToDrive(RobotMoveDirection.FORWARD, distance, speed, timeout);
                        } else {
                            drive.setInchesToDrive(RobotMoveDirection.BACKWARD, distance, speed, timeout);
                        }
                        break;
                }
            }
            drive.run();
            return drive.isBusy;
        }
    }
}
