package mango.winteroverhaul.item;

import mango.winteroverhaul.entities.ExplosiveSnowballEntity;
import mango.winteroverhaul.registery.ModEntities;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ExplosiveSnowballItem extends Item {

    public ExplosiveSnowballItem() {
        super(new FabricItemSettings().maxCount(4));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        if(!world.isClient) {
            ExplosiveSnowballEntity proj = new ExplosiveSnowballEntity(ModEntities.EXPLOSIVE_SNOWBALL, world);
            proj.setPos(user.getX(), user.getY() + 1.65, user.getZ());
            proj.setOwner(user);
            proj.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.5F);
            world.spawnEntity(proj);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }
}