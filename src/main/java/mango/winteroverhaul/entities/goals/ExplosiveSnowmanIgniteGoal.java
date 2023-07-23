package mango.winteroverhaul.entities.goals;

import mango.winteroverhaul.entities.custom.ExplosiveSnowmanEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class ExplosiveSnowmanIgniteGoal extends Goal {
    private final ExplosiveSnowmanEntity explosiveSnowman;
    @Nullable
    private LivingEntity target;

    public ExplosiveSnowmanIgniteGoal(ExplosiveSnowmanEntity explosiveSnowman) {
        this.explosiveSnowman = explosiveSnowman;
        this.setControls(EnumSet.of(Goal.Control.MOVE));
    }

    @Override
    public boolean canStart() {
        LivingEntity livingEntity = this.explosiveSnowman.getTarget();
        return this.explosiveSnowman.getFuseSpeed() > 0 || livingEntity != null && this.explosiveSnowman.squaredDistanceTo(livingEntity) < 9.0;
    }

    @Override
    public void start() {
        this.explosiveSnowman.getNavigation().stop();
        this.target = this.explosiveSnowman.getTarget();
    }

    @Override
    public void stop() {
        this.target = null;
    }

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        if (this.target == null) {
            this.explosiveSnowman.setFuseSpeed(-1);
            return;
        }
        if (this.explosiveSnowman.squaredDistanceTo(this.target) > 49.0) {
            this.explosiveSnowman.setFuseSpeed(-1);
            return;
        }
        if (!this.explosiveSnowman.getVisibilityCache().canSee(this.target)) {
            this.explosiveSnowman.setFuseSpeed(-1);
            return;
        }
        this.explosiveSnowman.setFuseSpeed(1);
    }
}
