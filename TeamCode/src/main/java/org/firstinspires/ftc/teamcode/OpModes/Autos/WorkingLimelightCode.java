package org.firstinspires.ftc.teamcode.OpModes.Autos;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

import java.util.Comparator;
import java.util.List;
@Autonomous
public class WorkingLimelightCode extends LinearOpMode {

    private Limelight3A limelight;
    private DcMotorEx leftFront;

    private DcMotorEx leftRear;

    private DcMotorEx rightFront;

    private DcMotorEx rightRear;


    public void runOpMode() throws InterruptedException {
        limelight = hardwareMap.get(Limelight3A.class, "Limelight3A"); // <-- semicolon was missing
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        leftFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        telemetry.setMsTransmissionInterval(11);

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
                telemetry.addLine("Tag 21 found!");
                telemetry.update();
                sleep(1000);
            }else if (seenId == 22) {
                telemetry.addLine("Tag 22 found!");
                telemetry.update();
                sleep(1000);
            }else if (seenId == 23) {
                telemetry.addLine("Tag 23 found!");
                telemetry.update();
            sleep(1000);
            }else if (seenId == -1) {
                telemetry.addLine("yeaaaaaaah so theres no april tag");
            }
            telemetry.update();
        }
    }
}