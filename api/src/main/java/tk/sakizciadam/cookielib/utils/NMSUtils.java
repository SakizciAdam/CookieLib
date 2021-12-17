package tk.sakizciadam.cookielib.utils;

import org.bukkit.Bukkit;

public class NMSUtils {
    public static String OBC_PREFIX = Bukkit.getServer().getClass().getPackage().getName();
    public static String NMS_PREFIX = OBC_PREFIX.replace("org.bukkit.craftbukkit", "net.minecraft.server");
    public static String VERSION = OBC_PREFIX.replace("org.bukkit.craftbukkit", "").replace(".", "");
    public static String COOKIE_NPC_NMS= "tk.sakizciadam.cookielib.npc.nms."+VERSION;

}
