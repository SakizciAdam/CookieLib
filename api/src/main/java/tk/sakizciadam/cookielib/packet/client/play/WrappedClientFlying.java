package tk.sakizciadam.cookielib.packet.client.play;

import tk.sakizciadam.cookielib.packet.MinecraftVariable;
import tk.sakizciadam.cookielib.packet.PacketType;
import tk.sakizciadam.cookielib.packet.Packets;
import tk.sakizciadam.cookielib.packet.WrappedPacket;

import java.util.ArrayList;

public class WrappedClientFlying extends WrappedPacket {

    @MinecraftVariable(varName = "x")
    protected double x;

    @MinecraftVariable(varName = "y")
    protected double y;

    @MinecraftVariable(varName = "z")
    protected double z;

    @MinecraftVariable(varName = "yaw")
    protected float yaw;

    @MinecraftVariable(varName = "pitch")
    protected float pitch;

    @MinecraftVariable(varName = "f")
    protected boolean onGround;

    @MinecraftVariable(varName = "hasPos")
    protected boolean hasPos;

    @MinecraftVariable(varName = "hasLook")
    protected boolean hasLook;


    public WrappedClientFlying(){
        super(Packets.Client.Play.FLYING);
    }

    public WrappedClientFlying(Object obj){
        super(Packets.Client.Play.FLYING,obj);
    }

    private WrappedClientFlying(PacketType packetType,Object obj){
        super(packetType,obj);

    }

    public static class WrappedClientFlyingLook extends WrappedClientFlying{
        public WrappedClientFlyingLook(){
            super(Packets.Client.Play.FLYING$LOOK);
        }

        public WrappedClientFlyingLook(Object obj){
            super(Packets.Client.Play.FLYING$LOOK,obj);
        }

        @Override
        public void read() {
            super.read();
        }




    }
    public static class WrappedClintFlyingMoveLook extends WrappedClientFlying{
        public WrappedClintFlyingMoveLook(){
            super(Packets.Client.Play.FLYING$POSITION_LOOK);
        }

        public WrappedClintFlyingMoveLook(Object obj){
            super(Packets.Client.Play.FLYING$POSITION_LOOK,obj);
        }

        @Override
        public void read() {
            super.read();
        }





    }

    public static class WrappedClintFlyingMove extends WrappedClientFlying{
        public WrappedClintFlyingMove(){
            super(Packets.Client.Play.FLYING$POSITION);
        }

        public WrappedClintFlyingMove(Object obj){
            super(Packets.Client.Play.FLYING$POSITION,obj);
        }

        @Override
        public void read() {
            super.read();
        }


    }

    public void setX(double x) {
        this.x = x;
        onChange("x");
    }

    public void setY(double y) {
        this.y = y;
        onChange("y");
    }

    public void setZ(double z){
        this.z=z;
        onChange("z");
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
        onChange("yaw");
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
        onChange("pitch");
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
        onChange("onGround");
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public void read() {
        super.read();
    }

    @Override
    public void onChange(String s) {
        super.onChange(s);
    }
}
