package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.commands.OpenClawCmd;

// work smarter not harder ;)
public class CloseClawCmd extends OpenClawCmd {
    @Override
    public void task() {
        ss.closeClaw();
    }

    @Override
    public boolean shouldRun() {
        return ss.isOpen();
    }
}