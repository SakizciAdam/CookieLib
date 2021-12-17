package tk.sakizciadam.cookielib.npc;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import tk.sakizciadam.cookielib.CookieLib;
import tk.sakizciadam.cookielib.npc.addon.NPCAddon;
import tk.sakizciadam.cookielib.utils.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public abstract class AbstractNPC {
    private final int npcID;
    private final NPCType npcType;
    private boolean canBeAttacked=false,collidable;
    private List<NPCAddon> addons;


    public AbstractNPC(NPCType npcType,int id){
        this.npcType=npcType;
        this.npcID=id;
        this.addons=new ArrayList<>();

    }

    public void spawn(Location location){

        addons.forEach(NPCAddon::onSpawn);
        CookieLib.getLib().getNPCManager().requestSpawnEvent(this);
        addons.forEach(NPCAddon::afterSpawn);
    }

    public NPCAddon getAddon(Class<? extends NPCAddon> clazz){
        return addons.stream().filter(addon -> addon.getClass().equals(clazz)).findFirst().orElse(null);
    }

    public void createAddon(Class<? extends NPCAddon> clazz){
        if(getAddon(clazz)!=null) return;

        try {
            Constructor constructor= null;
            for(Constructor c: clazz.getDeclaredConstructors()){
                if(c.getParameterCount()==1&&c.getParameterTypes()[0].equals(AbstractNPC.class)){
                    constructor=c;
                }
            }

            if(constructor==null){
                Logger.log(Level.SEVERE,"Default constructor not found for "+clazz.getCanonicalName());
                return;
            }




            NPCAddon addon= (NPCAddon) constructor.newInstance(this);
            if(addon!=null){
                Logger.info("Created addon "+clazz.getSimpleName());
                addons.add(addon);
                return;
            }
            Logger.log(Level.SEVERE,"Could not create addon "+clazz.getCanonicalName());

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public NPCAddon getOrCreateAddon(Class<? extends NPCAddon> clazz){
        createAddon(clazz);
        if(getAddon(clazz)!=null) return getAddon(clazz);


        return null;
    }

    public NPCType getNpcType() {
        return npcType;
    }

    public boolean canBeAttacked() {
        return canBeAttacked;
    }

    public void setCanBeAttacked(boolean canBeAttacked) {
        this.canBeAttacked = canBeAttacked;
    }

    public int getNPCID() {
        return npcID;
    }

    public abstract Entity getCraftEntity();

    public abstract void teleport(Location location);

    public abstract Location getLocation();

    public abstract String getName();

    public abstract String getCustomName();

    public abstract void setCustomName(String name);

    public abstract void setCustomNameVisible(boolean b);

    public abstract void destroy();

    public abstract void removeGoals();

    public abstract float getEntitySpeed();

    public boolean isCollidable() {
        return collidable;
    }

    public void setCollidable(boolean b){
        this.collidable=b;
    }

    public abstract int getEntityID();
}
