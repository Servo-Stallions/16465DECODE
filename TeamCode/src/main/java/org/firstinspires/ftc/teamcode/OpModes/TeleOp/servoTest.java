package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "servoTest", group = "StarterBot") //added this line
public class servoTest extends LinearOpMode{
    private CRServo lefty = null;
    private CRServo righty = null;



    @Override
    public void runOpMode() {
        lefty = hardwareMap.get(CRServo.class, "Servo1");
        righty = hardwareMap.get(CRServo.class, "Servo2");

        telemetry.addData("Status", "You're good go go go");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()) {

        if (gamepad1.b){
            lefty.setPower(-1);
            righty.setPower(1);
        }
        else{
            lefty.setPower(0);
            righty.setPower(0);
        }
    }
}
}
