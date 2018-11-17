package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.hardware.BaseHardware;
import org.firstinspires.ftc.teamcode.util.ActionSequence;

public class IfAction implements Action {

    Condition condition;
    ActionSequence trueSequence;
    ActionSequence falseSequence;
    ActionSequence execSequence;

    private Action action = null;
    private int actionNumber = 1;

    public IfAction(Condition condition, ActionSequence trueSequence, ActionSequence falseSequence) {
        this.condition = condition;
        this.trueSequence = trueSequence;
        this.falseSequence = falseSequence;
    }

    public void init(BaseHardware hardware) {
        if (condition.evaluate(hardware)) {
            execSequence = trueSequence;
        } else {
            execSequence = falseSequence;
        }
    }

    public boolean doAction(BaseHardware hardware) {
        if (execSequence == null) {
            return true;
        }

        action = execSequence.getCurrentAction();
        if (action != null) {
            if (action.doAction(hardware)) {
                execSequence.currentActionComplete();
                action = execSequence.getCurrentAction();
                action.init(hardware);
                actionNumber++;
            } else {
                hardware.telemetry.addData("Progress", "%d/%d, %d%%", actionNumber, execSequence.numberOfActions(),
                        (int) ((double) actionNumber / (double) execSequence.numberOfActions() * 100.0));
                hardware.telemetry.addData("Current Action", action.getClass().getSimpleName());
            }
        } else {
            return true;
        }
        return false;
    }
}
