package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;
import org.firstinspires.ftc.teamcode.util.OmniDrive;

public class RoverRuckusHardware extends BaseHardware {

    // Mecanum motors
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    public OmniDrive omniDrive;

    public DcMotor scooperHexMotor;
    public DcMotor scooperTransferMotor;
    public DcMotor liftHexMotor;

    public DcMotor dumperVerticalHexMotor;
    public Servo dumperLeftServo;
    public Servo dumperRightServo;

    public enum GOLD_MINERAL_POSITION {
        LEFT,
        CENTER,
        RIGHT,
        UNKNOWN
    }

    public enum RoverRuckusStartingPosition {
        TOWARDS_DEPOT,
        TOWARDS_CRATER
    }

    @Override
    public void init() {
        super.init();

        try {
            frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

            frontRight = hardwareMap.get(DcMotor.class, "frontRight");

            backLeft = hardwareMap.get(DcMotor.class, "backLeft");
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

            backRight = hardwareMap.get(DcMotor.class, "backRight");

            omniDrive = new OmniDrive(frontLeft, frontRight, backLeft, backRight);

            scooperHexMotor = hardwareMap.get(DcMotor.class, "scooperHexMotor");
            scooperHexMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            scooperTransferMotor = hardwareMap.get(DcMotor.class, "scooperTransferMotor");

            liftHexMotor = hardwareMap.get(DcMotor.class, "liftHexMotor");



            dumperVerticalHexMotor = hardwareMap.get(DcMotor.class, "dumperVerticalHexMotor");
            dumperLeftServo = hardwareMap.get(Servo.class, "dumperLeftServo");
            dumperRightServo = hardwareMap.get(Servo.class, "dumperRightServo");
            dumperRightServo.setDirection(Servo.Direction.REVERSE);
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }
    }
}
