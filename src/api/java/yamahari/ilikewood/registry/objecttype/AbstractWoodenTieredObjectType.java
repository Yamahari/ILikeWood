package yamahari.ilikewood.registry.objecttype;

import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class AbstractWoodenTieredObjectType<T extends IForgeRegistryEntry<? super T>> {
    private final String name;

    public AbstractWoodenTieredObjectType(final String name) {
        this.name = name;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        final AbstractWoodenTieredObjectType<?> that = (AbstractWoodenTieredObjectType<?>) o;

        return this.name.equals(that.name);
    }

    @Override
    public final int hashCode() {
        return name.hashCode();
    }

    public final String getName() {
        return this.name;
    }
}
