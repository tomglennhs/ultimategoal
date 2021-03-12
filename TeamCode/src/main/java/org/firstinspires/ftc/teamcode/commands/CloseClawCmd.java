package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.commands.OpenClawCmd;
import org.firstinspires.ftc.teamcode.subsystems.ClawSys;

// work smarter not harder ;)
public class CloseClawCmd extends OpenClawCmd {

    public CloseClawCmd(ClawSys sys) {
        super(sys);
    }

    @Override
    public void task() {
        ss.closeClaw();
    }

    @Override
    public boolean shouldRun() {
        return ss.isOpen();
    }
}