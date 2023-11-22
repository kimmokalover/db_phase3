package team8.phase3.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserRepo {

    private static final String SQL1 = "delete from normaluser where id = ?";
    private static final String SQL2 = "delete from branduser where id = ?";

    private final Connection conn;


    public JdbcUserRepo(Connection conn) {
        this.conn = conn;
    }

    public Status deleteNormalUser(String userId){
        try(PreparedStatement preparedStatement = conn.prepareStatement(SQL1)){
            preparedStatement.setString(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }

    public Status deleteBrandUser(String userId) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL2)) {
            preparedStatement.setString(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }
}
