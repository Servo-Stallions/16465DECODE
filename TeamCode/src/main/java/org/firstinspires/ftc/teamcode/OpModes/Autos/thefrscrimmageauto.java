package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous
@Disabled
public class thefrscrimmageauto extends LinearOpMode {

    private DcMotor ShootyMcShooterPants = null;
    private CRServo lefty = null;
    private CRServo righty = null;

    private DcMotor leftFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightFront = null;
    private DcMotor rightBack = null;

    private ElapsedTime runtime = new ElapsedTime();


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
        waitForStart();

        leftBack.setPower(-.5);
        rightBack.setPower(-.5);
        leftFront.setPower(-.5);
        rightFront.setPower(-.5);
        ShootyMcShooterPants.setPower(.6);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < .6)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        leftBack.setPower(0);
        rightBack.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
        ShootyMcShooterPants.setPower(1);
        sleep(4000);
        lefty.setPower(1);
        righty.setPower(1);
        ShootyMcShooterPants.setPower(1);
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        sleep(3000);
        lefty.setPower(1);
        righty.setPower(1);
        ShootyMcShooterPants.setPower(.6);
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        sleep(3000);
        lefty.setPower(1);
        righty.setPower(1);
        ShootyMcShooterPants.setPower(.6);
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        sleep(3000);
        lefty.setPower(1);
        righty.setPower(1);
        ShootyMcShooterPants.setPower(.6);
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        leftFront.setPower(.5);
        leftBack.setPower(.5);
        rightFront.setPower(-.5);
        rightBack.setPower(-.5);
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        sleep(3000);
        leftFront.setPower(.5);
        leftBack.setPower(.5);
        rightFront.setPower(.5);
        rightBack.setPower(.5);
        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }


}
