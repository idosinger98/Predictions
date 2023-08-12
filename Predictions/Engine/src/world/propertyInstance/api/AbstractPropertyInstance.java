package world.propertyInstance.api;

import org.w3c.dom.ranges.Range;
import world.range.RangeImpl;
import world.value.generator.api.ValueGenerator;
import world.enums.Type;

public abstract class AbstractPropertyInstance<T> implements Property {

    private final String name;
    private final Type propertyType;

    private  final RangeImpl range;
    private  ValueGenerator<T> valueGenerator;

    private Object value;

    public AbstractPropertyInstance(String name, Type propertyType, ValueGenerator<T> value, RangeImpl range) {
        this.name = name;
        this.propertyType = propertyType;
        this.valueGenerator = value;
        this.value = this.valueGenerator.generateValue();
        this.range = range;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Type getType() {
        return propertyType;
    }

    @Override
    public T generateValue() {
        return valueGenerator.generateValue();
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        if(range != null){
            if(value instanceof Integer){
                if ((Integer) value < range.getFrom() || (Integer) value > range.getTo()) {
                    return;
                }
            }
            else if(value instanceof Float) {
                if ((Float) value < range.getFrom() || (Float) value > range.getTo()) {
                    return;
                }
            }
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return "AbstractPropertyInstance{" +
                "name = '" + name + '\'' +
                ", value = " + value +
                '}';
    }

}
