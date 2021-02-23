package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.commands.ShootOneRingCmd;
import org.firstinspires.ftc.teamcode.commands.ShooterOnCmd;
import org.firstinspires.ftc.teamcode.commands.ShooterOffCmd;


public class ShootThreeRingsCmd extends SequentialCommandGroup {

    public ShootThreeRingsCmd(final ShooterSys s, final ConveyorSys c) {
        addCommands(
            new ShooterOnCmd(s),
            new ShootOneRingCmd(c, s),
            new ShootOneRingCmd(c, s),
            new ShootOneRingCmd(c, s),
            new ShooterOffCmd(s)
        );
    }
    
}