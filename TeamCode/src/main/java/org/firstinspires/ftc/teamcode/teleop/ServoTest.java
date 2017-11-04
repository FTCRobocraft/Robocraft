package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by djfigs1 on 10/21/17.
 */

@TeleOp(name = "servo")
public class ServoTest extends RobotHardware {

    @Override
    public void loop() {
        if (gamepad1.a) {
            lift_leftServo.setPosition(0);
        }

        if (gamepad2.b) {
            lift_leftServo.setPosition(100);
        }
    }
}
