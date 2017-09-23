package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cCompassSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
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
import com.qualcomm.robotcore.util.Range;

public class RobotHardware extends OpMode

{

    public static final String vulforiaKey = "ATYXw6b/////AAAAGbsBoJKrv00tn+OIBZOySi93f157TAsX4H3f444TrvUXKWFiNjsiBUjhwGrShLYay8wFSrlf+nRtoS+xnZJ3IApbJe2W0NSLKz21/B3/IpstUZGH29ZD/ogg3ZixfNyUGyb+F5gy5LzvGTdRhGLwy0d4z2i6QauSDPYHU4bBnhmehHBFMPkA6aP94fqOfa4qJGKBCCrn1EcH+c5TXD2EP21vciteCYktsfBedAnveiDGR7yLbTPr5kdfLvem0iyH8ESxhOsr90wGnIGWOQJa83eilaVbmLHtWkQx/hT/CnNTglJXb6TGRuDEwv/Zs+zdswp9dvCHZL5Qq1pT4y+LNUZZfhtmLlYXNifiEn7HnM5f";

    @Override public void init ()

    {

    }

    @Override public void start () {}

    @Override public void loop () {}

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
}