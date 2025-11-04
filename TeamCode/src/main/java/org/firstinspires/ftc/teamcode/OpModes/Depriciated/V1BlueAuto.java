package org.firstinspires.ftc.teamcode.OpModes.Depriciated;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
@Disabled

public class V1BlueAuto extends LinearOpMode{
    private DcMotor ShootyMcShooterPants = null;
    private CRServo lefty = null;
    private CRServo righty = null;
    private DcMotor leftFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightFront = null;
    private DcMotor rightBack = null;
    private DcMotor Shoot = null;
    private ElapsedTime Runtime = new ElapsedTime();

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightRear");
        ShootyMcShooterPants = hardwareMap.get(DcMotor.class, "Shooter");
        Shoot = hardwareMap.get(DcMotor.class,"Shoot");
        ShootyMcShooterPants.setDirection(DcMotor.Direction.FORWARD);
        lefty = hardwareMap.get(CRServo.class, "Servo1");
        righty = hardwareMap.get(CRServo.class, "Servo2");
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        Shoot.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        forward(1350);
        ShootIt();
        strafeRight(400);
        Runtime.reset();
    }
        public void forward(int x) {
            leftBack.setPower(0.5);
            rightBack.setPower(0.5);
            leftFront.setPower(0.5);
            rightFront.setPower(0.5);
            sleep(x);
            leftBack.setPower(0);
            rightBack.setPower(0);
            leftFront.setPower(0);
            rightFront.setPower(0)
            ;
        }
        public void ShootIt(){
ShootyMcShooterPants.setPower(-0.7);
Shoot.setPower(-0.7);
sleep(4000);
            lefty.setPower(-1);
            righty.setPower(1);
            sleep(500);
            lefty.setPower(-.01);
            righty.setPower(.01);
            sleep(1200);
            lefty.setPower(-1);
            righty.setPower(1);
            sleep(500);
            lefty.setPower(-.01);
            righty.setPower(.01);
            sleep(1000);
            lefty.setPower(-1);
            righty.setPower(1);
            sleep(4000);
            ShootyMcShooterPants.setPower(0);
            Shoot.setPower(0);
            lefty.setPower(0);
            righty.setPower(0);


        }

        public void strafeRight(int y){
            rightBack.setPower(1);
            rightFront.setPower(-1);
            leftFront.setPower(1);
            leftBack.setPower(-1);
            sleep(y);
            rightBack.setPower(0);
            rightFront.setPower(0);
            leftFront.setPower(0);
            leftBack.setPower(0);
        }

    }

