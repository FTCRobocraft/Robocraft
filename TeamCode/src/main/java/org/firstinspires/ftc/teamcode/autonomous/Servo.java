package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by lvern on 11/4/2017.
 */
@Autonomous (name="ServoTest")
public class Servo extends RobotHardware {

    double pos1 = 0;
    double pos2 = 90/190.5; //Degrees on the servo = degrees/190.5
    boolean toggle = false;
    boolean xPress = false;

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        if (gamepad1.x && !xPress) {
            xPress = true;
            if (toggle) {
                armServo.setPosition(pos1);
                toggle = false;
            } else {
                armServo.setPosition(pos2);
                toggle = true;
            }
        } else {
            if (!gamepad1.x && xPress) {
                xPress = false;
            }
        }
    }
}