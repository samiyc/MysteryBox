package service.solver.statElement;

public class OperatorStatElementInfos {

    private OperatorStatElement caller;
    private Integer inputHash;
    private Integer outputId;
    private Boolean outputValue;

    /**
     * Constructeur
     *
     * @param caller OperatorStatElement
     * @param inputHash Integer
     * @param outputId Integer
     * @param outputValue Boolean
     */
    public OperatorStatElementInfos(OperatorStatElement caller, Integer inputHash, Integer outputId, Boolean outputValue){
        this.caller = caller;
        this.inputHash = inputHash;
        this.outputId = outputId;
        this.outputValue = outputValue;
    }

    /**
     * getter for caller
     *
     * @return OperatorStatElement
     */
    public OperatorStatElement getCaller() {
        return caller;
    }

    /**
     * getter for inputHash
     *
     * @return Integer
     */
    public Integer getInputHash() {
        return inputHash;
    }

    /**
     * getter for outputId
     *
     * @return Integer
     */
    public Integer getOutputId() {
        return outputId;
    }

    /**
     * Getter for outputValue
     *
     * @return
     */
    public Boolean getOutputValue() {
        return outputValue;
    }
}
