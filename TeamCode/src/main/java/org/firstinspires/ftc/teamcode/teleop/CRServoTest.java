package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.hardware.TestHardware;

@TeleOp(name="CRServoTest")
public class CRServoTest extends TestHardware {

    @Override
    public void loop() {
        //CRServoTest1.setPower(0);
        if (gamepad1.a) {
            CRServoTest1.setPower(-1);
            //CRServoTest2.setPower(1);
        } else if (gamepad1.y) {
            CRServoTest1.setPower(1);
            //CRServoTest2.setPower(-1);
        } else {
            CRServoTest1.setPower(0);
            //CRServoTest2.setPower(0);
        }
    }
}