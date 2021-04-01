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
import yamahari.ilikewood.util.objecttype.WoodenObjectType;
import yamahari.ilikewood.util.objecttype.WoodenObjectTypes;

public final class ILikeWoodBlockTagsProvider extends BlockTagsProvider {
    public ILikeWoodBlockTagsProvider(final DataGenerator generator, final ExistingFileHelper existingFileHelper) {
        super(generator, Constants.MOD_ID, existingFileHelper);
    }

    private void registerTag(final ITag.INamedTag<Block> tag, final WoodenObjectType objectType) {
        if (objectType.equals(WoodenObjectTypes.BED)) {
            this.getOrCreateBuilder(tag).add(WoodenBlocks.getBedBlocks().toArray(Block[]::new));
        } else {
            this.getOrCreateBuilder(tag).add(WoodenBlocks.getBlocks(objectType).toArray(Block[]::new));
        }
    }

    @Override
    protected void registerTags() {
        registerTag(ILikeWoodBlockTags.BARRELS, WoodenObjectTypes.BARREL);
        registerTag(ILikeWoodBlockTags.CHESTS, WoodenObjectTypes.CHEST);
        registerTag(ILikeWoodBlockTags.COMPOSTER, WoodenObjectTypes.COMPOSTER);
        registerTag(ILikeWoodBlockTags.BOOKSHELFS, WoodenObjectTypes.BOOKSHELF);
        registerTag(ILikeWoodBlockTags.PANELS_SLABS, WoodenObjectTypes.SLAB);
        registerTag(ILikeWoodBlockTags.PANELS_STAIRS, WoodenObjectTypes.STAIRS);
        registerTag(ILikeWoodBlockTags.PANELS, WoodenObjectTypes.PANELS);
        registerTag(ILikeWoodBlockTags.POSTS, WoodenObjectTypes.POST);
        registerTag(ILikeWoodBlockTags.STRIPPED_POSTS, WoodenObjectTypes.STRIPPED_POST);
        registerTag(ILikeWoodBlockTags.WALLS, WoodenObjectTypes.WALL);
        registerTag(BlockTags.WALLS, WoodenObjectTypes.WALL);
        registerTag(ILikeWoodBlockTags.LADDERS, WoodenObjectTypes.LADDER);
        registerTag(ILikeWoodBlockTags.TORCHES, WoodenObjectTypes.TORCH);
        registerTag(ILikeWoodBlockTags.WALL_TORCHES, WoodenObjectTypes.WALL_TORCH);
        registerTag(ILikeWoodBlockTags.CRAFTING_TABLES, WoodenObjectTypes.CRAFTING_TABLE);
        registerTag(ILikeWoodBlockTags.SCAFFOLDINGS, WoodenObjectTypes.SCAFFOLDING);
        registerTag(ILikeWoodBlockTags.BEDS, WoodenObjectTypes.BED);
        registerTag(ILikeWoodBlockTags.SAWMILLS, WoodenObjectTypes.SAWMILL);
        registerTag(ILikeWoodBlockTags.SOUL_TORCHES, WoodenObjectTypes.SOUL_TORCH);
        registerTag(ILikeWoodBlockTags.WALL_SOUL_TORCHES, WoodenObjectTypes.WALL_SOUL_TORCH);
    }

    @Override
    public String getName() {
        return "I Like Wood - Block Tags";
    }
}
