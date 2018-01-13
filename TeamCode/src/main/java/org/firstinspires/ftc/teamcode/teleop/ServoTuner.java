package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.RobotHardware;

import java.util.List;

/**
 * Created by djfig on 1/7/2018.
 */


@TeleOp(name = "Servo Tuner")
public class ServoTuner extends RobotHardware {

    List<Servo> servos;
    int currentIndex = 0;

    Servo activeServo;
    boolean dpadLeftPressed = false;
    boolean dpadRightPressed = false;

    @Override
    public void init() {
        super.init();
        for (HardwareDevice device : hardwareMap.getAll(Servo.class)) {
            Servo servo = (Servo) device;
            servos.add(servo);
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

        if (gamepad1.right_stick_y > 0 || gamepad1.right_stick_y < 0) {
            if (activeServo != null) {
                activeServo.setPosition(activeServo.getPosition() + gamepad1.right_stick_y / 10);

            }
        }

        if (activeServo != null) {
            telemetry.addData("Servo", activeServo.getDeviceName());
            telemetry.addData("Port", activeServo.getPortNumber());
            telemetry.addData("Position", activeServo.getPosition());
        }
    }
}
