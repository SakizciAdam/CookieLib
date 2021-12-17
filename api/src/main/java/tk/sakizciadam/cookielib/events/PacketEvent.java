package tk.sakizciadam.cookielib.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import tk.sakizciadam.cookielib.packet.WrappedPacket;

public class PacketEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled;
    private WrappedPacket wrappedPacket;
    private final Player player;

    public PacketEvent(Player player, WrappedPacket wrappedPacket){
        this.player=player;
        this.wrappedPacket=wrappedPacket;
        this.isCancelled=false;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancelled=b;
    }

    public Player getPlayer() {
        return player;
    }

    public WrappedPacket getWrappedPacket() {
        return wrappedPacket;
    }

    public void setWrappedPacket(WrappedPacket wrappedPacket) {
        this.wrappedPacket = wrappedPacket;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
