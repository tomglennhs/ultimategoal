package org.firstinspires.ftc.teamcode.commands;

import java.lang.System;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.ConveyorSys;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSys;

public class ShootOneRingCmd extends CommandBase {
    private ConveyorSys conveyor;
    private ShooterSys shooter;
    private boolean shotRing = false;
    private boolean originalShooterState;

    public ShootOneRing(final ShooterSys s, final ConveyorSys c) {
        conveyor = c;
        shooter = s;
        addRequirements(conveyor);
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        conveyor.stop();
        // false means shooter wasn't running before executing the command, true means it was running
        originalShooterState = shooter.getState();
        if (!originalShooterState) {
            shooter.start();
        }
        
        while (!shooter.readyForRing()) {
            // wait for the shooter to be ready 
        }
        // feed in the ring
        conveyor.start();
        while (shooter.readyForRing()) {
            // wait for velocity to dip, indicating a ring was just shot
        }
        conveyor.stop();
        if (!originalShooterState) {
            shooter.stop();
        }
        shotRing = true;

    }

    public boolean isFinished() {
        return shotRing;
    }

}
