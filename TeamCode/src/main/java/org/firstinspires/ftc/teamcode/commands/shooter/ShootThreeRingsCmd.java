package org.firstinspires.ftc.teamcode.commands.shooter;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.ConveyorSys;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSys;


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
