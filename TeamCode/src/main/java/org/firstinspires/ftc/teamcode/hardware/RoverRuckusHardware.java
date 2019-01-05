package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.OmniDrive;

public class RoverRuckusHardware extends BaseHardware {

    // Mecanum motors
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    public OmniDrive omniDrive;

    public CRServo scooperCRServoLeft;
    public CRServo scooperCRServoRight;
    public DcMotor scooperTransferMotor;

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

            scooperCRServoLeft = hardwareMap.get(CRServo.class, "scooperCRServoLeft");
            scooperCRServoRight = hardwareMap.get(CRServo.class, "scooperCRServoRight");
            scooperCRServoRight.setDirection(DcMotorSimple.Direction.REVERSE);
            scooperTransferMotor = hardwareMap.get(DcMotor.class, "scooperTransferMotor");

            dumperVerticalHexMotor = hardwareMap.get(DcMotor.class, "dumperVerticalHexMotor");
            dumperLeftServo = hardwareMap.get(Servo.class, "dumperLeftServo");
            dumperRightServo = hardwareMap.get(Servo.class, "dumperRightServo");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }
    }
}
