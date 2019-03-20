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

    private final double MINERAL_DISTANCE = 18;
    private final double PUSH_DISTANCE = 14;
    private final double PUSH_TO_CRATER_DISTANCE = 30;
    private final double PUSH_TIMEOUT = 1500;
    private final double PUSH_TO_CRATER_TIMEOUT = 3000;
    private final double STRAFE_TIMEOUT = 750;

    private final float MOVE_SPEED = 0.6f;

    private final double SHIFT_DISTANCE = 4;
    private final float SHIFT_POWER = 0.5f;
    private final double SHIFT_TIMEOUT = 500;

    enum BLOCK_DETECTION_STAGES {
        CHECK_CENTER,
        MOVE_LEFT,
        CHECK_LEFT,
        MOVE_RIGHT,
        SHIFT_LEFT,
        MOVE_FORWARD,
        ROTATE,
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
                    stageAfterPush = MOVE_FORWARD;
                    changeStage(SHIFT_LEFT);
                }

                if (timer.milliseconds() >= DETECTION_TIME) {
                    changeStage(MOVE_LEFT);
                }

                break;

            case MOVE_LEFT:
                if (init) {
                    encoderDrive = new EncoderDrive(((RoverRuckusHardware) hardware).omniDrive);
                    encoderDrive.setInchesToDrive(BaseHardware.Direction.LEFT,
                            MINERAL_DISTANCE,
                            MOVE_SPEED, STRAFE_TIMEOUT);
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
                    stageAfterPush = MOVE_FORWARD;
                    changeStage(SHIFT_LEFT);
                }

                if (timer.milliseconds() >= DETECTION_TIME) {
                    position = RIGHT;
                    changeStage(MOVE_RIGHT);

                }
                break;

            case MOVE_RIGHT:
                if (init) {
                    encoderDrive = new EncoderDrive(((RoverRuckusHardware) hardware).omniDrive);
                    encoderDrive.setInchesToDrive(BaseHardware.Direction.RIGHT, position == RIGHT ?
                            MINERAL_DISTANCE * 2 : MINERAL_DISTANCE, MOVE_SPEED, STRAFE_TIMEOUT * 2);
                    init = false;
                }
                if (!encoderDrive.isBusy) {
                    stageAfterPush = MOVE_LEFT;
                    changeStage(position == RIGHT ? MOVE_FORWARD : END);
                } else {
                    encoderDrive.run(hardware);
                }

                break;

            case SHIFT_LEFT:
                if (init) {
                    encoderDrive = new EncoderDrive(((RoverRuckusHardware) hardware).omniDrive);
                    encoderDrive.setInchesToDrive(BaseHardware.Direction.LEFT, SHIFT_DISTANCE,
                            SHIFT_POWER, SHIFT_TIMEOUT);

                    init = false;
                }
                if (!encoderDrive.isBusy) {
                    changeStage(stageAfterPush);
                } else {
                    encoderDrive.run(hardware);
                }
                break;

            case MOVE_FORWARD:
                if (init) {
                    encoderDrive = new EncoderDrive(((RoverRuckusHardware) hardware).omniDrive);
                    encoderDrive.setInchesToDrive(BaseHardware.Direction.FORWARD,
                            PUSH_TO_CRATER_DISTANCE, MOVE_SPEED, PUSH_TO_CRATER_TIMEOUT);

                    init = false;
                }
                if (!encoderDrive.isBusy) {
                    changeStage(ROTATE);
                } else {
                    encoderDrive.run(hardware);
                }

                break;

            case ROTATE:
                if (init) {
                    encoderDrive = new EncoderDrive(((RoverRuckusHardware) hardware).omniDrive);
                    encoderDrive.setDegreesToDrive(position == RIGHT ? -45 : position == LEFT ? 45 : 0, 0.75f, 500);
                    init = false;
                }
                if (!encoderDrive.isBusy) {
                    changeStage(END);
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
