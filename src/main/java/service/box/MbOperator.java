package service.box;

import java.util.List;

import static utils.MathUtils.rndNb;

public class MbOperator {

    private static Integer TYPE_AND = 0;
    private static Integer TYPE_OR = 1;
    private static Integer TYPE_NOT = 2;

    private Integer type;
    private Integer aTarget;
    private Integer bTarget;
    private Boolean outValue;

    public MbOperator(Integer inputAmount) {
        type = rndNb(0, 2);
        aTarget = rndNb(0, inputAmount - 1);
        bTarget = rndNb(0, inputAmount - 1);
    }

    public void actOn(List<Boolean> inputs, List<MbOperator> operators) {
        Boolean a, b;
        if (aTarget >= inputs.size()) {
            a = operators.get(aTarget - inputs.size()).getOutValue();
        } else {
            a = inputs.get(aTarget);
        }
        if (bTarget >= inputs.size()) {
            b = operators.get(bTarget - inputs.size()).getOutValue();
        } else {
            b = inputs.get(bTarget);
        }
        actOn(a, b);
    }

    public void actOn(Boolean a, Boolean b) {
        if (type.equals(TYPE_AND)) outValue = a && b;
        if (type.equals(TYPE_OR))  outValue = a || b;
        if (type.equals(TYPE_NOT)) outValue = a != b;
    }

    public Boolean getOutValue() {
        return outValue;
    }

    @Override
    public String toString() {
        String op = "%";
        if (type.equals(TYPE_AND)) op = "AND";
        if (type.equals(TYPE_OR))  op = "OR";
        if (type.equals(TYPE_NOT)) op = "NOT";
        return "["+aTarget+" "+op+" "+bTarget+"]";
    }
}
