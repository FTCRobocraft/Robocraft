package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.hardware.RoverRuckusHardware;

/**
 * Created by djfig on 12/12/2016.
 */
public class WaitAction implements Action {
    double time;
    double endTime;
    boolean init = true;

    public WaitAction(double time){
        this.time = time;
    }

    @Override
    public boolean doAction(RoverRuckusHardware hardware){
        boolean finished = false;
        if (init){
            endTime = System.currentTimeMillis() + time;
                init = false;
            }
            if (System.currentTimeMillis() >= endTime){
            finished = true;
        }
        return finished;
    }
}
