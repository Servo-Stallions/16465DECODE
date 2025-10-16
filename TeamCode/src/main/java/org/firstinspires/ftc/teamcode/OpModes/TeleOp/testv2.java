package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
public class testv2 extends OpMode {
    private DcMotor intake = null;


    @Override
    public void init() {
        intake = hardwareMap.get(DcMotor.class, "intake");
        intake.setDirection(DcMotor.Direction.FORWARD);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry.addData("Status", "Initialized");
        ;


    }

    @Override
    public void loop() {

        if (gamepad1.right_bumper) {
            intake.setPower(1);
        } else {
            intake.setPower(0);

        }

        telemetry.addData("Motor Power", intake.getPower());
        telemetry.update();
        ;
    }
}