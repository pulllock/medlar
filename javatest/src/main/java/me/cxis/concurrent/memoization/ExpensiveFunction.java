package me.cxis.concurrent.memoization;

import java.math.BigInteger;

/**
 * Created by cx on 7/14/16.
 */
public class ExpensiveFunction implements Computable<String,BigInteger> {
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        //经过长时间计算之后
        return new BigInteger(arg);
    }
}
