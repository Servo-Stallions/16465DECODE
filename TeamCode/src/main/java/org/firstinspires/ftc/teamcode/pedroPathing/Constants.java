package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.ThreeWheelConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {

    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(7.8)
            .forwardZeroPowerAcceleration(-50.64720731991507)
            .lateralZeroPowerAcceleration(-74.60771544812575)
            .useSecondaryTranslationalPIDF(true)
            .useSecondaryHeadingPIDF(true)
            .useSecondaryDrivePIDF(true)
            .centripetalScaling(0.00009)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.09, 0, 0.012, 0))
            .headingPIDFCoefficients(new PIDFCoefficients(1.8, 0, 0.1, 0))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.065, 0, 0, 0.6, 0))
            .secondaryTranslationalPIDFCoefficients(
                    new PIDFCoefficients(0.25, 0, 0.02, 0)
            )
            .secondaryHeadingPIDFCoefficients(new PIDFCoefficients(1.8, 0, 0.125, 0))
            .secondaryDrivePIDFCoefficients(
                    new FilteredPIDFCoefficients(0.12, 0, 0, 0.6, 0)
            );

    public static MecanumConstants driveConstants = new MecanumConstants()
            .leftFrontMotorName("leftFront")
            .leftRearMotorName("leftRear")
            .rightFrontMotorName("rightFront")
            .rightRearMotorName("rightRear")
            .leftFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .leftRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .xVelocity(75.72916299871545)
            .yVelocity(60.55093540552427);

    public static ThreeWheelConstants localizerConstants =
            new ThreeWheelConstants()
                    .forwardTicksToInches(
                            (0.0029484821524663665 +
                                    0.0029229016217046495 +
                                    0.002942851128369217 +
                                    0.0029449372915088426 +
                                    0.0029242569639630037) /
                                    5
                    )
                    .strafeTicksToInches(
                            (0.002881137363098747 +
                                    0.002881137363098747 +
                                    0.0028859990752756358 +
                                    0.002869235608335478 +
                                    0.0028457820164093065) /
                                    5
                    )
                    .turnTicksToInches(.00067156)
                    .leftPodY(1)
                    .rightPodY(-1)
                    .strafePodX(-2.5)
                    .leftEncoder_HardwareMapName("leftFront")
                    .rightEncoder_HardwareMapName("rightRear")
                    .strafeEncoder_HardwareMapName("rightFront")
                    .leftEncoderDirection(Encoder.FORWARD)
                    .rightEncoderDirection(Encoder.REVERSE)
                    .strafeEncoderDirection(Encoder.FORWARD);

    public static PathConstraints pathConstraints = new PathConstraints(
            0.995,
            50,
            1,
            1
    );

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .mecanumDrivetrain(driveConstants)
                .threeWheelLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .build();
    }
}



