package tk.sakizciadam.cookielib.packet.server.play;

import net.minecraft.server.v1_8_R3.MathHelper;
import org.bukkit.Location;
import tk.sakizciadam.cookielib.packet.MinecraftVariable;
import tk.sakizciadam.cookielib.packet.Packets;
import tk.sakizciadam.cookielib.packet.WrappedPacket;

public class WrappedSpawnEntity extends WrappedPacket {

    @MinecraftVariable(varName = "a")
    protected int entityID;

    @MinecraftVariable(varName = "b")
    protected int locX;

    @MinecraftVariable(varName = "c")
    protected int locY;

    @MinecraftVariable(varName = "d")
    protected int locZ;

    @MinecraftVariable(varName = "h")
    protected int yaw;

    @MinecraftVariable(varName = "i")
    protected int pitch;

    @MinecraftVariable(varName = "j")
    protected int entityType;


    public WrappedSpawnEntity(Object obj) {
        super(Packets.Server.Play.SPAWN_ENTITY, obj);
    }

    public WrappedSpawnEntity(int entityID,int entityType,Location location) {
        super(Packets.Server.Play.SPAWN_ENTITY);
        setEntityID(entityID);
        setEntityType(entityType);
        setLocation(location);
    }


    @Override
    public void read() {
        super.read();
    }

    @Override
    public void onChange(String s) {
        super.onChange(s);
    }

    public int getEntityType() {
        return entityType;
    }

    public int getEntityID() {
        return entityID;
    }

    public void setEntityType(int entityType) {
        this.entityType = entityType;
        onChange("entityType");
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
        onChange("entityID");
    }

    public void setLocation(Location location){
        this.locX= MathHelper.floor(location.getX() * 32.0D);
        this.locY= MathHelper.floor(location.getY() * 32.0D);
        this.locZ= MathHelper.floor(location.getZ() * 32.0D);
        this.pitch = MathHelper.d(location.getPitch() * 256.0F / 360.0F);
        this.yaw = MathHelper.d(location.getYaw() * 256.0F / 360.0F);
        onChange("locX");
        onChange("locY");
        onChange("locZ");
        onChange("pitch");
        onChange("yaw");
    }
}
