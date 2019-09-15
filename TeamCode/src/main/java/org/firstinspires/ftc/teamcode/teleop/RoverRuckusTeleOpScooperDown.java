package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp(name="Manual (Scooper Down)")
public class RoverRuckusTeleOpScooperDown extends RoverRuckusTeleOp {

    @Override
    public void init() {
        super.init();
        this.scooperUp = false;
    }
}
