package org.firstinspires.ftc.teamcode.ops;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.commands.HDriveCmd;
import org.firstinspires.ftc.teamcode.commands.OpenClawCmd;
import org.firstinspires.ftc.teamcode.commands.CloseClawCmd;
import org.firstinspires.ftc.teamcode.subsystems.HDriveSys;
import org.firstinspires.ftc.teamcode.subsystems.ClawSys;

@TeleOp(name = "HDrive Teleop (FTCLib)", group = "HDrive")
public class HDriveTele extends CommandOpMode {
    private HDriveSys drivetrain;
    private ClawSys clawSys;
    private GamepadEx driverOp;
    private GamepadEx toolOp;
    private HDriveCmd driveCmd;

    @Override
    public void initialize() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        driverOp = new GamepadEx(gamepad1);
        toolOp = new GamepadEx(gamepad2);
        drivetrain = new HDriveSys(hardwareMap);
        clawSys = new ClawSys(hardwareMap);
        driveCmd = new HDriveCmd(drivetrain, driverOp);

        GamepadButton clawCtrl = new GamepadButton(toolOp, GamepadKeys.Button.A);
        GamepadButton clawCtrlBackup = new GamepadButton(driverOp, GamepadKeys.Button.A);

        clawCtrl.or(clawCtrlBackup).whenActive(new OpenClawCmd(clawSys)).whenInactive(new CloseClawCmd(clawSys));

        // bindings

        schedule(driveCmd);

    }
}
