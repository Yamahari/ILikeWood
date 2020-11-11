package yamahari.ilikewood.registry;

import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;
import net.minecraftforge.fml.RegistryObject;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.WoodenObjectType;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

public final class WoodenBlocks {
    static Map<WoodenObjectType, Map<IWoodType, RegistryObject<Block>>> REGISTRY_OBJECTS;
    static Map<IWoodType, Map<DyeColor, RegistryObject<Block>>> BED_REGISTRY_OBJECTS;

    public static RegistryObject<Block> getRegistryObject(final WoodenObjectType woodenObjectType, final IWoodType woodType) {
        return REGISTRY_OBJECTS.get(woodenObjectType).get(woodType);
    }

    public static Stream<RegistryObject<Block>> getRegistryObjects(final WoodenObjectType woodenObjectType) {
        return REGISTRY_OBJECTS.get(woodenObjectType).values().stream();
    }

    public static Stream<RegistryObject<Block>> getRegistryObjects(final WoodenObjectType... woodenObjectTypes) {
        return Arrays.stream(woodenObjectTypes).flatMap(WoodenBlocks::getRegistryObjects);
    }


    public static Block getBlock(final WoodenObjectType woodenObjectType, final IWoodType woodType) {
        return getRegistryObject(woodenObjectType, woodType).get();
    }

    public static Stream<Block> getBlocks(final WoodenObjectType woodenObjectType) {
        return getRegistryObjects(woodenObjectType).map(RegistryObject::get);
    }

    public static Stream<Block> getBlocks(final WoodenObjectType... woodenObjectTypes) {
        return getRegistryObjects(woodenObjectTypes).map(RegistryObject::get);
    }


    public static RegistryObject<Block> getBedRegistryObject(final IWoodType woodType, final DyeColor dyeColor) {
        return BED_REGISTRY_OBJECTS.get(woodType).get(dyeColor);
    }

    public static Stream<RegistryObject<Block>> getBedRegistryObjects(final IWoodType woodType) {
        return BED_REGISTRY_OBJECTS.get(woodType).values().stream();
    }

    public static Stream<RegistryObject<Block>> getBedRegistryObjects() {
        return BED_REGISTRY_OBJECTS.values().stream().flatMap(m -> m.values().stream());
    }


    public static Block getBedBlock(final IWoodType woodType, final DyeColor dyeColor) {
        return getBedRegistryObject(woodType, dyeColor).get();
    }

    public static Stream<Block> getBedBlocks(final IWoodType woodType) {
        return getBedRegistryObjects(woodType).map(RegistryObject::get);
    }

    public static Stream<Block> getBedBlocks() {
        return getBedRegistryObjects().map(RegistryObject::get);
    }

    private WoodenBlocks() {
    }
}
