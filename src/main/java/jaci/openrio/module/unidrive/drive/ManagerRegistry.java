package jaci.openrio.module.unidrive.drive;

import java.util.ArrayList;

/**
 * The ManagerRegistry is a class used to register {@link DriveManager} interfaces to work with UniversalDrive. These
 * should be registered during prestart.
 *
 * @author Jaci
 */
public class ManagerRegistry {

    static ArrayList<DriveManager> managers = new ArrayList<>();

    public static void init() {
        register(new DriveMecanum());
        register(new DriveTank());
        register(new DriveArcade());
    }

    /**
     * Register your own manager on the registry.
     */
    public static void register(DriveManager manager) {
        managers.add(manager);
    }

    public static DriveManager match(String name) {
        for (DriveManager manager : managers) {
            if (manager.type().toLowerCase().equals(name.toLowerCase()))
                return manager;
        }
        return null;
    }

}
