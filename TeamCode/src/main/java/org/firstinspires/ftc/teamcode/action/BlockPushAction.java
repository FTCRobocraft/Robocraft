package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.hardware.BaseHardware;

import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;
import org.firstinspires.ftc.teamcode.util.EncoderDrive;

import java.util.List;

import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.LABEL_GOLD_MINERAL;

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
    final float ALIGN_SPEED = 0.25f;
    final int CENTER_X = 350;
    final int CENTER_TOLERACE = 50;



    public BlockPushAction(BlockDetectionAction action) {
        this.action = action;
    }

    public void init(BaseHardware hardware) {

    }

    @Override
    public boolean doAction(BaseHardware hardware) {
        if (hardware instanceof RoverRuckusHardware) {
            switch(currentStage) {
                case ALIGN:
                    ((RoverRuckusHardware) hardware).omniDrive.moveLeft(ALIGN_SPEED);

                    // Get all new recognitions
                    List<Recognition> updatedRecognitions = hardware.tfod.getUpdatedRecognitions();

                    if (updatedRecognitions != null) {
                        hardware.telemetry.addData("# Object Detected", updatedRecognitions.size());
                        int goldMineralX = -1;
                        for (Recognition recognition : updatedRecognitions) {
                            if (recognition.getLabel().equals(LABEL_GOLD_MINERAL) && recognition.getConfidence() > 0.8f) {
                                goldMineralX = (int) (recognition.getRight() - (recognition.getWidth() / 2.0f));
                            }
                        }

                        if (goldMineralX > (CENTER_X - CENTER_TOLERACE)) {
                            ((RoverRuckusHardware) hardware).omniDrive.stopDrive();
                            currentStage = BLOCK_PUSH_STAGES.PUSH;
                        }

                        hardware.telemetry.addData("gold x", goldMineralX);
                        hardware.telemetry.update();
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
