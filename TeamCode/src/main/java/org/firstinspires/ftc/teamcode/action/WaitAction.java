package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.hardware.BaseHardware;

/**
 * Created by djfig on 12/12/2016.
 */
public class WaitAction implements Action {
    double time;
    double endTime;

    /**
     *
     * @param time Time in milliseconds for the action to wait.
     */
    public WaitAction(double time){
        this.time = time;
    }

    @Override
    public void init(BaseHardware hardware) {
        endTime = System.currentTimeMillis() + time;
    }

    @Override
    public boolean doAction(BaseHardware hardware){
        return (System.currentTimeMillis() >= endTime);
    }
}
