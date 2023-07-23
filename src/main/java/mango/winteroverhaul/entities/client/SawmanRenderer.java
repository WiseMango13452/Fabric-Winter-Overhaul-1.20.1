package mango.winteroverhaul.entities.client;

import mango.winteroverhaul.WinterOverhaul;
import mango.winteroverhaul.entities.custom.SawmanEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SawmanRenderer extends GeoEntityRenderer<SawmanEntity> {
    public SawmanRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new SawmanModel());
    }

    @Override
    public Identifier getTextureLocation(SawmanEntity animatable) {
        return new Identifier(WinterOverhaul.MOD_ID, "textures/entity/sawman.png");
    }

    @Override
    public void render(SawmanEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
