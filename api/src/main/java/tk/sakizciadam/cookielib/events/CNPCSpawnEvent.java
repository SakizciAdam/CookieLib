package tk.sakizciadam.cookielib.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import tk.sakizciadam.cookielib.npc.AbstractNPC;
import tk.sakizciadam.cookielib.packet.WrappedPacket;

public class CNPCSpawnEvent extends Event{
    private static final HandlerList HANDLERS = new HandlerList();

    private final AbstractNPC abstractNPC;

    public CNPCSpawnEvent(AbstractNPC npc){
        this.abstractNPC=npc;
    }

    public AbstractNPC getAbstractNPC() {
        return abstractNPC;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
