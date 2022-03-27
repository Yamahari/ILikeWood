package yamahari.ilikewood.registry.objecttype;

import java.util.function.Supplier;

public abstract class AbstractWoodenObjectType implements IObjectType {
    private final String name;
    private final String namePlural;
    private final boolean variesByWoodType;
    private final Supplier<Boolean> flag;

    public AbstractWoodenObjectType(
        final String name,
        final String namePlural,
        final boolean variesByWoodType,
        final Supplier<Boolean> flag
    ) {
        this.name = name;
        this.namePlural = namePlural;
        this.variesByWoodType = variesByWoodType;
        this.flag = flag;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final AbstractWoodenObjectType that = (AbstractWoodenObjectType) o;
        return this.name.equals(that.name);
    }

    @Override
    public final int hashCode() {
        return name.hashCode();
    }

    public final boolean variesByWoodType() {
        return this.variesByWoodType;
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
        return this.flag.get();
    }
}