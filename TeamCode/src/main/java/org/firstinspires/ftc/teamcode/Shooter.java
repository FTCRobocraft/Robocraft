package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//Created by Michael on 10/14/2016.

@TeleOp(name="Shooter")
public class Shooter extends RobotHardware {

    boolean toggle;
    boolean bPress;
    boolean toggle2;
    boolean bPress2;

    int shooterTime = 400;

    double shooterSpeed = 1;
    double shooterServoStart = 0.16;
    double spinnerSpeed = 1;
    int count = 0;


    public void waitfor(int time) {
        try{
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override public void init() {
        super.init();

        shooterMotor.setPower(shooterSpeed);
        waitfor(100);
        shooterMotor.setPower(0);

        shooterServo.setPosition(shooterServoStart);

    }

    //////////////////////////////

    @Override public void loop() {

        if(gamepad1.x) {

            shooterMotor.setPower(shooterSpeed);
            waitfor(shooterTime);
            shooterMotor.setPower(0);
        }

        //////////////////////////////

        if (gamepad1.y) {
            if (!bPress2){
                bPress2 = true;
                toggle2 = !toggle2;
            }
        } else {
            bPress2 = false;
        }

        if (toggle2) {
            spinnerMotor.setPower(spinnerSpeed);
        } else {
            spinnerMotor.setPower(0);
        }

        //////////////////////////////

        if (gamepad1.a) {
            if (!bPress){
                bPress = true;
                toggle = !toggle;
            }
        } else {
            bPress = false;
        }

        if (toggle){
            shooterServo.setPosition(0.55);
        } else {
            shooterServo.setPosition(shooterServoStart);
        }

        //////////////////////////////

        if (gamepad1.dpad_up) {
            count++;
            shooterServo.setPosition(count);
        }

        if (gamepad1.dpad_down) {
            count--;
            shooterServo.setPosition(count);
        }

    }

}