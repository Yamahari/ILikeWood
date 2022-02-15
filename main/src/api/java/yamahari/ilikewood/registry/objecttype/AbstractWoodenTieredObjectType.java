package yamahari.ilikewood.registry.objecttype;

import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class AbstractWoodenTieredObjectType<T extends IForgeRegistryEntry<? super T>> implements IObjectType {
    private final String name;
    private final String namePlural;

    public AbstractWoodenTieredObjectType(final String name, final String namePlural) {
        this.name = name;
        this.namePlural = namePlural;
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

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public String getNamePlural() {
        return this.namePlural;
    }
}
