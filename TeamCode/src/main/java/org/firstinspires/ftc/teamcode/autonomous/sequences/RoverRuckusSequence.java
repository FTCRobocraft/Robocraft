package org.firstinspires.ftc.teamcode.autonomous.sequences;

import org.firstinspires.ftc.teamcode.action.Action;
import org.firstinspires.ftc.teamcode.action.BlockDetectionAction;
import org.firstinspires.ftc.teamcode.action.IfAction;
import org.firstinspires.ftc.teamcode.action.LandRobotAction;
import org.firstinspires.ftc.teamcode.action.MecanumMoveAction;
import org.firstinspires.ftc.teamcode.action.MecanumRotationAction;
import org.firstinspires.ftc.teamcode.action.PlaceTeamMarkerAction;
import org.firstinspires.ftc.teamcode.hardware.BaseHardware;
import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;
import org.firstinspires.ftc.teamcode.util.ActionSequence;

public class RoverRuckusSequence extends ActionSequence {

    // Speeds
    public final float S_ROTATION = 0.5f;
    public final float S_MOVE = 0.5f;

    // Distances
    public final double D_LANDER_TO_MINERALS = 30;
    public final double D_BETWEEN_MINERALS = 39.5;
    public final double D_MINERAL_PUSH = 10;

    public final double D_MINERAL_TO_MIDLINE = 107.8;
    public final double D_MIDLINE_TO_DEPOT = 122.4;
    public final double D_DEPOT_TO_PARK = 200;

    public final double D_MINERAL_TO_WALL = 107.8;
    public final double D_WALL_TO_DEPOT = 121.9;

    // Timeouts
    public final double T_MOVE = 5000;
    public final double T_BLOCK_DETECT = 5000;

    ActionSequence leftActionSequence = new ActionSequence(new Action[] {
            // Move left towards the mineral
            new MecanumMoveAction(BaseHardware.Direction.LEFT, D_BETWEEN_MINERALS, S_MOVE, T_MOVE),

            // Push the mineral
            new MecanumMoveAction(BaseHardware.Direction.FORWARD, D_MINERAL_PUSH, S_MOVE, T_MOVE),

            // Backup from the mineral
            new MecanumMoveAction(BaseHardware.Direction.BACKWARD, D_MINERAL_PUSH, S_MOVE, T_MOVE),

            // Move right towards the center position
            new MecanumMoveAction(BaseHardware.Direction.RIGHT, D_BETWEEN_MINERALS, S_MOVE, T_MOVE)
    });

    ActionSequence centerActionSequence = new ActionSequence(new Action[] {
            // Push the mineral
            new MecanumMoveAction(BaseHardware.Direction.FORWARD, D_MINERAL_PUSH, S_MOVE, T_MOVE),

            // Backup from the mineral
            new MecanumMoveAction(BaseHardware.Direction.BACKWARD, D_MINERAL_PUSH, S_MOVE, T_MOVE)
    });

    ActionSequence rightActionSequence = new ActionSequence(new Action[] {
            // Move right towards the mineral
            new MecanumMoveAction(BaseHardware.Direction.RIGHT, D_BETWEEN_MINERALS, S_MOVE, T_MOVE),

            // Push the mineral
            new MecanumMoveAction(BaseHardware.Direction.FORWARD, D_MINERAL_PUSH, S_MOVE, T_MOVE),

            // Backup from the mineral
            new MecanumMoveAction(BaseHardware.Direction.BACKWARD, D_MINERAL_PUSH, S_MOVE, T_MOVE),

            // Move left towards the center position
            new MecanumMoveAction(BaseHardware.Direction.LEFT, D_BETWEEN_MINERALS, S_MOVE, T_MOVE)
    });

    BlockDetectionAction blockDetectionAction = new BlockDetectionAction(T_BLOCK_DETECT);


    public RoverRuckusSequence(RoverRuckusHardware.RoverRuckusStartingPosition startingPosition) {
            switch (startingPosition) {
                case TOWARDS_DEPOT:
                    // TODO: Land the robot
                    addAction(new LandRobotAction()); // does nothing as of now

                    //region Minerals

                    // TODO: Move to minerals
                    // Rotate 180 degrees to face minerals
                    addAction(new MecanumRotationAction(180, S_ROTATION));

                    // Move towards the minerals
                    addAction(new MecanumMoveAction(BaseHardware.Direction.FORWARD, D_LANDER_TO_MINERALS,
                            S_MOVE, T_MOVE));

                    //TODO: Pick correct mineral (gold)
                    // Attempt to try to detect the minerals
                    addAction(blockDetectionAction);

                    // Execute the following action sequence if the block is on the left:
                    addAction(new IfAction((hardware) -> blockDetectionAction.position == RoverRuckusHardware.GOLD_MINERAL_POSITION.LEFT,
                            leftActionSequence, null));

                    // Execute the following action sequence if the block is right in front of us:
                    addAction(new IfAction((hardware) -> blockDetectionAction.position == RoverRuckusHardware.GOLD_MINERAL_POSITION.CENTER,
                            centerActionSequence, null));

                    // Execute the following action sequence if the block is on the right:
                    addAction(new IfAction((hardware) -> blockDetectionAction.position == RoverRuckusHardware.GOLD_MINERAL_POSITION.RIGHT,
                            rightActionSequence, null));
                    //endregion

                    //region Team Marker
                    //TODO: Move to team marker bin
                    addAction(new MecanumRotationAction(-90, S_ROTATION));
                    addAction(new MecanumMoveAction(BaseHardware.Direction.LEFT, D_MINERAL_TO_MIDLINE, S_MOVE, T_MOVE));
                    addAction(new MecanumRotationAction(315, S_ROTATION));
                    addAction(new MecanumMoveAction(BaseHardware.Direction.FORWARD, D_MIDLINE_TO_DEPOT, S_MOVE, T_MOVE));

                    //TODO: Place team marker
                    addAction(new PlaceTeamMarkerAction());
                    //endregion

                    //region Parking
                    //TODO: Move to parking zone and park
                    addAction(new MecanumRotationAction(180, S_ROTATION));
                    addAction(new MecanumMoveAction(BaseHardware.Direction.FORWARD, D_DEPOT_TO_PARK, S_MOVE, T_MOVE));
                    //endregion

                    break;

                case TOWARDS_CRATER:
                    // TODO: Land the robot
                    addAction(new LandRobotAction()); // does nothing as of now

                    //region Minerals

                    // TODO: Move to minerals
                    // Rotate 180 degrees to face minerals
                    addAction(new MecanumRotationAction(180, S_ROTATION));

                    // Move towards the minerals
                    addAction(new MecanumMoveAction(BaseHardware.Direction.FORWARD, D_LANDER_TO_MINERALS,
                            S_MOVE, T_MOVE));

                    //TODO: Pick correct mineral (gold)
                    // Attempt to try to detect the minerals
                    addAction(blockDetectionAction);

                    // Execute the following action sequence if the block is on the left:
                    addAction(new IfAction((hardware) -> blockDetectionAction.position == RoverRuckusHardware.GOLD_MINERAL_POSITION.LEFT,
                            leftActionSequence, null));

                    // Execute the following action sequence if the block is right in front of us:
                    addAction(new IfAction((hardware) -> blockDetectionAction.position == RoverRuckusHardware.GOLD_MINERAL_POSITION.CENTER,
                            centerActionSequence, null));

                    // Execute the following action sequence if the block is on the right:
                    addAction(new IfAction((hardware) -> blockDetectionAction.position == RoverRuckusHardware.GOLD_MINERAL_POSITION.RIGHT,
                            rightActionSequence, null));
                    //endregion

                    //region Team Marker
                    //TODO: Move to team marker bin
                    addAction(new MecanumRotationAction(-90, S_ROTATION));
                    addAction(new MecanumMoveAction(BaseHardware.Direction.LEFT, D_MINERAL_TO_WALL, S_MOVE, T_MOVE));
                    addAction(new MecanumRotationAction(-45, S_ROTATION));
                    addAction(new MecanumMoveAction(BaseHardware.Direction.FORWARD, D_WALL_TO_DEPOT, S_MOVE, T_MOVE));

                    //TODO: Place team marker
                    addAction(new PlaceTeamMarkerAction());
                    //endregion

                    //region Parking
                    //TODO: Move to parking zone and park
                    addAction(new MecanumRotationAction(180, S_ROTATION));
                    addAction(new MecanumMoveAction(BaseHardware.Direction.FORWARD, D_DEPOT_TO_PARK, S_MOVE, T_MOVE));
                    //endregion

                    break;
            }
    }
}
