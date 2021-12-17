package tk.sakizciadam.cookielib.npc;

import net.minecraft.server.v1_8_R3.EntityTypes;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import tk.sakizciadam.cookielib.CookieLib;
import tk.sakizciadam.cookielib.events.CNPCDestroyEvent;
import tk.sakizciadam.cookielib.events.CNPCSpawnEvent;
import tk.sakizciadam.cookielib.utils.HashtablePlus;
import tk.sakizciadam.cookielib.utils.Logger;
import tk.sakizciadam.cookielib.utils.NMSUtils;
import tk.sakizciadam.cookielib.utils.reflection.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class NPCManager {
    private int id=-1;
    private final HashtablePlus<Integer,AbstractNPC> npcs;

    public NPCManager(){
        npcs=new HashtablePlus<>();
    }

    public AbstractNPC getByEntityID(World world, int i){
        for(AbstractNPC abstractNPC:npcs.values()){
            if(abstractNPC.getEntityID()>-1&&abstractNPC.getEntityID()==i&&abstractNPC.getLocation().getWorld()==world){
                return abstractNPC;
            }
        }
        return null;
    }

    public AbstractNPC getByID(int i){
        if(i<=npcs.size()) return null;

        return npcs.getOrDefault(i,null);
    }

    public Class<? extends NPCNavigator> getNPCNavigatorClass(){
        return ReflectionUtils.getClass(NMSUtils.COOKIE_NPC_NMS+".CNPCNavigator");
    }

    public void destroy(int id) {
        if(npcs.containsKey(id)){
            CNPCDestroyEvent event=new CNPCDestroyEvent(npcs.get(id));

            Bukkit.getPluginManager().callEvent(event);

            if(event.isCancelled()){
                return;
            }
            event.getAbstractNPC().destroy();
            npcs.remove(id);

        }
    }

    public void destroy(AbstractNPC abstractNPC){
        if(npcs.containsValue(abstractNPC)){
            try {
                int id=npcs.getFromValue(abstractNPC);

                destroy(id);

            } catch (Exception e){
                e.printStackTrace();
            }


        } else {
            Logger.log(Level.WARNING,"NPC HashTable doesn't contain "+abstractNPC.getNPCID());
        }
    }
    
    public void requestSpawnEvent(AbstractNPC abstractNPC){
        if(abstractNPC==null){
            Logger.log(Level.WARNING,"Invalid npc requesting spawnEvent");
            return;
        }

        if(npcs.containsKey(abstractNPC.getNPCID())){
            Logger.log(Level.WARNING,"ID already exists for "+abstractNPC.toString());
            return;
        }

        npcs.put(abstractNPC.getNPCID(),abstractNPC);
        CNPCSpawnEvent spawnEvent=new CNPCSpawnEvent(abstractNPC);
        Bukkit.getPluginManager().callEvent(spawnEvent);

    }

    public AbstractNPC createNPC(NPCType npcType){
        Class<? extends AbstractNPC> entityClass=ReflectionUtils.getClass("tk.sakizciadam.cookielib.npc.nms."+ NMSUtils.VERSION+".C"+npcType.getName());

        if(entityClass==null){
            Logger.log(Level.SEVERE, "Could not find class for "+npcType.getName());
            return null;
        }


        try {
            return entityClass.getConstructor(Integer.class).newInstance(id++);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void registerEntity(String name, int id, Class<?> nmsClass, Class<?> customClass,int x1,int x2){
        try {

            List<Map<?, ?>> dataMap = new ArrayList<Map<?, ?>>();
            for (Field f : EntityTypes.class.getDeclaredFields()){
                if (f.getType().getSimpleName().equals(Map.class.getSimpleName())){
                    f.setAccessible(true);
                    dataMap.add((Map<?, ?>) f.get(null));
                }
            }

            if (dataMap.get(2).containsKey(id)){
                dataMap.get(0).remove(name);
                dataMap.get(2).remove(id);
            }

            Method method = EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, int.class,int.class,int.class);
            method.setAccessible(true);
            method.invoke(null, customClass, name, id,x1,x2);

        } catch (NoSuchMethodException exception) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        }
    }

    public static NPCManager get(){
        return CookieLib.getLib().getNPCManager();
    }
}
