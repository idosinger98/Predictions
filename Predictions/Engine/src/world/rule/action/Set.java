package world.rule.action;

import exceptions.ObjectNotExist;
import exceptions.OperationNotCompatibleTypes;
import world.entity.instance.EntityInstance;
import world.enums.ActionType;
import world.enums.Type;
import world.propertyInstance.api.Property;
import world.rule.action.expression.ExpressionIml;
import world.worldInstance.WorldInstance;

import java.io.Serializable;

public class Set extends Action implements Serializable {
    private final String propertyName;
    private final ExpressionIml expression;

    public Set(String entityName, String propertyName, String expression) {
        super(entityName, ActionType.SET);
        this.propertyName = propertyName;
        this.expression = new ExpressionIml(expression, propertyName);
    }

    @Override
    public boolean operation(EntityInstance entity, WorldInstance worldInstance) throws ObjectNotExist, NumberFormatException, OperationNotCompatibleTypes {
          String value = expression.decipher(entity, worldInstance);
          Property property = entity.getAllProperty().get(propertyName);
          Type type = property.getType();
        try {
            if(type.equals(Type.DECIMAL)) {
                Integer number = Integer.parseInt(value);
                property.setValue(number);
            }
            else if (type.equals(Type.FLOAT)) {
                Float number = Float.parseFloat(value);
                property.setValue(number);
            }
            else if (type.equals(Type.BOOLEAN)) {
                if(value.equals("true") || value.equals("false")){
                    property.setValue(value);
                }
                else {
                    throw new NumberFormatException();
                }
            }
            else if (type.equals(Type.STRING)) {
                property.setValue(value);
            }
        }
        catch (NumberFormatException e){
            throw new NumberFormatException("The value: " + value + " that you provide in the action " + getActionType() + " is not a " + type);
        }
        return false;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getExpression() {
        return expression.getExpressionName();
    }
}
