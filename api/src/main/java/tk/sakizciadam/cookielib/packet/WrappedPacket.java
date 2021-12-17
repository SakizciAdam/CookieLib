package tk.sakizciadam.cookielib.packet;

import tk.sakizciadam.cookielib.utils.Logger;
import tk.sakizciadam.cookielib.utils.reflection.QuickReflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;

public class WrappedPacket {
    private Object object;
    private PacketType packetType;
    private HashMap<Field,Object> objects;




    public WrappedPacket(PacketType packetType,Object obj){
        this.packetType=packetType;
        this.object=obj;
        this.objects=new HashMap<>();
        this.searchForFields();
        this.read();

    }

    public WrappedPacket(PacketType packetType){
        this.packetType=packetType;
        try {
            this.object=packetType.getPacketClass().getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException exception) {
            exception.printStackTrace();
        }
        this.objects=new HashMap<>();
        this.searchForFields();


    }

    public void onChange(String fieldName){
        for(Field field: getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(MinecraftVariable.class)&&field.getName().equals(fieldName)) {
                final String name=field.getAnnotation(MinecraftVariable.class).varName();
                Field f=getFieldByName(name);

                if(f==null){
                    Logger.log(Level.WARNING,"Could not find "+name+" in "+packetType.getClassName());
                    continue;
                }
                field.setAccessible(true);
                f.setAccessible(true);
                try {


                    f.set(object,field.get(this));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void read(){



        for(Field field: getClass().getDeclaredFields()){



            if(field.isAnnotationPresent(MinecraftVariable.class)){
                final String name=field.getAnnotation(MinecraftVariable.class).varName();
                Field f=getFieldByName(name);


                if(f==null){
                    Logger.log(Level.WARNING,"Could not find "+name+" in "+packetType.getClassName());
                    continue;
                }
                field.setAccessible(true);
                f.setAccessible(true);
                try {
                    if(f.get(object)==null){
                        Logger.log(Level.WARNING,name+" in "+packetType.getClassName()+" is null!");
                        continue;
                    }

                    field.set(this,f.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if(field.isAnnotationPresent(EnumMinecraftVariable.class)){
                final String name=field.getAnnotation(EnumMinecraftVariable.class).varName();

                if(!field.getType().equals(String.class)){
                    Logger.log(Level.WARNING,field.getName()+" must be String not "+field.getType().getSimpleName());
                    continue;
                }

                Field f=getFieldByName(name);


                if(f==null){
                    Logger.log(Level.WARNING,"Could not find "+name+" in "+packetType.getClassName());
                    continue;
                }




                field.setAccessible(true);
                f.setAccessible(true);
                try {
                    if(f.get(object)==null){
                        Logger.log(Level.WARNING,name+" in "+packetType.getClassName()+" is null!");
                        continue;
                    }

                    Object obj=f.get(object);

                    if(!obj.getClass().isEnum()){
                        Logger.log(Level.WARNING,name +" in "+packetType.getClassName()+" must be enum!");
                        continue;
                    }



                    String n=((Enum)obj).name();



                    field.set(this,n);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }


            }

        }
    }

    private Field getFieldByName(String s){
        return objects.keySet().stream().filter(field -> field.getName().equals(s)).findFirst().orElse(null);
    }

    private void searchForFields(){

        for(Field field:this.packetType.getPacketClass().getDeclaredFields()){
            if(field!=null){
                field.setAccessible(true);
                try {
                    objects.put(field,field.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public boolean set(int index,Object value){
        if(objects.size()<=index){
            return false;
        }
        objects.put((Field) objects.keySet().toArray()[index],value);
        return true;

    }

    public Object get(int index){
        if(objects.size()<=index){
            return null;
        }
        return objects.get(objects.keySet().toArray()[index]);

    }

    public Object getRaw() {
        return object;
    }

    public PacketType getPacketType() {
        return packetType;
    }

    public PacketSender getPacketSenderType(){
        return this.packetType.getPacketSender();
    }
}
