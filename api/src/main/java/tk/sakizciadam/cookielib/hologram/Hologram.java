package tk.sakizciadam.cookielib.hologram;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.*;

public class Hologram {
    private ArmorStand armorStand;
    private String text;

    public Hologram(String text){
        this.text=text;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void spawn(Location location){
        this.armorStand= (ArmorStand) location.getWorld().spawnEntity(location,EntityType.ARMOR_STAND);
        this.armorStand.setGravity(false);
        this.armorStand.setCanPickupItems(false);
        this.armorStand.setCustomName(text);
        this.armorStand.setCustomNameVisible(true);
        this.armorStand.setVisible(false);
    }

    public void teleport(Location location){
        this.armorStand.teleport(location);
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }
}
