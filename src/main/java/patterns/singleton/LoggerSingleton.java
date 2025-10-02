package patterns.singleton;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class LoggerSingleton {
    private static final Logger LOGGER = Logger.getLogger("VirtualClassroomManager");

    static {
        LOGGER.setUseParentHandlers(false);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.INFO);
        LOGGER.addHandler(ch);
        LOGGER.setLevel(Level.INFO);
    }

    private LoggerSingleton() {}

    public static Logger getLogger() { return LOGGER; }
}
