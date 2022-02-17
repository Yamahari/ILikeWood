package yamahari.ilikewood.config;

import com.google.common.base.Suppliers;
import net.minecraftforge.fml.ModList;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.*;
import yamahari.ilikewood.util.Constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public final class ILikeWoodObjectTypesConfig {
    private static final Map<IObjectType, Supplier<Boolean>> OBJECT_TYPE_FLAGS;
    private static final Supplier<Boolean> IS_DATA_GEN;

    static {
        final Map<IObjectType, Supplier<Boolean>> objectTypeFlags = new HashMap<>();

        makeFlag(objectTypeFlags, WoodenBlockType.PANELS);
        makeFlag(objectTypeFlags, WoodenBlockType.PANELS_STAIRS);
        makeFlag(objectTypeFlags, WoodenBlockType.PANELS_SLAB);
        makeFlag(objectTypeFlags, WoodenBlockType.BARREL);
        makeBedFlag(objectTypeFlags);
        makeFlag(objectTypeFlags, WoodenBlockType.BOOKSHELF);
        makeFlag(objectTypeFlags, WoodenBlockType.COMPOSTER);
        makeFlag(objectTypeFlags, WoodenBlockType.CRAFTING_TABLE);
        makeFlag(objectTypeFlags, WoodenBlockType.CHEST);
        makeFlag(objectTypeFlags, WoodenBlockType.SAWMILL);
        makeFlag(objectTypeFlags, WoodenBlockType.LECTERN);
        makeFlag(objectTypeFlags, WoodenBlockType.LADDER);
        makeFlag(objectTypeFlags, WoodenBlockType.SCAFFOLDING);
        makeFlag(objectTypeFlags, WoodenBlockType.SOUL_TORCH);
        makeFlag(objectTypeFlags, WoodenBlockType.TORCH);
        makeFlag(objectTypeFlags, WoodenBlockType.LOG_PILE);
        makeFlag(objectTypeFlags, WoodenBlockType.POST);
        makeFlag(objectTypeFlags, WoodenBlockType.STRIPPED_POST);
        makeFlag(objectTypeFlags, WoodenBlockType.WALL);
        makeFlag(objectTypeFlags, WoodenBlockType.CHAIR);
        makeFlag(objectTypeFlags, WoodenBlockType.TABLE);
        makeFlag(objectTypeFlags, WoodenBlockType.STOOL);
        makeFlag(objectTypeFlags, WoodenBlockType.SINGLE_DRESSER);

        makeFlag(objectTypeFlags, WoodenItemType.STICK);
        makeFlag(objectTypeFlags, WoodenItemType.BOW);
        makeFlag(objectTypeFlags, WoodenItemType.CROSSBOW);
        makeFlag(objectTypeFlags, WoodenItemType.FISHING_ROD);
        makeFlag(objectTypeFlags, WoodenItemType.ITEM_FRAME);

        makeFlag(objectTypeFlags, WoodenTieredItemType.AXE);
        makeFlag(objectTypeFlags, WoodenTieredItemType.HOE);
        makeFlag(objectTypeFlags, WoodenTieredItemType.PICKAXE);
        makeFlag(objectTypeFlags, WoodenTieredItemType.SHOVEL);
        makeFlag(objectTypeFlags, WoodenTieredItemType.SWORD);

        OBJECT_TYPE_FLAGS = Collections.unmodifiableMap(objectTypeFlags);

        IS_DATA_GEN = Suppliers.memoize(ILikeWoodObjectTypesConfig::isDataGen);
    }

    private static boolean isDataGen() {
        try {
            final String dataModId = System.getProperty("ilikewood.datagen.modid");
            return dataModId != null;
        } catch (NullPointerException | SecurityException | IllegalArgumentException e) {
            ILikeWood.LOGGER.error(e.getMessage());
        }
        return false;
    }

    private static boolean getFlag(final IObjectType objectType) {
        return Objects.requireNonNull(OBJECT_TYPE_FLAGS.get(objectType).get(),
            String.format("Missing config flag for object type \"%s\".", objectType.getName()));
    }

    private static void makeFlag(final Map<IObjectType, Supplier<Boolean>> objectTypeFlags,
                                 final IObjectType objectType) {
        objectTypeFlags.put(objectType,
            Suppliers.memoize(() -> ModList
                .get()
                .isLoaded(String.format("%s_%s", Constants.MOD_ID, objectType.getNamePlural()))));
    }

    private static void makeBedFlag(final Map<IObjectType, Supplier<Boolean>> objectTypeFlags) {
        objectTypeFlags.put(WoodenBlockType.WHITE_BED,
            Suppliers.memoize(() -> ModList.get().isLoaded(String.format("%s_beds", Constants.MOD_ID))));
    }

    public static boolean isEnabled(final IObjectType objectType) {
        return IS_DATA_GEN.get() || objectType.acceptVisitor(VISITOR);
    }

    // TODO Chair Entity is used for stools also
    // TODO Tile entity types

    private static final IObjectTypeVisitor VISITOR = new IObjectTypeVisitor() {
        @Override
        public boolean visit(final WoodenBlockType blockType) {
            if (blockType.equals(WoodenBlockType.STRIPPED_POST)) {
                return getFlag(WoodenBlockType.POST);
            } else if (blockType.equals(WoodenBlockType.WALL_TORCH)) {
                return getFlag(WoodenBlockType.TORCH);
            } else if (blockType.equals(WoodenBlockType.WALL_SOUL_TORCH)) {
                return getFlag(WoodenBlockType.SOUL_TORCH);
            } else if (WoodenBlockType.getBeds().anyMatch(blockType::equals)) {
                return getFlag(WoodenBlockType.WHITE_BED);
            }
            return getFlag(blockType);
        }

        @Override
        public boolean visit(final WoodenItemType itemType) {
            return getFlag(itemType);
        }

        @Override
        public boolean visit(final WoodenTieredItemType tieredItemType) {
            return getFlag(tieredItemType);
        }

        @Override
        public boolean visit(final WoodenEntityType entityType) {
            if (entityType.equals(WoodenEntityType.CHAIR)) {
                return isEnabled(WoodenBlockType.CHAIR) || isEnabled(WoodenBlockType.STOOL);
            }

            if (entityType.equals(WoodenEntityType.ITEM_FRAME)) {
                return isEnabled(WoodenItemType.ITEM_FRAME);
            }

            ILikeWood.LOGGER.warn(String.format("No matching block/item type was found for entity type \"%s\".",
                entityType.getName()));

            return getFlag(entityType);
        }
    };
}
