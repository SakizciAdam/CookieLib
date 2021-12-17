package tk.sakizciadam.cookielib.processor;

public abstract class Processor<T> {
    public abstract T process(Object obj,Object... args);
}
