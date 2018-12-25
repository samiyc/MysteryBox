package service.solver.statElement;

import service.box.MbOperator;
import service.solver.LogicSolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperatorStatElement {

    private MbOperator operator;
    private List<OperatorStatElementInfos> infos;
    private OperatorStatElement childA, childB;
    private Integer nbOutputs;
    private LogicSolver solver;

    /**
     * Constructeur
     *  @param operator MbOperator
     * @param nbOutputs
     */
    public OperatorStatElement(MbOperator operator, LogicSolver solver, Integer nbOutputs) {
        infos = new ArrayList<OperatorStatElementInfos>();
        this.operator = operator;
        this.nbOutputs = nbOutputs;
        this.solver = solver;
    }

    /**
     * Calcul via l'operator
     */
    public void calc(List<Boolean> booleans, List<MbOperator> mbOperators) {
        operator.actOn(booleans, mbOperators);
    }

    /**
     * Getter for operator
     *
     * @return MbOperator
     */
    public MbOperator getOperator() {
        return operator;
    }

    /**
     * Back propagation
     *
     * @param inHash Integer
     * @param outId Integer
     */
    public void backPropagation(OperatorStatElement caller, Integer inHash, Integer outId, Boolean outValue) {
        OperatorStatElementInfos Infos = new OperatorStatElementInfos(caller, inHash, outId, outValue);
        infos.add(Infos);

        if (childA != null) {
            childA.backPropagation(this, inHash, outId, outValue);
        }
        if (childB != null) {
            childB.backPropagation(this, inHash, outId, outValue);
        }
    }

    /**
     * getter de childA
     * @return OperatorStatElement
     */
    public OperatorStatElement getChildA() {
        return childA;
    }

    /**
     * Setter de childA
     * @param childA OperatorStatElement
     */
    public void setChildA(OperatorStatElement childA) {
        this.childA = childA;
    }

    /**
     * Getter de childB
     * @return OperatorStatElement
     */
    public OperatorStatElement getChildB() {
        return childB;
    }

    /**
     * Setter de childB
     * @param childB OperatorStatElement
     */
    public void setChildB(OperatorStatElement childB) {
        this.childB = childB;
    }

    /**
     * Return infos on the use of the operator
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[" + operator.toString() + " " + infoOnInfos() + "]";
    }

    /**
     * Split the infos by output and self vs called
     *
     * @return String
     */
    private String infoOnInfos() {
        Map<Integer, infosOnInfos> mapInfos = generateMapInfos();

        //transforme into string
        String retour = "";
        for (Integer i = 0; i < nbOutputs; i++) {
            infosOnInfos infosOnInfos = mapInfos.get(i);
            Integer nbNoCall = infosOnInfos.getNbNoCall();
            String style = (nbNoCall < solver.getNbIteration() / 2) ? "X" : nbNoCall.toString();
            retour += "{" + style + "}";
        }
        return retour;
    }

    /**
     * Count the amount of time where this operator lead to a good response
     * this is use to calculate the accuracy for each output
     *
     * @return Map<Integer, infosOnInfos>
     */
    public Map<Integer, infosOnInfos> generateMapInfos() {
        //Initialisation de la map
        Map<Integer, infosOnInfos> mapInfos = new HashMap<Integer, infosOnInfos>();
        for (Integer i = 0; i < nbOutputs; i++) {
            mapInfos.put(i, new infosOnInfos());
        }

        //Rempli les valeurs de la map
        for (OperatorStatElementInfos elem : infos) {
            Integer outputId = elem.getOutputId();
            infosOnInfos infosOnInfos = mapInfos.get(outputId);

            if (elem.getCaller() == null) {
                infosOnInfos.incNbNoCall();
            } else {
                infosOnInfos.getCallers().add(elem.getCaller());
                infosOnInfos.incNbWithCall();
            }
        }
        return mapInfos;
    }
}
