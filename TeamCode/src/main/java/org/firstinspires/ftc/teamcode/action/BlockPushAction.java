package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.hardware.BaseHardware;

import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;
import org.firstinspires.ftc.teamcode.util.EncoderDrive;

public class BlockPushAction implements Action {

    enum BLOCK_PUSH_STAGES {
        ALIGN,
        PUSH
    }

    BLOCK_PUSH_STAGES currentStage = BLOCK_PUSH_STAGES.ALIGN;
    BlockDetectionAction action;
    EncoderDrive encoderDrive;
    boolean init = true;

    final double DISTANCE_BETWEEN_TAPE = 39.5;
    final double DISTANCE_PUSH = 10;
    final float POWER = 0.5f;
    final double TIMEOUT = 5;



    public BlockPushAction(BlockDetectionAction action) {
        this.action = action;
    }

    @Override
    public boolean doAction(BaseHardware hardware) {
        if (hardware instanceof RoverRuckusHardware) {
            switch(currentStage) {
                case ALIGN:
                    if (init) {
                        encoderDrive = new EncoderDrive(((RoverRuckusHardware) hardware).omniDrive);

                        switch (action.position) {
                            case LEFT:
                                encoderDrive.setInchesToDrive(BaseHardware.Direction.LEFT,
                                        DISTANCE_BETWEEN_TAPE, POWER, TIMEOUT);
                                break;

                            case CENTER:
                                break;

                            case RIGHT:
                                encoderDrive.setInchesToDrive(BaseHardware.Direction.LEFT,
                                        DISTANCE_BETWEEN_TAPE, POWER, TIMEOUT);
                                break;

                            case UNKNOWN:
                                // idk lol we're screwed if we get here so might as well die.
                                break;
                        }
                        init = false;
                    }

                    if (!encoderDrive.isBusy) {
                        encoderDrive.run(hardware);
                    } else {
                        init = true;
                        currentStage = BLOCK_PUSH_STAGES.PUSH;
                    }
                    break;

                case PUSH:
                    if (init) {
                        encoderDrive = new EncoderDrive(((RoverRuckusHardware) hardware).omniDrive);
                        encoderDrive.setInchesToDrive(BaseHardware.Direction.FORWARD,
                                DISTANCE_PUSH, POWER, TIMEOUT);
                        init = false;
                    }

                    if (!encoderDrive.isBusy) {
                        encoderDrive.run(hardware);
                    } else {
                        return true;
                    }
                    break;

            }
        }
        return false;
    }
}
