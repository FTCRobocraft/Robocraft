package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class TestHardware extends BaseHardware {

    public CRServo CRServoTest;
    public DcMotor hex;

    public void init() {

        try {
            CRServoTest = hardwareMap.get(CRServo.class, "CRServoTest");
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
