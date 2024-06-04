package syntax_analysis.ll1.Adapter;

import lexical_analysis.token.Token;
import syntax_analysis.facade.AbstractSyntaxerCore;

import java.util.List;

import static syntax_analysis.ll1.ll1.doLL1;

public class CoreAdapter extends AbstractSyntaxerCore {
    @Override
    public void read(List<Token> tokenList) {

    }

    @Override
    public void analyze() {
        LL1Error ll1Error = doLL1();
        if (ll1Error != null) {
            errorList.add(ll1Error);
        }
    }

    @Override
    public boolean hasError() {
        return errorList.isEmpty();
    }


}
