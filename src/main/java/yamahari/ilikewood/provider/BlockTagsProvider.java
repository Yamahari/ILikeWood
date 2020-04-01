package yamahari.ilikewood.provider;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.Tag;
import yamahari.ilikewood.data.tag.BlockTags;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.util.WoodenObjectType;

public final class BlockTagsProvider extends net.minecraft.data.BlockTagsProvider {
    public BlockTagsProvider(final DataGenerator generator) {
        super(generator);
    }

    private static void registerTag(final WoodenObjectType objectType, final Tag.Builder<Block> builder) {
        builder.add(WoodenBlocks.getBlocks(objectType).toArray(Block[]::new));
    }

    @Override
    protected void registerTags() {
        registerTag(WoodenObjectType.BARREL, this.getBuilder(BlockTags.BARRELS));
        registerTag(WoodenObjectType.CHEST, this.getBuilder(BlockTags.CHESTS));
        registerTag(WoodenObjectType.COMPOSTER, this.getBuilder(BlockTags.COMPOSTER));
        registerTag(WoodenObjectType.BOOKSHELF, this.getBuilder(BlockTags.BOOKSHELFS));
        registerTag(WoodenObjectType.SLAB, this.getBuilder(BlockTags.PANELS_SLABS));
        registerTag(WoodenObjectType.STAIRS, this.getBuilder(BlockTags.PANELS_STAIRS));
        registerTag(WoodenObjectType.PANELS, this.getBuilder(BlockTags.PANELS));
        registerTag(WoodenObjectType.POST, this.getBuilder(BlockTags.POSTS));
        registerTag(WoodenObjectType.STRIPPED_POST, this.getBuilder(BlockTags.STRIPPED_POSTS));
        registerTag(WoodenObjectType.WALL, this.getBuilder(BlockTags.WALLS));
        registerTag(WoodenObjectType.WALL, this.getBuilder(net.minecraft.tags.BlockTags.WALLS));
        registerTag(WoodenObjectType.LADDER, this.getBuilder(BlockTags.LADDERS));
        registerTag(WoodenObjectType.TORCH, this.getBuilder(BlockTags.TORCHES));
        registerTag(WoodenObjectType.WALL_TORCH, this.getBuilder(BlockTags.WALL_TORCHES));
        registerTag(WoodenObjectType.CRAFTING_TABLE, this.getBuilder(BlockTags.CRAFTING_TABLES));
        registerTag(WoodenObjectType.SCAFFOLDING, this.getBuilder(BlockTags.SCAFFOLDINGS));
        registerTag(WoodenObjectType.LECTERN, this.getBuilder(BlockTags.LECTERNS));
    }

    @Override
    public String getName() {
        return "I Like Wood - Block Tags";
    }
}
