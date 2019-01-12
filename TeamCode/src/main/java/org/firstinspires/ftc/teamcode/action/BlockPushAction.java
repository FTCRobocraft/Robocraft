package org.firstinspires.ftc.teamcode.action;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.hardware.BaseHardware;
import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;
import org.firstinspires.ftc.teamcode.util.EncoderDrive;

import java.util.List;

import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.LABEL_GOLD_MINERAL;
import static org.firstinspires.ftc.teamcode.action.BlockPushAction.BLOCK_DETECTION_STAGES.*;
import static org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware.GOLD_MINERAL_POSITION.*;

public class BlockPushAction implements Action {

    public RoverRuckusHardware.GOLD_MINERAL_POSITION position = RoverRuckusHardware.GOLD_MINERAL_POSITION.UNKNOWN;
    double timeout;
    double endTime;
    int goldMineralX;
    private final double DETECTION_TIME = 1750;
    private final double CONFIDENCE = 0.8;

    private final double MINERAL_DISTANCE = 17;
    private final double PUSH_DISTANCE = 12;
    private final double PUSH_TIMEOUT = 1500;

    enum BLOCK_DETECTION_STAGES {
        CHECK_CENTER,
        MOVE_LEFT,
        CHECK_LEFT,
        MOVE_RIGHT,
        MOVE_FORWARD,
        MOVE_BACKWARD,
        END
    }
    ElapsedTime timer;
    boolean init = true;
    EncoderDrive encoderDrive;

    private BLOCK_DETECTION_STAGES currentStage = BLOCK_DETECTION_STAGES.CHECK_CENTER;
    private BLOCK_DETECTION_STAGES stageAfterPush = BLOCK_DETECTION_STAGES.END;


    public BlockPushAction(double timeout) {
        this.timeout = timeout;
    }

    @Override
    public void init(BaseHardware hardware) {
        timer = new ElapsedTime();
        endTime = System.currentTimeMillis() + timeout;
    }

    private boolean goldBlockDetected(RoverRuckusHardware hardware) {
        List<Recognition> updatedRecognitions = hardware.tfod.getUpdatedRecognitions();

        // Check if anything new is detected
        if (updatedRecognitions != null) {
            hardware.telemetry.addData("# Object Detected", updatedRecognitions.size());
            // Check if all three objects are detected
            if (updatedRecognitions.size() > 0) {
                // Get X coordinate of each recognition
                for (Recognition recognition : updatedRecognitions) {
                    if (recognition.getLabel().equals(LABEL_GOLD_MINERAL) && recognition.getConfidence() >= CONFIDENCE) {
                        goldMineralX = (int) recognition.getLeft();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean doAction(BaseHardware hardware) {
        // Get all new recognitions
        switch (currentStage) {
            case CHECK_CENTER:
                if (init) {
                    timer.reset();
                    init = false;
                }

                if (goldBlockDetected((RoverRuckusHardware) hardware)) {
                    position = CENTER;
                    changeStage(MOVE_FORWARD);
                }

                if (timer.milliseconds() >= DETECTION_TIME) {
                    changeStage(MOVE_LEFT);
                }

                break;

            case MOVE_LEFT:
                if (init) {
                    encoderDrive = new EncoderDrive(((RoverRuckusHardware) hardware).omniDrive);
                    encoderDrive.setInchesToDrive(BaseHardware.Direction.RIGHT,
                            MINERAL_DISTANCE, 0.4f, 2000);
                    init = false;
                }
                if (!encoderDrive.isBusy) {
                    changeStage(position == RIGHT ? END : CHECK_LEFT);
                } else {
                    encoderDrive.run(hardware);
                }

                break;

            case CHECK_LEFT:
                if (init) {
                    timer.reset();
                    init = false;
                }

                if (goldBlockDetected((RoverRuckusHardware) hardware)) {
                    position = LEFT;
                    stageAfterPush = MOVE_RIGHT;
                    changeStage(MOVE_FORWARD);
                }

                if (timer.milliseconds() >= DETECTION_TIME) {
                    position = RIGHT;
                    changeStage(MOVE_RIGHT);

                }
                break;

            case MOVE_RIGHT:
                if (init) {
                    encoderDrive = new EncoderDrive(((RoverRuckusHardware) hardware).omniDrive);
                    encoderDrive.setInchesToDrive(BaseHardware.Direction.LEFT, position == RIGHT ?
                            MINERAL_DISTANCE * 2: MINERAL_DISTANCE, 0.4f, 2000);
                    init = false;
                }
                if (!encoderDrive.isBusy) {
                    stageAfterPush = MOVE_LEFT;
                    changeStage(position == RIGHT ? MOVE_FORWARD : END);
                } else {
                    encoderDrive.run(hardware);
                }

                break;

            case MOVE_FORWARD:
                if (init) {
                    encoderDrive = new EncoderDrive(((RoverRuckusHardware) hardware).omniDrive);
                    encoderDrive.setInchesToDrive(BaseHardware.Direction.BACKWARD,
                            PUSH_DISTANCE, 0.4f, PUSH_TIMEOUT);
                    init = false;
                }
                if (!encoderDrive.isBusy) {
                    changeStage(MOVE_BACKWARD);
                } else {
                    encoderDrive.run(hardware);
                }

                break;

            case MOVE_BACKWARD:
                if (init) {
                    encoderDrive = new EncoderDrive(((RoverRuckusHardware) hardware).omniDrive);
                    encoderDrive.setInchesToDrive(BaseHardware.Direction.FORWARD,
                            PUSH_DISTANCE, 0.4f, PUSH_TIMEOUT);
                    init = false;
                }
                if (!encoderDrive.isBusy) {
                    changeStage(stageAfterPush);
                } else {
                    encoderDrive.run(hardware);
                }

                break;

            case END:
                return true;
        }


        return System.currentTimeMillis() > endTime;
    }

    private void changeStage(BLOCK_DETECTION_STAGES stage) {
        this.currentStage = stage;
        init = true;
    }

}
