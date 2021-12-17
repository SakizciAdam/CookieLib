package tk.sakizciadam.cookielib.packet;

import tk.sakizciadam.cookielib.utils.Logger;
import tk.sakizciadam.cookielib.utils.NMSUtils;

import java.util.logging.Level;

public class PacketType {
    private final String className;
    private final PacketSender packetSender;
    private Class<?> packetClass;

    public PacketType(PacketSender sender, String className){
        this.packetSender=sender;
        this.className=className;

        try {
            Class clazz=Class.forName(NMSUtils.NMS_PREFIX+"." +className);

            if(clazz==null){
                Logger.log(Level.WARNING,className+" not found!");
                this.packetClass=null;
                return;
            }
            this.packetClass=clazz;

        } catch (Exception e){
            Logger.log(Level.WARNING,className+" not found!");
        }

        PacketSender target=className.startsWith("PacketPlayIn")||className.startsWith("PacketStatusIn")||className.startsWith("PacketLoginIn") ? PacketSender.CLIENT : PacketSender.SERVER;

        if(target!=sender){
            Logger.log(Level.WARNING,className+" don't match up with packet sender");
        }

    }

    public Class<?> getPacketClass() {
        return packetClass;
    }

    public PacketSender getPacketSender() {
        return packetSender;
    }

    public String getClassName() {
        return className;
    }
}
