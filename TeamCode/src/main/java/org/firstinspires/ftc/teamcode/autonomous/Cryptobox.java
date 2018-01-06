package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.util.ActionExecutor;
import org.firstinspires.ftc.teamcode.util.ActionSequence;

/**
 * Created by djfigs1 on 12/9/17.
 */

@Autonomous(name="Cryptobox")
public class Cryptobox extends ActionExecutor {

    class CryptoboxSequence extends ActionSequence {
        public CryptoboxSequence() {

        }
    }

    @Override
    public void init() {
        super.init();
        this.initVulforia = true;
    }

}