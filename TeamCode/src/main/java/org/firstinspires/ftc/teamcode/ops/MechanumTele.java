package org.firstinspires.ftc.teamcode.ops;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.commands.HDriveCmd;
import org.firstinspires.ftc.teamcode.commands.MechanumCmd;
import org.firstinspires.ftc.teamcode.commands.OpenClawCmd;
import org.firstinspires.ftc.teamcode.commands.CloseClawCmd;
import org.firstinspires.ftc.teamcode.subsystems.HDriveSys;
import org.firstinspires.ftc.teamcode.subsystems.ClawSys;
import org.firstinspires.ftc.teamcode.subsystems.MechanumSys;

import com.arcrobotics.ftclib.command.button.GamepadButton;

@TeleOp(name = "Mechanum Teleop (FTCLib)", group = "Mechanum")
public class MechanumTele extends CommandOpMode {
    private MechanumSys drivetrain;
    private ClawSys clawSys;
    private GamepadEx driverOp;
    private GamepadEx toolOp;
    private MechanumCmd driveCmd;

    @Override
    public void initialize() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        driverOp = new GamepadEx(gamepad1);
        toolOp = new GamepadEx(gamepad2);
        drivetrain = new MechanumSys(hardwareMap);
        clawSys = new ClawSys(hardwareMap);
        driveCmd = new MechanumCmd(drivetrain, driverOp);

        GamepadButton clawCtrl = new GamepadButton(toolOp, GamepadKeys.Button.A);
        GamepadButton clawCtrlBackup = new GamepadButton(driverOp, GamepadKeys.Button.A);
        clawCtrl.or(clawCtrlBackup).whenActive(new OpenClawCmd(clawSys)).whenInactive(new CloseClawCmd(clawSys));

        // bindings

        schedule(driveCmd);

    }
}
