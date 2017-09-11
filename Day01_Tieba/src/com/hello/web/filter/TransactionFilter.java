package com.hello.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.Session;

import com.hello.utils.HibernateUtil;

public class TransactionFilter implements Filter {

    public TransactionFilter() {
    }
    
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Session currentSession = HibernateUtil.getCurrentSession();
		currentSession.beginTransaction();
		
		try {
			chain.doFilter(request, response);
			if(currentSession!=null && currentSession.isOpen()){
				currentSession.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(currentSession!=null && currentSession.isOpen()){
				currentSession.getTransaction().rollback();
			}
		}
		
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
