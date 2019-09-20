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
            shoulder1= hardwareMap.get(DcMotor.class, "shoulder1");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }

        try {
            shoulder2= hardwareMap.get(DcMotor.class, "shoulder2");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }

        try {
            elbow= hardwareMap.get(DcMotor.class, "elbow");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }
    }
}
