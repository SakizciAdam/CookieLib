package tk.sakizciadam.cookielib.npc;

import tk.sakizciadam.cookielib.utils.NMSUtils;
import tk.sakizciadam.cookielib.utils.reflection.QuickReflection;
import tk.sakizciadam.cookielib.utils.reflection.ReflectionUtils;

import java.util.Map;

public enum NPCType {
    SHEEP("Sheep",91,"EntitySheep","CSheep$CSheepEntity",15198183, 16758197),
    CREEPER("Creeper",50, "EntityCreeper","CCreeper$CCreeperEntity",894731, 0)
    ;

    private final String name,vanillaClassName,customClassName;
    private final int vanillaID,x1,x2;

    NPCType(String name,int vanillaID,String vanillaClassName,String customClassName,int x1,int x2){
        this.name=name;
        this.vanillaID  = vanillaID;
        this.vanillaClassName=vanillaClassName;
        this.customClassName=customClassName;
        this.x1=x1;
        this.x2=x2;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public String getName() {
        return name;
    }

    public int getVanillaID() {
        return vanillaID;
    }

    public String getCustomClassName() {
        return customClassName;
    }

    public String getVanillaClassName() {
        return vanillaClassName;
    }

    public Class<? extends AbstractNPC> getNPCClass(){
        return ReflectionUtils.getClass(NMSUtils.COOKIE_NPC_NMS+"."+this.getCustomClassName());
    }

    public Class<?> getVanillaClass(){
        return ReflectionUtils.getMinecraftClass(getVanillaClassName());
    }

    @Override
    public String toString() {
        return "NPCType{" +
                "name='" + name + '\'' +
                ", vanillaClassName='" + vanillaClassName + '\'' +
                ", customClassName='" + customClassName + '\'' +
                ", vanillaID=" + vanillaID +
                '}';
    }
}
