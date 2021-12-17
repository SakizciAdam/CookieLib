package tk.sakizciadam.cookielib.utils.reflection;
import tk.sakizciadam.cookielib.utils.Logger;
import tk.sakizciadam.cookielib.utils.NMSUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;

public class ReflectionUtils {

    public static Class getMinecraftClass(String name){
        try {
            String n=NMSUtils.NMS_PREFIX+"."+name;
            Logger.info(n);
            return Class.forName(n);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Class getClass(String name){
        try {
            return Class.forName(name.replace("{nms}", NMSUtils.NMS_PREFIX).replace("{obc}",NMSUtils.OBC_PREFIX));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FieldGetter getField(Class className,String fieldName,Class fieldType){

        if(className==null){
            Logger.log(Level.SEVERE,"Could not find class for "+fieldName);
            return null;
        }

        for(Field f:className.getDeclaredFields()){

            if(fieldType==null||(fieldType!=null&&fieldType.isAssignableFrom(f.getType()))){

                if(fieldName!=""&&!f.getName().equals(fieldName)) continue;

                f.setAccessible(true);
                return new FieldGetter(f);
            }

        }
        Logger.info("Could not find "+fieldName+" field in "+className.getCanonicalName()+" with "+fieldType.getCanonicalName());
        return null;

    }

    public static FieldGetter getField(Class className,Class fieldType){

        return getField(className, "", fieldType);

    }

    public static FieldGetter getField(String className,Class fieldType){

        return getField(className, "", fieldType);

    }


    public static FieldGetter getField(String className,String fieldName){

        return getField(className, fieldName, Object.class);

    }
    public static FieldGetter getField(String className,String fieldName,Class fieldType){
        String classN=className.replace("{nms}", NMSUtils.NMS_PREFIX).replace("{obc}",NMSUtils.OBC_PREFIX)
                ;
        try {
            Class clazz=Class.forName(classN);

            if(clazz!=null){
                return getField(clazz, fieldName, fieldType);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static MethodGetter getMethod(String className,String methodName,Class returnType,Class... params){
        String classN=className.replace("{nms}", NMSUtils.NMS_PREFIX).replace("{obc}",NMSUtils.OBC_PREFIX)
                ;
        Class clazz=getClass(classN);

        if(clazz!=null){
            return getMethod(clazz, methodName, returnType,params);
        }

        return null;

    }

    public static MethodGetter getMethod(Class clazz,String methodName,Class returnType,Class... param){
        if(clazz==null){
            Logger.log(Level.SEVERE,"Could not find class for "+methodName);
            return null;
        }

        for(Method m:clazz.getDeclaredMethods()){
            if(m.getName().equals(methodName)||methodName==""){
                if(returnType!=null&&!m.getReturnType().equals(returnType)){
                    continue;
                }

                if(m.getParameterCount()>0&&param.length>0&& Arrays.equals(m.getParameterTypes(),param)) {
                    m.setAccessible(true);




                    return new MethodGetter(m);
                } else if(param.length==0&&m.getParameterCount()==0){
                    return new MethodGetter(m);
                }
            }

        }
        if(returnType!=null){
            Logger.log(Level.SEVERE,"Could not find "+methodName+" method in "+clazz.getCanonicalName()+" with "+returnType.getCanonicalName());
        } else {
            Logger.log(Level.SEVERE,"Could not find "+methodName+" method in "+clazz.getCanonicalName());
        }
        return null;
    }
}
