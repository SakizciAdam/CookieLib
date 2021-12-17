package tk.sakizciadam.cookielib.minecraft;

public class WrappedMathHelper {
    public static int floor(double var0) {
        int var2 = (int)var0;
        return var0 < (double)var2 ? var2 - 1 : var2;
    }
}
