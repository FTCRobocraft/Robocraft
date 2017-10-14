package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by lvern on 10/8/2017.
 */

@TeleOp(name = "JewelArm")
public class JewelArm extends RobotHardware {

    boolean isRed;
    double downPosition = 0.0;
    double upPosition = 90.0;

    @Override public void init() {
        super.init();
    }

    @Override public void loop() {

        if (gamepad2.x) {
            armServo.setPosition(downPosition);
            if (colorSensor.red() > 10) {moveForward(0.3f);} else {moveBackward(0.3f);}
            armServo.setPosition(upPosition);
        }


    }
}