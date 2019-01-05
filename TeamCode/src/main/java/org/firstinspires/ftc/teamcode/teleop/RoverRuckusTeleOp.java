package org.firstinspires.ftc.teamcode.teleop;

import android.media.MediaPlayer;
import android.os.Environment;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.action.Action;
import org.firstinspires.ftc.teamcode.autonomous.sequences.CollectBlockSequence;
import org.firstinspires.ftc.teamcode.autonomous.sequences.DumpBlockSequence;
import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;
import org.firstinspires.ftc.teamcode.util.ActionSequence;

@TeleOp(name="Manual")
public class RoverRuckusTeleOp extends RoverRuckusHardware {

    final float SPEED = 0.5f;
    final float TRANSFER_IDLE_SPEED = -0.05f;
    final float VERTICAL_POWER = 1f;
    final float VERTICAL_IDLE_POWER = 0.05f;
    final double DUMPER_DEPLOYED_POSTITION = 1;
    final double DUMPER_IDLE_POSITION = 0;

    boolean aPress = false;
    boolean scooperEnabled = false;

    Action action;
    boolean didInit = false;
    int actionNumber = 1;
    ActionSequence actionSequence;
    boolean manualControl = true;

    String filePath = "";

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        if (manualControl) {
            omniDrive.stopDrive();
            omniDrive.dpadMove(gamepad1, SPEED);
            scooperTransferMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            scooperTransferMotor.setPower(TRANSFER_IDLE_SPEED);

            /*if (gamepad2.left_trigger > 0) {
                scooperTransferMotor.setPower(SCOOPER_TRANSFER_SPEED);
            } else if (gamepad2.right_trigger > 0) {
                scooperTransferMotor.setPower(-SCOOPER_TRANSFER_SPEED);
            } else {
                scooperTransferMotor.setPower(0);
            }*/

            if (gamepad2.left_bumper) {
                dumperLeftServo.setPosition(DUMPER_IDLE_POSITION);
                dumperRightServo.setPosition(DUMPER_IDLE_POSITION);
            } else if (gamepad2.right_bumper) {
                dumperLeftServo.setPosition(DUMPER_DEPLOYED_POSTITION);
                dumperRightServo.setPosition(DUMPER_DEPLOYED_POSTITION);
            }

            if (gamepad2.a) {
                if (!aPress) {
                    aPress = true;
                    if (scooperEnabled) {
                        scooperCRServoLeft.setPower(0);
                        scooperCRServoRight.setPower(0);
                    } else {
                        scooperCRServoLeft.setPower(1);
                        scooperCRServoRight.setPower(1);
                    }
                    scooperEnabled = !scooperEnabled;
                }
            } else {
                aPress = false;
            }

            if (gamepad2.dpad_up) {
                dumperVerticalHexMotor.setPower(VERTICAL_POWER);
            } else if (gamepad2.dpad_down) {
                dumperVerticalHexMotor.setPower(-VERTICAL_POWER);
            } else {
                dumperVerticalHexMotor.setPower(VERTICAL_IDLE_POWER);
            }

            if (gamepad2.x) {
                manualControl = false;
                actionSequence = new CollectBlockSequence(false);
            }

            if (gamepad2.b){
                manualControl = false;
                actionSequence = new CollectBlockSequence(true);
            }

            if (gamepad2.y) {
                manualControl = false;
                actionSequence = new DumpBlockSequence();
            }
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

    void prepareForAutoControl() {
        actionNumber = 1;
        didInit = false;
    }

}
