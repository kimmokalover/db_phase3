package team8.phase3.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import team8.phase3.domain.NormalUser;

public class JdbcJoinRepo {
    private final Connection conn;
    private static final String SQL1 = "select * from normaluser u where u.id = ?";
    private static final String SQL2 = "insert into normaluser values(?, ?, ?, ?, ?, ?, ?, ?);";



    public JdbcJoinRepo(Connection conn) {
        this.conn = conn;
    }
    public Status checkDuplicate(String normalUserId){
        try(PreparedStatement preparedStatement = conn.prepareStatement(SQL1)){
            preparedStatement.setString(1, normalUserId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.FAIL : Status.SUCCESS;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }
    public Status join(NormalUser normalUser){
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL2)) {
            preparedStatement.setString(1, normalUser.getId());
            preparedStatement.setString(2, normalUser.getPassWord());
            preparedStatement.setString(3, normalUser.getEmailAddress());
            preparedStatement.setString(4, normalUser.getPhoneNumber());
            preparedStatement.setLong(5, 1L);
            preparedStatement.setString(6, normalUser.getName());
            preparedStatement.setLong(7, normalUser.getAge());
            preparedStatement.setString(8, normalUser.getAddress());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Status.SUCCESS : Status.FAIL;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Status.FAIL;
        }
    }



}
