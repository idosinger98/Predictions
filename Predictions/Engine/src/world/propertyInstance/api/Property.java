package world.propertyInstance.api;

import world.enums.Type;
import world.value.generator.api.ValueGenerator;

public interface Property {
    String getName();
    Type getType();
    Object getValue();
    void setValue(Object val);
    Object generateValue();
}