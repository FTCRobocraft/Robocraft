package org.firstinspires.ftc.teamcode;

/**
 * Created by djfig on 12/12/2016.
 */
public class WaitAction implements Action {
    double time;
    double endTime;
    boolean init;

    public WaitAction(double time){
        this.time = time;
    }

    @Override
    public boolean doAction(RobotHardware hardware){
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
