package yamahari.ilikewood.util.objecttype;

import java.util.Objects;

public final class WoodenObjectType {
    private final String name;

    private final boolean planks;
    private final boolean slab;
    private final boolean log;
    private final boolean strippedLog;

    public WoodenObjectType(final String name, final boolean planks, final boolean slab, final boolean log,
                            final boolean strippedLog) {
        this.name = name;
        this.planks = planks;
        this.slab = slab;
        this.log = log;
        this.strippedLog = strippedLog;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final WoodenObjectType that = (WoodenObjectType) o;
        return this.planks == that.planks && this.slab == that.slab && this.log == that.log &&
               this.strippedLog == that.strippedLog && this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.planks, this.slab, this.log, this.strippedLog);
    }

    public boolean requiresPlanks() {
        return this.planks;
    }

    public boolean requiresSlab() {
        return this.slab;
    }

    public boolean requiresLog() {
        return this.log;
    }

    public boolean requiresStrippedLog() {
        return this.strippedLog;
    }

    public String getName() {
        return this.name;
    }
}
