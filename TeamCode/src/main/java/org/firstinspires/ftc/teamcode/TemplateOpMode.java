package org.firstinspires.ftc.teamcode;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

// delete this next line once you've made a copy of the class
@Disabled
@TeleOp(name = "TemplateOpMode", group = "Does Literally Nothing")
public class TemplateOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        // Place your init code here. Initialize motors and sensors here
        MotorEx motor = new MotorEx(hardwareMap, "motor");

        // keep this here, this indicates the end of the init
        waitForStart();

        /*
            Insert your driving code after this comment.
            Keep in mind this is a linear OpMode, so anything you put here will run once.
            If you want it to loop, you'll have to put into the while block.
            Anything in the while loop will run until the driver hits stop.
        */

        while (opModeIsActive()) {
            telemetry.addData("Hmm?", "Yes");
            telemetry.update();
        }

    }
}
