package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by lvern on 1/6/2018.
 */

@TeleOp(name = "ArmTest")
public class ArmTest extends RobotHardware {

    @Override
    public void loop() {
        clawArmServo.setPower(gamepad2.left_stick_y / 2.0);
    }
}
