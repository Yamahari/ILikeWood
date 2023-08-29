package yamahari.ilikewood.validation;

import com.google.common.collect.Sets;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.*;
import yamahari.ilikewood.registry.woodtype.IWoodType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public final class WoodTypeValidator
{
    private static final Map<IObjectType, Set<ResourceRequirement>> RESOURCE_REQUIREMENTS;

    private static final Set<ResourceRequirement> REQUIRES_PLANKS = Sets.immutableEnumSet(ResourceRequirement.PLANKS);
    private static final Set<ResourceRequirement> REQUIRES_NONE = Collections.emptySet();
    private static final Set<ResourceRequirement> REQUIRES_LOGS = Sets.immutableEnumSet(ResourceRequirement.LOGS);
    private static final Set<ResourceRequirement> REQUIRES_STRIPPED_LOGS = Sets.immutableEnumSet(ResourceRequirement.STRIPPED_LOGS);
    private static final Set<ResourceRequirement> REQUIRES_LOGS_BOTH = Sets.immutableEnumSet(ResourceRequirement.LOGS, ResourceRequirement.STRIPPED_LOGS);

    static
    {
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
        resourceRequirements.put(WoodenBlockType.SAWMILL, Sets.immutableEnumSet(ResourceRequirement.PLANKS, ResourceRequirement.LOGS, ResourceRequirement.STRIPPED_LOGS));
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
        resourceRequirements.put(WoodenBlockType.CAMPFIRE, REQUIRES_LOGS);
        resourceRequirements.put(WoodenBlockType.SOUL_CAMPFIRE, REQUIRES_LOGS);
        resourceRequirements.put(WoodenBlockType.CRATE, REQUIRES_NONE);

        resourceRequirements.put(WoodenItemType.STICK, REQUIRES_NONE);
        resourceRequirements.put(WoodenItemType.BOW, REQUIRES_NONE);
        resourceRequirements.put(WoodenItemType.CROSSBOW, REQUIRES_NONE);
        resourceRequirements.put(WoodenItemType.FISHING_ROD, REQUIRES_NONE);
        resourceRequirements.put(WoodenItemType.ITEM_FRAME, REQUIRES_PLANKS);
        resourceRequirements.put(WoodenItemType.PAINTING, REQUIRES_PLANKS);

        resourceRequirements.put(WoodenTieredItemType.AXE, REQUIRES_NONE);
        resourceRequirements.put(WoodenTieredItemType.HOE, REQUIRES_NONE);
        resourceRequirements.put(WoodenTieredItemType.PICKAXE, REQUIRES_NONE);
        resourceRequirements.put(WoodenTieredItemType.SHOVEL, REQUIRES_NONE);
        resourceRequirements.put(WoodenTieredItemType.SWORD, REQUIRES_NONE);

        resourceRequirements.put(WoodenEntityType.ITEM_FRAME, REQUIRES_NONE);
        resourceRequirements.put(WoodenEntityType.CHAIR, REQUIRES_NONE);
        resourceRequirements.put(WoodenEntityType.PAINTING, REQUIRES_NONE);

        RESOURCE_REQUIREMENTS = Collections.unmodifiableMap(resourceRequirements);
    }

    private WoodTypeValidator()
    {
    }

    private static <T extends IObjectType> boolean validateRequirements(
        final StringBuilder builder,
        final Set<T> objectTypes,
        final T objectType,
        final IWoodType woodType,
        final BiFunction<IWoodType, T, Boolean> biFunction
    )
    {
        var result = true;
        if (objectTypes.contains(objectType))
        {
            for (final var requirement : RESOURCE_REQUIREMENTS.get(objectType))
            {
                switch (requirement)
                {
                    case PLANKS -> {
                        if (!ILikeWood.WOODEN_RESOURCE_REGISTRY.hasPlanks(woodType))
                        {
                            builder.append(
                                String.format("Resource requirement \"planks\" of object type \"%s\" for wood type \"%s\" was not satisfied.", objectType.getName(),
                                    woodType.getName()
                                ));
                            builder.append(System.lineSeparator());
                            result = false;
                        }
                    }
                    case LOGS -> {
                        if (!ILikeWood.WOODEN_RESOURCE_REGISTRY.hasLog(woodType))
                        {
                            builder.append(
                                String.format("Resource requirement \"logs\" of object type \"%s\" for wood type \"%s\" was not satisfied.", objectType.getName(),
                                    woodType.getName()
                                ));
                            builder.append(System.lineSeparator());
                            result = false;
                        }
                    }
                    case STRIPPED_LOGS -> {
                        if (!ILikeWood.WOODEN_RESOURCE_REGISTRY.hasStrippedLog(woodType))
                        {
                            builder.append(String.format("Resource requirement \"stripped logs\" of object type \"%s\" for wood type \"%s\" was not satisfied.",
                                objectType.getName(), woodType.getName()
                            ));
                            builder.append(System.lineSeparator());
                            result = false;
                        }
                    }
                    case SLAB -> {
                        if (!ILikeWood.WOODEN_RESOURCE_REGISTRY.hasSlab(woodType))
                        {
                            builder.append(
                                String.format("Resource requirement \"slabs\" of object type \"%s\" for wood type \"%s\" was not satisfied.", objectType.getName(),
                                    woodType.getName()
                                ));
                            builder.append(System.lineSeparator());
                            result = false;
                        }
                    }
                }
            }
        }
        else
        {
            if (!biFunction.apply(woodType, objectType))
            {
                builder.append(
                    String.format("Block resource requirement \"%s\" of built-in object type \"%s\" for wood type \"%s\" was not satisfied.", objectType.getName(),
                        objectType.getName(), woodType.getName()
                    ));
                builder.append(System.lineSeparator());
                result = false;
            }
        }

        return result;
    }

    private static boolean validateDependencies(
        final StringBuilder builder,
        final IObjectType objectType,
        final IObjectTypeVisitor visitor,
        final IWoodType woodType
    )
    {
        // TODO maybe add filter for enabled dependencies
        return ObjectTypeValidator.getDependencies(objectType).stream().map(ds -> ds.stream().reduce(true, (a, dependency) ->
        {
            final var x = dependency.acceptVisitor(visitor);
            if (!x)
            {
                builder.append(
                    String.format("Dependency \"%s\" of object type \"%s\" for wood type \"%s\" was not satisfied.", dependency.getName(), objectType.getName(),
                        woodType.getName()
                    ));
                builder.append(System.lineSeparator());
            }
            return a && x;
        }, Boolean::logicalAnd)).reduce(false, Boolean::logicalOr);
    }

    private static <T extends IObjectType> boolean validateExclusivity(
        final StringBuilder builder,
        final Set<T> objectTypes,
        final Set<T> builtinObjectTypes,
        final IWoodType woodType
    )
    {
        var result = true;
        final var intersection = Sets.intersection(objectTypes, builtinObjectTypes);

        if (!intersection.isEmpty())
        {
            builder.append(String.format("Object types set and builtin object types set for wood type \"%s\" set are not disconnected. Duplicates:", woodType.getName()));
            builder.append(System.lineSeparator());

            for (final var duplicate : intersection)
            {
                builder.append(duplicate.getName());
                builder.append(System.lineSeparator());
            }

            result = false;
        }

        return result;
    }

    private static <T extends IObjectType> boolean validateProperties(
        final StringBuilder builder,
        final Set<T> objectTypes,
        final IWoodType woodType,
        final BiFunction<IWoodType, T, IWoodType.Properties> biFunction
    )
    {
        var result = true;

        for (final T objectType : objectTypes)
        {
            final var properties = biFunction.apply(woodType, objectType);

            if (properties == null)
            {
                builder.append(String.format("Missing properties for block type \"%s\" of wood type \"%s\".", objectType.getName(), woodType.getName()));
                builder.append(System.lineSeparator());
                result = false;
            }
            else if (properties.burnTime() < -1)
            {
                builder.append(String.format("Invalid burn time \"%d\" for block type \"%s\" of wood type \"%s\".", properties.burnTime(), objectType.getName(),
                    woodType.getName()
                ));
                builder.append(System.lineSeparator());
                result = false;
            }
        }

        return result;
    }

    public static boolean validate(
        final StringBuilder builder,
        final IWoodType woodType
    )
    {
        final Set<WoodenBlockType> blockTypes = woodType.getBlockTypes();
        final Set<WoodenBlockType> builtinBlockTypes = woodType.getBuiltinBlockTypes();
        final Set<WoodenItemType> itemTypes = woodType.getItemTypes();
        final Set<WoodenItemType> builtinItemTypes = woodType.getBuiltinItemTypes();
        final Set<WoodenEntityType> entityTypes = woodType.getEntityTypes();
        final Set<WoodenTieredItemType> tieredItemTypes = woodType.getTieredItemTypes();

        final IObjectTypeVisitor visitor = new IObjectTypeVisitor()
        {
            @Override
            public boolean visit(final WoodenBlockType blockType)
            {
                return blockTypes.contains(blockType) || builtinBlockTypes.contains(blockType);
            }

            @Override
            public boolean visit(final WoodenItemType itemType)
            {
                return itemTypes.contains(itemType) || builtinItemTypes.contains(itemType);
            }

            @Override
            public boolean visit(final WoodenTieredItemType tieredItemType)
            {
                return tieredItemTypes.contains(tieredItemType);
            }

            @Override
            public boolean visit(final WoodenEntityType entityType)
            {
                return entityTypes.contains(entityType);
            }
        };

        final var exclusivityValidation =
            validateExclusivity(builder, blockTypes, builtinBlockTypes, woodType) && validateExclusivity(builder, itemTypes, builtinItemTypes, woodType);

        final var propertiesValidation =
            validateProperties(builder, blockTypes, woodType, IWoodType::getProperties) && validateProperties(builder, itemTypes, woodType, IWoodType::getProperties);

        final var blockTypesValidation = WoodenBlockType
            .getAll()
            .filter(blockType -> blockTypes.contains(blockType) || builtinBlockTypes.contains(blockType))
            .map(
                blockType -> validateRequirements(builder, blockTypes, blockType, woodType, ILikeWood.WOODEN_RESOURCE_REGISTRY::hasBlockResource) && validateDependencies(
                    builder, blockType, visitor, woodType))
            .reduce(true, Boolean::logicalAnd);

        final var itemTypesValidation = WoodenItemType
            .getAll()
            .filter(itemType -> itemTypes.contains(itemType) || builtinItemTypes.contains(itemType))
            .map(itemType -> validateRequirements(builder, itemTypes, itemType, woodType, ILikeWood.WOODEN_RESOURCE_REGISTRY::hasItemResource) && validateDependencies(
                builder, itemType, visitor, woodType))
            .reduce(true, Boolean::logicalAnd);

        final var tieredItemTypesValidation = WoodenTieredItemType
            .getAll()
            .filter(tieredItemTypes::contains)
            .map(tieredItemType -> validateDependencies(builder, tieredItemType, visitor, woodType))
            .reduce(true, Boolean::logicalAnd);

        final var entityTypesValidation = WoodenEntityType
            .getAll()
            .filter(entityTypes::contains)
            .map(entityType -> validateDependencies(builder, entityType, visitor, woodType))
            .reduce(true, Boolean::logicalAnd);

        return exclusivityValidation && propertiesValidation && blockTypesValidation && itemTypesValidation && tieredItemTypesValidation && entityTypesValidation;
    }

    private enum ResourceRequirement
    {
        PLANKS, LOGS, STRIPPED_LOGS, SLAB
    }
}