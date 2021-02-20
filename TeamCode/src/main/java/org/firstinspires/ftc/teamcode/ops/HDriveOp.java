package org.firstinspires.ftc.teamcode.ops;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.commands.HDriveCmd;
import org.firstinspires.ftc.teamcode.subsystems.HDriveSys;


@TeleOp(name = "HDrive Teleop (FTCLib)", group = "HDrive")
public class HDriveOp extends CommandOpMode {
    private HDriveSys drivetrain;
    private GamepadEx driverOp;
    private HDriveCmd driveCmd;

    @Override
    public void initialize() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        driverOp = new GamepadEx(gamepad1);
        drivetrain = new HDriveSys(hardwareMap);
        driveCmd = new HDriveCmd(drivetrain, new GamepadEx(gamepad1));

        // bindings

        schedule(driveCmd);

    }
}