package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.util.MathUtils;

import org.firstinspires.ftc.teamcode.subsystems.HDriveSys;

public class HDriveCmd extends CommandBase {
    private HDriveSys ss;
    private double drive, turn, strafe;
    private GamepadEx driverOp;

    public HDriveCmd(HDriveSys subsystem, GamepadEx ctrl) {
        ss = subsystem;
        driverOp = ctrl;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        drive = MathUtils.clamp(-driverOp.getLeftY() + driverOp.getRightY(),
                -1,
                1);
        turn  =  -driverOp.getLeftX();
        strafe = -driverOp.getRightX();
        ss.drive(drive, strafe, turn);
    }

}
