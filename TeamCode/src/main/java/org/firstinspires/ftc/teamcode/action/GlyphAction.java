package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.util.EncoderDrive;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by djfigs1 on 1/19/18.
 */

public class GlyphAction implements Action {

    double m_align = 19;
    double m_forward = 50;
    float s_align = 0.25f;
    float s_forward = 0.25f;
    double t_align = 1000;
    double t_forward = 1500;
    double t_open = 

    public enum GlyphPosition {
        LEFT,
        CENTER,
        RIGHT
    }

    private enum GlyphPlaceStages {
        ALIGN,
        FORWARD,
        PLACE,
        BACKWARD
    }

    GlyphPosition position;
    GlyphPlaceStages currentStage = GlyphPlaceStages.ALIGN;
    EncoderDrive encoderDrive;
    boolean init = true;

    public GlyphAction(GlyphPosition position) {
        this.position = position;
    }

    @Override
    public boolean doAction(RobotHardware hardware) {
        switch (currentStage) {
            case ALIGN:
                //region Align Init
                if (init) {
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
                }
                //endregion

                encoderDrive.run();
                if (!encoderDrive.isBusy) {
                    currentStage = GlyphPlaceStages.PLACE;
                    init = true;
                }
                break;

            case PLACE:

                break;
        }


        return false;
    }
}
