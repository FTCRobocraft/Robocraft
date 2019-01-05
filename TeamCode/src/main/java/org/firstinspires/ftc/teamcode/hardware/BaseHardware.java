package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareDevice;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

public class BaseHardware extends OpMode {

    //region Computer Vision
    public static final String vulforiaKey = "ATYXw6b/////AAAAGbsBoJKrv00tn+OIBZOySi93f157TAsX4H3f444TrvUXKWFiNjsiBUjhwGrShLYay8wFSrlf+nRtoS+xnZJ3IApbJe2W0NSLKz21/B3/IpstUZGH29ZD/ogg3ZixfNyUGyb+F5gy5LzvGTdRhGLwy0d4z2i6QauSDPYHU4bBnhmehHBFMPkA6aP94fqOfa4qJGKBCCrn1EcH+c5TXD2EP21vciteCYktsfBedAnveiDGR7yLbTPr5kdfLvem0iyH8ESxhOsr90wGnIGWOQJa83eilaVbmLHtWkQx/hT/CnNTglJXb6TGRuDEwv/Zs+zdswp9dvCHZL5Qq1pT4y+LNUZZfhtmLlYXNifiEn7HnM5f";

    public VuforiaLocalizer vuforia;
    public VuforiaTrackables relicTrackables;
    public VuforiaTrackable relicTemplate;

    public TFObjectDetector tfod;
    //endregion
//We had peruvian chicken today
    // Encoder Setup
    static final double     COUNTS_PER_MOTOR_REV    = 28 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 72.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuri7ng circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);


    //region Enum
    public enum Direction {
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
        RED,
        BLUE
    }

    public enum StartingPosition {
        LEFT,
        RIGHT
    }
    //endregion


    @Override public void init () {}

    @Override public void start () {}

    @Override public void loop () {}
}
//1-up DJ = impossible
