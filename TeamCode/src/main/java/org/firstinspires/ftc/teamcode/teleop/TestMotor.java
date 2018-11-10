package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.hardware.TestHardware;

@TeleOp(name="TestMotor")
public class TestMotor extends TestHardware {

    @Override
    public void loop() {
        if (gamepad1.a) {
            hex.setPower(1);
        } else {
            hex.setPower(0);
        }

        if (gamepad1.y) {
            hex.setPower(-1);
        } else {
            hex.setPower(0);
        }
    }

}
