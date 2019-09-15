package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.hardware.TestHardware;

@TeleOp(name="TestMotor")
public class TestMotor extends TestHardware {

    @Override
    public void loop() {
        shoulder1.setPower(gamepad2.right_stick_y);
        shoulder2.setPower(gamepad2.right_stick_y);
        elbow.setPower(gamepad2.left_stick_y);
    }
}