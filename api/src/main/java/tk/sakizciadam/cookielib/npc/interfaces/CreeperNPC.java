package tk.sakizciadam.cookielib.npc.interfaces;

import tk.sakizciadam.cookielib.minecraft.WrappedEnumColor;

public interface CreeperNPC {
    void setIgnited(boolean b);

    boolean isIgnited();

    int getFuseTicks();

    void setFuseTicks(int x);

    int getMaxFuseTicks();

    void setMaxFuseTicks(int x);
}
