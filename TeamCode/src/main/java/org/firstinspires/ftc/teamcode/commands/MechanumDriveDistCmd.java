package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.util.MathUtils;

import org.firstinspires.ftc.teamcode.subsystems.MechanumSys;

public class MechanumDriveDistCmd extends CommandBase {
    private MechanumSys ss;
    private int d;
    public MechanumDriveDistCmd(MechanumSys subsystem, int dist) {
        ss = subsystem;
        d = dist;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        ss.driveDist(d);
    }
}
