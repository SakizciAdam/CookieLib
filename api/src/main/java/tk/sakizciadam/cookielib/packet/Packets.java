package tk.sakizciadam.cookielib.packet;

import tk.sakizciadam.cookielib.utils.reflection.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static tk.sakizciadam.cookielib.packet.PacketSender.*;

public class Packets {





    public static class Client {
        public static class  Status{

            public static final PacketType START=new PacketType(CLIENT,"PacketStatusInStart");
            public static final PacketType PING=new PacketType(CLIENT,"PacketStatusInPing");



        }

        public static class  Login{
            public static final PacketType START=new PacketType(CLIENT,"PacketLoginInStart");
            public static final PacketType ENCRYPTION_BEGIN=new PacketType(CLIENT,"PacketLoginInEncryptionBegin");




        }

        public static class Play{
            public static final PacketType ARM_ANIMATION = new PacketType(CLIENT,"PacketPlayInArmAnimation");
            public static final PacketType CLOSE_WINDOW = new PacketType(CLIENT,"PacketPlayInCloseWindow");
            public static final PacketType BLOCK_PLACE = new PacketType(CLIENT,"PacketPlayInBlockPlace");
            public static final PacketType RESOURCE_PACK_STATUS = new PacketType(CLIENT,"PacketPlayInResourcePackStatus");
            public static final PacketType CHAT = new PacketType(CLIENT,"PacketPlayInChat");
            public static final PacketType TAB_COMPLETE = new PacketType(CLIENT,"PacketPlayInTabComplete");
            public static final PacketType CLIENT_COMMAND = new PacketType(CLIENT,"PacketPlayInClientCommand");
            public static final PacketType SETTINGS = new PacketType(CLIENT,"PacketPlayInSettings");
            public static final PacketType TRANSACTION = new PacketType(CLIENT,"PacketPlayInTransaction");
            public static final PacketType ENCHANT_ITEM = new PacketType(CLIENT,"PacketPlayInEnchantItem");
            public static final PacketType WINDOW_CLICK = new PacketType(CLIENT,"PacketPlayInWindowClick");
            public static final PacketType CUSTOM_PAYLOAD = new PacketType(CLIENT,"PacketPlayInCustomPayload");
            public static final PacketType USE_ENTITY = new PacketType(CLIENT,"PacketPlayInUseEntity");
            public static final PacketType KEEP_ALIVE = new PacketType(CLIENT,"PacketPlayInKeepAlive");
            public static final PacketType FLYING$POSITION = new PacketType(CLIENT,"PacketPlayInFlying$PacketPlayInPosition");
            public static final PacketType FLYING$POSITION_LOOK = new PacketType(CLIENT,"PacketPlayInFlying$PacketPlayInPositionLook");
            public static final PacketType FLYING$LOOK = new PacketType(CLIENT,"PacketPlayInFlying$PacketPlayInLook");
            public static final PacketType FLYING = new PacketType(CLIENT,"PacketPlayInFlying");
            public static final PacketType ABILITIES = new PacketType(CLIENT,"PacketPlayInAbilities");
            public static final PacketType BLOCK_DIG = new PacketType(CLIENT,"PacketPlayInBlockDig");
            public static final PacketType ENTITY_ACTION = new PacketType(CLIENT,"PacketPlayInEntityAction");
            public static final PacketType STEER_VEHICLE = new PacketType(CLIENT,"PacketPlayInSteerVehicle");
            public static final PacketType HELD_ITEM_SLOT = new PacketType(CLIENT,"PacketPlayInHeldItemSlot");
            public static final PacketType SET_CREATIVE_SLOT = new PacketType(CLIENT,"PacketPlayInSetCreativeSlot");
            public static final PacketType UPDATE_SIGN = new PacketType(CLIENT,"PacketPlayInUpdateSign");
            public static final PacketType SPECTATE = new PacketType(CLIENT,"PacketPlayInSpectate");
        }

    }
    public static class Server {
        public static class Play{
            public static final PacketType MAP_CHUNK = new PacketType(SERVER,"PacketPlayOutMapChunk");
            public static final PacketType OPEN_WINDOW = new PacketType(SERVER,"PacketPlayOutOpenWindow");
            public static final PacketType CHAT = new PacketType(SERVER,"PacketPlayOutChat");
            public static final PacketType MAP_CHUNK$CHUNK_MAP = new PacketType(SERVER,"PacketPlayOutMapChunk$ChunkMap");
            public static final PacketType MAP_CHUNK_BULK = new PacketType(SERVER,"PacketPlayOutMapChunkBulk");
            public static final PacketType POSITION = new PacketType(SERVER,"PacketPlayOutPosition");
            public static final PacketType SPAWN_ENTITY = new PacketType(SERVER,"PacketPlayOutSpawnEntity");
            public static final PacketType SPAWN_ENTITY_EXPERIENCE_ORB = new PacketType(SERVER,"PacketPlayOutSpawnEntityExperienceOrb");
            public static final PacketType SPAWN_ENTITY_WEATHER = new PacketType(SERVER,"PacketPlayOutSpawnEntityWeather");
            public static final PacketType SPAWN_ENTITY_LIVING = new PacketType(SERVER,"PacketPlayOutSpawnEntityLiving");
            public static final PacketType SPAWN_ENTITY_PAINTING = new PacketType(SERVER,"PacketPlayOutSpawnEntityPainting");
            public static final PacketType NAMED_ENTITY_SPAWN = new PacketType(SERVER,"PacketPlayOutNamedEntitySpawn");
            public static final PacketType ANIMATION = new PacketType(SERVER,"PacketPlayOutAnimation");
            public static final PacketType STATISTIC = new PacketType(SERVER,"PacketPlayOutStatistic");
            public static final PacketType BLOCK_BREAK_ANIMATION = new PacketType(SERVER,"PacketPlayOutBlockBreakAnimation");
            public static final PacketType TILE_ENTITY_DATA = new PacketType(SERVER,"PacketPlayOutTileEntityData");
            public static final PacketType BLOCK_ACTION = new PacketType(SERVER,"PacketPlayOutBlockAction");
            public static final PacketType BLOCK_CHANGE = new PacketType(SERVER,"PacketPlayOutBlockChange");
            public static final PacketType SERVER_DIFFICULTY = new PacketType(SERVER,"PacketPlayOutServerDifficulty");
            public static final PacketType TAB_COMPLETE = new PacketType(SERVER,"PacketPlayOutTabComplete");
            public static final PacketType MULTI_BLOCK_CHANGE$MULTI_BLOCK_CHANGE_INFO = new PacketType(SERVER,"PacketPlayOutMultiBlockChange$MultiBlockChangeInfo");
            public static final PacketType MULTI_BLOCK_CHANGE = new PacketType(SERVER,"PacketPlayOutMultiBlockChange");
            public static final PacketType TRANSACTION = new PacketType(SERVER,"PacketPlayOutTransaction");
            public static final PacketType CLOSE_WINDOW = new PacketType(SERVER,"PacketPlayOutCloseWindow");
            public static final PacketType WINDOW_ITEMS = new PacketType(SERVER,"PacketPlayOutWindowItems");
            public static final PacketType WINDOW_DATA = new PacketType(SERVER,"PacketPlayOutWindowData");
            public static final PacketType SET_SLOT = new PacketType(SERVER,"PacketPlayOutSetSlot");
            public static final PacketType CUSTOM_PAYLOAD = new PacketType(SERVER,"PacketPlayOutCustomPayload");
            public static final PacketType KICK_DISCONNECT = new PacketType(SERVER,"PacketPlayOutKickDisconnect");
            public static final PacketType ENTITY_STATUS = new PacketType(SERVER,"PacketPlayOutEntityStatus");
            public static final PacketType UPDATE_ENTITY_NB_T = new PacketType(SERVER,"PacketPlayOutUpdateEntityNBT");
            public static final PacketType EXPLOSION = new PacketType(SERVER,"PacketPlayOutExplosion");
            public static final PacketType SET_COMPRESSION = new PacketType(SERVER,"PacketPlayOutSetCompression");
            public static final PacketType GAME_STATE_CHANGE = new PacketType(SERVER,"PacketPlayOutGameStateChange");
            public static final PacketType KEEP_ALIVE = new PacketType(SERVER,"PacketPlayOutKeepAlive");
            public static final PacketType WORLD_EVENT = new PacketType(SERVER,"PacketPlayOutWorldEvent");
            public static final PacketType WORLD_PARTICLES = new PacketType(SERVER,"PacketPlayOutWorldParticles");
            public static final PacketType NAMED_SOUND_EFFECT = new PacketType(SERVER,"PacketPlayOutNamedSoundEffect");
            public static final PacketType LOGIN = new PacketType(SERVER,"PacketPlayOutLogin");
            public static final PacketType MAP = new PacketType(SERVER,"PacketPlayOutMap");
            public static final PacketType ENTITY$REL_ENTITY_MOVE = new PacketType(SERVER,"PacketPlayOutEntity$PacketPlayOutRelEntityMove");
            public static final PacketType ENTITY$REL_ENTITY_MOVE_LOOK = new PacketType(SERVER,"PacketPlayOutEntity$PacketPlayOutRelEntityMoveLook");
            public static final PacketType ENTITY$ENTITY_LOOK = new PacketType(SERVER,"PacketPlayOutEntity$PacketPlayOutEntityLook");
            public static final PacketType ENTITY = new PacketType(SERVER,"PacketPlayOutEntity");
            public static final PacketType OPEN_SIGN_EDITOR = new PacketType(SERVER,"PacketPlayOutOpenSignEditor");
            public static final PacketType ABILITIES = new PacketType(SERVER,"PacketPlayOutAbilities");
            public static final PacketType COMBAT_EVENT = new PacketType(SERVER,"PacketPlayOutCombatEvent");
            public static final PacketType PLAYER_INFO$PLAYER_INFO_DATA = new PacketType(SERVER,"PacketPlayOutPlayerInfo$PlayerInfoData");
            public static final PacketType PLAYER_INFO = new PacketType(SERVER,"PacketPlayOutPlayerInfo");
            public static final PacketType BED = new PacketType(SERVER,"PacketPlayOutBed");
            public static final PacketType ENTITY_DESTROY = new PacketType(SERVER,"PacketPlayOutEntityDestroy");
            public static final PacketType REMOVE_ENTITY_EFFECT = new PacketType(SERVER,"PacketPlayOutRemoveEntityEffect");
            public static final PacketType RESOURCE_PACK_SEND = new PacketType(SERVER,"PacketPlayOutResourcePackSend");
            public static final PacketType RESPAWN = new PacketType(SERVER,"PacketPlayOutRespawn");
            public static final PacketType ENTITY_HEAD_ROTATION = new PacketType(SERVER,"PacketPlayOutEntityHeadRotation");
            public static final PacketType WORLD_BORDER = new PacketType(SERVER,"PacketPlayOutWorldBorder");
            public static final PacketType CAMERA = new PacketType(SERVER,"PacketPlayOutCamera");
            public static final PacketType HELD_ITEM_SLOT = new PacketType(SERVER,"PacketPlayOutHeldItemSlot");
            public static final PacketType SCOREBOARD_DISPLAY_OBJECTIVE = new PacketType(SERVER,"PacketPlayOutScoreboardDisplayObjective");
            public static final PacketType ENTITY_METADATA = new PacketType(SERVER,"PacketPlayOutEntityMetadata");
            public static final PacketType ATTACH_ENTITY = new PacketType(SERVER,"PacketPlayOutAttachEntity");
            public static final PacketType ENTITY_VELOCITY = new PacketType(SERVER,"PacketPlayOutEntityVelocity");
            public static final PacketType ENTITY_EQUIPMENT = new PacketType(SERVER,"PacketPlayOutEntityEquipment");
            public static final PacketType EXPERIENCE = new PacketType(SERVER,"PacketPlayOutExperience");
            public static final PacketType UPDATE_HEALTH = new PacketType(SERVER,"PacketPlayOutUpdateHealth");
            public static final PacketType SCOREBOARD_OBJECTIVE = new PacketType(SERVER,"PacketPlayOutScoreboardObjective");
            public static final PacketType SCOREBOARD_TEAM = new PacketType(SERVER,"PacketPlayOutScoreboardTeam");
            public static final PacketType SCOREBOARD_SCORE = new PacketType(SERVER,"PacketPlayOutScoreboardScore");
            public static final PacketType SPAWN_POSITION = new PacketType(SERVER,"PacketPlayOutSpawnPosition");
            public static final PacketType UPDATE_TIME = new PacketType(SERVER,"PacketPlayOutUpdateTime");
            public static final PacketType TITLE = new PacketType(SERVER,"PacketPlayOutTitle");
            public static final PacketType UPDATE_SIGN = new PacketType(SERVER,"PacketPlayOutUpdateSign");
            public static final PacketType PLAYER_LIST_HEADER_FOOTER = new PacketType(SERVER,"PacketPlayOutPlayerListHeaderFooter");
            public static final PacketType COLLECT = new PacketType(SERVER,"PacketPlayOutCollect");
            public static final PacketType ENTITY_TELEPORT = new PacketType(SERVER,"PacketPlayOutEntityTeleport");
            public static final PacketType UPDATE_ATTRIBUTES$ATTRIBUTE_SNAPSHOT = new PacketType(SERVER,"PacketPlayOutUpdateAttributes$AttributeSnapshot");
            public static final PacketType UPDATE_ATTRIBUTES = new PacketType(SERVER,"PacketPlayOutUpdateAttributes");
            public static final PacketType ENTITY_EFFECT = new PacketType(SERVER,"PacketPlayOutEntityEffect");
        }
        public static class   Login{


            public static final PacketType DISCONNECT=new PacketType(SERVER,"PacketLoginOutDisconnect");
            public static final PacketType SUCCESS=new PacketType(SERVER,"PacketLoginOutSuccess");
            public static final PacketType ENCRYPTION_BEGIN=new PacketType(SERVER,"PacketLoginOutEncryptionBegin");
            public static final PacketType SET_COMPRESSION=new PacketType(SERVER,"PacketLoginOutSetCompression");

        }
        public static class  Status{

            public static final PacketType SERVER_INFO=new PacketType(CLIENT,"PacketStatusOutServerInfo");
            public static final PacketType PING=new PacketType(SERVER,"PacketStatusOutPing");



        }


    }



    public static List<PacketType> getPackets(){
        ArrayList<PacketType> list=new ArrayList<PacketType>();
        final Class client= ReflectionUtils.getClass(Packets.class.getCanonicalName()+"$Client");
        final Class server= ReflectionUtils.getClass(Packets.class.getCanonicalName()+"$Server");

        for(Class innerClass: client.getDeclaredClasses()){
            for(Field field:innerClass.getDeclaredFields()){
                if(field.getType().equals(PacketType.class)){
                    field.setAccessible(true);
                    try {
                        list.add((PacketType)field.get(null));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        for(Class innerClass: server.getDeclaredClasses()){
            for(Field field:innerClass.getDeclaredFields()){
                if(field.getType().equals(PacketType.class)){
                    field.setAccessible(true);
                    try {
                        list.add((PacketType)field.get(null));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        return list;


    }


}
