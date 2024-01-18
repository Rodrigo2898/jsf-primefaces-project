package com.jsfproject.jsfproject.util;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import javax.inject.Inject;
import java.io.Serializable;

@Interceptor
@Transactional
@Priority(Interceptor.Priority.APPLICATION)
public class TransactionalInterceptor implements Serializable {
    private static long serialVersionUID = 1L;

    private EntityManager manager;

    public TransactionalInterceptor() {
    }

    public TransactionalInterceptor(EntityManager manager) {
        this.manager = manager;
    }

    @AroundInvoke
    public Object invoke(InvocationContext invocationContext) throws Exception {
        EntityTransaction entityTransaction = manager.getTransaction();
        boolean criador = false;

        try {
            if (!entityTransaction.isActive()) {
                // fazendo um rollbak do que já passou
                entityTransaction.begin();
                entityTransaction.rollback();

                // agora iniciando a transação
                entityTransaction.begin();

                criador = true;
            }
            return invocationContext.proceed();
        } catch (Exception e) {
            if (entityTransaction != null && criador) {
                entityTransaction.rollback();
            }
            throw e;
        } finally {
            if (entityTransaction != null && entityTransaction.isActive() && criador) {
                entityTransaction.commit();
            }
        }
    }
}
