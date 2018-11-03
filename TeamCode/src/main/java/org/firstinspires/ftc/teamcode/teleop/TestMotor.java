package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.util.RelicRecoveryHardware;

@TeleOp(name="TestMotor")
public class TestMotor extends RelicRecoveryHardware {

    @Override
    public void loop() {
        if (gamepad1.a) {
            hex.setPower(0.5);
        } else {
            hex.setPower(0);
        }

        if (gamepad1.y) {
            hex.setPower(-0.5);
        } else {
            hex.setPower(0);
        }
    }

}
