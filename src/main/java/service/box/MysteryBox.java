package service.box;

import java.util.ArrayList;
import java.util.List;

import static utils.MathUtils.*;

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
    public void init(Integer nbInputs, Integer nbOutputs, Integer nbOperators) {
        this.nbInputs = nbInputs;
        this.nbOutputs = nbOutputs;
        this.nbOperators = nbOperators;

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

    /**
     * Lunch 20 runs and print the values
     */
    public void debug() {
        for (int i=0; i< 20; i++) {
            run();
            System.out.println(toString());
        }
    }

    /**
     * Return input and output as string
     *
     * @return String
     */
    @Override
    public String toString() {
        return "IN:" + listBoolToString(inputs) + " OUT:" + listBoolToString(outputs);
    }

    /**
     * Output the operators
     */
    public void printOp() {
        System.out.println("OP:" + operators.toString());
    }

    //****** GETTER SETTER ******

    /**
     * Getter de nbInputs
     *
     * @return Integer
     */
    public Integer getNbInputs() {
        return nbInputs;
    }

    /**
     * Getter de nbOutputs
     *
     * @return Integer
     */
    public Integer getNbOutputs() {
        return nbOutputs;
    }

    /**
     * Getter for inputs
     *
     * @return List<Boolean>
     */
    public List<Boolean> getInputs() {
        return inputs;
    }

    /**
     * Getter for outputs
     *
     * @return List<Boolean>
     */
    public List<Boolean> getOutputs() {
        return outputs;
    }
}
