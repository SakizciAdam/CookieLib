package tk.sakizciadam.cookielib.utils.reflection;

import tk.sakizciadam.cookielib.utils.NMSUtils;

import java.lang.reflect.Field;

public class FieldGetter {

    private final Field field;

    protected FieldGetter(Field field){


        this.field = field;
    }

    public boolean hasField(Object obj){
        return field.getDeclaringClass().isAssignableFrom(obj.getClass());
    }

    public Object get(Object obj){
        if(field==null) return null;

        try {
            field.setAccessible(true);
            return field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void set(Object obj,Object value){
        if(field==null) return;

        try {
            field.setAccessible(true);
            field.set(obj,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
