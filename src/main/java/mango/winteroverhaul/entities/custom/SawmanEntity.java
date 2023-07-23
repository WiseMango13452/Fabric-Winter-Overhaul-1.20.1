package mango.winteroverhaul.entities.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;

public class SawmanEntity extends HostileEntity implements GeoEntity {
    private static final RawAnimation ATTACK = RawAnimation.begin().thenLoop("attack");
    private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public SawmanEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.25, false));
        this.goalSelector.add(2, new WanderAroundFarGoal(this, 1, 1));

        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 5f));
        this.goalSelector.add(4, new LookAroundGoal(this));

        this.targetSelector.add(1, new ActiveTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller",
                0, state -> state.setAndContinue(isAttacking() ? ATTACK : IDLE)));
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
