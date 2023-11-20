package team8.phase3.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import team8.phase3.domain.Review;
import team8.phase3.domain.ShoppingCart;

public class JdbcReviewRepo {

    private static final String SQL = "select n.id, t1.review from (select r.userid, r.review from PurchaseTransaction pt, review r where pt.productId = r.productId and pt.userid = r.userid) t1 join NormalUser n on t1.userId = n.id";

    private final Connection conn;


    public JdbcReviewRepo(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<Review> getReviewInfos(){
        ArrayList<Review> reviews = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String userId = resultSet.getString(1);
                String review = resultSet.getString(2);
                reviews.add(new Review(null, userId, null, review, null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }
}
