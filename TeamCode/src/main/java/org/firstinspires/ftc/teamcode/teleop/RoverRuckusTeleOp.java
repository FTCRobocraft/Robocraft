package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;

@TeleOp(name="Manual")
public class RoverRuckusTeleOp extends RoverRuckusHardware {

    final float SPEED = 0.5f;
    final float SCOOPER_TRANSFER_SPEED = 0.5f;
    final double DUMPER_DEPLOYED_POSTITION = 1;
    final double DUMPER_IDLE_POSITION = 0;

    @Override
    public void loop() {
        omniDrive.dpadMove(gamepad1, SPEED);

        if (gamepad2.left_trigger > 0) {
            scooperTransferMotor.setPower(SCOOPER_TRANSFER_SPEED);
        } else if (gamepad2.left_bumper) {
            scooperTransferMotor.setPower(-SCOOPER_TRANSFER_SPEED);
        } else {
            scooperTransferMotor.setPower(0);
        }

        if (gamepad2.right_trigger > 0) {
            dumperLeftServo.setPosition(DUMPER_DEPLOYED_POSTITION);
            dumperRightServo.setPosition(DUMPER_DEPLOYED_POSTITION);
        } else if (gamepad2.right_bumper) {
            dumperLeftServo.setPosition(DUMPER_IDLE_POSITION);
            dumperRightServo.setPosition(DUMPER_IDLE_POSITION);
        }

        if (gamepad2.a) {
            scooperCRServoLeft.setPower(1);
            scooperCRServoRight.setPower(1);
        } else {
            scooperCRServoLeft.setPower(0);
            scooperCRServoRight.setPower(0);
        }

        dumperVerticalHexMotor.setPower(gamepad2.left_stick_y);
    }

}
