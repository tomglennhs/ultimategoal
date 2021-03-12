package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.commands.ShootOneRingCmd;
import org.firstinspires.ftc.teamcode.commands.ShooterOnCmd;
import org.firstinspires.ftc.teamcode.commands.ShooterOffCmd;

import org.firstinspires.ftc.teamcode.subsystems.ConveyorSys;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSys;
import org.firstinspires.ftc.teamcode.util.CmdManager;


public class ShootThreeRingsCmd extends SequentialCommandGroup {

    public ShootThreeRingsCmd(final ShooterSys s, final ConveyorSys c) {
//        CmdManager.register(this, );
        addCommands(
            new ShooterOnCmd(s),
            new ShootOneRingCmd(s, c),
            new ShootOneRingCmd(s, c),
            new ShootOneRingCmd(s, c),
            new ShooterOffCmd(s)
        );
    }
    
}