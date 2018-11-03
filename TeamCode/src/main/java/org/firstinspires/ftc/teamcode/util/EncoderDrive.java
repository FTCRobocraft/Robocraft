package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by djfigs1 on 9/30/17. not really
 */

public class EncoderDrive {

    static final double     COUNTS_PER_MOTOR_REV    = 28;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 40;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    private RelicRecoveryHardware hardware;
    public boolean isBusy = false;

    private double inchesToDrive;
    private RelicRecoveryHardware.RobotMoveDirection direction;
    private DcMotor.RunMode previousRunMode;
    private double timeoutS;
    private ElapsedTime runTime;

    private int FL_targetPosition;
    private int FR_targetPosition;
    private int BL_targetPosition;
    private int BR_targetPosition;

    private float FL_speed;
    private float FR_speed;
    private float BL_speed;
    private float BR_speed;

    public EncoderDrive(RelicRecoveryHardware hardware) {
        this.hardware = hardware;
    }

    public void setInchesToDrive(RelicRecoveryHardware.RobotMoveDirection direction, double distance, float power, double timeout) {
        this.inchesToDrive = distance;
        this.isBusy = true;
        this.timeoutS = timeout;
        this.runTime = new ElapsedTime();
        this.runTime.reset();

        this.previousRunMode = hardware.frontLeft.getMode();

        switch (direction) {
            case FORWARD:
                FL_speed = power;
                FR_speed = power;
                BL_speed = power;
                BR_speed = power;
                break;

            case BACKWARD:
                FL_speed = -power;
                FR_speed = -power;
                BL_speed = -power;
                BR_speed = -power;
                break;

            case LEFT:
                FL_speed = -power;
                FR_speed = power;
                BL_speed = power;
                BR_speed = -power;
                break;

            case RIGHT:
                FL_speed = power;
                FR_speed = -power;
                BL_speed = -power;
                BR_speed = power;
                break;

            case FORWARD_LEFT:
                FL_speed = 0;
                FR_speed = power;
                BL_speed = power;
                BR_speed = 0;
                break;

            case FORWARD_RIGHT:
                FL_speed = power;
                FR_speed = 0;
                BL_speed = 0;
                BR_speed = power;
                break;

            case BACKWARD_LEFT:
                FL_speed = 0;
                FR_speed = -power;
                BL_speed = -power;
                BR_speed = 0;
                break;

            case BACKWARD_RIGHT:
                FL_speed = -power;
                FR_speed = 0;
                BL_speed = 0;
                BR_speed = -power;
                break;

            case ROTATE_LEFT:
                FL_speed = -power;
                FR_speed = power;
                BL_speed = -power;
                BR_speed = power;
                break;

            case ROTATE_RIGHT:
                FL_speed = power;
                FR_speed = -power;
                BL_speed = power;
                BR_speed = -power;
                break;

        }

        int FL_direction = (FL_speed > 0) ? 1 : (FL_speed < 0) ? -1 : 0;
        int FR_direction = (FR_speed > 0) ? 1 : (FR_speed < 0) ? -1 : 0;
        int BL_direction = (BL_speed > 0) ? 1 : (BL_speed < 0) ? -1 : 0;
        int BR_direction = (BR_speed > 0) ? 1 : (BR_speed < 0) ? -1 : 0;

        FL_targetPosition = hardware.frontLeft.getCurrentPosition() + FL_direction * (int)(COUNTS_PER_INCH * distance);
        FR_targetPosition = hardware.frontRight.getCurrentPosition() + FR_direction * (int)(COUNTS_PER_INCH * distance);
        BL_targetPosition = hardware.backLeft.getCurrentPosition() + BL_direction * (int)(COUNTS_PER_INCH * distance);
        BR_targetPosition = hardware.backRight.getCurrentPosition() + BR_direction * (int)(COUNTS_PER_INCH * distance);

        hardware.frontLeft.setTargetPosition(FL_targetPosition);
        hardware.frontRight.setTargetPosition(FR_targetPosition);
        hardware.backLeft.setTargetPosition(BL_targetPosition);
        hardware.backRight.setTargetPosition(BR_targetPosition);

        hardware.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardware.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void run() {
        if (this.isBusy) {
            boolean busy = hardware.frontLeft.isBusy() || hardware.frontRight.isBusy()
                    || hardware.backLeft.isBusy() || hardware.backRight.isBusy();

            if (busy && this.runTime.seconds() < this.timeoutS) {
                hardware.frontLeft.setPower(FL_speed);
                hardware.frontRight.setPower(FR_speed);
                hardware.backLeft.setPower(BL_speed);
                hardware.backRight.setPower(BR_speed);

                hardware.telemetry.addData("FL", String.format("%d -> %d", hardware.frontLeft.getCurrentPosition(), hardware.frontLeft.getTargetPosition()));
                hardware.telemetry.addData("FR", String.format("%d -> %d", hardware.frontRight.getCurrentPosition(), hardware.frontRight.getTargetPosition()));
                hardware.telemetry.addData("BL", String.format("%d -> %d", hardware.backLeft.getCurrentPosition(), hardware.backLeft.getTargetPosition()));
                hardware.telemetry.addData("BR", String.format("%d -> %d", hardware.backRight.getCurrentPosition(), hardware.backRight.getTargetPosition()));
            } else {
                isBusy = false;
                hardware.stopDrive();

                hardware.frontLeft.setMode(previousRunMode);
                hardware.frontRight.setMode(previousRunMode);
                hardware.backLeft.setMode(previousRunMode);
                hardware.backRight.setMode(previousRunMode);
            }

        }
    }
}
