package org.firstinspires.ftc.teamcode.autonomous.sequences;

import org.firstinspires.ftc.teamcode.action.BlockPushAction;
import org.firstinspires.ftc.teamcode.action.EncoderToPositionAction;
import org.firstinspires.ftc.teamcode.action.KeepScooperUnderPowerAction;
import org.firstinspires.ftc.teamcode.action.LandAction;
import org.firstinspires.ftc.teamcode.action.MecanumMoveAction;
import org.firstinspires.ftc.teamcode.action.MecanumRotationAction;
import org.firstinspires.ftc.teamcode.hardware.BaseHardware;
import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;
import org.firstinspires.ftc.teamcode.util.ActionSequence;
import org.firstinspires.ftc.teamcode.hardware.BaseHardware.Direction;

public class RoverRuckusSequence extends ActionSequence {

    public final float MOVE_SPEED = 0.75f;
    public final float MOVE_DEPOT_SPEED = 0.7f;
    public final float ROTATE_SPEED = 0.5f;
    public final float TRANSFER_SPEED = 0.25f;

    public final double LANDER_TO_MINERALS = 13;
    public final int TRANSFER_MOTOR_POSITION = 900;

    public final double HOOK_DISTANCE = 6;
    public final double TC_TO_WALL = 58;
    public final int TC_DEGREES_TO_DEPOT = 60;
    public final double TC_TO_DEPOT = 34;
    public final double TC_DEPOT_TO_CRATER = 64;

    public final double TD_TO_WALL = 58;
    public final int TD_DEGREES_TO_DEPOT = -170;
    public final double TD_TO_DEPOT = 52;
    public final double TD_DEPOT_TO_CRATER = 62;

    public final float TRANSFER_TIMEOUT = 1000;
    public final double SHIFT_DISTANCE = 10;
    public final double NUDGE_DISTANCE = 2;
    public final int ROTATION_CORRECTION = -10;

    public RoverRuckusSequence(RoverRuckusHardware.RoverRuckusStartingPosition startingPosition) {
            addAction(new KeepScooperUnderPowerAction());

            switch (startingPosition) {
                case TOWARDS_DEPOT:
                    addAction(new KeepScooperUnderPowerAction());

                    // DONT LAAAANNDDD
                    //addAction(new LandAction());

                    // Get out of the hook
                    /*addAction(new MecanumMoveAction(Direction.LEFT,
                            HOOK_DISTANCE, MOVE_SPEED, 1000));

                    addAction(new MecanumMoveAction(Direction.FORWARD,
                            NUDGE_DISTANCE, MOVE_SPEED, 250));

                    addAction(new MecanumRotationAction(ROTATION_CORRECTION, 0.6f));
                    */

                    // Creep up towards the minerals so we can detect them better
                    addAction(new MecanumMoveAction(BaseHardware.Direction.FORWARD,
                            LANDER_TO_MINERALS, MOVE_SPEED, 1000));

                    /*addAction(new MecanumMoveAction(Direction.RIGHT,
                            SHIFT_DISTANCE, MOVE_SPEED, 500));*/

                    // Push the gold mineral
                    addAction(new BlockPushAction(15000));

                    // Place the team marker by dropping the scooper
                    addAction(new EncoderToPositionAction("scooperTransferMotor",
                            TRANSFER_MOTOR_POSITION, TRANSFER_SPEED, TRANSFER_TIMEOUT));
                    break;

                case TOWARDS_CRATER:
                    addAction(new KeepScooperUnderPowerAction());

                   /* // LAAAANNDDD
                    addAction(new LandAction());

                    // Get out of the hook
                    addAction(new MecanumMoveAction(Direction.LEFT,
                            HOOK_DISTANCE, MOVE_SPEED, 1000));

                    addAction(new MecanumMoveAction(Direction.FORWARD,
                            NUDGE_DISTANCE, MOVE_SPEED, 250));


                    addAction(new MecanumRotationAction(ROTATION_CORRECTION, 0.5f));
                    */

                    // Creep up towards the minerals so we can detect them better
                    addAction(new MecanumMoveAction(BaseHardware.Direction.FORWARD,
                            LANDER_TO_MINERALS, MOVE_SPEED, 1000));

                    /*addAction(new MecanumMoveAction(Direction.RIGHT,
                            SHIFT_DISTANCE, 0.6f, 500));*/

                    // Push the gold mineral
                    addAction(new BlockPushAction(15000));
                    break;
            }
    }
}
