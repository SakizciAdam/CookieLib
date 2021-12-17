package tk.sakizciadam.cookielib.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import tk.sakizciadam.cookielib.minecraft.WrappedEntityUseAction;
import tk.sakizciadam.cookielib.npc.AbstractNPC;

public class CNPCUseEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();

    private final AbstractNPC abstractNPC;
    private WrappedEntityUseAction action;
    private boolean isCancelled;

    public CNPCUseEvent(AbstractNPC npc, WrappedEntityUseAction action){
        this.abstractNPC=npc;
        this.action=action;
        this.isCancelled=false;
    }

    public void setAction(WrappedEntityUseAction action) {
        this.action = action;
    }

    public WrappedEntityUseAction getAction() {
        return action;
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

    @Override
    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }
}
