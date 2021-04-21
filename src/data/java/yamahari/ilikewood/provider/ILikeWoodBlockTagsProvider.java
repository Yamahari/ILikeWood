package yamahari.ilikewood.provider;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.data.tag.ILikeWoodBlockTags;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.objecttype.WoodenBlockType;

public final class ILikeWoodBlockTagsProvider extends BlockTagsProvider {
    public ILikeWoodBlockTagsProvider(final DataGenerator generator, final ExistingFileHelper existingFileHelper) {
        super(generator, Constants.MOD_ID, existingFileHelper);
    }

    private void registerTag(final ITag.INamedTag<Block> tag, final WoodenBlockType blockType) {
        if (blockType.equals(WoodenBlockType.WHITE_BED)) {
            this
                .getOrCreateBuilder(tag)
                .add(ILikeWood.BLOCK_REGISTRY.getObjects(WoodenBlockType.getBeds()).toArray(Block[]::new));
        } else {
            this.getOrCreateBuilder(tag).add(ILikeWood.BLOCK_REGISTRY.getObjects(blockType).toArray(Block[]::new));
        }
    }

    @Override
    protected void registerTags() {
        registerTag(ILikeWoodBlockTags.BARRELS, WoodenBlockType.BARREL);
        registerTag(ILikeWoodBlockTags.CHESTS, WoodenBlockType.CHEST);
        registerTag(Tags.Blocks.CHESTS, WoodenBlockType.CHEST);
        registerTag(Tags.Blocks.CHESTS_WOODEN, WoodenBlockType.CHEST);
        registerTag(ILikeWoodBlockTags.COMPOSTER, WoodenBlockType.COMPOSTER);
        registerTag(ILikeWoodBlockTags.BOOKSHELFS, WoodenBlockType.BOOKSHELF);
        registerTag(ILikeWoodBlockTags.PANELS_SLABS, WoodenBlockType.PANELS_SLAB);
        registerTag(ILikeWoodBlockTags.PANELS_STAIRS, WoodenBlockType.PANELS_STAIRS);
        registerTag(ILikeWoodBlockTags.PANELS, WoodenBlockType.PANELS);
        registerTag(ILikeWoodBlockTags.POSTS, WoodenBlockType.POST);
        registerTag(ILikeWoodBlockTags.STRIPPED_POSTS, WoodenBlockType.STRIPPED_POST);
        registerTag(ILikeWoodBlockTags.WALLS, WoodenBlockType.WALL);
        registerTag(BlockTags.WALLS, WoodenBlockType.WALL);
        registerTag(ILikeWoodBlockTags.LADDERS, WoodenBlockType.LADDER);
        registerTag(ILikeWoodBlockTags.TORCHES, WoodenBlockType.TORCH);
        registerTag(ILikeWoodBlockTags.WALL_TORCHES, WoodenBlockType.WALL_TORCH);
        registerTag(ILikeWoodBlockTags.CRAFTING_TABLES, WoodenBlockType.CRAFTING_TABLE);
        registerTag(ILikeWoodBlockTags.SCAFFOLDINGS, WoodenBlockType.SCAFFOLDING);
        registerTag(ILikeWoodBlockTags.BEDS, WoodenBlockType.WHITE_BED);
        registerTag(ILikeWoodBlockTags.SAWMILLS, WoodenBlockType.SAWMILL);
        registerTag(ILikeWoodBlockTags.SOUL_TORCHES, WoodenBlockType.SOUL_TORCH);
        registerTag(BlockTags.PIGLIN_REPELLENTS, WoodenBlockType.SOUL_TORCH);
        registerTag(ILikeWoodBlockTags.WALL_SOUL_TORCHES, WoodenBlockType.WALL_SOUL_TORCH);
        registerTag(BlockTags.PIGLIN_REPELLENTS, WoodenBlockType.WALL_SOUL_TORCH);
    }

    @Override
    public String getName() {
        return "I Like Wood - Block Tags";
    }
}
