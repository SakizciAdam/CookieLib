package tk.sakizciadam.cookielib.utils.reflection;

import tk.sakizciadam.cookielib.utils.Logger;
import tk.sakizciadam.cookielib.utils.NMSUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;

public class MethodGetter {
    private final Method method;


    protected MethodGetter(Method method){

        if(method==null){
            Logger.log(Level.SEVERE,"Could not find method");
        }

        this.method=method;

    }

    public Object invoke(Object obj,Object... values){
        try {

            return method.invoke(obj,values);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
