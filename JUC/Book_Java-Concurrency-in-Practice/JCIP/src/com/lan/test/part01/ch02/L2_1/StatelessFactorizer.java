package com.lan.test.part01.ch02.L2_1;

import java.math.BigInteger;
import javax.servlet.*;

import com.lan.test.annotations.*;

/**
 * StatelessFactorizer
 *
 * A stateless servlet
 * 一个无状态的servlet
 * 
 * 无状态的对象一定是线程安全的
 * 
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class StatelessFactorizer extends GenericServlet implements Servlet {

    private static final long serialVersionUID = -6386836124688250021L;

	public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(resp, factors);
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
