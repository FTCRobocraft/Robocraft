package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class TestHardware extends BaseHardware {

    public CRServo CRServoTest1;
    public CRServo CRServoTest2;
    public DcMotor hex;

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
            hex = hardwareMap.get(DcMotor.class, "hex");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }
    }
}
