package org.firstinspires.ftc.teamcode.commands;

import java.lang.System;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.ClawSys;

public class OpenClawCmd extends CommandBase {
    private ClawSys ss;
    private final long openMs = 750l;
    private final long start = System.currentTimeMillis();

    public OpenClaw(final ClawSys subsystem) {
        ss = subsystem;
        addRequirements(subsystem);
    }

    public void task() {
        ss.openClaw();
    }

    public boolean shouldRun() {
        return !ss.isOpen();
    }

    @Override
    public void execute() {
        if (shouldRun()) {
            task();
        }
    }

    public boolean isFinished() {
        if (!shouldRun()) {
            return true;
        }
        return System.currentTimeMillis() - start >= openMs; 
    }

}
