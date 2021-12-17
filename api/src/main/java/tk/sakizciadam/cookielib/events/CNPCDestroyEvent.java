package tk.sakizciadam.cookielib.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import tk.sakizciadam.cookielib.npc.AbstractNPC;

public class CNPCDestroyEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();

    private final AbstractNPC abstractNPC;
    private boolean isCancelled;

    public CNPCDestroyEvent(AbstractNPC npc){
        this.abstractNPC=npc;
        this.isCancelled=false;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
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
