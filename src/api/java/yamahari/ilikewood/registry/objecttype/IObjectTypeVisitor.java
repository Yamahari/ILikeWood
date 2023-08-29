package yamahari.ilikewood.registry.objecttype;

public interface IObjectTypeVisitor {
    boolean visit(WoodenBlockType blockType);

    boolean visit(WoodenItemType itemType);

    boolean visit(WoodenTieredItemType tieredItemType);

    boolean visit(WoodenEntityType entityType);
}
