package com.lan.test.part01.ch02.L2_6;

import java.math.BigInteger;
import javax.servlet.*;

import com.lan.test.annotations.*;

/**
 * SynchronizedFactorizer
 *
 * Servlet that caches last result, but with unnacceptably poor concurrency
 * 
 * 这个Servlet能正确地缓存最新的计算结果，但并发性却非常糟糕
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class SynchronizedFactorizer extends GenericServlet implements Servlet {

    private static final long serialVersionUID = -8102294178205832084L;

	@GuardedBy("this") private BigInteger lastNumber;

    @GuardedBy("this") private BigInteger[] lastFactors;

    // 多个访问时，由于这种锁的存在，响应很慢
    public synchronized void service(ServletRequest req,
                                     ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        if (i.equals(lastNumber))
            encodeIntoResponse(resp, lastFactors);
        else {
            BigInteger[] factors = factor(i);
            lastNumber = i;
            lastFactors = factors;
            encodeIntoResponse(resp, factors);
        }
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[] { i };
    }
}

