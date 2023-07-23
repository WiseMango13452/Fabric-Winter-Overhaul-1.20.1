package mango.winteroverhaul.entities.client;

import mango.winteroverhaul.WinterOverhaul;
import mango.winteroverhaul.entities.custom.ExplosiveSnowmanEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import java.util.Objects;

public class ExplosiveSnowmanModel extends GeoModel<ExplosiveSnowmanEntity> {

    @Override
    public Identifier getModelResource(ExplosiveSnowmanEntity animatable) {
        return new Identifier(WinterOverhaul.MOD_ID, "geo/explosive_snowman.geo.json");
    }

    @Override
    public Identifier getTextureResource(ExplosiveSnowmanEntity animatable) {
        return new Identifier(WinterOverhaul.MOD_ID, "textures/entity/explosive_snowman.png");
    }

    @Override
    public Identifier getAnimationResource(ExplosiveSnowmanEntity animatable) {
        return new Identifier(WinterOverhaul.MOD_ID, "animations/explosive_snowman.animation.json");
    }

    @Override
    public void setCustomAnimations(ExplosiveSnowmanEntity entity, long instanceId, AnimationState<ExplosiveSnowmanEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if(head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }

        CoreGeoBone stick = getAnimationProcessor().getBone("stick");

        if (stick != null) {
            int currentFuseTime = entity.getFuseTime();
            entity.getWorld().getClosestPlayer(entity, 8).sendMessage(Text.literal("." + currentFuseTime));
            if(currentFuseTime > 0) {
                stick.updateScale(5,5,5);
            }
        }
    }
}
