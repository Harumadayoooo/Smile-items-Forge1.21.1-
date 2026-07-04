package com.haruma.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, HarumaMod.MODID);

    public static final RegistryObject<Block> SMILE_BLOCK = registerBlockWithItem("smile_block",
            () -> new SmileBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.COAL_BLOCK)));

    private static <T extends Block> RegistryObject<T> registerBlockWithItem(String name, Supplier<T> supplier){
        RegistryObject<T> block = BLOCKS.register(name, supplier);
        ModItems.ITEMS.register(name,
                () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
