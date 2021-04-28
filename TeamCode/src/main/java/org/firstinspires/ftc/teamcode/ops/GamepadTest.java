package org.firstinspires.ftc.teamcode.ops;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name="Gamepad Test", group = "Test")
public class GamepadTest extends LinearOpMode {
    Telemetry tele;
    boolean graph;
    TelemetryPacket packet;
    FtcDashboard dash;
    Gamepad gamepad;
    int no = 1;

    @Override
    public void runOpMode() throws InterruptedException {
        dash = FtcDashboard.getInstance();
        packet = new TelemetryPacket();
        tele = new MultipleTelemetry(telemetry, dash.getTelemetry());
        graph = true;

        waitForStart();

        while (opModeIsActive()) {
            gamepad = gamepad1;
            no = 1;
            addGamepadInfo();

            gamepad = gamepad2;
            no = 2;
            addGamepadInfo();

            update();
        }
    }

    private void addGamepadInfo() {
        add("Gamepad " + no + " Left Joystick X", gamepad.left_stick_x);
        add("Gamepad " + no + " Left Joystick Y", gamepad.left_stick_y);
        add("Gamepad " + no + " Left Joystick Button", gamepad.left_stick_button);
        add("Gamepad " + no + " Right Joystick X", gamepad.right_stick_x);
        add("Gamepad " + no + " Right Joystick Y", gamepad.right_stick_y);
        add("Gamepad " + no + " Right Joystick Button", gamepad.right_stick_button);
        add("Gamepad " + no + " Left Trigger", gamepad.left_trigger);
        add("Gamepad " + no + " Right Trigger", gamepad.right_trigger);
        add("Gamepad " + no + " Left Bumper", gamepad.left_bumper);
        add("Gamepad " + no + " Right Bumper", gamepad.right_bumper);
        add("Gamepad " + no + " A", gamepad.a);
        add("Gamepad " + no + " B", gamepad.b);
        add("Gamepad " + no + " X", gamepad.x);
        add("Gamepad " + no + " Y", gamepad.y);
        add("Gamepad " + no + " Guide", gamepad.guide);
        add("Gamepad " + no + " Start", gamepad.start);
        add("Gamepad " + no + " Back", gamepad.back);
        add("Gamepad " + no + " DPad Up", gamepad.dpad_up);
        add("Gamepad " + no + " DPad Down", gamepad.dpad_down);
        add("Gamepad " + no + " DPad Left", gamepad.dpad_left);
        add("Gamepad " + no + " DPad Right", gamepad.dpad_right);

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
}
