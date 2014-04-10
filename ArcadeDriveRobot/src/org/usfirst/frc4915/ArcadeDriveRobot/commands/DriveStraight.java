// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc4915.ArcadeDriveRobot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4915.ArcadeDriveRobot.Robot;
/**
 *
 */
public class DriveStraight extends Command {
    private static final double KP = 0.03;
    private double timeMovingForward = 2.4;
    //timeout = 2.4 in seconds
    public DriveStraight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        timeMovingForward = Robot.prefs.getDouble("Autonomous_Duration", timeMovingForward);
        Robot.driveTrain.setMaxOutput(0.60);
        Robot.gyroscope.reset();
        setTimeout(timeMovingForward); // 15 ft 4 inches = 3 seconds 3 seconds in competition
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double angle = Robot.gyroscope.getAngle(); // get current heading
        Robot.driveTrain.drive(1.0, (angle) * (KP)); // drive towards heading 0
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }
    // Called once after isFinished returns true
    protected void end() {
        Robot.driveTrain.stop();
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
