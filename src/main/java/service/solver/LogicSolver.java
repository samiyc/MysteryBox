package service.solver;

import service.box.MbOperator;
import service.box.MysteryBox;
import service.solver.statElement.OperatorStatElement;
import service.solver.statElement.infosOnInfos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LogicSolver {

    private MysteryBox box;
    private List<OperatorStatElement> listOperatorStatElement;
    private List<MbOperator> operators;
    private Integer nbIteration;

    /**
     * Constructeur.
     *
     * @param box MysteryBox
     */
    public LogicSolver(MysteryBox box, Integer nbOperatorMax) {
        this.box = box;
        initListOperator(nbOperatorMax);
    }

    private void initListOperator(Integer nbOperatorMax) {
        operators = new ArrayList<MbOperator>();
        listOperatorStatElement = new ArrayList<OperatorStatElement>();
        for (int i = 0; i < nbOperatorMax; i++) {
            MbOperator mbOperator = new MbOperator(box.getNbInputs() + listOperatorStatElement.size());
            operators.add(mbOperator);

            Integer aTarget = mbOperator.getATarget();
            Integer bTarget = mbOperator.getBTarget();
            OperatorStatElement element = new OperatorStatElement(mbOperator, this, box.getNbOutputs());

            //Split les inputs et les operator
            if (aTarget >= box.getNbInputs()) {
                element.setChildA(listOperatorStatElement.get(aTarget - box.getNbInputs()));
            }
            if (bTarget >= box.getNbInputs()) {
                element.setChildB(listOperatorStatElement.get(bTarget - box.getNbInputs()));
            }

            listOperatorStatElement.add(element);
        }
    }

    /**
     * Try to solve the MysteryBox
     */
    public void solve(Integer nbIteration) {
        this.nbIteration = nbIteration;
        for (int i = 0; i < nbIteration; i++) {
            //run mb
            box.run();
            //try guess
            guess();
            //check with expexted result
            checkResult();
        }
        System.out.println(this.toString());
    }

    /**
     * base on the last analyse générate a résult acordingly
     * first close to random
     * then get closer to the expected logic intération by itération
     */
    private void guess() {
        for (OperatorStatElement elem : listOperatorStatElement) {
            elem.calc(box.getInputs(), operators);
        }
    }

    /**
     * Vérification des résultats
     */
    private void checkResult() {
        for (OperatorStatElement elem : listOperatorStatElement) {
            Boolean outValue = elem.getOperator().getOutValue();

            //est ce que le résultat de l'opération correspond à une des valeurs attendu
            for (int i=0; i<box.getNbOutputs(); i++) {
                Boolean out = box.getOutputs().get(i);
                if (out.equals(outValue)) {

                    //adjust infos of the element
                    elem.backPropagation(null, box.getInputs().hashCode(), i, out);
                }
            }
        }
    }

    /**
     * Only keep the good
     */
    public void cleanUp() {
        List<Integer> listMax = new ArrayList<Integer>();
        List<OperatorStatElement> listIdOperatorMaxAccuracy = new ArrayList<OperatorStatElement>();
        for (int i=0; i < box.getNbOutputs(); i++) {
            listMax.add(0);
            listIdOperatorMaxAccuracy.add(null);
        }
        for (OperatorStatElement elem : listOperatorStatElement) {
            Map<Integer, infosOnInfos> infosMap = elem.generateMapInfos();
            for (int j=0; j < box.getNbOutputs(); j++) {
                infosOnInfos infos = infosMap.get(j);
                Integer nbNoCall = infos.getNbNoCall();
                if (nbNoCall > listMax.get(j)) {
                    listMax.set(j, nbNoCall);
                    listIdOperatorMaxAccuracy.set(j, elem);
                }
            }
        }
        for (int k=0; k < box.getNbOutputs(); k++) {
            OperatorStatElement element = listIdOperatorMaxAccuracy.get(k);
            System.out.println(k + " - max:" + listMax.get(k) + " use:" + element);
        }
    }

    /**
     * Return infos on the use of the operator
     *
     * @return String
     */
    @Override
    public String toString() {
        return listOperatorStatElement.toString();
    }

    public Integer getNbIteration() {
        return nbIteration;
    }

}
