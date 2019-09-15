package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="TestHarware")
@Disabled
public class TestHardware extends BaseHardware {

    public CRServo CRServoTest1;
    public CRServo CRServoTest2;
    public Servo servo1;
    public Servo servo2;
    public DcMotor hex;
    public DcMotor transferMotor;
    public DcMotor shoulder1;
    public DcMotor shoulder2;
    public DcMotor elbow;

    public void init() {

        try {
            CRServoTest1 = hardwareMap.get(CRServo.class, "CRServoTest1");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }

        try {
            CRServoTest2 = hardwareMap.get(CRServo.class, "CRServoTest2");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }

        try {
            servo1 = hardwareMap.get(Servo.class, "servo1");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }

        try {
            servo2 = hardwareMap.get(Servo.class, "servo2");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }

        try {
            hex = hardwareMap.get(DcMotor.class, "hex");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }

        try {
            transferMotor = hardwareMap.get(DcMotor.class, "transferMotor");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }
    }
}
