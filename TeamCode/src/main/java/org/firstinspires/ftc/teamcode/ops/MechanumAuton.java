package org.firstinspires.ftc.teamcode.ops;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.commands.mechanum.MechanumDriveDistCmd;
import org.firstinspires.ftc.teamcode.subsystems.MechanumSys;

@TeleOp(name = "Mechanum Auton (FTCLib)", group = "Mechanum")
public class MechanumAuton extends CommandOpMode {
    @Override
    public void initialize() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        MechanumSys drivetrain = new MechanumSys(hardwareMap);
        MechanumDriveDistCmd driveCmd = new MechanumDriveDistCmd(drivetrain, 7*12);

        schedule(driveCmd);

    }
}
