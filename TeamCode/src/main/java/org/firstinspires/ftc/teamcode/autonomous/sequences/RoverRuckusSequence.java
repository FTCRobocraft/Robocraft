package org.firstinspires.ftc.teamcode.autonomous.sequences;

import org.firstinspires.ftc.teamcode.action.Action;
import org.firstinspires.ftc.teamcode.action.BlockPushAction;
import org.firstinspires.ftc.teamcode.action.EncoderToPositionAction;
import org.firstinspires.ftc.teamcode.action.IfAction;
import org.firstinspires.ftc.teamcode.action.KeepScooperUnderPowerAction;
import org.firstinspires.ftc.teamcode.action.MecanumMoveAction;
import org.firstinspires.ftc.teamcode.action.MecanumRotationAction;
import org.firstinspires.ftc.teamcode.action.PlaceTeamMarkerAction;
import org.firstinspires.ftc.teamcode.hardware.BaseHardware;
import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;
import org.firstinspires.ftc.teamcode.util.ActionSequence;

public class RoverRuckusSequence extends ActionSequence {

    public final float MOVE_SPEED = 0.4f;
    public final float MOVE_DEPOT_SPEED = 0.7f;
    public final float ROTATE_SPEED = 0.5f;
    public final float TRANSFER_SPEED = 0.25f;

    public final double CRATER_TO_MINERALS = 13;
    public final int TRANSFER_MOTOR_POSITION = 900;

    public final double TC_TO_WALL = 58;
    public final int TC_DEGREES_TO_DEPOT = 60;
    public final double TC_TO_DEPOT = 34;
    public final double TC_DEPOT_TO_CRATER = 64;

    public final double TD_TO_WALL = 58;
    public final int TD_DEGREES_TO_DEPOT = -170;
    public final double TD_TO_DEPOT = 52;
    public final double TD_DEPOT_TO_CRATER = 62;

    public final float TRANSFER_TIMEOUT = 1000;

    public RoverRuckusSequence(RoverRuckusHardware.RoverRuckusStartingPosition startingPosition) {
            addAction(new KeepScooperUnderPowerAction());

            switch (startingPosition) {
                case TOWARDS_DEPOT:
                    // Creep up towards the minerals so we can detect them better
                    addAction(new MecanumMoveAction(BaseHardware.Direction.BACKWARD,
                            CRATER_TO_MINERALS, MOVE_SPEED, 1000));

                    // Push the gold mineral
                    addAction(new BlockPushAction(15000));

                    // Move towards the wall
                    addAction(new MecanumMoveAction(BaseHardware.Direction.RIGHT,
                            TD_TO_WALL, MOVE_SPEED, 4000));

                    // Rotate towards the depot
                    addAction(new MecanumRotationAction(TD_DEGREES_TO_DEPOT, ROTATE_SPEED));

                    // Better align to wall
                    addAction(new MecanumMoveAction(BaseHardware.Direction.LEFT,
                            8, 0.75f,
                            1250));

                    // Move towards the depot
                    addAction(new MecanumMoveAction(BaseHardware.Direction.FORWARD,
                            TD_TO_DEPOT, MOVE_DEPOT_SPEED, 3000));

                    // Place the team marker by dropping the scooper
                    addAction(new EncoderToPositionAction("scooperTransferMotor",
                            TRANSFER_MOTOR_POSITION, TRANSFER_SPEED, TRANSFER_TIMEOUT));

                    // Retract the scooper
                    addAction(new EncoderToPositionAction("scooperTransferMotor",
                            -TRANSFER_MOTOR_POSITION, TRANSFER_SPEED, TRANSFER_TIMEOUT));

                    // Make sure the scooper doesn't fall
                    addAction(new KeepScooperUnderPowerAction());

                    // Better align to wall
                    addAction(new MecanumMoveAction(BaseHardware.Direction.LEFT,
                            8, 0.75f,
                            1250));

                    // Park in the crater
                    addAction(new MecanumMoveAction(BaseHardware.Direction.BACKWARD,
                            TD_DEPOT_TO_CRATER, 0.5f, 3000));
                    break;

                case TOWARDS_CRATER:
                    // Creep up towards the minerals so we can detect them better
                    addAction(new MecanumMoveAction(BaseHardware.Direction.BACKWARD,
                            CRATER_TO_MINERALS, MOVE_SPEED, 1000));

                    // Push the gold mineral
                    addAction(new BlockPushAction(15000));

                    // Move towards the wall
                    addAction(new MecanumMoveAction(BaseHardware.Direction.RIGHT,
                            TC_TO_WALL, MOVE_SPEED, 4000));

                    // Rotate towards the depot
                    addAction(new MecanumRotationAction(TC_DEGREES_TO_DEPOT, ROTATE_SPEED));

                    addAction(new MecanumMoveAction(BaseHardware.Direction.RIGHT,
                            8, 0.4f, 1250));

                    // Move towards the depot
                    addAction(new MecanumMoveAction(BaseHardware.Direction.FORWARD,
                            TC_TO_DEPOT, MOVE_DEPOT_SPEED, 3000));

                    // Place the team marker by dropping the scooper
                    addAction(new EncoderToPositionAction("scooperTransferMotor",
                            TRANSFER_MOTOR_POSITION, TRANSFER_SPEED, TRANSFER_TIMEOUT));

                    // Retract the scooper
                    addAction(new EncoderToPositionAction("scooperTransferMotor",
                            -TRANSFER_MOTOR_POSITION, TRANSFER_SPEED, TRANSFER_TIMEOUT));

                    // Make sure the scooper doesn't fall
                    addAction(new KeepScooperUnderPowerAction());

                    // Park in the crater
                    addAction(new MecanumMoveAction(BaseHardware.Direction.BACKWARD,
                            TC_DEPOT_TO_CRATER, MOVE_DEPOT_SPEED, 3000));
                    break;
            }
    }
}
