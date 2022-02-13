package yamahari.ilikewood.validation;

import com.google.common.collect.Sets;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.*;
import yamahari.ilikewood.registry.woodtype.IWoodType;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public final class WoodTypeValidator {
    private static final Map<IObjectType, Set<ResourceRequirement>> RESOURCE_REQUIREMENTS;

    private static final Set<ResourceRequirement> REQUIRES_PLANKS = Sets.immutableEnumSet(ResourceRequirement.PLANKS);
    private static final Set<ResourceRequirement> REQUIRES_NONE = Collections.emptySet();
    private static final Set<ResourceRequirement> REQUIRES_LOGS = Sets.immutableEnumSet(ResourceRequirement.LOGS);
    private static final Set<ResourceRequirement> REQUIRES_STRIPPED_LOGS =
        Sets.immutableEnumSet(ResourceRequirement.STRIPPED_LOGS);
    private static final Set<ResourceRequirement> REQUIRES_LOGS_BOTH =
        Sets.immutableEnumSet(ResourceRequirement.LOGS, ResourceRequirement.STRIPPED_LOGS);

    static {
        final Map<IObjectType, Set<ResourceRequirement>> resourceRequirements = new HashMap<>();

        resourceRequirements.put(WoodenBlockType.PANELS, REQUIRES_PLANKS);
        resourceRequirements.put(WoodenBlockType.PANELS_STAIRS, REQUIRES_PLANKS);
        resourceRequirements.put(WoodenBlockType.PANELS_SLAB, REQUIRES_PLANKS);
        resourceRequirements.put(WoodenBlockType.BARREL, REQUIRES_NONE);
        WoodenBlockType.getBeds().forEach(blockType -> resourceRequirements.put(blockType, REQUIRES_PLANKS));
        resourceRequirements.put(WoodenBlockType.BOOKSHELF, REQUIRES_PLANKS);
        resourceRequirements.put(WoodenBlockType.COMPOSTER, REQUIRES_NONE);
        resourceRequirements.put(WoodenBlockType.CRAFTING_TABLE, REQUIRES_PLANKS);
        resourceRequirements.put(WoodenBlockType.CHEST, REQUIRES_NONE);
        resourceRequirements.put(WoodenBlockType.SAWMILL,
            Sets.immutableEnumSet(ResourceRequirement.PLANKS,
                ResourceRequirement.LOGS,
                ResourceRequirement.STRIPPED_LOGS));
        resourceRequirements.put(WoodenBlockType.LECTERN, REQUIRES_NONE);
        resourceRequirements.put(WoodenBlockType.LADDER, REQUIRES_NONE);
        resourceRequirements.put(WoodenBlockType.SCAFFOLDING, REQUIRES_NONE);
        resourceRequirements.put(WoodenBlockType.SOUL_TORCH, REQUIRES_NONE);
        resourceRequirements.put(WoodenBlockType.TORCH, REQUIRES_NONE);
        resourceRequirements.put(WoodenBlockType.WALL_TORCH, REQUIRES_NONE);
        resourceRequirements.put(WoodenBlockType.WALL_SOUL_TORCH, REQUIRES_NONE);
        resourceRequirements.put(WoodenBlockType.LOG_PILE, REQUIRES_LOGS);
        resourceRequirements.put(WoodenBlockType.POST, REQUIRES_LOGS);
        resourceRequirements.put(WoodenBlockType.STRIPPED_POST, REQUIRES_PLANKS);
        resourceRequirements.put(WoodenBlockType.WALL, REQUIRES_LOGS_BOTH);
        resourceRequirements.put(WoodenBlockType.CHAIR, REQUIRES_STRIPPED_LOGS);
        resourceRequirements.put(WoodenBlockType.TABLE, REQUIRES_LOGS_BOTH);
        resourceRequirements.put(WoodenBlockType.STOOL, REQUIRES_STRIPPED_LOGS);
        resourceRequirements.put(WoodenBlockType.SINGLE_DRESSER, REQUIRES_LOGS_BOTH);

        resourceRequirements.put(WoodenItemType.STICK, REQUIRES_NONE);
        resourceRequirements.put(WoodenItemType.BOW, REQUIRES_NONE);
        resourceRequirements.put(WoodenItemType.CROSSBOW, REQUIRES_NONE);
        resourceRequirements.put(WoodenItemType.FISHING_ROD, REQUIRES_NONE);
        resourceRequirements.put(WoodenItemType.ITEM_FRAME, REQUIRES_PLANKS);

        resourceRequirements.put(WoodenTieredItemType.AXE, REQUIRES_NONE);
        resourceRequirements.put(WoodenTieredItemType.HOE, REQUIRES_NONE);
        resourceRequirements.put(WoodenTieredItemType.PICKAXE, REQUIRES_NONE);
        resourceRequirements.put(WoodenTieredItemType.SHOVEL, REQUIRES_NONE);
        resourceRequirements.put(WoodenTieredItemType.SWORD, REQUIRES_NONE);

        // TODO entity types do not really fit this design
        resourceRequirements.put(WoodenEntityType.ITEM_FRAME, REQUIRES_NONE);
        resourceRequirements.put(WoodenEntityType.CHAIR, REQUIRES_NONE);

        RESOURCE_REQUIREMENTS = Collections.unmodifiableMap(resourceRequirements);
    }

    private WoodTypeValidator() {
    }

    private static Set<ResourceRequirement> resolveRequirements(final IObjectType objectType) {
        final var dependencies = ObjectTypeValidator.resolveDependencies(objectType);

        // TODO maybe add filter for enabled dependencies
        return dependencies
            .stream()
            .flatMap(dependency -> Objects
                .requireNonNull(RESOURCE_REQUIREMENTS.get(dependency),
                    String.format("Missing resource requirement set for object type \"%s\".", objectType.getName()))
                .stream())
            .collect(Collectors.toSet());
    }

    private static <T extends IObjectType> boolean validateRequirements(final Set<T> objectTypes, final T objectType,
                                                                        final IWoodType woodType,
                                                                        final BiFunction<IWoodType, T, Boolean> biFunction) {
        var result = true;
        if (objectTypes.contains(objectType)) {
            final var requirements = resolveRequirements(objectType);
            for (final var requirement : requirements) {
                switch (requirement) {
                case PLANKS -> {
                    if (!ILikeWood.WOODEN_RESOURCE_REGISTRY.hasPlanks(woodType)) {
                        ILikeWood.LOGGER.error(String.format(
                            "Resource requirement \"planks\" of object type \"%s\" for wood type \"%s\" was not satisfied.",
                            objectType.getName(),
                            woodType.getName()));
                        result = false;
                    }
                }
                case LOGS -> {
                    if (!ILikeWood.WOODEN_RESOURCE_REGISTRY.hasLog(woodType)) {
                        ILikeWood.LOGGER.error(String.format(
                            "Resource requirement \"logs\" of object type \"%s\" for wood type \"%s\" was not satisfied.",
                            objectType.getName(),
                            woodType.getName()));
                        result = false;
                    }
                }
                case STRIPPED_LOGS -> {
                    if (!ILikeWood.WOODEN_RESOURCE_REGISTRY.hasStrippedLog(woodType)) {
                        ILikeWood.LOGGER.error(String.format(
                            "Resource requirement \"stripped logs\" of object type \"%s\" for wood type \"%s\" was not satisfied.",
                            objectType.getName(),
                            woodType.getName()));
                        result = false;
                    }
                }
                case SLAB -> {
                    if (!ILikeWood.WOODEN_RESOURCE_REGISTRY.hasSlab(woodType)) {
                        ILikeWood.LOGGER.error(String.format(
                            "Resource requirement \"slabs\" of object type \"%s\" for wood type \"%s\" was not satisfied.",
                            objectType.getName(),
                            woodType.getName()));
                        result = false;
                    }
                }
                }
            }
        } else {
            if (!biFunction.apply(woodType, objectType)) {
                ILikeWood.LOGGER.error(String.format(
                    "Block resource requirement \"%s\" of built-in object type \"%s\" for wood type \"%s\" was not satisfied.",
                    objectType.getName(),
                    objectType.getName(),
                    woodType.getName()));
                result = false;
            }
        }

        return result;
    }

    private static boolean validateDependencies(final IObjectType objectType, final IObjectTypeVisitor visitor,
                                                final IWoodType woodType) {
        // TODO maybe add filter for enabled dependencies
        return ObjectTypeValidator.resolveDependencies(objectType).stream().reduce(true, (a, dependency) -> {
            final var x = dependency.acceptVisitor(visitor);
            if (!x) {
                ILikeWood.LOGGER.error(String.format(
                    "Dependency \"%s\" of object type \"%s\" for wood type \"%s\" was not satisfied.",
                    dependency.getName(),
                    objectType.getName(),
                    woodType.getName()));
            }
            return a && x;
        }, Boolean::logicalAnd);
    }

    // TODO Fuel Map
    public static boolean validate(final IWoodType woodType) {
        final Set<WoodenBlockType> blockTypes = woodType.getBlockTypes();
        final Set<WoodenBlockType> builtinBlockTypes = woodType.getBuiltinBlockTypes();
        final Set<WoodenItemType> itemTypes = woodType.getItemTypes();
        final Set<WoodenItemType> builtinItemTypes = woodType.getBuiltinItemTypes();
        final Set<WoodenEntityType> entityTypes = woodType.getEntityTypes();
        final Set<WoodenTieredItemType> tieredItemTypes = woodType.getTieredItemTypes();

        final IObjectTypeVisitor visitor = new IObjectTypeVisitor() {
            @Override
            public boolean visit(final WoodenBlockType blockType) {
                return blockTypes.contains(blockType) || builtinBlockTypes.contains(blockType);
            }

            @Override
            public boolean visit(final WoodenItemType itemType) {
                return itemTypes.contains(itemType) || builtinItemTypes.contains(itemType);
            }

            @Override
            public boolean visit(final WoodenTieredItemType tieredItemType) {
                return tieredItemTypes.contains(tieredItemType);
            }

            @Override
            public boolean visit(final WoodenEntityType entityType) {
                return entityTypes.contains(entityType);
            }
        };

        final var blockTypesValidation = WoodenBlockType
            .getAll()
            .filter(blockType -> blockTypes.contains(blockType) || builtinBlockTypes.contains(blockType))
            .map(blockType -> validateRequirements(blockTypes,
                blockType,
                woodType,
                ILikeWood.WOODEN_RESOURCE_REGISTRY::hasBlockResource) &&
                              validateDependencies(blockType, visitor, woodType))
            .reduce(true, Boolean::logicalAnd);

        final var itemTypesValidation = WoodenItemType
            .getAll()
            .filter(itemType -> itemTypes.contains(itemType) || builtinItemTypes.contains(itemType))
            .map(itemType -> validateRequirements(itemTypes,
                itemType,
                woodType,
                ILikeWood.WOODEN_RESOURCE_REGISTRY::hasItemResource) &&
                             validateDependencies(itemType, visitor, woodType))
            .reduce(true, Boolean::logicalAnd);

        final var tieredItemTypesValidation = WoodenTieredItemType
            .getAll()
            .filter(tieredItemTypes::contains)
            .map(tieredItemType -> validateDependencies(tieredItemType, visitor, woodType))
            .reduce(true, Boolean::logicalAnd);

        final var entityTypesValidation = WoodenEntityType
            .getAll()
            .filter(entityTypes::contains)
            .map(entityType -> validateDependencies(entityType, visitor, woodType))
            .reduce(true, Boolean::logicalAnd);

        return blockTypesValidation && itemTypesValidation && tieredItemTypesValidation && entityTypesValidation;
    }

    private enum ResourceRequirement {
        PLANKS, LOGS, STRIPPED_LOGS, SLAB
    }
}