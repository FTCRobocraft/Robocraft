package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.util.OmniDrive;

public class SkyStoneHardware extends BaseHardware {

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    public OmniDrive omniDrive;

    @Override
    public void init() {
        super.init();

        try {
            frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");

            frontRight = hardwareMap.get(DcMotor.class, "frontRight");

            backLeft = hardwareMap.get(DcMotor.class, "backLeft");

            backRight = hardwareMap.get(DcMotor.class, "backRight");

            omniDrive = new OmniDrive(frontLeft, frontRight, backLeft, backRight);

        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }
    }
}
