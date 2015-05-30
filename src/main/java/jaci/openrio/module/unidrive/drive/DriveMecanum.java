package jaci.openrio.module.unidrive.drive;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import jaci.openrio.module.unidrive.UDPreference;

/**
 * The DriveManager for Mecanum systems (Cartesian). This will handle influencing of the RobotDrive for Mecanum wheel drives
 * with the values specified in the Configuration.
 *
 * @author Jaci
 */
public class DriveMecanum implements DriveManager {
    @Override
    public String type() {
        return "MECANUM";
    }

    @Override
    public void init() { }

    @Override
    public void tick() {
        Joystick joy = UDPreference.joystick;
        if (UDPreference.JOYSTICK_LAYOUT.toLowerCase().equals("xbox_stick")) {
            UDPreference.drive.mecanumDrive_Cartesian(joy.getX(GenericHID.Hand.kLeft), joy.getY(GenericHID.Hand.kLeft), joy.getX(GenericHID.Hand.kRight), 0);
        }
    }
}
