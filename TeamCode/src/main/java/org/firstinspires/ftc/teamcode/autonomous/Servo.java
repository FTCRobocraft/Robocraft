package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by lvern on 11/4/2017.
 */
@Autonomous (name="ServoTest")
public class Servo extends RobotHardware {

    double pos1 = 0;
    double pos2 = 0.75;

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        if (gamepad1.x) {armServo.setPosition(pos1);}
        if (gamepad1.y) {armServo.setPosition(pos2);}
    }

}