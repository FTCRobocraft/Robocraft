package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.hardware.TestHardware;

@TeleOp(name="CRServoOpposite")
@Disabled
public class CRServoOpposite extends TestHardware {

    @Override
    public void init() {
        super.init();
        servo1.setDirection(Servo.Direction.FORWARD);
        servo2.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            servo1.setPosition(0);
            servo2.setPosition(0);
        } else if (gamepad1.y) {
            servo1.setPosition(1);
            servo2.setPosition(1);
        }
    }
}