package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//Created by Michael on 10/14/2016.

public class Shooter extends RobotHardware {

    boolean shooterToggle;
    boolean shooterBpress;
    boolean spinnerToggle;
    boolean spinnerBpress;


    int shooterTime = 500;
    boolean check = true;

    double servoDownPosition = 0.13;
    double shooterUpPosition = 0.46;
    double spinnerSpeed = 1;
    double shooterSpeed = 1;

    public void waitFor(int time) {
        try{
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void shake() {
        if(gamepad1.left_bumper || gamepad1.right_bumper) {
            double currentPosition = shooterServo.getPosition();
            check = false;
            shooterServo.setPosition(currentPosition - 0.1);
            shooterServo.setPosition(currentPosition + 0.1);
            check = true;
        }
    }


    @Override public void init() {
        super.init();

        shooterMotor.setPower(shooterSpeed);
        waitFor(100);
        shooterMotor.setPower(0);

        shooterServo.setPosition(servoDownPosition);

    }

    //////////////////////////////

    @Override public void loop() {

        if(gamepad1.x) {
            shooterServo.setPosition(servoDownPosition);
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
            shooterServo.setPosition(servoDownPosition);
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
                shooterServo.setPosition(shooterUpPosition);
            } else {
                shooterServo.setPosition(servoDownPosition);
            }
        }

        shake();




        //////////////////////////////




    }

}