package team8.phase3.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcLoginRepo {

    private static final String SQL = "SELECT * FROM normaluser u WHERE u.id = ? AND u.passWord = ?";
    private final Connection conn;

    public JdbcLoginRepo(Connection conn) {
        this.conn = conn;
    }

    public Status normalUserLogin(String normalUserId, String passWord) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            preparedStatement.setString(1, normalUserId);
            preparedStatement.setString(2, passWord);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }
}
