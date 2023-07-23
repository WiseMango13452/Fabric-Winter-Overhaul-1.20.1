package mango.winteroverhaul.entities.client;

import mango.winteroverhaul.WinterOverhaul;
import mango.winteroverhaul.entities.ExplosiveSnowballEntity;
import mango.winteroverhaul.entities.custom.ExplosiveSnowmanEntity;
import mango.winteroverhaul.entities.custom.SawmanEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@Environment(EnvType.CLIENT)
public class ExplosiveSnowmanRenderer extends GeoEntityRenderer<ExplosiveSnowmanEntity> {

    public ExplosiveSnowmanRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new ExplosiveSnowmanModel());
    }

    @Override
    public Identifier getTextureLocation(ExplosiveSnowmanEntity animatable) {
        return new Identifier(WinterOverhaul.MOD_ID, "textures/entity/explosive_snowman.png");
    }

    @Override
    public void render(ExplosiveSnowmanEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
