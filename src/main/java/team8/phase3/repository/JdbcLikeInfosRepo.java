package team8.phase3.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import team8.phase3.domain.LikeInfo;
import team8.phase3.domain.Product;
import team8.phase3.domain.ShoppingCart;

public class JdbcLikeInfosRepo {

    private static final String SQL = "select p.productname, p.productprice from product p where p.productid in (select l.productid from normaluser n, like_info l where n.id = l.userid and p.productid = l.productid)";
    private final Connection conn;

    public JdbcLikeInfosRepo(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<Product> getLikeInfos(){
        ArrayList<Product> likeInfos = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String productName = resultSet.getString(1);
                Long productPrice = resultSet.getLong(2);
                likeInfos.add(new Product(null, null, null, productName, productPrice, null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return likeInfos;
    }

}
