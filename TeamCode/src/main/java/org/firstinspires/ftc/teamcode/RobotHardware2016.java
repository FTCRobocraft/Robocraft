package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cCompassSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class RobotHardware2016 extends OpMode

{

    public static final String vulforiaKey = "ATYXw6b/////AAAAGbsBoJKrv00tn+OIBZOySi93f157TAsX4H3f444TrvUXKWFiNjsiBUjhwGrShLYay8wFSrlf+nRtoS+xnZJ3IApbJe2W0NSLKz21/B3/IpstUZGH29ZD/ogg3ZixfNyUGyb+F5gy5LzvGTdRhGLwy0d4z2i6QauSDPYHU4bBnhmehHBFMPkA6aP94fqOfa4qJGKBCCrn1EcH+c5TXD2EP21vciteCYktsfBedAnveiDGR7yLbTPr5kdfLvem0iyH8ESxhOsr90wGnIGWOQJa83eilaVbmLHtWkQx/hT/CnNTglJXb6TGRuDEwv/Zs+zdswp9dvCHZL5Qq1pT4y+LNUZZfhtmLlYXNifiEn7HnM5f";

    @Override public void init ()

    {
        //Sensors
        //Color Sensors
        try {
            leftColorSensor = hardwareMap.colorSensor.get("leftColorSensor");
        } catch (Exception p_exeception) {
            //DbgLog.msg(p_exeception.getLocalizedMessage());
            telemetry.addData("error: ", "left color");
            leftColorSensor = null;
        }

        try {
            //The Right Color Sensor's I2C Address has been modified to 62.
            rightColorSensor = hardwareMap.colorSensor.get("rightColorSensor");
            rightColorSensor.setI2cAddress(I2cAddr.create8bit(62));
        } catch (Exception p_exeception) {
            //DbgLog.msg(p_exeception.getLocalizedMessage());
            telemetry.addData("error: ", "right color");
            rightColorSensor = null;
        }

        try {
            beaconColorSensor = hardwareMap.colorSensor.get("beaconColorSensor");
            beaconColorSensor.setI2cAddress(I2cAddr.create8bit(58));
            beaconColorSensor.enableLed(false);
        } catch (Exception p_exeception) {
            //DbgLog.msg(p_exeception.getLocalizedMessage());
            telemetry.addData("error: ", "beacon color");
            beaconColorSensor = null;
        }

        try {
            leftRangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "leftRangeSensor");
        } catch (Exception p_exeception) {
            //DbgLog.msg(p_exeception.getLocalizedMessage());
            telemetry.addData("error: ", "left range sensor");
            leftRangeSensor = null;
        }

        try {
            I2cDevice range;
            range = hardwareMap.i2cDevice.get("rightRangeSensor");
            rightRangeReader = new I2cDeviceSynchImpl(range, I2cAddr.create8bit(100), false);
            rightRangeReader.engage();
        } catch (Exception e){
            //DbgLog.msg(e.getLocalizedMessage());
            telemetry.addData("error", "right range sensor");
            telemetry.addData("dbgmsg", e.getLocalizedMessage());
            rightRangeReader = null;
        }

        try {
            gyroSensor = hardwareMap.gyroSensor.get("gyroSensor");
            gyroSensor.calibrate();
        } catch (Exception e) {
            //DbgLog.msg(e.getLocalizedMessage());
            telemetry.addData("error", "gyro sensor");
            gyroSensor = null;
        }

        try {
            leftWheelMotor = hardwareMap.dcMotor.get("leftMotor");
        } catch (Exception p_exeception) {
            //DbgLog.msg(p_exeception.getLocalizedMessage());

            leftWheelMotor = null;
        }

        try {
            rightWheelMotor = hardwareMap.dcMotor.get("rightMotor");
            leftWheelMotor.setDirection(DcMotor.Direction.REVERSE);
        } catch (Exception p_exeception) {
            //DbgLog.msg(p_exeception.getLocalizedMessage());

            rightWheelMotor = null;
        }

        try {
            scooperMotor = hardwareMap.dcMotor.get("scooperMotor");
        } catch (Exception p_exception) {
            //DbgLog.msg(p_exception.getLocalizedMessage());

            scooperMotor = null;
    }

        try {
            shooterMotor = hardwareMap.dcMotor.get("shooterMotor");
        } catch (Exception p_exception) {
            //DbgLog.msg(p_exception.getLocalizedMessage());

            shooterMotor = null;
        }

        try {
            beaconServo = hardwareMap.servo.get("beaconServo");
        } catch (Exception p_exception) {
            //DbgLog.msg(p_exception.getLocalizedMessage());

            beaconServo = null;
        }

        try {
            shooterServo = hardwareMap.servo.get("shooterServo");
        } catch (Exception p_exception) {
            //DbgLog.msg(p_exception.getLocalizedMessage());

            shooterServo = null;
        }

        try {
            spinnerMotor = hardwareMap.dcMotor.get("spinnerMotor");
        } catch (Exception p_exception) {
            //DbgLog.msg(p_exception.getLocalizedMessage());

            spinnerMotor = null;
        }

    }

    public double getRightCm() {
        byte[] rangeCache;

        rangeCache = rightRangeReader.read(0x04, 2);
        int ultrasonicCM = rangeCache[0] & 0xFF;
        return (double)ultrasonicCM;

    }


    @Override public void start () {}

    @Override public void loop () {}

    //--------------------------------------------------------------------------
    //
    // scale_motor_power
    //
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    float scale_motor_power (float p_power)
    {
        //
        // Assume no scaling.
        //
        float l_scale = 0.0f;

        //
        // Ensure the values are legal.
        //
        float l_power = Range.clip (p_power, -1, 1);

        float[] l_array =
                { 0.00f, 0.05f, 0.09f, 0.10f, 0.12f
                        , 0.15f, 0.18f, 0.24f, 0.30f, 0.36f
                        , 0.43f, 0.50f, 0.60f, 0.72f, 0.85f
                        , 1.00f, 1.00f
                };

        //
        // Get the corresponding index for the specified argument/parameter.
        //
        int l_index = (int)(l_power * 16.0);
        if (l_index < 0)
        {
            l_index = -l_index;
        }
        else if (l_index > 16)
        {
            l_index = 16;
        }

        if (l_power < 0)
        {
            l_scale = -l_array[l_index];
        }
        else
        {
            l_scale = l_array[l_index];
        }

        return l_scale;

    }

    //--------------------------------------------------------------------------
    //
    // set_drive_power
    //
    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    void set_drive_power (double p_left_power, double p_right_power)

    {
        if (leftWheelMotor != null && rightWheelMotor != null)
        {
            leftWheelMotor.setPower(p_left_power);
            rightWheelMotor.setPower(p_right_power);
        }

    }

    void stopdrive() {
        set_drive_power(0, 0);
    }


    private boolean v_warning_generated = false;

    void beaconPosition (double p_position)
    {
        //
        // Ensure the specific value is legal.
        //
        double l_position = Range.clip(p_position, 0.2, 0.96);

        if (beaconServo != null)
        {
            beaconServo.setPosition(l_position);
        }

    }

    double getBeaconPosition ()
    {
        double l_return = 0.0;

        if (beaconServo != null)
        {
            l_return = beaconServo.getPosition();
        }

        return l_return;

    }

    //region Functions
    VV_BEACON_COLOR getBeaconColor() {
        int red = beaconColorSensor.red();
        int blue = beaconColorSensor.blue();
        int threshold = 1;

        if (red >= threshold && red > blue){
            return VV_BEACON_COLOR.RED;
        }else{
            if (blue >= threshold && blue > red) {
                return VV_BEACON_COLOR.BLUE;
            }
        }

        return VV_BEACON_COLOR.NONE;
    }

    public ROBOT_LINE_FOLLOW_STATE getLineFollowState(VV_LINE_COLOR color, int threshold) {
        switch (color) {
            case RED:
                if (leftColorSensor != null || rightColorSensor != null) {
                    if (leftColorSensor.red() >= threshold && rightColorSensor.red() < threshold) {
                        return ROBOT_LINE_FOLLOW_STATE.LEFT;
                    }
                    if (leftColorSensor.red() < threshold && rightColorSensor.red() >= threshold) {
                        return ROBOT_LINE_FOLLOW_STATE.RIGHT;
                    }
                    if (leftColorSensor.red() >= threshold && rightColorSensor.red() >= threshold) {
                        return ROBOT_LINE_FOLLOW_STATE.BOTH;
                    }
                    if (leftColorSensor.red() < threshold && rightColorSensor.red() < threshold) {
                        return ROBOT_LINE_FOLLOW_STATE.NONE;
                    }
                }
                break;

            case BLUE:
                if (leftColorSensor != null || rightColorSensor != null) {
                    if (leftColorSensor.blue() >= threshold && rightColorSensor.blue() < threshold) {
                        return ROBOT_LINE_FOLLOW_STATE.LEFT;
                    }
                    if (leftColorSensor.blue() < threshold && rightColorSensor.blue() >= threshold) {
                        return ROBOT_LINE_FOLLOW_STATE.RIGHT;
                    }
                    if (leftColorSensor.blue() >= threshold && rightColorSensor.blue() >= threshold) {
                        return ROBOT_LINE_FOLLOW_STATE.BOTH;
                    }
                    if (leftColorSensor.blue() < threshold && rightColorSensor.blue() < threshold) {
                        return ROBOT_LINE_FOLLOW_STATE.NONE;
                    }
                }
                break;

            case WHITE:
                if (leftColorSensor != null || rightColorSensor != null) {
                    if (leftColorSensor.red() > threshold && leftColorSensor.blue() >= threshold && leftColorSensor.green() >= threshold && rightColorSensor.red() < threshold && rightColorSensor.blue() < threshold && rightColorSensor.green() < threshold) {
                        return ROBOT_LINE_FOLLOW_STATE.LEFT;
                    }
                    if (leftColorSensor.red() < threshold && leftColorSensor.blue() < threshold && leftColorSensor.green() < threshold && rightColorSensor.red() >= threshold && rightColorSensor.blue() >= threshold && rightColorSensor.green() >= threshold) {
                        return ROBOT_LINE_FOLLOW_STATE.RIGHT;
                    }
                    if (leftColorSensor.red() >= threshold && leftColorSensor.blue() >= threshold && leftColorSensor.green() >= threshold && rightColorSensor.red() >= threshold && rightColorSensor.blue() >= threshold && rightColorSensor.green() >= threshold) {
                        return ROBOT_LINE_FOLLOW_STATE.BOTH;
                    }
                    if (leftColorSensor.red() < threshold && leftColorSensor.blue() < threshold && leftColorSensor.green() < threshold && rightColorSensor.red() < threshold && rightColorSensor.blue() < threshold && rightColorSensor.green() < threshold) {
                        return ROBOT_LINE_FOLLOW_STATE.NONE;
                    }
                }
                break;
        }
        return ROBOT_LINE_FOLLOW_STATE.NONE;
    }
    //endregion

    public void moveForward(float power) {

    }

    public void moveBackward(float power) {

    }

    public void moveLeft(float power) {

    }

    public void moveRight(float power) {

    }

    public void rotateRight(float power) {

    }

    public void rotateLeft(float power) {

    }


    enum VV_BEACON_COLOR {RED, BLUE, NONE}
    enum VV_LINE_COLOR {RED, BLUE, WHITE}
    enum ROBOT_LINE_FOLLOW_STATE {LEFT, RIGHT, BOTH, NONE}
    enum TEAM {RED, BLUE}
    enum DIRECTION {LEFT, RIGHT}

    private DcMotor leftWheelMotor;
    private DcMotor rightWheelMotor;
    public Servo beaconServo;
    public DcMotor scooperMotor;
    public DcMotor shooterMotor;
    public Servo shooterServo;
    public DcMotor spinnerMotor;

    public ColorSensor leftColorSensor;
    public ColorSensor rightColorSensor;
    public ColorSensor beaconColorSensor;
    public ModernRoboticsI2cRangeSensor leftRangeSensor;
    public ModernRoboticsI2cRangeSensor rightRangeSensor;
    public GyroSensor gyroSensor;
    public I2cDeviceSynchImpl rightRangeReader;
    public ModernRoboticsI2cCompassSensor compassSensor;





}