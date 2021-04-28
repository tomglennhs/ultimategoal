package org.firstinspires.ftc.teamcode.ops;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.commands.ClawCtrlCmd;
import org.firstinspires.ftc.teamcode.commands.MechanumCmd;
import org.firstinspires.ftc.teamcode.subsystems.ClawSys;
import org.firstinspires.ftc.teamcode.subsystems.MechanumSys;

@TeleOp(name = "Mechanum Teleop (FTCLib)", group = "Mechanum")
public class MechanumTele extends CommandOpMode {
    private MechanumSys drivetrain;
    private ClawSys clawSys;
    private GamepadEx driverOp;
    private MechanumCmd driveCmd;
    private ClawCtrlCmd angleCmd;

    @Override
    public void initialize() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        driverOp = new GamepadEx(gamepad1);
        drivetrain = new MechanumSys(hardwareMap);
        clawSys = new ClawSys(hardwareMap);
        driveCmd = new MechanumCmd(drivetrain, driverOp);
        angleCmd =  new ClawCtrlCmd(clawSys, driverOp);

        // bindings

        schedule(driveCmd, angleCmd);

    }
}
