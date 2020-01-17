package com.example.jta;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

public class BitronixJtaPlatform extends AbstractJtaPlatform {

    private static TransactionManager transactionManager;
    private static UserTransaction userTransaction;

    @Override
    protected TransactionManager locateTransactionManager() {
        if (transactionManager == null) {
            throw new RuntimeException("TransactionManager has not been setted");
        }
        return transactionManager;
    }

    @Override
    protected UserTransaction locateUserTransaction() {
        if (userTransaction == null) {
            throw new RuntimeException("UserTransaction has not been setted");
        }
        return userTransaction;
    }

    public static void setBitronixTransactionManager(TransactionManager transactionManager) {
        BitronixJtaPlatform.transactionManager = transactionManager;
    }

    public static void setBitronixUserTransaction(UserTransaction userTransaction) {
        BitronixJtaPlatform.userTransaction = userTransaction;
    }
}
