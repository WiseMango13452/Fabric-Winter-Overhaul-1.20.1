package mango.winteroverhaul.registery;

import mango.winteroverhaul.WinterOverhaul;
import mango.winteroverhaul.entities.ExplosiveSnowballEntity;
import mango.winteroverhaul.entities.custom.ExplosiveSnowmanEntity;
import mango.winteroverhaul.entities.custom.SawmanEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<ExplosiveSnowballEntity> EXPLOSIVE_SNOWBALL = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(WinterOverhaul.MOD_ID, "explosive_snowball"),
            FabricEntityTypeBuilder.<ExplosiveSnowballEntity>create(SpawnGroup.MISC, ExplosiveSnowballEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());
    public static final EntityType<SawmanEntity> SAWMAN = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(WinterOverhaul.MOD_ID, "sawman"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SawmanEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.5f)).build());
    public static final EntityType<ExplosiveSnowmanEntity> EXPLOSIVE_SNOWMAN = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(WinterOverhaul.MOD_ID, "explosive_snowman"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ExplosiveSnowmanEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.5f)).build());
}
