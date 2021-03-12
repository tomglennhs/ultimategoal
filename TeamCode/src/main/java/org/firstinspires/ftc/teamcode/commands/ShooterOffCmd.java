package org.firstinspires.ftc.teamcode.commands;

import java.lang.System;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.ConveyorSys;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSys;
import org.firstinspires.ftc.teamcode.commands.ShooterOnCmd;

public class ShooterOffCmd extends ShooterOnCmd {
    public ShooterOffCmd(ShooterSys sys) {
        super(sys);
    }

    @Override
    public void execute() {
        shooter.stop();
        done = true;
    }
}
