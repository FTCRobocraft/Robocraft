package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by djfigs1 on 11/27/16.
 */
@TeleOp(name="Robot Servo Test")
public class ServoTest extends RobotHardware {

    boolean powerOff = false;
    boolean pressed = false;

    @Override
    public void init() {
       super.init();
    }

    @Override
    public void loop() {
        if (gamepad1.x){
            if (!pressed){
                if (powerOff){
                    beaconServo.getController().pwmEnable();
                }else{
                    beaconServo.getController().pwmDisable();
                }
            }
            pressed = true;
        }else{
            pressed = false;
        }

    }
}
