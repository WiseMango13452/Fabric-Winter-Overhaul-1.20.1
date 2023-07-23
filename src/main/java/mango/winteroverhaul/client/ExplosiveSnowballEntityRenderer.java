package mango.winteroverhaul.client;

import mango.winteroverhaul.entities.ExplosiveSnowballEntity;
import mango.winteroverhaul.registery.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

import static net.minecraft.client.render.entity.EntityRendererFactory.*;

public class ExplosiveSnowballEntityRenderer extends EntityRenderer<ExplosiveSnowballEntity> {

    public static final ItemStack STACK = new ItemStack(ModItems.EXPLOSIVE_SNOWBALL);

    public ExplosiveSnowballEntityRenderer(Context ctx) {
        super(ctx);
    }


    public void render(ExplosiveSnowballEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity.age >= 0 || !(this.dispatcher.camera.getFocusedEntity().squaredDistanceTo(entity) < 12.25D)) {
            matrices.push();
            float scale = 0.5f;
            matrices.scale(scale, scale, scale);
            matrices.multiply(this.dispatcher.getRotation());
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F));
            MinecraftClient.getInstance().getItemRenderer().renderItem(
                    STACK,
                    ModelTransformationMode.FIXED,
                    light,
                    OverlayTexture.DEFAULT_UV,
                    matrices,
                    vertexConsumers,
                    entity.getWorld(),
                    1
            );
            matrices.pop();
            super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        }
    }

    public Identifier getTexture(ExplosiveSnowballEntity entity) {
        return null;
    }
}