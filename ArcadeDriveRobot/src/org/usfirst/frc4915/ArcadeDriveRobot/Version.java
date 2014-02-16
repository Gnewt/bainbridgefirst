/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc4915.ArcadeDriveRobot;

/**
 *
 * @author Tarkan
 */
public class Version {
    
    private static final String VERSION = "v2.00.01";
    // Adds IntakeDown and IntakeUp commands
    // Adds Magnetic Switch
    // Changed buttons on Joystick to activate the Intake Down and Intake Up instead of Extend/Retract Pneumatics
    // Fixed exceptions when there is no gyroscope (again)
    // Added turnPID(double angle) which turns the robot using PID control (fixes)
    // Testable turnPID(angle)
    // Version system changed
    // PID ratios altered
    // Throttle values updated when robot is stopped
    // Limit switch returns true when pressed
    
    public static String getVersion() {
        return VERSION;
    }
}
