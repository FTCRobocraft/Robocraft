package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.hardware.TestHardware;

@TeleOp(name="CRServoTest")
public class CRServoTest extends TestHardware {

    @Override
    public void loop() {
        CRServoTest.setPower(0);
        if (gamepad1.a) {
            CRServoTest.setPower(-1);
        }
        if (gamepad1.y) {
            CRServoTest.setPower(1);
        }

    }
}