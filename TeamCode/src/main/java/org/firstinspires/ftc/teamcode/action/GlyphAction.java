package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.util.EncoderDrive;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by djfigs1 on 1/19/18.
 */

public class GlyphAction implements Action {

    double m_align = 8.5;
    double m_forward = 8.5;
    float s_align = 0.25f;
    float s_forward = 0.25f;
    double t_align = 5;
    double t_forward = 5;
    double t_open = 2000;

    double endTime;

    public enum GlyphPosition {
        LEFT,
        CENTER,
        RIGHT
    }

    private enum GlyphPlaceStages {
        ALIGN,
        FORWARD,
        PLACE,
        BACKWARD,
        PUSH,
        BACKWARD2,
        END
    }

    GlyphPosition position;
    GlyphPlaceStages currentStage = GlyphPlaceStages.ALIGN;
    EncoderDrive encoderDrive;
    ImageDetectionAction imageDetectionAction;
    boolean init = true;

    public GlyphAction(ImageDetectionAction imageDetectionAction) {
        this.imageDetectionAction = imageDetectionAction;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        switch (currentStage) {
            case ALIGN:
                //region Align Init
                if (init) {
                    switch (imageDetectionAction.vuMark) {
                        case LEFT:
                            this.position = GlyphPosition.LEFT;
                            break;
                        case CENTER:
                            this.position = GlyphPosition.CENTER;
                            break;
                        case RIGHT:
                            this.position = GlyphPosition.RIGHT;
                            break;
                    }

                    encoderDrive = new EncoderDrive(hardware);
                    switch (position) {
                        case LEFT:
                            encoderDrive.setInchesToDrive(RobotHardware.RobotMoveDirection.LEFT,
                                    m_align, s_align, t_align);
                            break;
                        case CENTER:
                            break;
                        case RIGHT:
                            encoderDrive.setInchesToDrive(RobotHardware.RobotMoveDirection.RIGHT,
                                    m_align, s_align, t_align);
                            break;
                    }
                    init = false;
                }
                //endregion

                encoderDrive.run();
                if (!encoderDrive.isBusy) {
                    currentStage = GlyphPlaceStages.FORWARD;
                    init = true;
                }
                break;

            case FORWARD:
                //region Forward Init
                if (init) {
                    encoderDrive = new EncoderDrive(hardware);
                    encoderDrive.setInchesToDrive(RobotHardware.RobotMoveDirection.FORWARD,
                            m_forward, s_forward, t_forward);
                    init = false;
                }
                //endregion

                encoderDrive.run();
                if (!encoderDrive.isBusy) {
                    currentStage = GlyphPlaceStages.PLACE;
                    init = true;
                }
                break;

            case PLACE:
                if (init) {
                    endTime = System.currentTimeMillis() + t_open;
                    init = false;
                }

                hardware.lift_gripServo.setPosition(hardware.m_liftGripOpen);
                if (System.currentTimeMillis() >= endTime) {
                    currentStage = GlyphPlaceStages.BACKWARD;
                    init = true;
                }
                break;

            case BACKWARD:
                if (init) {
                    encoderDrive = new EncoderDrive(hardware);
                    encoderDrive.setInchesToDrive(RobotHardware.RobotMoveDirection.BACKWARD, m_forward, s_forward, t_forward);
                    init = false;
                }

                encoderDrive.run();
                if (!encoderDrive.isBusy) {
                    currentStage = GlyphPlaceStages.PUSH;
                    init = true;
                }
                break;

            case PUSH:
                if (init) {
                    hardware.lift_gripServo.setPosition(hardware.m_liftGripClosed);
                    encoderDrive = new EncoderDrive(hardware);
                    encoderDrive.setInchesToDrive(RobotHardware.RobotMoveDirection.FORWARD, m_forward, s_forward, t_forward);
                    init = false;
                }

                encoderDrive.run();
                if (!encoderDrive.isBusy) {
                    currentStage = GlyphPlaceStages.BACKWARD2;
                    init = true;
                }
                break;

            case BACKWARD2:
                if (init) {
                    encoderDrive = new EncoderDrive(hardware);
                    encoderDrive.setInchesToDrive(RobotHardware.RobotMoveDirection.BACKWARD, m_forward, s_forward, t_forward);
                    init = false;
                }

                encoderDrive.run();
                if (!encoderDrive.isBusy) {
                    currentStage = GlyphPlaceStages.END;
                    init = true;
                }
                break;


            case END:
                return true;
        }


        return false;
    }
}
