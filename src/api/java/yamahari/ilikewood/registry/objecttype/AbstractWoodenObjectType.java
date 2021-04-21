package yamahari.ilikewood.registry.objecttype;

public abstract class AbstractWoodenObjectType {
    private final String name;
    private final boolean variesByWoodType;

    public AbstractWoodenObjectType(final String name, final boolean variesByWoodType) {
        this.name = name;
        this.variesByWoodType = variesByWoodType;
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

    // TODO wtf was this supposed to be used for?
    public final boolean variesByWoodType() {
        return this.variesByWoodType;
    }

    public final String getName() {
        return this.name;
    }
}
