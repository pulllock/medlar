package me.cxis.gof.interpreter_pattern;

public class NonterminalExpression extends AbstractExpression {

    private AbstractExpression left;

    private AbstractExpression right;

    public NonterminalExpression(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void interprete(Context context) {
        // 递归调用每个组成部分的interpret方法
    }
}
