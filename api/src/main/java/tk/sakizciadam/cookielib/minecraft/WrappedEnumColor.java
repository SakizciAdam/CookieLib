package tk.sakizciadam.cookielib.minecraft;

import net.minecraft.server.v1_8_R3.EnumChatFormat;
import net.minecraft.server.v1_8_R3.MaterialMapColor;

public enum WrappedEnumColor {
    WHITE(0,  "white"),
    ORANGE(1,  "orange"),
    MAGENTA(2,  "magenta"),
    LIGHT_BLUE(3, "light_blue"),
    YELLOW(4,  "yellow"),
    LIME(5,  "lime"),
    PINK(6,  "pink"),
    GRAY(7,  "gray"),
    SILVER(8,  "silver"),
    CYAN(9,  "cyan"),
    PURPLE(10,  "purple"),
    BLUE(11,  "blue"),
    BROWN(12,  "brown"),
    GREEN(13,  "green"),
    RED(14,  "red"),
    BLACK(15, "black");

    private final int id;
    private final String name;

    private WrappedEnumColor(int id, String name) {
        this.id=id;
        this.name=name;

    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
