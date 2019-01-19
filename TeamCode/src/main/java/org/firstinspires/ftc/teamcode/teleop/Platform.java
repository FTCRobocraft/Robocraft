package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.PlatformHardware;

@TeleOp(name="Platform")
@Disabled
public class Platform extends PlatformHardware {

    float power = 0.5f;

    @Override
    public void loop() {
        super.loop();
        omniDrive.stopDrive();
        omniDrive.dpadMove(gamepad1, power, false);

        if (gamepad1.left_trigger > 0) {
            omniDrive.rotateLeft(gamepad1.left_trigger);
        }
        if (gamepad1.right_trigger > 0) {
            omniDrive.rotateRight(gamepad1.right_trigger);
        }

    }

}
