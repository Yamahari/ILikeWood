package yamahari.ilikewood.validation;

import com.google.common.collect.Sets;
import yamahari.ilikewood.registry.objecttype.*;

import java.util.*;

public final class ObjectTypeValidator {
    private static final Map<IObjectType, List<Set<IObjectType>>> DEPENDENCIES;
    private static final List<Set<IObjectType>> DEPENDS_ON_PANELS = Collections.singletonList(Sets.newHashSet(
        WoodenBlockType.PANELS));
    private static final List<Set<IObjectType>> DEPENDS_ON_PANELS_SLABS = Collections.singletonList(Sets.newHashSet(
        WoodenBlockType.PANELS_SLAB));
    private static final List<Set<IObjectType>> DEPENDS_ON_STICKS = Collections.singletonList(Sets.newHashSet(
        WoodenItemType.STICK));
    private static final List<Set<IObjectType>> DEPENDS_ON_NONE = Collections.singletonList(Collections.emptySet());
    private static final List<Set<IObjectType>> DEPENDS_ON_STRIPPED_POSTS_AND_ENTITY_CHAIR =
        Collections.singletonList(Sets.newHashSet(WoodenBlockType.STRIPPED_POST, WoodenEntityType.CHAIR));
    private static final List<Set<IObjectType>> DEPENDS_ON_STICKS_AND_PANELS =
        Collections.singletonList(Sets.newHashSet(WoodenItemType.STICK, WoodenBlockType.PANELS));

    static {
        final Map<IObjectType, List<Set<IObjectType>>> dependencies = new HashMap<>();

        dependencies.put(WoodenBlockType.PANELS, DEPENDS_ON_NONE);
        dependencies.put(WoodenBlockType.PANELS_STAIRS, DEPENDS_ON_PANELS);
        dependencies.put(WoodenBlockType.PANELS_SLAB, DEPENDS_ON_PANELS);
        dependencies.put(
            WoodenBlockType.BARREL,
            Collections.singletonList(Sets.newHashSet(WoodenBlockType.PANELS, WoodenBlockType.PANELS_SLAB))
        );
        WoodenBlockType.getBeds().forEach(blockType -> dependencies.put(blockType, DEPENDS_ON_PANELS));
        dependencies.put(WoodenBlockType.BOOKSHELF, DEPENDS_ON_PANELS);
        dependencies.put(WoodenBlockType.COMPOSTER, DEPENDS_ON_PANELS_SLABS);
        dependencies.put(WoodenBlockType.CRAFTING_TABLE, DEPENDS_ON_PANELS);
        dependencies.put(WoodenBlockType.CHEST, DEPENDS_ON_PANELS);
        dependencies.put(
            WoodenBlockType.SAWMILL,
            Collections.singletonList(Sets.newHashSet(WoodenBlockType.PANELS, WoodenBlockType.LOG_PILE))
        );
        dependencies.put(
            WoodenBlockType.LECTERN,
            Collections.singletonList(Sets.newHashSet(WoodenBlockType.PANELS_SLAB, WoodenBlockType.BOOKSHELF))
        );
        dependencies.put(WoodenBlockType.LADDER, DEPENDS_ON_STICKS);
        dependencies.put(WoodenBlockType.SCAFFOLDING, DEPENDS_ON_STICKS);
        dependencies.put(
            WoodenBlockType.SOUL_TORCH,
            Collections.singletonList(Sets.newHashSet(WoodenBlockType.TORCH, WoodenItemType.STICK))
        );
        dependencies.put(
            WoodenBlockType.TORCH,
            Collections.singletonList(Sets.newHashSet(WoodenBlockType.SOUL_TORCH, WoodenItemType.STICK))
        );
        dependencies.put(
            WoodenBlockType.WALL_TORCH,
            Collections.singletonList(Sets.newHashSet(WoodenItemType.STICK, WoodenBlockType.TORCH))
        );
        dependencies.put(
            WoodenBlockType.WALL_SOUL_TORCH,
            Collections.singletonList(Sets.newHashSet(WoodenItemType.STICK, WoodenBlockType.SOUL_TORCH))
        );
        dependencies.put(WoodenBlockType.LOG_PILE, Collections.singletonList(Sets.newHashSet(WoodenBlockType.POST)));
        dependencies.put(WoodenBlockType.POST, DEPENDS_ON_NONE);
        dependencies.put(
            WoodenBlockType.STRIPPED_POST,
            Collections.singletonList(Sets.newHashSet(WoodenBlockType.POST))
        );
        dependencies.put(WoodenBlockType.WALL, DEPENDS_ON_NONE);
        dependencies.put(WoodenBlockType.CHAIR, DEPENDS_ON_STRIPPED_POSTS_AND_ENTITY_CHAIR);
        dependencies.put(
            WoodenBlockType.TABLE,
            Collections.singletonList(Sets.newHashSet(WoodenBlockType.STRIPPED_POST))
        );
        dependencies.put(WoodenBlockType.STOOL, DEPENDS_ON_STRIPPED_POSTS_AND_ENTITY_CHAIR);
        dependencies.put(WoodenBlockType.SINGLE_DRESSER, DEPENDS_ON_NONE);

        dependencies.put(WoodenItemType.STICK, DEPENDS_ON_PANELS);
        dependencies.put(WoodenItemType.BOW, DEPENDS_ON_STICKS);
        dependencies.put(WoodenItemType.CROSSBOW, DEPENDS_ON_STICKS);
        dependencies.put(WoodenItemType.FISHING_ROD, DEPENDS_ON_STICKS);
        dependencies.put(
            WoodenItemType.ITEM_FRAME,
            Collections.singletonList(Sets.newHashSet(WoodenItemType.STICK, WoodenEntityType.ITEM_FRAME))
        );

        dependencies.put(WoodenTieredItemType.AXE, DEPENDS_ON_STICKS_AND_PANELS);
        dependencies.put(WoodenTieredItemType.HOE, DEPENDS_ON_STICKS_AND_PANELS);
        dependencies.put(WoodenTieredItemType.PICKAXE, DEPENDS_ON_STICKS_AND_PANELS);
        dependencies.put(WoodenTieredItemType.SHOVEL, DEPENDS_ON_STICKS_AND_PANELS);
        dependencies.put(WoodenTieredItemType.SWORD, DEPENDS_ON_STICKS_AND_PANELS);


        dependencies.put(
            WoodenEntityType.CHAIR,
            List.of(Sets.newHashSet(WoodenBlockType.CHAIR), Sets.newHashSet(WoodenBlockType.STOOL))
        );
        dependencies.put(
            WoodenEntityType.ITEM_FRAME,
            Collections.singletonList(Sets.newHashSet(WoodenItemType.ITEM_FRAME))
        );

        DEPENDENCIES = Collections.unmodifiableMap(dependencies);
    }

    private ObjectTypeValidator() {
    }

    public static List<Set<IObjectType>> getDependencies(final IObjectType objectType) {
        return Objects.requireNonNull(
            DEPENDENCIES.get(objectType),
            String.format("Missing dependency set for object type \"%s\".", objectType.getName())
        );
    }

    public static boolean validate(final StringBuilder builder, final IObjectType objectType) {
        final var dependencies = getDependencies(objectType);

        if (objectType.isEnabled()) {
            return dependencies.stream().map(ds -> {
                var success = true;
                for (final var d : ds) {
                    if (!d.isEnabled()) {
                        success = false;
                        builder.append(String.format(
                            "Dependency \"%s\" of object type \"%s\" was not satisfied.",
                            d.getName(),
                            objectType.getName()
                        ));
                        builder.append(System.lineSeparator());
                    }
                }
                return success;
            }).reduce(false, Boolean::logicalOr);
        }
        return true;
    }
}