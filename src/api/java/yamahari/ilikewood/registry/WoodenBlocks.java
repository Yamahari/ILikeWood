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

    public static Stream<Block> getBlocks(final WoodenObjectType objectType) {
        return REGISTRY_OBJECTS.get(objectType).values().stream().map(RegistryObject::get);
    }

    public static Block getBlock(final WoodenObjectType objectType, final IWoodType woodType) {
        return REGISTRY_OBJECTS.get(objectType).get(woodType).get();
    }

    public static Stream<Block> getBlocks(final WoodenObjectType... objectTypes) {
        return Arrays.stream(objectTypes).flatMap(WoodenBlocks::getBlocks);
    }

    public static RegistryObject<Block> getRegistryObject(final WoodenObjectType objectType, final IWoodType woodType) {
        return REGISTRY_OBJECTS.get(objectType).get(woodType);
    }

    public static Stream<RegistryObject<Block>> getRegistryObjects(final WoodenObjectType objectType) {
        return REGISTRY_OBJECTS.get(objectType).values().stream();
    }

    public static Stream<Block> getBedBlocks() {
        return getBedRegistryObjects().map(RegistryObject::get);
    }

    public static Stream<Block> getBedBlocks(final IWoodType woodType) {
        return getBedRegistryObjects(woodType).map(RegistryObject::get);
    }

    public static Block getBedBlock(final IWoodType woodType, final DyeColor color) {
        return getBedRegistryObject(woodType, color).get();
    }

    public static Stream<RegistryObject<Block>> getBedRegistryObjects() {
        return BED_REGISTRY_OBJECTS.values().stream().flatMap(m -> m.values().stream());
    }

    public static Stream<RegistryObject<Block>> getBedRegistryObjects(final IWoodType woodType) {
        return Arrays.stream(DyeColor.values()).map(color -> getBedRegistryObject(woodType, color));
    }

    public static RegistryObject<Block> getBedRegistryObject(final IWoodType woodType, final DyeColor color) {
        return BED_REGISTRY_OBJECTS.get(woodType).get(color);
    }

    private WoodenBlocks() {
    }

}
