package service.solver.statElement;

import java.util.HashSet;
import java.util.Set;

public class infosOnInfos {

    private Integer nbNoCall;
    private Integer nbWithCall;
    private Set<OperatorStatElement> callers;

    /**
     * Constructeur
     */
    public infosOnInfos() {
        nbNoCall = 0;
        nbWithCall = 0;
        callers = new HashSet<OperatorStatElement>();
    }

    /**
     * Increment the nbNoCall value
     */
    public void incNbNoCall() {
        nbNoCall ++;
    }

    /**
     * Increment the nbWithCall value
     */
    public void incNbWithCall() {
        nbWithCall ++;
    }

    /**
     * Getter for nbNoCall
     *
     * @return Integer
     */
    public Integer getNbNoCall() {
        return nbNoCall;
    }

    /**
     * Setter for nbNoCall
     *
     * @param nbNoCall Integer
     */
    public void setNbNoCall(Integer nbNoCall) {
        this.nbNoCall = nbNoCall;
    }

    /**
     * Getter for nbWithCall
     *
     * @return Integer
     */
    public Integer getNbWithCall() {
        return nbWithCall;
    }

    /**
     * Setter for nbWithCall
     *
     * @param nbWithCall Integer
     */
    public void setNbWithCall(Integer nbWithCall) {
        this.nbWithCall = nbWithCall;
    }

    /**
     * Getter for callers
     *
     * @return Set<OperatorStatElement>
     */
    public Set<OperatorStatElement> getCallers() {
        return callers;
    }

    /**
     * Setter for callers
     *
     * @param callers Set<OperatorStatElement>
     */
    public void setCallers(Set<OperatorStatElement> callers) {
        this.callers = callers;
    }
}
