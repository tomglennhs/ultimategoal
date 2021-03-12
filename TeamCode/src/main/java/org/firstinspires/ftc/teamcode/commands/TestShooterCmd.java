package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSys;

public class TestShooterCmd extends CommandBase {
    ShooterSys shooter;
    Telemetry tele;
    boolean graph;
    TelemetryPacket packet;
    FtcDashboard dash;

    public TestShooterCmd(final ShooterSys s, Telemetry t, boolean withGraphing) {
        dash = FtcDashboard.getInstance();
        packet = new TelemetryPacket();
        tele = new MultipleTelemetry(t, dash.getTelemetry());
        graph = withGraphing;
        shooter = s;
        addRequirements(shooter);
    }

    public TestShooterCmd(final ShooterSys s, Telemetry t) {
        this(s, t, false);
    }

    @Override
    public void execute() {
        shooter.start();
        add("Running", shooter.getState());
        add("Inverted", shooter.invert);
        add("Current Velocity (deg/s)", shooter.getVelocity());
        add("Current Velocity (unknown units)", shooter.get());
        add("Expected Velocity (deg/s)", shooter.getExpectedVelocity());
        add("Ready For Ring", shooter.readyForRing());
        update();
    }

    private void add(String cap, Object data) {
        if (graph) {
            packet.put(cap, data);
        } else {
            tele.addData(cap, data);
        }
    }

    private void update() {
        if (graph) {
            dash.sendTelemetryPacket(packet);
            packet = new TelemetryPacket();
        } else {
            tele.update();
        }
    }

    public void end() {
        shooter.stop();
    }

}
