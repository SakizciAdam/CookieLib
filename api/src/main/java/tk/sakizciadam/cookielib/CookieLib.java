package tk.sakizciadam.cookielib;

import io.netty.channel.Channel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import tk.sakizciadam.cookielib.events.CNPCUseEvent;
import tk.sakizciadam.cookielib.events.PacketEvent;
import tk.sakizciadam.cookielib.npc.AbstractNPC;
import tk.sakizciadam.cookielib.npc.NPCManager;
import tk.sakizciadam.cookielib.npc.NPCType;
import tk.sakizciadam.cookielib.packet.Packets;
import tk.sakizciadam.cookielib.packet.client.play.WrappedClientFlying;
import tk.sakizciadam.cookielib.packet.client.play.WrappedEntityUse;
import tk.sakizciadam.cookielib.processor.packet.PacketProcessor;
import tk.sakizciadam.cookielib.packet.WrappedPacket;
import tk.sakizciadam.cookielib.protocol.TinyProtocol;
import tk.sakizciadam.cookielib.utils.Logger;
import tk.sakizciadam.cookielib.utils.PlayerUtils;

import java.util.logging.Level;

public final class CookieLib extends JavaPlugin {

    private TinyProtocol protocol;
    private PacketProcessor packetProcessor;
    private NPCManager npcManager;
    private static CookieLib INSTANCE;


    @Override
    public void onEnable() {
        INSTANCE=this;
        Logger.info("Thanks for installing CookieLib!");




        this.packetProcessor=new PacketProcessor()
        ;
        this.npcManager=new NPCManager();

        NPCType.values();

        for(NPCType npcType:NPCType.values()){
            this.npcManager.registerEntity(npcType.getName(),npcType.getVanillaID(),npcType.getVanillaClass(),npcType.getNPCClass(), npcType.getX1(), npcType.getX2());
            Logger.info("Registered "+npcType.toString());
        }

        Packets.getPackets();
        PlayerUtils.init();


        this.protocol = new TinyProtocol(this) {

            @Override
            public Object onPacketInAsync(Player sender, Channel channel, Object packet) {
                WrappedPacket wrappedPacket=packetProcessor.process(packet,sender);

                if(wrappedPacket==null){
                    Logger.log(Level.WARNING,"Could not process "+packet.getClass().getCanonicalName());
                    return super.onPacketInAsync(sender, channel, packet);
                }

                PacketEvent event=new PacketEvent(sender,wrappedPacket);

                Bukkit.getPluginManager().callEvent(event);

                if(event.isCancelled()){
                    return null;
                }

                //Other stuff

                if(event.getWrappedPacket().getPacketType().equals(Packets.Client.Play.USE_ENTITY)){

                    WrappedEntityUse mL=new WrappedEntityUse(event.getWrappedPacket().getRaw());
                    mL.read();

                    AbstractNPC abstractNPC=npcManager.getByEntityID(sender.getLocation().getWorld(), mL.getEntityID());

                    if(abstractNPC!=null){


                        CNPCUseEvent useEvent=new CNPCUseEvent(abstractNPC, mL.getAction());

                        mL.setAction(useEvent.getAction());

                        Bukkit.getPluginManager().callEvent(useEvent);

                        if(useEvent.isCancelled()){
                            return null;
                        }

                    }
                }





                return super.onPacketInAsync(sender, channel, event.getWrappedPacket().getRaw());
            }

            @Override
            public Object onPacketOutAsync(Player reciever, Channel channel, Object packet) {
                WrappedPacket wrappedPacket=packetProcessor.process(packet,reciever);

                if(wrappedPacket==null) {
                    Logger.log(Level.WARNING,"Could not process "+packet.getClass().getCanonicalName());
                    return super.onPacketInAsync(reciever, channel, packet);
                }

                PacketEvent event=new PacketEvent(reciever,wrappedPacket);

                Bukkit.getPluginManager().callEvent(event);

                if(event.isCancelled()){
                    return null;
                }


                return super.onPacketOutAsync(reciever, channel, event.getWrappedPacket().getRaw());
            }

        };

    }

    @Override
    public void onDisable() {

    }

    public NPCManager getNPCManager() {
        return npcManager;
    }

    public static CookieLib getLib() {
        return INSTANCE;
    }
}
