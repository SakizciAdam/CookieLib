package tk.sakizciadam.cookielib.npc.nms.v1_8_R3;

import net.minecraft.server.v1_8_R3.EntityCreature;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import tk.sakizciadam.cookielib.CookieLib;
import tk.sakizciadam.cookielib.npc.AbstractNPC;
import tk.sakizciadam.cookielib.npc.NPCNavigator;
import tk.sakizciadam.cookielib.npc.NPCType;
import tk.sakizciadam.cookielib.utils.Logger;

import java.lang.reflect.Field;
import java.util.logging.Level;

public abstract class CEntity extends AbstractNPC {
    private EntityCreature entity;

    public CEntity(NPCType npcType,Integer id) {
        super(npcType,id);

    }

    public EntityCreature getEntity() {
        return entity;
    }

    public void spawn(Location location){
        super.spawn(location);
    }

    @Override
    public int getEntityID() {
        return entity!=null ? entity.getId() : -1;
    }

    @Override
    public float getEntitySpeed() {

        return (float) ((float) entity.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getValue()*3.2);
    }

    public Entity getCraftEntity() {
        return entity.getBukkitEntity();
    }

    @Override
    public void teleport(Location loc) {
        if(this.entity!=null){
            entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        }
    }

    @Override
    public Location getLocation() {
        return entity!=null ? entity.getBukkitEntity().getLocation() : null;
    }

    @Override
    public String getName() {
        return this.entity.getName();
    }

    @Override
    public String getCustomName() {
        return this.entity.getCustomName();
    }

    @Override
    public void setCustomName(String name) {
        if(this.entity!=null){
            entity.setCustomName(name);
        }
    }

    @Override
    public void setCustomNameVisible(boolean b) {
        if(this.entity!=null){
            entity.setCustomNameVisible(b);
        }
    }

    @Override
    public void destroy() {
        this.entity.getBukkitEntity().remove();
        this.entity=null;
    }

    @Override
    public void removeGoals() {
        try {
            Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
            bField.setAccessible(true);
            Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
            cField.setAccessible(true);

            if(bField==null){
                Logger.log(Level.WARNING,"B field for npc "+getNPCID()+" is null");
                return;
            }

            if(cField==null){
                Logger.log(Level.WARNING,"C field for npc "+getNPCID()+" is null");
                return;
            }

            if(entity==null){
                Logger.log(Level.SEVERE,"EntityCreature for "+getNPCID()+" is null");
                return;
            }

            if(entity.goalSelector==null){
                Logger.log(Level.WARNING,"Goal Selector for npc "+getNPCID()+" is null");
                return;
            }

            if(entity.targetSelector==null){
                Logger.log(Level.WARNING,"Target Selector for npc "+getNPCID()+" is null");
                return;
            }



            bField.set(entity.goalSelector, new UnsafeList<>());
            bField.set(entity.targetSelector, new UnsafeList<>());
            cField.set(entity.goalSelector, new UnsafeList<>());
            cField.set(entity.targetSelector, new UnsafeList<>());


        } catch (Exception exc) {exc.printStackTrace();}
    }

    protected void setEntity(EntityCreature entity){
        this.entity=entity;

    }
}
