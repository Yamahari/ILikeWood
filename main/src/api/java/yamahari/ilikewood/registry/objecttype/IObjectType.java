package yamahari.ilikewood.registry.objecttype;

public interface IObjectType {
    String getName();

    String getNamePlural();

    boolean equals(final Object o);

    int hashCode();

    boolean acceptVisitor(IObjectTypeVisitor visitor);

    boolean isEnabled();
}