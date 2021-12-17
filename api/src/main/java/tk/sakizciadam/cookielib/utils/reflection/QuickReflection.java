package tk.sakizciadam.cookielib.utils.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class QuickReflection {
    public static void setField(String fieldName, Class clazz, Object object,Object newValue){

        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object,newValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    public static Object getField(String fieldName, Class clazz, Object object){

        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static <E extends Enum> E[] getEnumValues(Class<E> enumClass)
             {
                 Field f = null;
                 try {
                     f = enumClass.getDeclaredField("$VALUES");
                 } catch (NoSuchFieldException e) {
                     e.printStackTrace();
                 }
                 f.setAccessible(true);
                 Object o = null;
                 try {
                     o = f.get(null);
                 } catch (IllegalAccessException e) {
                     e.printStackTrace();
                 }
                 return (E[]) o;
    }


}
