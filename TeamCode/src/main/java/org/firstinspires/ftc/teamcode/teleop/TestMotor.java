package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.hardware.TestHardware;

@TeleOp(name="TestMotor")
@Disabled
public class TestMotor extends TestHardware {

    @Override
    public void loop() {
        hex.setPower(gamepad1.right_stick_y);
    }
}