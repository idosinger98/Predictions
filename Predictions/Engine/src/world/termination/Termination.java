package world.termination;

import jaxb.schema.generated.PRDBySecond;
import jaxb.schema.generated.PRDByTicks;

public class Termination {

    private Integer ticks = null;
    private Integer second = null;

    public Termination(PRDByTicks prdByTicks, PRDBySecond prdBySecond) {
        if (prdByTicks != null) {
            this.ticks = prdByTicks.getCount();
        }
        if (prdBySecond != null) {
            this.second = prdBySecond.getCount();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("    Termination: ");

        if (ticks != null) {
            stringBuilder.append("ticks = ").append(ticks);
        }

        if (ticks != null && second != null) {
            stringBuilder.append(", ");
        }

        if (second != null) {
            stringBuilder.append("seconds = ").append(second);
        }

        return stringBuilder.toString();
    }
}