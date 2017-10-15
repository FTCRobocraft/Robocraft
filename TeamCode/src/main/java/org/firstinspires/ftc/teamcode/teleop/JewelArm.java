package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.util.RobotHardware;

/**
 * Created by lvern on 10/8/2017.
 */

@TeleOp(name = "JewelArm")
public class JewelArm extends RobotHardware {


    boolean manual = true;
    JewelAutoStages currentStage = JewelAutoStages.Down;
    double endTime;
    boolean init = true;
    boolean forward;

    boolean isRed;
    double downPosition = 0.0;
    double upPosition = 90.0;
    final int sensitivity = 100;
    double servoWaitTime = 1000;
    double moveWaitTime = 2000;

    enum JewelAutoStages {
        Down,
        Move,
        Move2,
        Up
    }

    @Override public void init() {
        super.init();
    }

    @Override public void loop() {

        if (manual) {
            if (gamepad2.x) {
                manual = false;
                armServo.setPosition(downPosition);
                if (colorSensor.red() > sensitivity) {
                    moveForward(0.3f);
                } else {
                    moveBackward(0.3f);
                }
                armServo.setPosition(upPosition);
            }
        } else {
            switch (currentStage) {
                case Down:
                    if (init) {
                        armServo.setPosition(downPosition);
                        endTime = System.currentTimeMillis() + servoWaitTime;
                        init = false;
                    }
                    if (System.currentTimeMillis() >= endTime) {
                        init = true;
                        currentStage = JewelAutoStages.Move;

                    }
                    break;
                case Move:
                    if (init) {
                        init = false;
                        if (colorSensor.red() > sensitivity) {
                            forward = true;
                            moveForward(0.3f);
                            endTime = System.currentTimeMillis() + moveWaitTime;
                        } else {
                            forward = false;
                            moveBackward(0.3f);
                            endTime = System.currentTimeMillis() + moveWaitTime;
                        }

                    }
                    if (System.currentTimeMillis() >= endTime) {
                        init = true;
                        currentStage = JewelAutoStages.Move2;

                    }
                    break;
                case Move2:
                    if (init) {
                        init = false;
                        if (forward) {
                            moveBackward(0.3f);
                            endTime = System.currentTimeMillis() + moveWaitTime;
                        } else {
                            moveForward(0.3f);
                            endTime = System.currentTimeMillis() + moveWaitTime;
                        }
                    }
                    if (System.currentTimeMillis() >= endTime) {
                        init = true;
                        currentStage = JewelAutoStages.Up;

                    }
                    break;
                case Up:
                    if (init) {
                        armServo.setPosition(upPosition);
                        endTime = System.currentTimeMillis() + servoWaitTime;
                        init = false;
                    }
                    if (System.currentTimeMillis() >= endTime) {
                        init = true;
                        currentStage = JewelAutoStages.Down;
                        manual = true;
                    }
                    break;

            }
        }
    }

}