package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Deprecated
public class RelicRecoveryHardware extends BaseHardware
{

    // Hardware
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;


    public BNO055IMU revIMU;

    public CRServo lift_verticalServo;
    public Servo lift_gripServo;
    public Servo armServo;
    public Servo relicArmServo;
    public Servo relicClawServo;
    public Servo relicShoulderServo;

    public ColorSensor jewelSensor;

    public final double m_liftGripOpen = 0.35;
    public final double m_liftGripClosed = 0.7;

    public final double m_relicShoulderRetracted = 0.4844;
    public final double m_relicShoulderUp = 0.3522;
    public final double m_relicShoulderDown = 0.2072;
    public final double m_relicArmUp = 0.5;
    public final double m_relicArmDown = 0.3405;
    public final double m_relicClawOpen = 1;
    public final double m_relicClawClosed = 0.155;

    public enum RobotMoveDirection {
        FORWARD,
        LEFT,
        RIGHT,
        BACKWARD,
        FORWARD_LEFT,
        FORWARD_RIGHT,
        BACKWARD_LEFT,
        BACKWARD_RIGHT,
        ROTATE_LEFT,
        ROTATE_RIGHT
    }

    public enum Team {
        Red,
        Blue
    }

    public enum Position {
        Top,
        Bottom
    }


    @Override public void init ()
    {
        /*
        try {
            frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }

        try {
            frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }

        try {
            backLeft = hardwareMap.get(DcMotor.class, "backLeft");
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }

        try {
            backRight = hardwareMap.get(DcMotor.class, "backRight");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }
        
        try {
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
            parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
            parameters.loggingEnabled      = true;
            parameters.loggingTag          = "IMU";
            parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

            revIMU = hardwareMap.get(BNO055IMU.class, "imu");
            revIMU.initialize(parameters);
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }

        try {
            jewelSensor = hardwareMap.get(ColorSensor.class, "jewelSensor");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }

        // Servos
        try {
            lift_gripServo = hardwareMap.get(Servo.class, "liftClaw");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }

        try {
            lift_verticalServo = hardwareMap.get(CRServoTest1.class, "liftVerticalServo");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }

        try {
            armServo = hardwareMap.get(Servo.class, "armServo");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }

        try {
            relicArmServo = hardwareMap.get(Servo.class, "relicArmServo");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }

        try {
            relicClawServo = hardwareMap.get(Servo.class, "relicClawServo");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }

        try {
            relicShoulderServo = hardwareMap.get(Servo.class, "relicShoulderServo");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }
        */


    }


    @Override public void start () {}

    @Override public void loop () {}

    private boolean v_warning_generated = false;

    public void stopDrive() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void moveInDirection(RobotMoveDirection direction, float power) {
        switch (direction) {
            case FORWARD:
                moveForward(power);
                break;
            case FORWARD_LEFT:
                moveForwardLeft(power);
                break;
            case FORWARD_RIGHT:
                moveForwardRight(power);
                break;
            case LEFT:
                moveLeft(power);
                break;
            case RIGHT:
                moveRight(power);
                break;
            case BACKWARD_LEFT:
                moveBackwardLeft(power);
                break;
            case BACKWARD_RIGHT:
                break;
            case BACKWARD:
                break;
        }
    }

    public void moveForward(float power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }

    public void moveBackward(float power) {
        frontLeft.setPower(-power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(-power);
    }

    public void moveLeft(float power) {
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(-power);
    }

    public void moveRight(float power) {
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(power);
    }

    public void rotateRight(float power) {
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(power);
        backRight.setPower(-power);
    }

    public void rotateLeft(float power) {
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(-power);
        backRight.setPower(power);
    }

    public void moveForwardLeft(float power) {
        frontLeft.setPower(0);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(0);
    }

    public void moveForwardRight(float power) {
        frontLeft.setPower(power);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(power);
    }

    public void moveBackwardLeft(float power) {
        frontLeft.setPower(-power);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(-power);
    }

    public void moveBackwardRight(float power) {
        frontLeft.setPower(0);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(0);
    }

    public void circleMove(float x, float y) {
        frontLeft.setPower(x);
        backRight.setPower(x);
        backLeft.setPower(y);
        frontRight.setPower(y);
    }

}