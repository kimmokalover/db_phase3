package team8.phase3.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import team8.phase3.domain.PaymentType;
import team8.phase3.domain.PurchaseTransaction;

public class JdbcTransactionInfoRepo {

    private static final String SQL = "select * from normaluser n, purchasetransaction pt, product p where n.id = pt.userid and pt.productid = p.productid";

    private final Connection conn;

    public JdbcTransactionInfoRepo(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<PurchaseTransaction> getTransactionInfos() {
        ArrayList<PurchaseTransaction> transactionInfos = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Long transactionId = resultSet.getLong(1);
                String userId = resultSet.getString(2);
                Long productId = resultSet.getLong(3);
                Long totalPrice = resultSet.getLong(4);
                PaymentType paymentType = intToPaymentType(resultSet.getInt(5));
                Time purchaseTime = resultSet.getTime(6);
                transactionInfos.add(new PurchaseTransaction(transactionId, userId, productId, totalPrice, paymentType, purchaseTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactionInfos;
    }

    private PaymentType intToPaymentType(int num){
        if (num == 1)return PaymentType.CASH;
        else if (num == 2)return PaymentType.CARD;
        else if (num == 3)return PaymentType.KAKAO;
        else return PaymentType.NAVER;
    }

}
