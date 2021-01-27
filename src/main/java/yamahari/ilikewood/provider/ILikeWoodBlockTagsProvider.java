package yamahari.ilikewood.provider;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.WoodenObjectType;

public final class ILikeWoodBlockTagsProvider extends BlockTagsProvider {
    public ILikeWoodBlockTagsProvider(final DataGenerator generator, final ExistingFileHelper existingFileHelper) {
        super(generator, Constants.MOD_ID, existingFileHelper);
    }

    private void registerTag(final ITag.INamedTag<Block> tag, final WoodenObjectType objectType) {
        if (objectType.equals(WoodenObjectType.BED)) {
            this.getOrCreateBuilder(tag).add(WoodenBlocks.getBedBlocks().toArray(Block[]::new));
        } else {
            this.getOrCreateBuilder(tag).add(WoodenBlocks.getBlocks(objectType).toArray(Block[]::new));
        }
    }

    @Override
    protected void registerTags() {
        registerTag(ILikeWoodBlockTags.BARRELS, WoodenObjectType.BARREL);
        registerTag(ILikeWoodBlockTags.CHESTS, WoodenObjectType.CHEST);
        registerTag(ILikeWoodBlockTags.COMPOSTER, WoodenObjectType.COMPOSTER);
        registerTag(ILikeWoodBlockTags.BOOKSHELFS, WoodenObjectType.BOOKSHELF);
        registerTag(ILikeWoodBlockTags.PANELS_SLABS, WoodenObjectType.SLAB);
        registerTag(ILikeWoodBlockTags.PANELS_STAIRS, WoodenObjectType.STAIRS);
        registerTag(ILikeWoodBlockTags.PANELS, WoodenObjectType.PANELS);
        registerTag(ILikeWoodBlockTags.POSTS, WoodenObjectType.POST);
        registerTag(ILikeWoodBlockTags.STRIPPED_POSTS, WoodenObjectType.STRIPPED_POST);
        registerTag(ILikeWoodBlockTags.WALLS, WoodenObjectType.WALL);
        registerTag(BlockTags.WALLS, WoodenObjectType.WALL);
        registerTag(ILikeWoodBlockTags.LADDERS, WoodenObjectType.LADDER);
        registerTag(ILikeWoodBlockTags.TORCHES, WoodenObjectType.TORCH);
        registerTag(ILikeWoodBlockTags.WALL_TORCHES, WoodenObjectType.WALL_TORCH);
        registerTag(ILikeWoodBlockTags.CRAFTING_TABLES, WoodenObjectType.CRAFTING_TABLE);
        registerTag(ILikeWoodBlockTags.SCAFFOLDINGS, WoodenObjectType.SCAFFOLDING);
        registerTag(ILikeWoodBlockTags.BEDS, WoodenObjectType.BED);
        registerTag(ILikeWoodBlockTags.SAWMILLS, WoodenObjectType.SAWMILL);
    }

    @Override
    public String getName() {
        return "I Like Wood - Block Tags";
    }
}
