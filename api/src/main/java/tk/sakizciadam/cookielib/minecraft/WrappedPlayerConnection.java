package tk.sakizciadam.cookielib.minecraft;

import org.bukkit.Bukkit;
import tk.sakizciadam.cookielib.CookieLib;
import tk.sakizciadam.cookielib.packet.WrappedPacket;
import tk.sakizciadam.cookielib.utils.Logger;
import tk.sakizciadam.cookielib.utils.reflection.FieldGetter;
import tk.sakizciadam.cookielib.utils.reflection.MethodGetter;
import tk.sakizciadam.cookielib.utils.reflection.ReflectionUtils;

import java.util.ArrayList;
import java.util.logging.Level;

public class WrappedPlayerConnection {
    private final WrappedEntityPlayer entityPlayer;
    private final Class<?> PACKET=ReflectionUtils.getMinecraftClass("Packet");
    private final Class<?> PLAYER_CONNECTION=ReflectionUtils.getMinecraftClass("PlayerConnection");
    private final FieldGetter getConnection = ReflectionUtils.getField("{nms}.EntityPlayer", "playerConnection",PLAYER_CONNECTION);
    private final MethodGetter sendPacket=ReflectionUtils.getMethod(PLAYER_CONNECTION,"sendPacket",null,PACKET);
    private Object playerConnection;
    private ArrayList<WrappedPacket> queue;

    public WrappedPlayerConnection(WrappedEntityPlayer entityPlayer){
        this.entityPlayer=entityPlayer;
        Object obj=getConnection.get(entityPlayer.getEntityPlayer());

        if(obj==null){
            Bukkit.getScheduler().runTaskLater(CookieLib.getLib(), new Runnable() {


                @Override
                public void run() {
                    playerConnection=PLAYER_CONNECTION.cast(getConnection.get(entityPlayer));

                    if(playerConnection==null){
                        Logger.log(Level.SEVERE,"Player connection for "+entityPlayer.getEntityPlayer().toString()+" is null!");
                        return;
                    }

                    if(!queue.isEmpty()){
                        queue.forEach(WrappedPlayerConnection.this::sendPacket);
                    }

                }
            },10l);
            return;
        }

        this.playerConnection=PLAYER_CONNECTION.cast(obj);

        if(playerConnection==null){
            Logger.log(Level.SEVERE,"Player connection for "+entityPlayer.getEntityPlayer().toString()+" is null!");
        }
    }

    public void sendPacket(WrappedPacket packet){
       if(this.playerConnection==null){
           Logger.log(Level.WARNING,"Player connection for "+entityPlayer.getEntityPlayer().toString()+" is null, adding packets to the queue!");
           queue.add(packet);
           return;
       }

        this.sendPacket.invoke(this.playerConnection,packet.getRaw());

    }


}
