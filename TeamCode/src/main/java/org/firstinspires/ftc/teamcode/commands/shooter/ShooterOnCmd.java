package org.firstinspires.ftc.teamcode.commands.shooter;

import java.lang.System;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.ConveyorSys;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSys;

public class ShooterOnCmd extends CommandBase {
    public ShooterSys shooter;
    public boolean done = false;

    public ShooterOnCmd(final ShooterSys s) {
        shooter = s;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.start();
        while (!shooter.readyForRing()) {
            // wait for the shooter to be ready before declaring the command done
        }
        done = true;
    }

    public boolean isFinished() {
        return done;
    }

}
