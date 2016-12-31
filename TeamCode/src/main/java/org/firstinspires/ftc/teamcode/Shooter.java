package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//Created by Michael on 10/14/2016.

@TeleOp(name="Shooter")
public class Shooter extends RobotHardware {

    boolean shooterToggle;
    boolean shooterBpress;
    boolean spinnerToggle;
    boolean spinnerBpress;


    int shooterTime = 500;
    boolean check = true;

    double shooterSpeed = 1;
    double shooterServoStart = 0.3;
    double spinnerSpeed = 1;


    public void waitFor(int time) {
        try{
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Override public void init() {
        super.init();

        shooterMotor.setPower(shooterSpeed);
        waitFor(100);
        shooterMotor.setPower(0);

        shooterServo.setPosition(shooterServoStart);

    }

    //////////////////////////////

    @Override public void loop() {

        if(gamepad1.x) {
            shooterServo.setPosition(shooterServoStart);
            shooterToggle = false;
            shooterMotor.setPower(shooterSpeed);
            waitFor(shooterTime);
            shooterMotor.setPower(0);
        }

        //////////////////////////////

        if (gamepad1.y) {
            if (!spinnerBpress){
                spinnerBpress = true;
                spinnerToggle = !spinnerToggle;
            }
        } else {
            spinnerBpress = false;
        }


        if (spinnerToggle) {
            check = false;
            shooterToggle = true;
            shooterServo.setPosition(shooterServoStart);
            spinnerMotor.setPower(spinnerSpeed);
        } else {
            spinnerMotor.setPower(0);
            check = true;
        }

        //////////////////////////////

        if (gamepad1.a) {
            if (!shooterBpress){
                shooterBpress = true;
                shooterToggle = !shooterToggle;
            }
        } else {
            shooterBpress = false;
        }

        if (check) {
            if (shooterToggle) {
                shooterServo.setPosition(0.55);
            } else {
                shooterServo.setPosition(shooterServoStart);
            }
        }


        //////////////////////////////

        if (gamepad1.left_bumper || gamepad1.right_bumper) {
            check = false;
            shooterServo.setPosition(0.15);
            shooterServo.setPosition(0.55);
            check = true;
        }

        //////////////////////////////

        /*

        if (gamepad1.dpad_up) {
            count = count + 0.1;
            shooterServo.setPosition(count);
        }

        if (gamepad1.dpad_down) {
            count = count - 0.1;
            shooterServo.setPosition(count);
        }

        */

    }

}