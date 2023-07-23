package mango.winteroverhaul.entities;

import io.netty.buffer.Unpooled;
import mango.winteroverhaul.WinterOverhaul;
import mango.winteroverhaul.registery.ModEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.UUID;

public class ExplosiveSnowballEntity extends ThrownItemEntity {

    public static final Identifier SPAWN_PACKET = new Identifier(WinterOverhaul.MOD_ID, "explosive_snowball");

    public ExplosiveSnowballEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Environment(EnvType.CLIENT)
    public ExplosiveSnowballEntity(World world, double x, double y, double z, int id, UUID uuid) {
        super(ModEntities.EXPLOSIVE_SNOWBALL, world);
        updatePosition(x, y, z);
        updateTrackedPosition(x, y, z);
        setId(id);
        setUuid(uuid);
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Override
    public void initDataTracker() {
        super.initDataTracker();
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer());

        packet.writeDouble(getX());
        packet.writeDouble(getY());
        packet.writeDouble(getZ());

        packet.writeInt(getId());
        packet.writeUuid(getUuid());

        return new CustomPayloadS2CPacket(ExplosiveSnowballEntity.SPAWN_PACKET, packet);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().createExplosion(this, getX(), getY(), getZ(), 2, World.ExplosionSourceType.TNT);
            this.getWorld().sendEntityStatus(this, (byte)3);
            this.discard();
        }

    }
}
