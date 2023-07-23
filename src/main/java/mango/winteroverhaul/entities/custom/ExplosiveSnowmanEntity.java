package mango.winteroverhaul.entities.custom;

import mango.winteroverhaul.entities.goals.ExplosiveSnowmanIgniteGoal;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;

import java.util.Collection;

public class ExplosiveSnowmanEntity extends HostileEntity implements GeoEntity {
    private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
    private static final RawAnimation NULL = null;

    private static final TrackedData<Integer> FUSE_TIME = DataTracker.registerData(ExplosiveSnowmanEntity.class, TrackedDataHandlerRegistry.INTEGER);

    private int fuseSpeed;
    private int lastFuseTime;
    private int currentFuseTime;
    private int fuseTimeNeeded = 30;
    private int explosionRadius = 3;

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public ExplosiveSnowmanEntity(EntityType<? extends HostileEntity> entityType, World world) {super(entityType, world);}

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.22f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new ExplosiveSnowmanIgniteGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1, 1));

        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8f));

        this.targetSelector.add(1, new ActiveTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
        this.targetSelector.add(1, new ActiveTargetGoal<CowEntity>(this, CowEntity.class, true));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller",
                0, state -> state.setAndContinue(getFuseTime() == 0 ? IDLE : NULL)));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(FUSE_TIME, 0);
    }

    @Override
    public void tick() {
        if(!getWorld().isClient()) {
            if (this.isAlive()) {
                /*PlayerEntity p = this.getWorld().getClosestPlayer(this, 32);
                assert p != null;
                p.sendMessage(Text.literal("ticks:" + currentFuseTime));*/
                this.lastFuseTime = this.currentFuseTime;
                if ((this.getFuseSpeed()) > 0 && this.currentFuseTime == 0) {
                    this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0f, 1.0f);
                    this.emitGameEvent(GameEvent.PRIME_FUSE);
                }
                this.currentFuseTime += getFuseSpeed();
                if (this.currentFuseTime < 0) {
                    setFuseTime(0);
                }
                if (this.currentFuseTime >= this.fuseTimeNeeded) {
                    this.currentFuseTime = this.fuseTimeNeeded;
                    this.explode();
                }
            }
        }
        super.tick();
    }

    private void explode() {
        if (!this.getWorld().isClient) {
            this.dead = true;
            this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)this.explosionRadius, World.ExplosionSourceType.MOB);
            this.discard();
            this.spawnEffectsCloud();
        }
    }

    private void spawnEffectsCloud() {
        Collection<StatusEffectInstance> collection = this.getStatusEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.getWorld(), this.getX(), this.getY(), this.getZ());
            areaEffectCloudEntity.setRadius(2.5f);
            areaEffectCloudEntity.setRadiusOnUse(-0.5f);
            areaEffectCloudEntity.setWaitTime(10);
            areaEffectCloudEntity.setDuration(areaEffectCloudEntity.getDuration() / 2);
            areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
            for (StatusEffectInstance statusEffectInstance : collection) {
                areaEffectCloudEntity.addEffect(new StatusEffectInstance(statusEffectInstance));
            }
            this.getWorld().spawnEntity(areaEffectCloudEntity);
        }
    }

    public int getFuseSpeed() {
        return this.fuseSpeed;
    }

    public void setFuseSpeed(int i) {
        fuseSpeed = i;
    }

    public int getFuseTime() {
        return this.dataTracker.get(FUSE_TIME);
    }

    public void setFuseTime(int fuseTime) {
        this.dataTracker.set(FUSE_TIME, fuseTime);
    }
}
