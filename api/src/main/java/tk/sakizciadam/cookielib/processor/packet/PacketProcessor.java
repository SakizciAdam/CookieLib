package tk.sakizciadam.cookielib.processor.packet;

import tk.sakizciadam.cookielib.packet.PacketType;
import tk.sakizciadam.cookielib.packet.Packets;
import tk.sakizciadam.cookielib.packet.WrappedPacket;
import tk.sakizciadam.cookielib.processor.Processor;
import tk.sakizciadam.cookielib.utils.Logger;
import tk.sakizciadam.cookielib.utils.NMSUtils;
import tk.sakizciadam.cookielib.utils.reflection.ReflectionUtils;

public class PacketProcessor extends Processor<WrappedPacket> {
    public WrappedPacket process(Object object,Object... args){
        String rawName=object.getClass().getCanonicalName();
        String name=rawName.replaceAll(NMSUtils.NMS_PREFIX+".","");
        Class clazz=null;
        try {
            clazz= Class.forName(rawName);


        } catch (Exception e){
            String other=NMSUtils.NMS_PREFIX+"."+name.replace(".","$");

            if(ReflectionUtils.getClass(other)!=null){
                try {
                    rawName=other;
                    clazz=Class.forName(rawName);

                    name=name.replace(".","$");
                } catch (ClassNotFoundException classNotFoundException) {
                    Logger.info("Could not find "+rawName+" and "+other);
                }


            }
        }

        if(clazz==null) return null;


        String finalName = name;
        PacketType packetType= Packets.getPackets().stream().filter(packet -> packet.getClassName().equalsIgnoreCase(finalName)).findFirst().orElse(null);

        if(packetType!=null){
            return new WrappedPacket(packetType,object);
        }


        return null;
    }
}
