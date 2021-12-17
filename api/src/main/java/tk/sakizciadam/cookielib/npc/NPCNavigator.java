package tk.sakizciadam.cookielib.npc;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import tk.sakizciadam.cookielib.npc.addon.NPCAddon;

public abstract class NPCNavigator implements NPCAddon {
    private final AbstractNPC npc;

    public NPCNavigator(AbstractNPC npc){
        this.npc=npc;
    }

    public abstract void walkTo(Location location);

    public abstract void targetEntity(Entity entity);

    public AbstractNPC getNPC() {
        return npc;
    }
}
