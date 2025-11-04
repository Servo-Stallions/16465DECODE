package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.pedropathing.util.Timer;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

import java.util.Comparator;
import java.util.List;
@Autonomous
public class BlueFar extends LinearOpMode {

    private Limelight3A limelight;
    private DcMotorEx leftFront;

    private DcMotorEx leftRear;

    private DcMotorEx rightFront;

    private DcMotorEx rightRear;
    private DcMotor ShooterR = null;
    private CRServo servoL = null;
    private CRServo servoR = null;
    private DcMotor ShooterL = null;
    private Servo door = null;
    private Timer opTime;
    private DcMotor intake = null;


    public void runOpMode() throws InterruptedException {
        limelight = hardwareMap.get(Limelight3A.class, "Limelight3A"); // <-- semicolon was missing
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        leftRear.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.REVERSE);
        telemetry.setMsTransmissionInterval(11);
        ShooterL = hardwareMap.get(DcMotor.class, "shooterL");
        ShooterL.setDirection(DcMotor.Direction.FORWARD );
        ShooterR = hardwareMap.get(DcMotor.class, "shooterR");
        ShooterR.setDirection(DcMotor.Direction.REVERSE );
        servoR = hardwareMap.get(CRServo.class,"servoR");
        servoL = hardwareMap.get(CRServo.class,"servoL");
        door = hardwareMap.get(Servo.class,"door");
        opTime = new Timer();
        intake = hardwareMap.get(DcMotor.class, "intake");
        intake.setDirection(DcMotor.Direction.FORWARD );
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        limelight.pipelineSwitch(0);
        limelight.start();

        waitForStart();
        while (opModeIsActive()) {


            LLResult result = limelight.getLatestResult();
            int seenId = -1; // -1 = none this frame

            if (result != null && result.isValid()) {

                Pose3D botpose = result.getBotpose();
                telemetry.addData("tx (primary)", result.getTx());
                telemetry.addData("ty (primary)", result.getTy());
                telemetry.addData("ta (primary area)", result.getTa());
                telemetry.addData("Botpose", botpose);

                List<LLResultTypes.FiducialResult> tags = result.getFiducialResults();

                if (tags != null && !tags.isEmpty()) {
                    LLResultTypes.FiducialResult best = tags.stream()
                            .max(Comparator.comparingDouble(LLResultTypes.FiducialResult::getTargetArea))
                            .orElse(null);
                    if (best != null) {
                        seenId = best.getFiducialId();
                        telemetry.addData("AprilTag ID", seenId);
                        telemetry.addData("Tag area", best.getTargetArea());
                        telemetry.addData("Tag tx", result.getTx());
                        telemetry.addData("Tag ty", result.getTy());
                    }
                }

            }else {
                telemetry.addLine("No valid Limelight result this frame.");
            }if(seenId == 21) {
                telemetry.addLine("Tag 21 found");
                telemetry.update();
                Backward( 2700);
                rTurn(1100);
                while (opModeIsActive()) {
            if (opTime.getElapsedTimeSeconds() > 3.94-0.2){
                ShooterR.setPower(0.60);
                ShooterL.setPower(0.60);}
                if (opTime.getElapsedTimeSeconds() > 7-0.2){
                    door.setPosition(.57);
                    telemetry.addLine("Door movement");
                    telemetry.update();}
                if (opTime.getElapsedTimeSeconds() > 7.15-0.2){
                    servoR.setPower(1);
                    telemetry.addLine("ServoR movement");
                    telemetry.update();}
                if (opTime.getElapsedTimeSeconds() > 10.15-0.2){
                    servoR.setPower(0);
                door.setPosition(0.96);
                    telemetry.addLine("Door movement 2 and servoR stop");
                    telemetry.update();}
                if (opTime.getElapsedTimeSeconds() > 10.5-0.2){
                    ShooterR.setPower(0.70);
                    ShooterL.setPower(0.70);
                    servoL.setPower(-.8);
                    telemetry.addLine("ServoL active");
                    telemetry.update();}
                if (opTime.getElapsedTimeSeconds() > 11-0.2){
                    servoL.setPower(0);
                    intake.setPower(1);
                    telemetry.addLine("servoL stop");
                    telemetry.update();}
                if (opTime.getElapsedTimeSeconds() > 11.7-0.2){
                    servoL.setPower(-.8);
                    telemetry.addLine("ServoL 2 active");
                    telemetry.update();}
                if (opTime.getElapsedTimeSeconds() > 12.2){
                    intake.setPower(0);
                ShooterR.setPower(0);
                ShooterL.setPower(0);}
                    if (opTime.getElapsedTimeSeconds() > 12.3+5){
                        servoL.setPower(0);
                        rightRear.setPower(-0.5);
                        rightFront.setPower(-0.5);
                        leftFront.setPower(-0.5);
                        leftRear.setPower(-0.5);}
                    if (opTime.getElapsedTimeSeconds() > 12.8+5) {
                        rightRear.setPower(-1);
                        rightFront.setPower(1);
                        leftFront.setPower(-1);
                        leftRear.setPower(1);}
                if (opTime.getElapsedTimeSeconds() > 13.1+5.3){
                    rightRear.setPower(0);
                    rightFront.setPower(0);
                    leftFront.setPower(0);
                    leftRear.setPower(0);
                    sleep(30000);}}
            }else if (seenId == 22) {
                telemetry.addLine("Tag 22 found");
                telemetry.update();
                Backward( 2700);
                rTurn(1100);
                while (opModeIsActive()) {
                    if (opTime.getElapsedTimeSeconds() > 3.94-0.2){
                        ShooterR.setPower(0.60);
                        ShooterL.setPower(0.60);}
                    if (opTime.getElapsedTimeSeconds() > 7-0.2){
                        door.setPosition(.96);
                        telemetry.addLine("Door movement");
                        telemetry.update();}
                    if (opTime.getElapsedTimeSeconds() > 7.15-0.2){
                        servoL.setPower(-1);intake.setPower(1);}
                    if (opTime.getElapsedTimeSeconds() > 10.15-0.2){
                        servoL.setPower(0);
                    if (opTime.getElapsedTimeSeconds() > 10.5-0.2){
                        ShooterR.setPower(0.6);
                        ShooterL.setPower(0.6);
                        servoR.setPower(1);
                        telemetry.update();}
                    if (opTime.getElapsedTimeSeconds() > 11-0.2){
                        servoR.setPower(0);
                        intake.setPower(0);
                        intake.setPower(1);}}
                    if (opTime.getElapsedTimeSeconds() > 11.3-0.2){
                        door.setPosition(0.57);}
                    if (opTime.getElapsedTimeSeconds() > 11.7-0.2){
                        servoL.setPower(-1);}
                    if (opTime.getElapsedTimeSeconds() > 12.3+6.3){
                        servoL.setPower(0);
                        intake.setPower(0);
                        ShooterR.setPower(0);
                        ShooterL.setPower(0);
                        rightRear.setPower(-0.5);
                        rightFront.setPower(-0.5);
                        leftFront.setPower(-0.5);
                        leftRear.setPower(-0.5);}
                    if (opTime.getElapsedTimeSeconds() > 12.8+6.3) {
                        rightRear.setPower(-1);
                        rightFront.setPower(1);
                        leftFront.setPower(-1);
                        leftRear.setPower(1);}
                    if (opTime.getElapsedTimeSeconds() > 13.1+6.6){
                        rightRear.setPower(0);
                        rightFront.setPower(0);
                        leftFront.setPower(0);
                        leftRear.setPower(0);
                        sleep(30000);}}
            }else if (seenId == 23) {
                telemetry.addLine("Tag 23 found");
                telemetry.update();
                Backward( 2700);
                rTurn(1100);
                while (opModeIsActive()) {
                    if (opTime.getElapsedTimeSeconds() > 3.94-0.2){
                        ShooterR.setPower(0.60);
                        ShooterL.setPower(0.60);}
                    if (opTime.getElapsedTimeSeconds() > 7-0.2){
                        door.setPosition(.96);
                        telemetry.addLine("Door movement");
                        telemetry.update();}
                    if (opTime.getElapsedTimeSeconds() > 7.15-0.2){
                        servoL.setPower(-1);intake.setPower(1);}
                    if (opTime.getElapsedTimeSeconds() > 10.15-0.2){
                        servoL.setPower(0);
                        door.setPosition(0.57);
                    intake.setPower(1);}
                    if (opTime.getElapsedTimeSeconds() > 10.5-0.2){
                        ShooterR.setPower(0.6);
                        ShooterL.setPower(0.6);
                        servoL.setPower(-.8);
                        telemetry.addLine("ServoL active");
                        telemetry.update();}
                    if (opTime.getElapsedTimeSeconds() > 11-0.2){
                        servoL.setPower(0);
                        intake.setPower(0);
                        telemetry.addLine("servoL stop");
                        telemetry.update();}
                    if (opTime.getElapsedTimeSeconds() > 11.7-0.2){
                        servoR.setPower(.8);}
                    if (opTime.getElapsedTimeSeconds() > 12.3+5){
                        servoR.setPower(0);
                        rightRear.setPower(-0.5);
                        rightFront.setPower(-0.5);
                        leftFront.setPower(-0.5);
                        leftRear.setPower(-0.5);}
                    if (opTime.getElapsedTimeSeconds() > 12.8+5) {
                        rightRear.setPower(-1);
                        rightFront.setPower(1);
                        leftFront.setPower(-1);
                        leftRear.setPower(1);}
                    if (opTime.getElapsedTimeSeconds() > 13.1+5.3){
                        rightRear.setPower(0);
                        rightFront.setPower(0);
                        leftFront.setPower(0);
                        leftRear.setPower(0);
                        sleep(30000);}}
            }else if (seenId == -1) {
                telemetry.addLine("No april tag found");
                /*Backward( 2600);
                rTurn(1150);
                sleep(1000);
                shootPower=1;
                sleep(4000);*/


            }
            telemetry.update();
        }

    }
    public void forward(int x) {
        leftRear.setPower(0.5);
        rightRear.setPower(0.5);
        leftFront.setPower(0.5);
        rightFront.setPower(0.5);
        sleep(x);
        leftRear.setPower(0);
        rightRear.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
    }
    public void Backward(int x) {
        leftRear.setPower(-0.5);
        rightRear.setPower(-0.5);
        leftFront.setPower(-0.5);
        rightFront.setPower(-0.5);
        sleep(x);
        leftRear.setPower(0);
        rightRear.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
    }
    public void rTurn(int x) {
        leftRear.setPower(-0.5);
        rightRear.setPower(0.5);
        leftFront.setPower(-0.5);
        rightFront.setPower(0.5);
        sleep(x);
        leftRear.setPower(0);
        rightRear.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
    }
    public void lTurn(int x) {
        leftRear.setPower(0.5);
        rightRear.setPower(-0.5);
        leftFront.setPower(0.5);
        rightFront.setPower(-0.5);
        sleep(x);
        leftRear.setPower(0);
        rightRear.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
    }

    public void strafeRight(int y){
        rightRear.setPower(1);
        rightFront.setPower(-1);
        leftFront.setPower(1);
        leftRear.setPower(-1);
        sleep(y);
        rightRear.setPower(0);
        rightFront.setPower(0);
        leftFront.setPower(0);
        leftRear.setPower(0);
    }
    public void strafeLeft(int y){
        rightRear.setPower(-1);
        rightFront.setPower(1);
        leftFront.setPower(-1);
        leftRear.setPower(1);
        sleep(y);
        rightRear.setPower(0);
        rightFront.setPower(0);
        leftFront.setPower(0);
        leftRear.setPower(0);
    }

}