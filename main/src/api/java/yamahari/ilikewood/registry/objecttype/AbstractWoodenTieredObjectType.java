package yamahari.ilikewood.registry.objecttype;

import yamahari.ilikewood.config.ILikeWoodConfig;

public abstract class AbstractWoodenTieredObjectType<T> implements IObjectType
{
    private final String name;
    private final String namePlural;
    private final ILikeWoodConfig config;

    public AbstractWoodenTieredObjectType(final String name, final String namePlural, final ILikeWoodConfig config) {
        this.name = name;
        this.namePlural = namePlural;
        this.config = config;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o)
        {
            return true;
        }
        if (o == null || this.getClass() != o.getClass())
        {
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

    @Override
    public boolean isEnabled() {
        return this.config.isEnabled();
    }
}
