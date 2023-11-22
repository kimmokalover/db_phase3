package team8.phase3.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import team8.phase3.domain.PurchaseTransaction;
import team8.phase3.repository.JdbcTransactionInfoRepo;
import team8.phase3.repository.Status;

public class TransactionService {
    private final JdbcTransactionInfoRepo jdbcTransactionInfoRepo;

    public TransactionService(JdbcTransactionInfoRepo jdbcTransactionInfoRepo) {
        this.jdbcTransactionInfoRepo = jdbcTransactionInfoRepo;
    }

    public Status recordTransaction(PurchaseTransaction purchaseTransaction){
        return jdbcTransactionInfoRepo.createTransaction(purchaseTransaction);
    }

    public ArrayList<PurchaseTransaction> getTransactionInfos(String userId){

        ArrayList<PurchaseTransaction> ret = jdbcTransactionInfoRepo.getTransactionInfos();
        ArrayList<PurchaseTransaction> newRet = new ArrayList<>();
        for(PurchaseTransaction item : ret){
            if(item.getUserId().equals(userId))newRet.add(item);
        }
        return newRet;
    }
}
