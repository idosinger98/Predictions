package world.entity.definition;

import jaxb.schema.generated.PRDProperty;
import world.enums.Type;
import world.range.Range;

public class PropertyDefinition  {
    private final String name;

    private final Type type;

    private final boolean isRandomInitialize;

    private Object init = null;

    private Range range = null;

    public PropertyDefinition(PRDProperty prdProperty) {
        this.name = prdProperty.getPRDName();
        this.type = Enum.valueOf(Type.class, prdProperty.getType().toUpperCase());
        this.isRandomInitialize = prdProperty.getPRDValue().isRandomInitialize();
        if (prdProperty.getPRDValue().getInit() != null) {
            this.init = prdProperty.getPRDValue().getInit();
        }
        if(prdProperty.getPRDRange()!= null) {
            this.range = new Range((float) prdProperty.getPRDRange().getFrom(), (float) prdProperty.getPRDRange().getTo());
        };
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Property{");
        stringBuilder.append("Name='").append(name).append('\'');
        stringBuilder.append(", Type=").append(type.name());
        stringBuilder.append(", Is random initialize=").append(isRandomInitialize);

        if (range != null) {
            stringBuilder.append(", Range=").append(range);
        }

        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public boolean isRandomInitialize() {
        return isRandomInitialize;
    }

    public Object getInit() {
        return init;
    }

    public Range getRange() {
        return range;
    }
}
