package tk.sakizciadam.cookielib.minecraft;

import org.bukkit.entity.Player;
import tk.sakizciadam.cookielib.utils.Logger;
import tk.sakizciadam.cookielib.utils.reflection.MethodGetter;
import tk.sakizciadam.cookielib.utils.reflection.ReflectionUtils;

import java.util.logging.Level;

public class WrappedCraftPlayer {


    private final Class<?> CRAFT_PLAYER= ReflectionUtils.getClass("{obc}.entity.CraftPlayer");
    private Object craftPlayer;
    private WrappedEntityPlayer wrappedEntityPlayer;

    public WrappedCraftPlayer(Player player){
        if(player==null||!player.isOnline()) return;
        this.craftPlayer=CRAFT_PLAYER.cast(player);

        if(this.craftPlayer==null){
            Logger.log(Level.SEVERE,"Could not get craftPlayer for "+player.getUniqueId());
            return;
        }

        this.wrappedEntityPlayer=new WrappedEntityPlayer(this);


    }

    public WrappedEntityPlayer getWrappedEntityPlayer() {
        return wrappedEntityPlayer;
    }

    public Object getCraftPlayer() {
        return craftPlayer;
    }
}
