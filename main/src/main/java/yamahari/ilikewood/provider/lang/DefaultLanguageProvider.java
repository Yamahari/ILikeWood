package yamahari.ilikewood.provider.lang;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.IObjectType;
import yamahari.ilikewood.registry.objecttype.IObjectTypeVisitor;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.objecttype.WoodenEntityType;
import yamahari.ilikewood.registry.objecttype.WoodenItemType;
import yamahari.ilikewood.registry.objecttype.WoodenTieredItemType;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.Util;

import java.util.Objects;

public class DefaultLanguageProvider extends LanguageProvider implements IObjectTypeVisitor
{
    private final IObjectType objectType;

    public DefaultLanguageProvider(final DataGenerator generator, final IObjectType objectType) {
        super(generator, Constants.MOD_ID, "en_us");
        this.objectType = objectType;
    }

    @Override
    protected void addTranslations() {
        this.objectType.acceptVisitor(this);
    }

    @Override
    public boolean visit(final WoodenBlockType blockType) {
        ILikeWood.BLOCK_REGISTRY.getObjects(blockType).forEach(block -> this.add(block,
            Util.toTranslationName(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath())
        ));
        return true;
    }

    @Override
    public boolean visit(final WoodenItemType itemType) {
        ILikeWood.ITEM_REGISTRY.getObjects(itemType).forEach(item -> this.add(item,
            Util.toTranslationName(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath())
        ));
        return true;
    }

    @Override
    public boolean visit(final WoodenTieredItemType tieredItemType) {
        ILikeWood.TIERED_ITEM_REGISTRY.getObjects(tieredItemType).forEach(tieredItem -> this.add(tieredItem,
            Util.toTranslationName(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(tieredItem)).getPath())
        ));
        return true;
    }

    @Override
    public boolean visit(final WoodenEntityType entityType) {
        // TODO do entities needs translation?
        return false;
    }
}
