package tk.sakizciadam.cookielib.npc.nms.v1_8_R3;

import net.minecraft.server.v1_8_R3.EntityCreature;
import net.minecraft.server.v1_8_R3.PathfinderGoal;
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import tk.sakizciadam.cookielib.npc.AbstractNPC;
import tk.sakizciadam.cookielib.npc.NPCNavigator;
import tk.sakizciadam.cookielib.utils.Logger;

public class CNPCNavigator extends NPCNavigator {

    private PathFinderWalkToEntity entityGoal;
    private PathFinderWalkToLocation walkGoal;

    public CNPCNavigator(AbstractNPC npc) {
        super(npc);
    }

    @Override
    public void walkTo(Location location) {


        if(this.walkGoal!=null){
            this.walkGoal.setLocation(location);
        }

    }

    @Override
    public void targetEntity(Entity entity) {
        if(this.entityGoal!=null){
            this.entityGoal.setTargetEntity(entity);
        }
    }

    @Override
    public void onSpawn() {

    }

    @Override
    public void afterSpawn() {

        this.walkGoal=new PathFinderWalkToLocation(((CEntity)getNPC()).getEntity(),this.getNPC().getEntitySpeed(),null);
        this.entityGoal=new PathFinderWalkToEntity(((CEntity)getNPC()).getEntity(),this.getNPC().getEntitySpeed(),null);
        ((CEntity)getNPC()).getEntity().goalSelector.a(0, new PathFinderJump(((CEntity) getNPC()).getEntity()));
        ((CEntity)getNPC()).getEntity().goalSelector.a(1,this.entityGoal);
        ((CEntity)getNPC()).getEntity().goalSelector.a(2,this.walkGoal);
    }

    public static class PathFinderJump extends PathfinderGoal {

        private EntityCreature entitycreature;

        public PathFinderJump(EntityCreature entitycreature){
            this.entitycreature = entitycreature;


        }

        @Override
        public boolean a() {
            return  this.entitycreature.V() || this.entitycreature.ab();
        }

        public void e() {
            if (this.entitycreature.bc().nextFloat() < 0.8F) {
                this.entitycreature.motY=0.48;
                Location loc = entitycreature.getBukkitEntity().getLocation();
                Vector dir = loc.getDirection();
                dir.normalize();
                dir.multiply(1.2); //5 blocks a way
                loc.add(dir);
                entitycreature.getBukkitEntity().teleport(loc);

            }

        }
    }

    public static class PathFinderWalkToEntity extends PathfinderGoal {

        private float speed;
        private EntityCreature entitycreature;
        private org.bukkit.entity.Entity targetEntity;
        public PathFinderWalkToEntity(EntityCreature entitycreature, float speed,org.bukkit.entity.Entity targetEntity){
            this.entitycreature = entitycreature;
            this.speed = speed;
            this.targetEntity=targetEntity;
        }


        @Override
        public boolean a() {
            if(targetEntity==null||targetEntity.isDead()||targetEntity.getWorld()!=entitycreature.getBukkitEntity().getWorld()) return false;

            return true;
        }

        public void walk(){
            this.entitycreature.getNavigation().a(targetEntity.getLocation().getX(),targetEntity.getLocation().getY(),targetEntity.getLocation().getZ(), speed);
        }

        @Override
        public void c(){

            walk();
        }

        public void e() {
            walk();

        }

        public Entity getTargetEntity() {
            return targetEntity;
        }

        public void setTargetEntity(Entity targetEntity) {
            this.targetEntity = targetEntity;
        }
    }

    public static class PathFinderWalkToLocation extends PathfinderGoal {

        private float speed;
        private EntityCreature entitycreature;
        private Location location;
        public PathFinderWalkToLocation(EntityCreature entitycreature, float speed,Location location){
            this.entitycreature = entitycreature;
            this.speed = speed;
            this.location=location;
        }


        @Override
        public boolean a() {
            return location!=null;
        }

        public void walk(){
            this.entitycreature.getNavigation().a(location.getX(),location.getY(),location.getZ(), speed);
        }

        @Override
        public void c(){

            walk();
        }

        public void e() {
            walk();

        }

        public Location getTargetLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }
    }
}
