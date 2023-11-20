package team8.phase3.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import team8.phase3.domain.Product;

public class JdbcProductRepo {

    private static final String SQL1 = "insert into product values (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL2 = "select p.productId, p.productName from product p join brandUser b on p.branduserid = b.id where b.id = ?";
    private static final String SQL3 = "delete from product where productId = ?";

    private final Connection conn;

    public JdbcProductRepo(Connection conn) {
        this.conn = conn;
    }

    public Status addProductInfo(Product product){

        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL1)) {
            preparedStatement.setLong(1, product.getProductId());
            preparedStatement.setLong(2, product.getProductCategoryId());
            preparedStatement.setString(3, product.getBrandUserId());
            preparedStatement.setString(4, product.getProductName());
            preparedStatement.setLong(5, product.getProductPrice());
            preparedStatement.setString(6, product.getProductDesc());


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }

    public ArrayList<Product> getProductsById(String brandUserId){
        ArrayList<Product> products = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL2)) {
            preparedStatement.setString(1,brandUserId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long productId = resultSet.getLong(1);
                String productName = resultSet.getString(2);
                products.add(new Product(productId,null,null, productName, null, null));
            }
    } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return products;
    }

    public Status updateProductAttribute(String attributeName, String newValue, long productId) {
        String updateSql = "UPDATE product SET " + attributeName + " = ? WHERE productId = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(updateSql)) {
            preparedStatement.setString(1, newValue);
            preparedStatement.setLong(2, productId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return Status.SUCCESS;
            } else {
                return Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Status.FAIL;
    }
    public Status updateProductAttribute(String attributeName, Long newValue, long productId) {
        String updateSql = "UPDATE product SET " + attributeName + " = ? WHERE productId = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(updateSql)) {
            preparedStatement.setLong(1, newValue);
            preparedStatement.setLong(2, productId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return Status.SUCCESS;
            } else {
                return Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Status.FAIL;
    }

    public Status deleteProduct(Long productId){
        try(PreparedStatement preparedStatement = conn.prepareStatement(SQL3)){
            preparedStatement.setLong(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return Status.FAIL;
    }
}
