package yamahari.ilikewood.util.objecttype.tiered;

import java.util.Objects;

public final class WoodenTieredObjectType {
    private final String name;

    public WoodenTieredObjectType(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final WoodenTieredObjectType that = (WoodenTieredObjectType) o;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    public String getName() {
        return this.name;
    }
}
