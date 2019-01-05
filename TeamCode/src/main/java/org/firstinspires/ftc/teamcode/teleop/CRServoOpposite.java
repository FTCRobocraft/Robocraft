package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.hardware.TestHardware;

@TeleOp(name="CRServoOpposite")
public class CRServoOpposite extends TestHardware {

    @Override
    public void init() {
        super.init();
        servo1.setDirection(Servo.Direction.FORWARD);
        servo2.setDirection(Servo.Direction.REVERSE);
    }
//my name is dj and i got a 2070 and a 9500k for christmas :)
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