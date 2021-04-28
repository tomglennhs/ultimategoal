package org.firstinspires.ftc.teamcode.ops.mechanum.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.commands.mechanum.MechanumDriveDistCmd;
import org.firstinspires.ftc.teamcode.subsystems.MechanumSys;

@TeleOp(name = "Mechanum Auton C (FTCLib)", group = "Mechanum")
public class MechanumC extends CommandOpMode {
    @Override
    public void initialize() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        MechanumSys drivetrain = new MechanumSys(hardwareMap);
        MechanumDriveDistCmd driveCmd = new MechanumDriveDistCmd(drivetrain, 11*12);

        schedule(driveCmd);

    }
}
