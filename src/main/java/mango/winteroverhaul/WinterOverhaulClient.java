package mango.winteroverhaul;

import mango.winteroverhaul.client.ExplosiveSnowballEntityRenderer;
import mango.winteroverhaul.entities.ExplosiveSnowballEntity;
import mango.winteroverhaul.registery.ModBlocks;
import mango.winteroverhaul.entities.client.ExplosiveSnowmanRenderer;
import mango.winteroverhaul.entities.client.SawmanRenderer;
import mango.winteroverhaul.registery.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;

import java.util.UUID;

public class WinterOverhaulClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WINTER_CORE_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WINTER_CORE_BLOCK, RenderLayer.getTranslucent());

        EntityRendererRegistry.register(ModEntities.EXPLOSIVE_SNOWBALL, ExplosiveSnowballEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.SAWMAN, SawmanRenderer::new);
        EntityRendererRegistry.register(ModEntities.EXPLOSIVE_SNOWMAN, ExplosiveSnowmanRenderer::new);

        ClientPlayNetworking.registerGlobalReceiver(ExplosiveSnowballEntity.SPAWN_PACKET, (client, handler, packet, responseSender) -> {

            double x = packet.readDouble();
            double y = packet.readDouble();
            double z = packet.readDouble();

            int entityID = packet.readInt();
            UUID entityUUID = packet.readUuid();

            client.execute(() -> {
                ExplosiveSnowballEntity proj = new ExplosiveSnowballEntity(MinecraftClient.getInstance().world, x, y, z, entityID, entityUUID);
                MinecraftClient.getInstance().world.addEntity(entityID, proj);
            });
        });
    }
}
