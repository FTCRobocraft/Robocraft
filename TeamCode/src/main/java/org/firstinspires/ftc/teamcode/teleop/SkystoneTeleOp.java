package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.SkyStoneHardware;

@TeleOp(name="Manual")
public class SkystoneTeleOp extends SkyStoneHardware {
    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        omniDrive.circleMove(gamepad1.left_stick_x, gamepad1.left_stick_y);
    }
}