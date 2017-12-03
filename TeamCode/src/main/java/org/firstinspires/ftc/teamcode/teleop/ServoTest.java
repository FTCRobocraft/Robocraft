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
            armServo.setPosition(armServo.getPosition() + .0005);
        }

        if (gamepad1.b) {
            armServo.setPosition(armServo.getPosition() - .0005);
        }

        telemetry.addData("arm", armServo.getPosition());
    }
}
