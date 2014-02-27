/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc4915.ArcadeDriveRobot;

public class Version {

    private static final String VERSION = "v4.9.15-competition code";
    // Should be ready for practice
    // Implements safety changes
    // --Safety enabled for both Harvester a:nd launcher motors
    // --Removed delay on drive straight command
    // --Added debug info for the WindingMotor's Safety
    // Adds default behaviors for Winding and Harvester motors.

    public static String getVersion() {
        return VERSION;
    }
}
