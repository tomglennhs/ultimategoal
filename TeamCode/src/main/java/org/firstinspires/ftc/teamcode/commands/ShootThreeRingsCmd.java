package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.commands.ShootOneRingCmd;
import org.firstinspires.ftc.teamcode.commands.ShooterOnCmd;
import org.firstinspires.ftc.teamcode.commands.ShooterOffCmd;

import org.firstinspires.ftc.teamcode.subsystems.ConveyorSys;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSys;


public class ShootThreeRingsCmd extends SequentialCommandGroup {

    public ShootThreeRingsCmd(final ShooterSys s, final ConveyorSys c) {
        addCommands(
            new ShooterOnCmd(s),
            new ShootOneRingCmd(s, c),
            new ShootOneRingCmd(s, c),
            new ShootOneRingCmd(s, c),
            new ShooterOffCmd(s)
        );
    }
    
}