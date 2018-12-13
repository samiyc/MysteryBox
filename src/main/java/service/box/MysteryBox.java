package service.box;

import java.util.ArrayList;
import java.util.List;

import static utils.MathUtils.rndBool;
import static utils.MathUtils.rndNb;

public class MysteryBox {

    private Integer nbInputs;
    private Integer nbOutputs;
    private Integer nbOperators;

    private List<Boolean> inputs;
    private List<Boolean> outputs;
    private List<MbOperator> operators = new ArrayList<MbOperator>();

    /**
     * Building input, output and operator lists
     */
    public void init() {
        nbInputs = rndNb(10, 10);
        nbOutputs = rndNb(4, 4);
        nbOperators = rndNb(12, 12);

        inputs = new ArrayList<Boolean>(nbInputs);
        outputs = new ArrayList<Boolean>(nbOutputs);

        for (int i=0; i<nbOperators; i++) {
            MbOperator operator = new MbOperator(nbInputs + operators.size());
            operators.add(operator);
        }
        run();
    }

    /**
     * Set random inputs, then run the operators to the outs
     */
    public void run() {
        inputs.clear();
        for (int i=0; i<nbInputs; i++) {
            inputs.add(i, rndBool());
        }
        for (MbOperator operator : operators) {
            operator.actOn(inputs, operators);
        }
        outputs.clear();
        for (int i=0; i<nbOutputs; i++) {
            outputs.add(i, operators.get(nbOperators - 1 - i).getOutValue());
        }
    }

    @Override
    public String toString() {
        return "IN:" + outBool(inputs) + " OUT:" + outBool(outputs);
    }

    private String outBool(List<Boolean> booleans) {
        StringBuilder retour = new StringBuilder();
        for(Boolean bool : booleans) {
            String strBool = bool ? "1" : ".";
            retour.append(strBool);
        }
        return retour.toString();
    }

    public void printOp() {
        System.out.println("OP:" + operators.toString());
    }
}
