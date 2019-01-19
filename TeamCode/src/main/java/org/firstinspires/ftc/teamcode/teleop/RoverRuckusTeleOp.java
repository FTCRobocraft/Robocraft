package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.action.Action;
import org.firstinspires.ftc.teamcode.autonomous.sequences.CollectBlockSequence;
import org.firstinspires.ftc.teamcode.autonomous.sequences.DumpBlockSequence;
import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;
import org.firstinspires.ftc.teamcode.util.ActionSequence;

@TeleOp(name="Manual")
public class RoverRuckusTeleOp extends RoverRuckusHardware {

    final float SPEED = 1f;
    final float SLOW_SPEED = 0.5f;

    final float TRANSFER_IDLE_SPEED = -0.05f;


    boolean a1Press = false;
    boolean x1Press = false;

    boolean a2Press = false;
    boolean x2Press = false;

    boolean slowMode = true;
    boolean reverseMode = false;
    boolean scooperEnabled = false;

    Action action;
    boolean didInit = false;
    int actionNumber = 1;
    ActionSequence actionSequence;
    boolean manualControl = true;
    boolean scooperUp = true;

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        if (manualControl) {
            omniDrive.stopDrive();
            scooperTransferMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            scooperTransferMotor.setPower(TRANSFER_IDLE_SPEED);

            // region Gamepad 1
            omniDrive.dpadMove(gamepad1, slowMode ? SLOW_SPEED : SPEED, reverseMode);

            if (gamepad1.a) {
                if (!a1Press) {
                    slowMode = !slowMode;
                    a1Press = true;
                }
            } else {
                a1Press = false;
            }

            if (gamepad1.x) {
                if (!x1Press) {
                    reverseMode = !reverseMode;
                    x1Press = true;
                }
            } else {
                x1Press = false;
            }
            //endregion

            //region Gamepad 2
            if (gamepad2.a) {
                if (!a2Press && !scooperUp) {
                    a2Press = true;
                    if (scooperEnabled) {
                        scooperHexMotor.setPower(0);
                    } else {
                        scooperHexMotor.setPower(1);
                    }
                    scooperEnabled = !scooperEnabled;
                }
            } else {
                a2Press = false;
            }

            if (gamepad2.x) {
                if (!x2Press && !scooperUp) {
                    x2Press = true;
                    if (scooperEnabled) {
                        scooperHexMotor.setPower(0);
                    } else {
                        scooperHexMotor.setPower(-1);
                    }
                    scooperEnabled = !scooperEnabled;
                }
            } else {
                x2Press = false;
            }

            if (gamepad2.b){
                prepareForAutoControl(new CollectBlockSequence(scooperUp));
                scooperUp = !scooperUp;
            }

            if (gamepad2.y) {
                prepareForAutoControl(new DumpBlockSequence(scooperUp));
                scooperUp = true;
            }
            //endregion
        } else {
            action = actionSequence.getCurrentAction();

            if (action != null) {
                if (!didInit) {
                    action.init(this);
                    didInit = true;
                }

                if (action.doAction(this)) {
                    actionSequence.currentActionComplete();
                    action = actionSequence.getCurrentAction();
                    actionNumber++;
                    didInit = false;
                } else {
                    telemetry.addData("Progress", "%d/%d, %d%%", actionNumber, actionSequence.numberOfActions(),
                            (int) ((double) actionNumber / (double) actionSequence.numberOfActions() * 100.0));
                    telemetry.addData("Current Action", action.getClass().getSimpleName());
                }
            } else {
                manualControl = true;
            }
        }

    }

    void prepareForAutoControl(ActionSequence sequence) {
        omniDrive.stopDrive();
        this.actionSequence = sequence;
        manualControl = false;
        actionNumber = 1;
        didInit = false;
    }

}
