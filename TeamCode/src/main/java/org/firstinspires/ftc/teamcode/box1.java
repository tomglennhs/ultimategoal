package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name = "ddsfdsfs", group = "HDrive", preselectTeleOp="HDrive Teleop")
public class auton3 extends auton2 {
    @Override
    public void initThings() {
        distanceL = 80.0;
        distanceR = 80.0;
    }
}
