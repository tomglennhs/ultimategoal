package org.firstinspires.ftc.teamcode.ops;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.TestShooterCmd;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSys;

@TeleOp(name="Shooter Subsystem Test (FTCLib, Dashboard Graphing)")
public class ShooterSysTest extends CommandOpMode {
    TestShooterCmd cmd;
    ShooterSys shooter;

    @Override
    public void initialize() {
        shooter = new ShooterSys(hardwareMap);
        cmd = new TestShooterCmd(shooter, telemetry, true);
        schedule(cmd);
    }
}
