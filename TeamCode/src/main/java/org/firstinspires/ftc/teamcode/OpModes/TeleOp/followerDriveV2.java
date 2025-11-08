package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.drawCurrent;
import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.drawCurrentAndHistory;
import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;
import com.bylazar.configurables.annotations.IgnoreConfigurable;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.util.PoseHistory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Configurable
@TeleOp

public class followerDriveV2 extends OpMode{
    public static Follower follower;
    public static Pose startingPose;
    private boolean automatedDrive;
    private final Pose startpose = new Pose(0,0,0);

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor intake = null;

    private DcMotor shooterL = null;

    private DcMotor shooterR = null;

    private CRServo servoL = null;

    private CRServo servoR = null;

    private Servo door = null;
    double dore = .76;
    double toggle = 0;
    double kms = 0;

    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startingPose == null ? new Pose() : startingPose);
        follower.update();
        intake = hardwareMap.get(DcMotor.class, "intake");
        intake.setDirection(DcMotor.Direction.FORWARD );
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooterL = hardwareMap.get(DcMotor.class, "shooterL");
        shooterL.setDirection(DcMotor.Direction.FORWARD );
        shooterR = hardwareMap.get(DcMotor.class, "shooterR");
        shooterR.setDirection(DcMotor.Direction.REVERSE );
        servoL = hardwareMap.get(CRServo.class, "servoL");
        servoR = hardwareMap.get(CRServo.class, "servoR");
        door = hardwareMap.get(Servo.class, "door");
        intake = hardwareMap.get(DcMotor.class, "intake");
        intake.setDirection(DcMotor.Direction.FORWARD );
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void init_loop() {

    }

    public void start() {

        follower.startTeleopDrive();
    }

    public void loop() {
        follower.update();
        if (!automatedDrive) {
            follower.setTeleOpDrive(
                    gamepad1.left_stick_y,
                    gamepad1.left_stick_x,
                    gamepad1.right_stick_x,
                    true // Robot Centric
            );
        }
        if (gamepad1.b) {
            intake.setPower(1);
        }
        else if (gamepad1.x) {
            intake.setPower(-1);
        }
        else {
            intake.setPower(0);
        }
        if (gamepad1.a) {
            shooterR.setPower(.67);
            shooterL.setPower(.67);

        }
        else if (gamepad1.y) {
            shooterR.setPower(-.67);
            shooterL.setPower(-.67);}

            //else if (gamepad1.b) {
            //intake.setPower(1);      }

        else {
            shooterL.setPower(0);
            shooterR.setPower(0);

        }
        if (gamepad1.dpad_up) {

            door.setPosition(0.78);

        }
        else if (gamepad1.dpad_down) {
            servoR.setPower(-1);
        }
        else {
            servoR.setPower(0);

        }
        if (gamepad1.dpad_left) {

        }
        else if (gamepad1.dpad_right) {
            servoL.setPower(1);
        }
        else {
            servoL.setPower(0);

        }/*
        if (gamepad1.right_bumper){
            door.setPosition(.57);
            //dore = dore - 0.05;
            //door.setPosition(dore);
        }
        else if (gamepad1.left_bumper){
            door.setPosition(0.96);
            //dore = dore + 0.05;
            //door.setPosition(dore);

        }else {
            door.setPosition(0.78);

        }*/

        //r2 L1 M0
        if (gamepad1.left_bumper) {
            /*kms=1;
            if (toggle == 2) {
                door.setPosition(0.78);
                toggle = 0;
                kms=0;
            } else if ((toggle == 1) && (kms == 1)) {
                door.setPosition(.57);
                toggle = 2;
                kms=0;

            } else if ((toggle == 0) && (kms == 1)) {
                door.setPosition(.57);
                toggle = 2;
                kms=0;

            }*/
            door.setPosition(.40);
        }
        else if (gamepad1.right_bumper){
           /* kms=1;

            if ((toggle == 1) && (kms == 1)) {
                door.setPosition(0.78);
                toggle=0;
                kms=0;
            }else  if ((toggle == 0) && (kms == 1)) {
            door.setPosition(0.96);
                kms=0;
                toggle=1;}
            else if ((toggle == 2) && (kms == 1)) {
            door.setPosition(0.96);
                kms=0;
                toggle=1;}*/
            door.setPosition(1.2);

        }       else if (gamepad1.touchpad){

            door.setPosition(0.78);

        }
        if (gamepad1.left_trigger > 0.1) {
            servoR.setPower(1);
        }
        if (gamepad1.right_trigger > 0.1) {
            servoL.setPower(-1);
        }

        /*double toggle = 0;
        //r2 L1 M0
        if (gamepad1.right_bumper && toggle == 2) {
            door.setPosition(0.78);
            toggle = 0;
        }
        if (gamepad1.right_bumper && toggle == 1) {
            door.setPosition(.57);
            toggle = 2;
        }
        if (gamepad1.right_bumper && toggle == 0) {
            door.setPosition(.57);
            toggle = 2;
        }

        if (gamepad1.left_bumper && toggle == 1) {
            door.setPosition(0.78);
            toggle = 0;
        }
        if (gamepad1.left_bumper && toggle == 0) {
            door.setPosition(0.96);
            toggle = 1;
        }
        if (gamepad1.left_bumper && toggle == 2) {
            door.setPosition(0.96);
            toggle = 1;
        }*/
        }}