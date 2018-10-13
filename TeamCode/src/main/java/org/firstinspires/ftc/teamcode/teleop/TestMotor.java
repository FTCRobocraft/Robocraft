package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

@TeleOp(name="TestMotor")
public class TestMotor extends RobotHardware {

    @Override
    public void loop() {

        if (gamepad1.y) {
            hex.setPower(1);
        } else {
            hex.setPower(0);
        }

        if (gamepad1.b) {
            hex.setPower(-1);
        } else {
            hex.setPower(0);
        }

        if (gamepad1.x) {
            lift_verticalServo.setPower(1);
        } else {
            lift_verticalServo.setPower(0);
        }
        if (gamepad1.a) {
            lift_verticalServo.setPower(-1);
        } else {
            lift_verticalServo.setPower(0);
        }
    }

}