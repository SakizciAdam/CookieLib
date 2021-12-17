package tk.sakizciadam.cookielib.npc.nms.v1_8_R3;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import tk.sakizciadam.cookielib.minecraft.WrappedEnumColor;
import tk.sakizciadam.cookielib.npc.AbstractNPC;
import tk.sakizciadam.cookielib.npc.NPCType;
import tk.sakizciadam.cookielib.npc.interfaces.CreeperNPC;
import tk.sakizciadam.cookielib.npc.interfaces.SheepNPC;
import tk.sakizciadam.cookielib.utils.reflection.QuickReflection;

public class CCreeper extends CEntity implements CreeperNPC {

    public CCreeper(Integer id) {
        super(NPCType.CREEPER,id);

    }

    public void spawn(Location loc) {



        World mcWorld = ((CraftWorld) loc.getWorld()).getHandle();
        final CCreeperEntity entity = new CCreeperEntity(mcWorld,this);
        entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        ((CraftLivingEntity) entity.getBukkitEntity()).setRemoveWhenFarAway(false);
        mcWorld.addEntity(entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        setEntity(entity);


        super.spawn(loc);
    }

    @Override
    public void setIgnited(boolean b) {
        ((CCreeperEntity)this.getEntity()).setPowered(b);
    }

    @Override
    public boolean isIgnited() {
        return ((CCreeperEntity)this.getEntity()).isPowered();
    }

    @Override
    public int getFuseTicks() {
        CCreeperEntity entity=((CCreeperEntity)this.getEntity());
        return (int) QuickReflection.getField("fuseTicks",EntityCreeper.class,entity);
    }

    @Override
    public void setFuseTicks(int x) {
        CCreeperEntity entity=((CCreeperEntity)this.getEntity());
        QuickReflection.setField("fuseTicks",EntityCreeper.class,entity,x);
    }

    @Override
    public int getMaxFuseTicks() {
        CCreeperEntity entity=((CCreeperEntity)this.getEntity());
        return (int) QuickReflection.getField("maxFuseTicks",EntityCreeper.class,entity);

    }

    @Override
    public void setMaxFuseTicks(int x) {
        CCreeperEntity entity=((CCreeperEntity)this.getEntity());
        QuickReflection.setField("maxFuseTicks",EntityCreeper.class,entity,x);
    }


    public static class CCreeperEntity extends EntityCreeper {
        private AbstractNPC abstractNPC;


        public CCreeperEntity(World world,AbstractNPC abstractNPC) {
            super(world);
            this.abstractNPC=abstractNPC;
        }

        public boolean isInvulnerable(DamageSource damagesource) {
            if(!this.abstractNPC.canBeAttacked()){
                return true;
            }
            return super.isInvulnerable(damagesource);
        }

        @Override
        public void g(double d0, double d1, double d2) {
            if(!abstractNPC.isCollidable()) return;

            super.g(d0, d1, d2);
        }

        public float bE(){
            return 0.42f;
        }


    }




}
