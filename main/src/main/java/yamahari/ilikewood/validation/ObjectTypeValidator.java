package yamahari.ilikewood.validation;

import com.google.common.collect.Sets;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.config.ILikeWoodObjectTypesConfig;
import yamahari.ilikewood.registry.objecttype.*;

import java.util.*;

public final class ObjectTypeValidator {
    private static final Map<IObjectType, Set<IObjectType>> OBJECT_TYPE_DEPENDENCIES;
    private static final Set<IObjectType> DEPENDS_ON_PANELS = Sets.newHashSet(WoodenBlockType.PANELS);
    private static final Set<IObjectType> DEPENDS_ON_PANELS_SLABS = Sets.newHashSet(WoodenBlockType.PANELS_SLAB);
    private static final Set<IObjectType> DEPENDS_ON_STICKS = Sets.newHashSet(WoodenItemType.STICK);
    private static final Set<IObjectType> DEPENDS_ON_NONE = Collections.emptySet();
    private static final Set<IObjectType> DEPENDS_ON_STRIPPED_POSTS_AND_ENTITY_CHAIR =
        Sets.newHashSet(WoodenBlockType.STRIPPED_POST, WoodenEntityType.CHAIR);
    private static final Set<IObjectType> DEPENDS_ON_STICKS_AND_PANELS =
        Sets.newHashSet(WoodenItemType.STICK, WoodenBlockType.PANELS);

    static {
        final Map<IObjectType, Set<IObjectType>> objectTypeDependencies = new HashMap<>();

        objectTypeDependencies.put(WoodenBlockType.PANELS, DEPENDS_ON_NONE);
        objectTypeDependencies.put(WoodenBlockType.PANELS_STAIRS, DEPENDS_ON_PANELS);
        objectTypeDependencies.put(WoodenBlockType.PANELS_SLAB, DEPENDS_ON_PANELS);
        objectTypeDependencies.put(WoodenBlockType.BARREL,
            Sets.newHashSet(WoodenBlockType.PANELS, WoodenBlockType.PANELS_SLAB));
        WoodenBlockType.getBeds().forEach(blockType -> objectTypeDependencies.put(blockType, DEPENDS_ON_PANELS));
        objectTypeDependencies.put(WoodenBlockType.BOOKSHELF, DEPENDS_ON_PANELS);
        objectTypeDependencies.put(WoodenBlockType.COMPOSTER, DEPENDS_ON_PANELS_SLABS);
        objectTypeDependencies.put(WoodenBlockType.CRAFTING_TABLE, DEPENDS_ON_PANELS);
        objectTypeDependencies.put(WoodenBlockType.CHEST, DEPENDS_ON_PANELS);
        objectTypeDependencies.put(WoodenBlockType.SAWMILL, DEPENDS_ON_PANELS);
        objectTypeDependencies.put(WoodenBlockType.LECTERN,
            Sets.newHashSet(WoodenBlockType.PANELS_SLAB, WoodenBlockType.BOOKSHELF));
        objectTypeDependencies.put(WoodenBlockType.LADDER, Sets.newHashSet(WoodenItemType.STICK));
        objectTypeDependencies.put(WoodenBlockType.SCAFFOLDING, DEPENDS_ON_STICKS);
        objectTypeDependencies.put(WoodenBlockType.SOUL_TORCH, DEPENDS_ON_STICKS);
        objectTypeDependencies.put(WoodenBlockType.TORCH, DEPENDS_ON_STICKS);
        objectTypeDependencies.put(WoodenBlockType.WALL_TORCH,
            Sets.newHashSet(WoodenItemType.STICK, WoodenBlockType.TORCH));
        objectTypeDependencies.put(WoodenBlockType.WALL_SOUL_TORCH,
            Sets.newHashSet(WoodenItemType.STICK, WoodenBlockType.SOUL_TORCH));
        objectTypeDependencies.put(WoodenBlockType.LOG_PILE, Sets.newHashSet(WoodenBlockType.POST));
        objectTypeDependencies.put(WoodenBlockType.POST, DEPENDS_ON_NONE);
        objectTypeDependencies.put(WoodenBlockType.STRIPPED_POST, Sets.newHashSet(WoodenBlockType.POST));
        objectTypeDependencies.put(WoodenBlockType.WALL, DEPENDS_ON_NONE);
        objectTypeDependencies.put(WoodenBlockType.CHAIR, DEPENDS_ON_STRIPPED_POSTS_AND_ENTITY_CHAIR);
        objectTypeDependencies.put(WoodenBlockType.TABLE, Sets.newHashSet(WoodenBlockType.STRIPPED_POST));
        objectTypeDependencies.put(WoodenBlockType.STOOL, DEPENDS_ON_STRIPPED_POSTS_AND_ENTITY_CHAIR);
        objectTypeDependencies.put(WoodenBlockType.SINGLE_DRESSER, DEPENDS_ON_NONE);

        objectTypeDependencies.put(WoodenItemType.STICK, DEPENDS_ON_PANELS);
        objectTypeDependencies.put(WoodenItemType.BOW, DEPENDS_ON_STICKS);
        objectTypeDependencies.put(WoodenItemType.CROSSBOW, DEPENDS_ON_STICKS);
        objectTypeDependencies.put(WoodenItemType.FISHING_ROD, DEPENDS_ON_STICKS);
        objectTypeDependencies.put(WoodenItemType.ITEM_FRAME,
            Sets.newHashSet(WoodenItemType.STICK, WoodenEntityType.ITEM_FRAME));

        objectTypeDependencies.put(WoodenTieredItemType.AXE, DEPENDS_ON_STICKS_AND_PANELS);
        objectTypeDependencies.put(WoodenTieredItemType.HOE, DEPENDS_ON_STICKS_AND_PANELS);
        objectTypeDependencies.put(WoodenTieredItemType.PICKAXE, DEPENDS_ON_STICKS_AND_PANELS);
        objectTypeDependencies.put(WoodenTieredItemType.SHOVEL, DEPENDS_ON_STICKS_AND_PANELS);
        objectTypeDependencies.put(WoodenTieredItemType.SWORD, DEPENDS_ON_STICKS_AND_PANELS);

        // TODO actually depends on CHAIR >OR< STOOL, does not really fit the design
        objectTypeDependencies.put(WoodenEntityType.CHAIR, DEPENDS_ON_NONE);

        objectTypeDependencies.put(WoodenEntityType.ITEM_FRAME, Sets.newHashSet(WoodenItemType.ITEM_FRAME));

        OBJECT_TYPE_DEPENDENCIES = Collections.unmodifiableMap(objectTypeDependencies);
    }

    private ObjectTypeValidator() {
    }

    private static Set<IObjectType> getDependencies(final IObjectType objectType) {
        return Objects.requireNonNull(OBJECT_TYPE_DEPENDENCIES.get(objectType),
            String.format("Missing dependency set for object type \"%s\".", objectType.getName()));
    }

    public static Set<IObjectType> resolveDependencies(final IObjectType objectType) {
        var dependencies = new HashSet<>(getDependencies(objectType));

        boolean changed;
        do {
            changed = false;
            var tmpDependencies = new HashSet<>(dependencies);
            for (final var objectTypeOuter : dependencies) {
                final var outerDependencies = getDependencies(objectTypeOuter);
                for (final var objectTypeInner : outerDependencies) {
                    changed = tmpDependencies.add(objectTypeInner) || changed;
                }
            }
            dependencies = tmpDependencies;
        } while (changed);

        return dependencies;
    }

    public static boolean validate(final IObjectType objectType) {
        final var dependencies = resolveDependencies(objectType);
        var success = true;
        if (ILikeWoodObjectTypesConfig.isEnabled(objectType)) {
            for (final var dependency : dependencies) {
                if (!ILikeWoodObjectTypesConfig.isEnabled(dependency)) {
                    success = false;
                    ILikeWood.LOGGER.error(String.format("Dependency \"%s\" of object type \"%s\" was not satisfied.",
                        dependency.getName(),
                        objectType.getName()));
                }
            }
        }
        return success;
    }
}