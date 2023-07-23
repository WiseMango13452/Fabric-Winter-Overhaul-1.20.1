package mango.winteroverhaul.entities.client;

import mango.winteroverhaul.WinterOverhaul;
import mango.winteroverhaul.entities.custom.SawmanEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SawmanModel extends GeoModel<SawmanEntity> {
    @Override
    public Identifier getModelResource(SawmanEntity animatable) {
        return new Identifier(WinterOverhaul.MOD_ID, "geo/sawman.geo.json");
    }

    @Override
    public Identifier getTextureResource(SawmanEntity animatable) {
        return new Identifier(WinterOverhaul.MOD_ID, "textures/entity/sawman.png");
    }

    @Override
    public Identifier getAnimationResource(SawmanEntity animatable) {
        return new Identifier(WinterOverhaul.MOD_ID, "animations/sawman.animation.json");
    }

    @Override
    public void setCustomAnimations(SawmanEntity animatable, long instanceId, AnimationState<SawmanEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if(head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
