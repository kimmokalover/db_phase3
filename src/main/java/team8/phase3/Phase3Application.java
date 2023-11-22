package team8.phase3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import team8.phase3.controller.ProgramController;
import team8.phase3.domain.NormalUser;
import team8.phase3.domain.Rank;
import team8.phase3.repository.JdbcJoinRepo;
import team8.phase3.repository.JdbcLikeInfosRepo;
import team8.phase3.repository.JdbcLoginRepo;
import team8.phase3.repository.JdbcProductRepo;
import team8.phase3.repository.JdbcReviewRepo;
import team8.phase3.repository.JdbcShoppingCartRepo;
import team8.phase3.repository.JdbcTransactionInfoRepo;
import team8.phase3.repository.JdbcUpdateUserInfoRepo;
import team8.phase3.repository.JdbcUserRepo;
import team8.phase3.repository.Status;
import team8.phase3.service.JoinService;
import team8.phase3.service.LikeInfoService;
import team8.phase3.service.LoginService;
import team8.phase3.service.ProductService;
import team8.phase3.service.ReviewService;
import team8.phase3.service.ShoppingCartService;
import team8.phase3.service.TransactionService;
import team8.phase3.service.UpdateInfoService;
import team8.phase3.service.UserService;
import team8.phase3.view.JoinView;

@SpringBootApplication
public class Phase3Application {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USER_UNIVERSITY ="SYSTEM";
	public static final String USER_PASSWD ="oracle";

	public static void main(String[] args) {
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(URL, USER_UNIVERSITY, USER_PASSWD);
			System.out.println("Connected.");
		}catch(SQLException e){
			e.printStackTrace();
			System.err.println("Cannot get a connection: " + e.getLocalizedMessage());
			System.err.println("Cannot get a connection: " + e.getMessage());
		}
		/*
		JoinService joinService = new JoinService(new JdbcJoinRepo(conn));
		NormalUser normalUser= new NormalUser("aassdd123", "aassdd123", "aassdd123",  "01059599916", Rank.NORMAL_BRONZE, "kim SeungJun", 24L, "idw");
		joinService.join(normalUser);

		LoginService loginService = new LoginService(new JdbcLoginRepo(conn));

		if(loginService.login("aassdd123", "aassdd123") == Status.SUCCESS){
			System.out.println("성공");
		}
		else {
			System.out.println("시발");
		}

		UpdateInfoService updateInfoService = new UpdateInfoService(new JdbcUpdateUserInfoRepo(conn));

		if(updateInfoService.updateInfo("age", 21L, "aassdd123") == Status.SUCCESS){
			System.out.println("성공");
		}
		else{
			System.out.println("좇까");
		}*/

		ProgramController programController = new ProgramController(new JoinService(new JdbcJoinRepo(conn)),
				new LikeInfoService(new JdbcLikeInfosRepo(conn)), new LoginService(new JdbcLoginRepo(conn)),
				new ProductService(new JdbcProductRepo(conn)), new ReviewService(new JdbcReviewRepo(conn)),
				new ShoppingCartService(new JdbcShoppingCartRepo(conn)), new TransactionService(new JdbcTransactionInfoRepo(conn))
				,new UpdateInfoService(new JdbcUpdateUserInfoRepo(conn)), new UserService(new JdbcUserRepo(conn)), new Scanner(System.in));

		programController.startProgram();
	}

}
