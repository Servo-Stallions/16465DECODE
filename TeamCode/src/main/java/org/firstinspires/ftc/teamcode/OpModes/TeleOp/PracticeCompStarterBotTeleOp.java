package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name = "RUNTHISTELEOP", group = "StarterBot") //added this line
public class PracticeCompStarterBotTeleOp extends LinearOpMode {
    private DcMotor leftFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightFront = null;
    private DcMotor rightBack = null;
    private DcMotor ShootyMcShooterPants = null;
    private CRServo lefty = null;
    private CRServo righty = null;


    @Override
    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightRear");
        ShootyMcShooterPants = hardwareMap.get(DcMotor.class, "Shooter");
        lefty = hardwareMap.get(CRServo.class, "Servo1");
        righty = hardwareMap.get(CRServo.class, "Servo2");
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        /*leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);e
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        ShootyMcShooterPants.setDirection(DcMotor.Direction.REVERSE);*/


        telemetry.addData("Status", "If I had a nickel for every time this worked, I would have two nickels, which isn't a lot, but its weird that it happened twice");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()) {
            double left;
            double right;

            left = -gamepad1.left_stick_y;
            right = -gamepad1.right_stick_y;

            leftFront.setPower(left);
            rightFront.setPower(right * 0.85);
            leftBack.setPower(left);
            rightBack.setPower(right);
            if (gamepad1.right_bumper) {
                leftFront.setPower(-1.0);
                rightFront.setPower(1.0);
                leftBack.setPower(1.0);
                rightBack.setPower(-1.0);
            } //strafe right
            else if (gamepad1.left_bumper) {
                leftFront.setPower(1.0);
                rightFront.setPower(-1.0);
                leftBack.setPower(-1.0);
                rightBack.setPower(1.0);
            }

            if (gamepad1.a) {
                ShootyMcShooterPants.setPower(0.7);
            } else if (gamepad1.y) {
                ShootyMcShooterPants.setPower(1.0);
            } else if (gamepad1.x) {
                ShootyMcShooterPants.setPower(-1.0);
            }
            else {
                ShootyMcShooterPants.setPower(0.6);
            }

            if (gamepad1.b) {
                lefty.setPower(-1);
                righty.setPower(1);
            }
            if (gamepad1.dpad_down) {
                lefty.setPower(1);
                righty.setPower(-1);
            }else {
                lefty.setPower(0);
                righty.setPower(0);
            }
        }
    }
}
