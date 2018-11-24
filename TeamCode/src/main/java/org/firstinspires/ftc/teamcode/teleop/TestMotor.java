package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.hardware.TestHardware;

@TeleOp(name="TestMotor")
public class TestMotor extends TestHardware {

    @Override
    public void loop() {
        if (gamepad1.right_stick_y != 0) {
            hex.setPower(gamepad1.right_stick_y);
        } else {hex.setPower(0);}
    }
}
