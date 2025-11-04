package org.firstinspires.ftc.teamcode.pedroPathing;
import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.ftc.localization.constants.ThreeWheelConstants;
import com.pedropathing.ftc.localization.constants.TwoWheelConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(10.9)
            .forwardZeroPowerAcceleration(0)
            .lateralZeroPowerAcceleration(0)
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
            .xVelocity(135.34349571633902)
            .yVelocity(60.55093540552427);
    public static TwoWheelConstants localizerConstants = new TwoWheelConstants()
            .forwardEncoder_HardwareMapName("leftRear")
            .strafeEncoder_HardwareMapName("leftFront")
            .forwardTicksToInches(-0.00669863689717)
            .strafeTicksToInches(0.00337429148311)
            .forwardPodY(2.25)
            .strafePodX(-1)
            .forwardEncoderDirection(Encoder.FORWARD)
            .strafeEncoderDirection(Encoder.REVERSE)
            //.forwardTicksToInches()
            .IMU_HardwareMapName("imu")
            .IMU_Orientation(
                    new RevHubOrientationOnRobot(
                            RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                            RevHubOrientationOnRobot.UsbFacingDirection.UP
                    )
            );
    public static PathConstraints pathConstraints = new PathConstraints(
            0.995,
            50,
            1,
            1
    );
    /*public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(-9.5)
            .strafePodX(-8.5)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);*/
    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .mecanumDrivetrain(driveConstants)
                .twoWheelLocalizer(localizerConstants)
                //.pinpointLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .build();
    }}
