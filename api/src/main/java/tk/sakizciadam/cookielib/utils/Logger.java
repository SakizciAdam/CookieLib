package tk.sakizciadam.cookielib.utils;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public class Logger {
    public static void log(Level level, String msg){
        Bukkit.getLogger().log(level,msg);

    }

    public static void info(String msg){
        Bukkit.getLogger().info(msg);

    }
}
