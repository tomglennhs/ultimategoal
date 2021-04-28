package org.firstinspires.ftc.teamcode.commands.shooter;

import org.firstinspires.ftc.teamcode.subsystems.ShooterSys;

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
