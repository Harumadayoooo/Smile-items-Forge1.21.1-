package com.haruma.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class SmileBlock extends Block {
    public static final BooleanProperty UNSTABLE = BlockStateProperties.UNSTABLE;
    private Item Items;

    public SmileBlock(BlockBehaviour.Properties properties){
        super(properties);
    }

    private static void prime(Level level, BlockPos pos, @Nullable LivingEntity igniter){
        if(level.isClientSide) return;

        level.explode(
                null,
                pos.getX() + 0.5,
                pos.getY() + 0.5,
                pos.getZ() + 0.5,
                10.0F,
                Level.ExplosionInteraction.TNT
        );

        level.playSound(
                null,
                pos.getX(), pos.getY(), pos.getZ(),
                SoundEvents.ANVIL_HIT,
                SoundSource.BLOCKS,
                1.0F,
                1.0F
        );

        level.gameEvent(igniter, GameEvent.PRIME_FUSE, pos);

        level.removeBlock(pos, false);
    }

    @Override
    protected ItemInteractionResult useItemOn(
            ItemStack stack,
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            BlockHitResult hit
    ) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());

        boolean canIgnite = itemId.getNamespace().equals("harumamod") && itemId.getPath().equals("smile");

        if(!canIgnite){
            return super.useItemOn(stack, state, level, pos, player, hand, hit);
        }

        prime(level, pos, player);

        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }
}
