package tk.sakizciadam.cookielib.npc.nms.v1_8_R3;

import com.google.common.collect.Sets;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftCreeper;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftSheep;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.CreatureSpawnEvent;
import tk.sakizciadam.cookielib.CookieLib;
import tk.sakizciadam.cookielib.minecraft.WrappedEnumColor;
import tk.sakizciadam.cookielib.npc.AbstractNPC;
import tk.sakizciadam.cookielib.npc.NPCType;
import tk.sakizciadam.cookielib.npc.interfaces.SheepNPC;
import tk.sakizciadam.cookielib.packet.server.play.WrappedSpawnEntity;
import tk.sakizciadam.cookielib.utils.Logger;
import tk.sakizciadam.cookielib.utils.PlayerUtils;
import tk.sakizciadam.cookielib.utils.reflection.QuickReflection;

import java.lang.reflect.Field;
import java.util.List;

public class CSheep extends CEntity implements SheepNPC {

    public CSheep(Integer id) {
        super(NPCType.SHEEP,id);

    }

    public void spawn(Location loc) {



        World mcWorld = ((CraftWorld) loc.getWorld()).getHandle();
        final CSheepEntity entity = new CSheepEntity(mcWorld,this);
        entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        ((CraftLivingEntity) entity.getBukkitEntity()).setRemoveWhenFarAway(false);
        mcWorld.addEntity(entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        setEntity(entity);


        super.spawn(loc);
    }



    @Override
    public void setColor(WrappedEnumColor color) {
        ((CSheepEntity)this.getEntity()).setColor(EnumColor.fromColorIndex(color.getId()));
    }

    @Override
    public WrappedEnumColor getColor() {
        return WrappedEnumColor.valueOf(((CSheepEntity)this.getEntity()).getColor().name());
    }

    public static class CSheepEntity extends EntitySheep {
        private AbstractNPC abstractNPC;


        public CSheepEntity(World world,AbstractNPC abstractNPC) {
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
    }




}
