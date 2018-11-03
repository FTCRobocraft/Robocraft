package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
<<<<<<< HEAD

import org.firstinspires.ftc.teamcode.util.RelicRecoveryHardware;
=======
import org.firstinspires.ftc.teamcode.util.RobotHardware;
>>>>>>> 5eb1273b95ea99c7747678e4d3f6d811b700d5ef

@TeleOp(name="TestMotor")
public class TestMotor extends RelicRecoveryHardware {

    @Override
    public void loop() {
        if (gamepad1.a) {
            hex.setPower(0.5);
        } else {
            hex.setPower(0);
        }

        if (gamepad1.y) {
            hex.setPower(-0.5);
        } else {
            hex.setPower(0);
        }
    }

}
