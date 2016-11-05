package org.firstinspires.ftc.teamcode;

import android.location.LocationListener;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

//Created by Michael on 10/14/2016.

@TeleOp(name="MotorTest")
public class MotorTest extends RobotHardware {


    boolean toggle;
    boolean bPress;
    boolean toggle2;
    boolean bPress2;
    double shooterSpeed = 1;
    double scooperSpeed = 1;

    @Override public void loop() {


        if(gamepad1.x) {

            shooterMotor.setPower(shooterSpeed);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            shooterMotor.setPower(0);
        }

        /*

        if (gamepad1.x) {
            if (!bPress){
                bPress = true;
                toggle = !toggle;
            }
        } else {
            bPress = false;
        }

        if (toggle){
            scooperMotor.setPower(scooperSpeed);
        } else {
            scooperMotor.setPower(0);
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

        if (toggle2){
            shooterMotor.setPower(shooterSpeed);
        } else {
            shooterMotor.setPower(0);
        }

        */
    }
}