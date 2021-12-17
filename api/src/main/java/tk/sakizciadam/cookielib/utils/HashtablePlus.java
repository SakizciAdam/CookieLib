package tk.sakizciadam.cookielib.utils;

import java.util.Hashtable;
import java.util.Map;

public class HashtablePlus<K,V> extends Hashtable<K,V> {

    public K getFromValue(V value){
        if(!this.containsValue(value)) return null;



        for(Map.Entry entry: this.entrySet()){
            if(value.equals(entry.getValue())){
                return (K) entry.getKey();
            }
        }
        return null;
    }
}
