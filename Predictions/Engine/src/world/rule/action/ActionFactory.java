package world.rule.action;

import jaxb.schema.generated.PRDDivide;
import jaxb.schema.generated.PRDMultiply;
import world.enums.ActionType;
import world.rule.action.calculation.binaryCalculationAction.BinaryCalcuationActionFactory;
import world.rule.action.condition.Condition;

import static world.enums.CalculationBinaryTypeAction.DIVIDE;
import static world.enums.CalculationBinaryTypeAction.MULTIPLY;


public final class ActionFactory {

    public static Action createAction(ActionType type, String entityName, String propertyName, String by, String value,
                                      PRDMultiply multiply, PRDDivide divide, String resultPropertyName)
    {
        Action selectedAction = null;
        switch (type) {
            case INCREASE:
                selectedAction = new Increase(entityName, propertyName, by);
                break;
            case DECREASE:
                selectedAction = new Decrease(entityName, propertyName, by);
                break;
            case CALCULATION:
                if(multiply != null) {
                    selectedAction = BinaryCalcuationActionFactory.createBinaryAction(MULTIPLY,entityName, resultPropertyName, multiply.getArg1(), multiply.getArg2());
                }
                else if (divide != null) {
                    selectedAction = BinaryCalcuationActionFactory.createBinaryAction(DIVIDE ,entityName, resultPropertyName, divide.getArg1(), divide.getArg2());
                }
                break;
            case CONDITION:
                selectedAction = new Condition(entityName);
                break;
            case SET:
                selectedAction = new Set(entityName, propertyName, value);
                break;
            case KILL:
                selectedAction = new Kill(entityName);
                break;
        }

        return selectedAction;
    }
}
