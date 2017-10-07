package org.firstinspires.ftc.teamcode.util;

import android.os.MessageQueue;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cCompassSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.hardware.motors.RevRoboticsCoreHexMotor;
import com.qualcomm.hardware.motors.RevRoboticsHdHexMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceReader;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.MotorConfigurationType;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class RobotHardware extends OpMode
{

    public static final String vulforiaKey = "ATYXw6b/////AAAAGbsBoJKrv00tn+OIBZOySi93f157TAsX4H3f444TrvUXKWFiNjsiBUjhwGrShLYay8wFSrlf+nRtoS+xnZJ3IApbJe2W0NSLKz21/B3/IpstUZGH29ZD/ogg3ZixfNyUGyb+F5gy5LzvGTdRhGLwy0d4z2i6QauSDPYHU4bBnhmehHBFMPkA6aP94fqOfa4qJGKBCCrn1EcH+c5TXD2EP21vciteCYktsfBedAnveiDGR7yLbTPr5kdfLvem0iyH8ESxhOsr90wGnIGWOQJa83eilaVbmLHtWkQx/hT/CnNTglJXb6TGRuDEwv/Zs+zdswp9dvCHZL5Qq1pT4y+LNUZZfhtmLlYXNifiEn7HnM5f";

    // Encoder Setup
    static final double     COUNTS_PER_MOTOR_REV    = 28 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 72.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    // Hardware
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    public BNO055IMU revIMU;

    public enum RobotMoveDirection {
        FORWARD,
        LEFT,
        RIGHT,
        BACKWARD,
        FORWARD_LEFT,
        FORWARD_RIGHT,
        BACKWARD_LEFT,
        BACKWARD_RIGHT
    }

    public GyroSensor gyroSensor;

    @Override public void init ()
    {
        try {
            frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
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
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }

        try {
            backRight = hardwareMap.get(DcMotor.class, "backRight");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }
        
        try {
            revIMU = hardwareMap.get(BNO055IMU.class, "imu");
        } catch (Exception e) {
            telemetry.addData("Not Found:", e.getMessage());
        }
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
        frontLeft.setPower(0);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(0);
    }

    public void moveBackwardRight(float power) {
        frontLeft.setPower(-power);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(-power);
    }
}