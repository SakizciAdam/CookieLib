package tk.sakizciadam.cookielib.utils;

import io.netty.channel.Channel;
import org.apache.commons.lang3.RandomUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import tk.sakizciadam.cookielib.CookieLib;
import tk.sakizciadam.cookielib.events.CNPCSpawnEvent;
import tk.sakizciadam.cookielib.hologram.Hologram;
import tk.sakizciadam.cookielib.minecraft.WrappedCraftPlayer;
import tk.sakizciadam.cookielib.minecraft.WrappedEntityPlayer;
import tk.sakizciadam.cookielib.npc.AbstractNPC;
import tk.sakizciadam.cookielib.npc.NPCManager;
import tk.sakizciadam.cookielib.npc.NPCNavigator;
import tk.sakizciadam.cookielib.npc.NPCType;
import tk.sakizciadam.cookielib.npc.interfaces.CreeperNPC;
import tk.sakizciadam.cookielib.packet.WrappedPacket;
import tk.sakizciadam.cookielib.utils.reflection.FieldGetter;
import tk.sakizciadam.cookielib.utils.reflection.MethodGetter;
import tk.sakizciadam.cookielib.utils.reflection.ReflectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

public class PlayerUtils {


    private static HashMap<UUID, WrappedCraftPlayer> cacheCraftPlayer=new HashMap<>();

    public static WrappedCraftPlayer get(Player player){
        UUID uuid=cacheCraftPlayer.keySet().stream().filter(u -> u.equals(player.getUniqueId())).findFirst().orElse(null);

        if(uuid==null){
            Logger.log(Level.SEVERE,"Could not get WrappedCraftPlayer for "+player.getUniqueId());
            return null;
        }


        return cacheCraftPlayer.getOrDefault(uuid,null);
    }

    public static void sendPacket(Player player, WrappedPacket... wrappedPacket){
        WrappedCraftPlayer craftPlayer=get(player);

        new ArrayList<>(Arrays.asList(wrappedPacket)).forEach(craftPlayer.getWrappedEntityPlayer().getPlayerConnection()::sendPacket);






    }

    public static void init(){
        Listener listener=new Listener() {
            @EventHandler(priority = EventPriority.LOWEST)
            public final void onNPCSpawn(CNPCSpawnEvent event){
                Hologram hologram=new Hologram("Gamer");
                hologram.spawn(event.getAbstractNPC().getLocation());
                
                event.getAbstractNPC().attachHologram(hologram);
                event.getAbstractNPC().removeGoals();
                event.getAbstractNPC().setCanBeAttacked(false);
                event.getAbstractNPC().setCollidable(true);

                NPCManager npcManager=CookieLib.getLib().getNPCManager();

                NPCNavigator navigator=(NPCNavigator)event.getAbstractNPC().getOrCreateAddon(npcManager.getNPCNavigatorClass());
                navigator.targetEntity(Bukkit.getPlayer("SakizciAdam"));
            }

            @EventHandler(priority = EventPriority.LOWEST)
            public final void onPlayerJoin(PlayerJoinEvent e) {
                cacheCraftPlayer.put(e.getPlayer().getUniqueId(), new WrappedCraftPlayer(e.getPlayer()));

                Bukkit.getScheduler().runTaskLater(CookieLib.getLib(), new Runnable() {
                    @Override
                    public void run() {
                        AbstractNPC abstractNPC= NPCManager.get().createNPC(NPCType.CREEPER);


                        abstractNPC.spawn(e.getPlayer().getLocation());

                    }
                },60l);

            }

            @EventHandler(priority = EventPriority.LOWEST)
            public final void onPlayerQuit(PlayerQuitEvent e) {
                if(cacheCraftPlayer.containsKey(e.getPlayer().getUniqueId())){
                    cacheCraftPlayer.remove(e.getPlayer().getUniqueId());
                }



            }


        };

        CookieLib.getLib().getServer().getPluginManager().registerEvents(listener, CookieLib.getLib());
    }
}
