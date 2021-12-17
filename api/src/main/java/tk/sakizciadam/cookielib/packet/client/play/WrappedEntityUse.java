package tk.sakizciadam.cookielib.packet.client.play;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import tk.sakizciadam.cookielib.minecraft.WrappedEntityUseAction;
import tk.sakizciadam.cookielib.packet.EnumMinecraftVariable;
import tk.sakizciadam.cookielib.packet.MinecraftVariable;
import tk.sakizciadam.cookielib.packet.Packets;
import tk.sakizciadam.cookielib.packet.WrappedPacket;
import tk.sakizciadam.cookielib.utils.Logger;

import static tk.sakizciadam.cookielib.minecraft.WrappedEntityUseAction.*;

public class WrappedEntityUse extends WrappedPacket {
    @MinecraftVariable(varName = "a")
    private int entityID;

    @EnumMinecraftVariable(varName = "action")
    private String action="";

    public WrappedEntityUse(){
        super(Packets.Client.Play.USE_ENTITY);
    }

    public WrappedEntityUse(Object obj){
        super(Packets.Client.Play.USE_ENTITY,obj);
    }

    public Entity getEntity(World world){
        return world.getEntities().stream().filter(ent -> ent.getEntityId()==entityID).findFirst().orElse(null);
    }

    public WrappedEntityUseAction getAction() {

        try {
            WrappedEntityUseAction a= WrappedEntityUseAction.valueOf(this.action);

            a.name();
            return a;
        } catch (Exception e){
            return UNKNOWN;

        }
    }

    public int getEntityID() {
        return entityID;
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
        onChange("entityID");
    }

    public void setAction(WrappedEntityUseAction action) {
        this.action = action.name();
        onChange("action");
    }

    @Override
    public void read() {
        super.read();
    }

    @Override
    public void onChange(String s) {
        super.onChange(s);
    }
}
