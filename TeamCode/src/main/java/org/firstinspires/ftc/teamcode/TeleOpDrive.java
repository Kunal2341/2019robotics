package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "Tel", group = "Sensor")
public class TeleOpDrive extends LinearOpMode{

    HardwarePushBot robot = new
    HardwarePushBot();

    //drive variables
    //double PI = Math.PI;
    //double power = 0.5 * Math.cos(PI*(-gamepad1.right_stick_y)-PI) + 0.5;
    //double turn_Speed = -gamepad1.left_stick_x * 0.5;

    @Override
    public void runOpMode() throws InterruptedException {

        // Intialize the robot's hardware from HardwareMap amd allows you to run all this code within TeleOp
        robot.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.addData("Hello Driver", "Press Play Button");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Drive variables
            double rightPower = -gamepad1.right_stick_y * 0.8;  //what's the 0.8
            double leftPower = -gamepad1.left_stick_y * 0.8;
            double liftPower = -gamepad2.right_stick_y * 0.8;
            double gripPower = -gamepad2.left_stick_y * 0.8;

            double reverse_Thres = -0.3;
            double forward_Thres = 0.3;

            double up_Thres = 0.3;
            double down_Thres = -0.3;

            double drive_Speed = 1.0;
            double turn_Speed = 0.5;

            double intake_Speed = 0.7;         // mentions grip lift and intake
            double lift_Speed = 1.0;

            double grip_speed = 0.5;
//:)
            // Send calculated power to wheels
            // Drive system
            if (rightPower < forward_Thres && rightPower > reverse_Thres && leftPower < forward_Thres && leftPower > reverse_Thres) {
                DriveMethods.stopRightMotors();
                DriveMethods.stopLeftMotors();
            } else if (rightPower != 0.0 && leftPower != 0.0) {
                DriveMethods.driveRight(rightPower * drive_Speed);
                DriveMethods.driveLeft(leftPower * drive_Speed);
            } else if (rightPower != 0.0 && leftPower == 0.0) {
                DriveMethods.driveRight(rightPower * drive_Speed);
            } else if (leftPower != 0.0 && rightPower == 0.0) {
                DriveMethods.driveLeft(leftPower * drive_Speed);
            } else if (rightPower < reverse_Thres && leftPower > forward_Thres) {
                DriveMethods.driveLeft(leftPower * turn_Speed);
                DriveMethods.driveRight(rightPower * turn_Speed);
            } else if (rightPower > forward_Thres && leftPower < reverse_Thres) {
                DriveMethods.driveLeft(leftPower * turn_Speed);
                DriveMethods.driveRight(rightPower * turn_Speed);
            }
            //take out left power

            /*if(-gamepad1.right_stick_y != 0) {
                DriveMethods.driveRight(power);
                DriveMethods.driveLeft(power);
            }else if(-gamepad1.right_stick_x < 0){
                DriveMethods.driveRight(turn_Speed);
                DriveMethods.driveLeft(-turn_Speed);
            }else if(-gamepad1.left_stick_x > 0){
                 DriveMethods.driveRight(-turn_Speed);
                DriveMethods.driveLeft(turn_Speed);
            }else{
                DriveMethods.stopRightMotors();
                DriveMethods.stopLeftMotors();
            }
            */

           /* if (power < forward_Thres && power > reverse_Thres && power < forward_Thres && power > reverse_Thres) {
                DriveMethods.stopRightMotors();
                DriveMethods.stopLeftMotors();
            } else if (power != 0.0 && power != 0.0) {
                DriveMethods.driveRight(power * drive_Speed);
                DriveMethods.driveLeft(power * drive_Speed);
            } else if (power != 0.0 && power == 0.0) {
                DriveMethods.driveRight(power * drive_Speed);
            } else if (power != 0.0 && power == 0.0) {
                DriveMethods.driveLeft(power * drive_Speed);
            } else if (power < reverse_Thres && power > forward_Thres) {
                DriveMethods.driveLeft(power * turn_Speed);
                DriveMethods.driveRight(power * turn_Speed);
            } else if (power > forward_Thres && power < reverse_Thres) {
                DriveMethods.driveLeft(power * turn_Speed);
                DriveMethods.driveRight(power * turn_Speed);
            }
*/

            if(gamepad2.y) {
                DriveMethods.startIntake(intake_Speed);
            }
            if(gamepad2.x){
                DriveMethods.stopIntake();
            }
            if(gamepad2.a){
                DriveMethods.startIntake(intake_Speed * -0.5);
            }

            if (liftPower > up_Thres)
                DriveMethods.liftUp(lift_Speed * 0.5);

            if (liftPower < down_Thres)
                DriveMethods.liftDown(lift_Speed * -0.5);

            if (liftPower == 0)
                DriveMethods.stopLift();
// what if youre not within threshold or at 0, stop

            if (gripPower > up_Thres)
                DriveMethods.gripUp(grip_speed * 0.5);

            if (gripPower < down_Thres)
                DriveMethods.gripDown(grip_speed * -0.5);
                 // mentions grip power
            if (gripPower == 0)
                DriveMethods.stopGrip();


        }

        }
    }
