package org.firstinspires.ftc.teamcode.teleop;

import org.firstinspires.ftc.teamcode.hardware.TestHardware;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

//This class is a combination of CRServoOpposite, CRServoSame, and TestMotor

@TeleOp(name="ArmControl")
@Disabled
public class ArmControl extends TestHardware {

    @Override
    public void init() {
        super.init();
        //Set the directions of the basket servos opposite because they are facing different directions
        servo1.setDirection(Servo.Direction.FORWARD);
        servo2.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void loop() {
        //Control basket
        if (gamepad1.a) {
            servo1.setPosition(0);
            servo2.setPosition(0);
        } else if (gamepad1.y) {
            servo1.setPosition(1);
            servo2.setPosition(1);
        }

        //Control dumper
        if (gamepad1.x) {
            CRServoTest1.setPower(-1);
            CRServoTest2.setPower(1);
        } else if (gamepad1.b) {
            CRServoTest1.setPower(1);
            CRServoTest2.setPower(-1);
        } else {
            CRServoTest1.setPower(0);
            CRServoTest2.setPower(0);
        }

        //Control lifter
        hex.setPower(gamepad1.right_stick_y);
        transferMotor.setPower(gamepad1.left_stick_y);
    }
}