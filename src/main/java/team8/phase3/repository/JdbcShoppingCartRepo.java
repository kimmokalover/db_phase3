package team8.phase3.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import team8.phase3.domain.ShoppingCart;

public class JdbcShoppingCartRepo {

    private static final String SQL = "SELECT s.orderid, s.userid, s.productid, s.amount FROM shoppingcart s, normaluser n WHERE n.id = s.userid";

    private final Connection conn;

    public JdbcShoppingCartRepo(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<ShoppingCart> getShoppingCartInfos() {
        ArrayList<ShoppingCart> shoppingCarts = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String orderId = resultSet.getString(1);
                String userId = resultSet.getString(2);
                Long productId = resultSet.getLong(3);
                Long amount = resultSet.getLong(4);
                shoppingCarts.add(new ShoppingCart(orderId, userId, productId, amount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shoppingCarts;
    }
}
