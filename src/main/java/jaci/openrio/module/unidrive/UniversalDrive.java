package jaci.openrio.module.unidrive;

import jaci.openrio.module.unidrive.drive.DriveManager;
import jaci.openrio.module.unidrive.drive.ManagerRegistry;
import jaci.openrio.toast.core.loader.annotation.Branch;
import jaci.openrio.toast.lib.module.ToastStateModule;
import jaci.openrio.toast.lib.state.RobotState;

/**
 * The Universal Drive API. Universal Drive is a simple way to program a Drive Configuration through Toast's configuration file.
 * Supported Drive Types are Mecanum and Tank Drive, with plans for more in the future.
 *
 * @author Jaci
 */
@Branch(branch = "jaci.openrio.module.unidrive.addon.ToastDroidHandler", dependency = "ToastDroid", method = "toast_droid")
public class UniversalDrive extends ToastStateModule {

    static DriveManager manager;

    @Override
    public String getModuleName() {
        return "Universal-Drive";
    }

    @Override
    public String getModuleVersion() {
        return "1.1.0";
    }

    @Override
    public void prestart() {
        ManagerRegistry.init();
        UDPreference.load();
    }

    @Override
    public void start() {
        if (UDPreference.valid) {
            manager = ManagerRegistry.match(UDPreference.DRIVE_TYPE);
            if (manager == null) {
                UDPreference.valid = false;
                return;
            }
            manager.init();
        }
    }

    @Override
    public void tickState(RobotState state) {
        if (manager != null && (state == RobotState.TELEOP || state == RobotState.TEST)) {
            manager.tick();
        }
    }
}
