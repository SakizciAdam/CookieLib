package tk.sakizciadam.cookielib.minecraft;

import org.bukkit.entity.Player;
import tk.sakizciadam.cookielib.utils.Logger;
import tk.sakizciadam.cookielib.utils.reflection.MethodGetter;
import tk.sakizciadam.cookielib.utils.reflection.ReflectionUtils;

import java.util.logging.Level;

public class WrappedEntityPlayer {
    private final Class<?> CRAFT_PLAYER= ReflectionUtils.getClass("{obc}.entity.CraftPlayer");
    private final Class<?> ENTITY_PLAYER= ReflectionUtils.getClass("{nms}.EntityPlayer");
    private final MethodGetter getPlayerHandle = ReflectionUtils.getMethod(CRAFT_PLAYER, "getHandle",null);
    private Object entityPlayer;
    private WrappedPlayerConnection playerConnection;

    public WrappedEntityPlayer(WrappedCraftPlayer wrappedCraftPlayer){


        this.entityPlayer=ENTITY_PLAYER.cast(getPlayerHandle.invoke(wrappedCraftPlayer.getCraftPlayer()));
        if(entityPlayer==null){
            Logger.log(Level.SEVERE,"EntityPlayer for "+this.getEntityPlayer().toString()+" is null!");
            return;
        }

        this.playerConnection=new WrappedPlayerConnection(this);


    }

    public WrappedPlayerConnection getPlayerConnection() {
        return playerConnection;
    }

    public Object getEntityPlayer() {
        return entityPlayer;
    }
}
