package world.rule.action;

import exceptions.EntityNotDefine;
import exceptions.FormatException;
import exceptions.ObjectNotExist;
import exceptions.OperationNotCompatibleTypes;
import jaxb.schema.generated.PRDAction;
import world.entity.instance.EntityInstance;
import world.enums.ActionType;
import world.enums.Type;
import world.propertyInstance.api.Property;
import world.rule.action.expression.ExpressionIml;
import world.worldInstance.WorldInstance;

import java.io.Serializable;
import java.util.List;

public class Increase extends Action implements Serializable {

    private final String propertyName;
    private final ExpressionIml expression;

        public Increase(PRDAction prdAction) {
        super(prdAction.getEntity(), ActionType.INCREASE, prdAction.getPRDSecondaryEntity());
        this.propertyName = prdAction.getProperty();
        this.expression = new ExpressionIml(prdAction.getBy(), propertyName);
    }


    @Override
    public List<Action> operation(EntityInstance primaryEntity, WorldInstance worldInstance, EntityInstance secondaryEntity , String secondEntityName, List<EntityInstance> proximity) throws ObjectNotExist, NumberFormatException, ClassCastException, OperationNotCompatibleTypes, FormatException, EntityNotDefine {
        EntityInstance entityInstance = checkAndGetAppropriateInstance(primaryEntity, secondaryEntity, secondEntityName);
        if (entityInstance != null) {
            String by;
            if (entityInstance == secondaryEntity) {
                by = expression.decipher(entityInstance, worldInstance, primaryEntity, secondEntityName);
            } else {
                by = expression.decipher(entityInstance, worldInstance, secondaryEntity, secondEntityName);
            }
            if (by != null) {
                Property property = entityInstance.getAllProperty().get(propertyName);
                Type type = property.getType();
                try {
                    if (type.equals(Type.DECIMAL)) {
                        Integer number = Integer.parseInt(by);
                        property.setValue(number + (Integer) property.getValue());
                    } else if (type.equals(Type.FLOAT)) {
                        Float number = Float.parseFloat(by);
                        property.setValue(number + (Float) property.getValue());
                    }
                    return null;
                } catch (NumberFormatException | ClassCastException e) {
                    throw new NumberFormatException("The value " + by + " that you provide in the action " + getActionType() + " is not a " + type);
                }
            }
            else {
                return null;
            }
        }
        else{
            return null;
        }
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getExpression() {
        return expression.getExpressionName();
    }
}
