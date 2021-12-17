package tk.sakizciadam.cookielib.packet;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MinecraftVariable {
    String varName();
}
