package org.firstinspires.ftc.teamcode;

import android.location.LocationListener;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

//Created by Michael on 10/14/2016.

@TeleOp(name="Shooter")
public class Shooter extends RobotHardware {

    boolean toggle;
    boolean bPress;
    boolean toggle2;
    boolean bPress2;
    boolean toggle3;
    boolean bPress3;

    int shooterTime = 500;

    double shooterSpeed = 1;
    double scooperSpeed = 1;
    double shooterServoStart = 0.80;
    double spinnerPower = 0.75;


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

    }

    //////////////////////////////

    @Override public void loop() {

        if(gamepad1.x) {

            shooterMotor.setPower(shooterSpeed);
            waitfor(100);
            shooterMotor.setPower(0);
        }

        //////////////////////////////

        if (gamepad1.y) {
            if (!bPress3){
                bPress3 = true;
                toggle3 = !toggle3;
            }
        } else {
            bPress3 = false;
        }

        if (toggle3) {
            spinnerMotor.setPower(spinnerPower);
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
            shooterServo.setPosition(shooterServoStart);
        } else {
            shooterServo.setPosition(0.50);
        }

    }

}