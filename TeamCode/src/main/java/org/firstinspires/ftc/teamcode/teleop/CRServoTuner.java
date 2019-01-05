package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareDevice;

import org.firstinspires.ftc.teamcode.hardware.RelicRecoveryHardware;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djfig on 1/7/2018.
 */
//I wish I was as cool as you


//@TeleOp(name = "CR Servo Tuner")
public class CRServoTuner extends RelicRecoveryHardware {

    List<CRServo> servos;
    int currentIndex = 0;

    CRServo activeServo;
    boolean dpadLeftPressed = false;
    boolean dpadRightPressed = false;

    @Override
    public void init() {
        servos = new ArrayList<CRServo>();
        for (HardwareDevice device : hardwareMap.getAll(CRServo.class)) {
            CRServo servo = (CRServo) device;
            servos.add(servo);
        }

        if (servos.size() > 0) {
            activeServo = servos.get(currentIndex);
        }
    }

    @Override
    public void loop() {
        if (gamepad1.dpad_left) {
            if (!dpadLeftPressed) {
                currentIndex -= 1;
                if (currentIndex < 0) {
                    currentIndex = servos.size() - 1;
                }
                activeServo = servos.get(currentIndex);
                dpadLeftPressed = true;

            }
        } else {
            dpadLeftPressed = false;
        }

        if (gamepad1.dpad_right) {
            if (!dpadRightPressed) {
                currentIndex += 1;
                if (currentIndex >= servos.size() - 1) {
                    currentIndex = 0;
                }
                activeServo = servos.get(currentIndex);
                dpadRightPressed = true;
            }
        } else {
            dpadRightPressed = false;
        }

        if (activeServo != null) {
            activeServo.setPower(gamepad1.right_stick_y);
            telemetry.addData("Servo", activeServo.getDeviceName());
            telemetry.addData("Port", activeServo.getPortNumber());
            telemetry.addData("Power", activeServo.getPower());
        }
    }
}
