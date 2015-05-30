package jaci.openrio.module.unidrive.drive;

/**
 * The Drive Manager interface for the configuration. This is where Tank and Mecanum drives are done, as well as any other configurations in the future.
 *
 * @author Jaci
 */
public interface DriveManager {

    /**
     * The Type of Drive Manager (Tank, Mecanum, Swerve, etc)
     */
    public String type();

    /**
     * Called when the drive manager is selected
     */
    public void init();

    /**
     * Called each StateTracker periodic tick for Teleoperated and Test modes.
     */
    public void tick();

}
