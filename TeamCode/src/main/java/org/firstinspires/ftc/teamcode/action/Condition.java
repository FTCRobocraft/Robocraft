package org.firstinspires.ftc.teamcode.action;

import org.firstinspires.ftc.teamcode.hardware.BaseHardware;

public interface Condition {
    boolean evaluate(BaseHardware hardware);
}
