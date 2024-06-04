package syntax_analysis.ll1.Adapter;

import syntax_analysis.facade.AbstractError;

public class LL1Error extends AbstractError {

    private String need;

    public LL1Error(String need) {
        this.need = need;
    }
    public void printError(){
        System.out.println(need);
    }

}
