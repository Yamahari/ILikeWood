package yamahari.ilikewood.util;

public enum WoodenTieredObjectType {
    AXE("axe"),
    HOE("hoe"),
    PICKAXE("pickaxe"),
    SHOVEL("shovel"),
    SWORD("sword");

    private final String name;

    WoodenTieredObjectType(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
