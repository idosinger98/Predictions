package world.rule.action.calculation.unaryCalculationAction;

import world.rule.action.calculation.CalculationImpl;
import world.rule.action.expression.ExpressionIml;

public abstract class UnaryAction extends CalculationImpl {
    private ExpressionIml argument1;

    public UnaryAction(String entityName, String resultPropertyName, String argument1) {
        super(entityName, resultPropertyName);
        this.argument1 = new ExpressionIml(argument1, resultPropertyName);
    }
}
