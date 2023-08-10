package world.propertyInstance.api;

import world.value.generator.api.ValueGenerator;
import world.enums.Type;

public abstract class AbstractPropertyInstance<T> implements Property {

    private final String name;
    private final Type propertyType;
    private  ValueGenerator<T> valueGenerator;

    public AbstractPropertyInstance(String name, Type propertyType, ValueGenerator<T> valueGenerator) {
        this.name = name;
        this.propertyType = propertyType;
        this.valueGenerator = valueGenerator;
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
    public void setValueGenerator(Object valueGenerator) {
        this.valueGenerator = (ValueGenerator<T>)valueGenerator;
    }

    @Override
    public String toString() {
        return "AbstractPropertyInstance{" +
                "name='" + name + '\'' +
                ", valueGenerator=" + valueGenerator +
                '}';
    }

}
