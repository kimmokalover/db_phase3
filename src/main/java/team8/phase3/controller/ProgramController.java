package team8.phase3.controller;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import team8.phase3.domain.BrandUser;
import team8.phase3.domain.LikeInfo;
import team8.phase3.domain.NormalUser;
import team8.phase3.domain.PaymentType;
import team8.phase3.domain.Product;
import team8.phase3.domain.PurchaseTransaction;
import team8.phase3.domain.Rank;
import team8.phase3.domain.Review;
import team8.phase3.domain.ShoppingCart;
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
import team8.phase3.view.BrandUserPageView;
import team8.phase3.view.JoinView;
import team8.phase3.view.LikeView;
import team8.phase3.view.LoginView;
import team8.phase3.view.MainPageView;
import team8.phase3.view.ProductView;
import team8.phase3.view.ReviewView;
import team8.phase3.view.ShoppingCartView;
import team8.phase3.view.UserPageView;

public class ProgramController {

    private final JoinService joinService;
    private final LikeInfoService likeInfoService;
    private final LoginService loginService;
    private final ProductService productService;
    private final ReviewService reviewService;
    private final ShoppingCartService shoppingCartService;
    private final TransactionService transactionService;
    private final UpdateInfoService updateInfoService;
    private final UserService userService;
    private final Scanner scanner;

    private static String id;
    private static int type;

    public ProgramController(JoinService joinService, LikeInfoService likeInfoService, LoginService loginService,
            ProductService productService, ReviewService reviewService, ShoppingCartService shoppingCartService,
            TransactionService transactionService, UpdateInfoService updateInfoService, UserService userService, Scanner scanner) {
        this.joinService = joinService;
        this.likeInfoService = likeInfoService;
        this.loginService = loginService;
        this.productService = productService;
        this.reviewService = reviewService;
        this.shoppingCartService = shoppingCartService;
        this.transactionService = transactionService;
        this.updateInfoService = updateInfoService;
        this.userService = userService;
        this.scanner = scanner;
    }

    public void startProgram(){
        JoinView.userChoice();
        int choice = scanner.nextInt();

        if (choice == 1){
            loginProgram();
            mainPageProgram();

        }
        else {
            joinProgram();
        }

    }

    private void joinProgram(){
        JoinView.joinForm();
        int choice = scanner.nextInt();
        if (choice == 1){
            JoinView.normalUserJoinForm();
            String id = scanner.next();
            String passWord = scanner.next();
            String emailAddress = scanner.next();
            String phoneNumber = scanner.next();
            Rank rank = Rank.NORMAL_BRONZE;
            String name = scanner.next();
            Long age = scanner.nextLong();
            String address = scanner.next();
            NormalUser normalUser = new NormalUser(id,passWord,emailAddress,phoneNumber,rank,name,age,address);
            Status status = joinService.join(normalUser);
            if(status == Status.SUCCESS){
                JoinView.joinSuccess();
            }
            else{
                JoinView.duplicateId();
            }
        }
        else{
            JoinView.brandUserJoinForm();
            String id = scanner.next();
            String passWord = scanner.next();
            String emailAddress = scanner.next();
            String phoneNumber = scanner.next();
            Rank rank = Rank.BRAND;
            String firmName = scanner.next();
            String firmPhoneNumber = scanner.next();
            String firmAddress = scanner.next();
            String businessRegistration = scanner.next();
            String firmWebUrl = scanner.next();
            BrandUser brandUser = new BrandUser(id,passWord,emailAddress,phoneNumber,rank,firmName,firmPhoneNumber,firmAddress,businessRegistration,firmWebUrl);
            Status status = joinService.join(brandUser);

            if(status == Status.SUCCESS){
                JoinView.joinSuccess();
            }
            else{
                JoinView.duplicateId();
            }
        }
    }

    private void loginProgram(){
        LoginView.loginForm();
        String userId;
        String passWord;
        Status status;
        int choice = scanner.nextInt();
        if (choice == 1){
            LoginView.userLoginForm();
            userId = scanner.next();
            passWord = scanner.next();
            status = loginService.normalLogin(userId, passWord);
        }
        else if (choice == 2){
            LoginView.userLoginForm();
            userId = scanner.next();
            passWord = scanner.next();
            status = loginService.brandLogin(userId, passWord);
        }
        else{
            LoginView.userLoginForm();
            userId = scanner.next();
            passWord = scanner.next();
            status = loginService.adminLogin(userId, passWord);
        }

        if (status == Status.SUCCESS){
            LoginView.successLogin();
            id = userId;
            type = choice;
        }

        else{
            LoginView.failLogin();
            loginProgram();
        }
    }

    private void mainPageProgram(){
        while(true){
            int choice;
            if (type == 1){
                MainPageView.normalUserMainPage();
                choice = scanner.nextInt();
                if (choice == 1){
                    productPage();
                }
                else if(choice == 2){
                    likePage();
                }
                else if (choice == 3){
                    reviewPage();
                }
                else if (choice == 4){
                    shoppingCartPage();
                }
                else{
                    System.out.println("로그아웃 합니다.");
                    System.exit(0);
                }
            }
            else if(type == 2){
                MainPageView.brandUserMainPage();
                choice = scanner.nextInt();
                if (choice== 1){
                    BrandUserPageView.addProductMessage();
                    Long productId = productService.getLastId() + 1;
                    Long productCategoryId = scanner.nextLong();
                    String brandUserId = id;
                    String productName = scanner.next();
                    Long productPrice = scanner.nextLong();
                    String productDesc = scanner.nextLine();

                    Product product = new Product(productId, productCategoryId, brandUserId, productName, productPrice, productDesc);
                    productService.addProduct(product);
                    BrandUserPageView.afterAddProductMessage();
                }
                else if (choice == 2){
                    BrandUserPageView.modifyProductMessage();
                    String attributeName = scanner.next();
                    String newVal = scanner.next();
                    Long productId = scanner.nextLong();

                    try{
                        productService.updateProduct(attributeName, newVal, productId);
                    }catch(Exception e){
                        productService.updateProduct(attributeName, Long.parseLong(newVal), productId);
                    }

                    BrandUserPageView.afterModifyProductMessage();

                }
                else if (choice == 3){
                    BrandUserPageView.deleteProductMessage();
                    Long productId = scanner.nextLong();
                    productService.deleteProduct(productId);
                    BrandUserPageView.afterDeleteProductMessage();
                }
                else {
                    System.out.println("로그아웃 합니다.");
                    System.exit(0);
                }
            }
            else {
                MainPageView.adminUserMainPage();
                choice = scanner.nextInt();

                if (choice == 1){
                    UserPageView.deleteNormalUser();
                    String userId = scanner.next();
                    userService.deleteNormalUser(userId);
                    UserPageView.afterDeleteNormalUser();
                }
                else if (choice == 2){
                    UserPageView.deleteBrandUser();
                    String userId = scanner.next();
                    userService.deleteBrandUser(userId);
                    UserPageView.afterDeleteBrandUser();
                }
                else {
                    System.out.println("프로그램 종료!");
                    System.exit(0);
                }
            }
        }
    }

    private void productPage(){
        while(true) {
            ProductView.productPage();
            int choice = scanner.nextInt();

            if (choice == 1) {
                ArrayList<Product> products = productService.getProductInfos();
                ProductView.productInfos(products);
            } else if (choice == 2) {
                ProductView.likeProduct();
                Long productId = scanner.nextLong();
                likeInfoService.doLike(id, productId);
                LikeView.afterDoLike();
            } else if (choice == 3) {
                ProductView.buyProduct();
                Long productId = scanner.nextLong();
                Product product = productService.getProductById(productId);
                LocalDateTime t = LocalDateTime.now();
                PurchaseTransaction purchaseTransaction = new PurchaseTransaction((long) (t.toString() + id).hashCode(),
                        id, productId, product.getProductPrice(),
                        PaymentType.CARD, Time.valueOf(t.toLocalTime()));
                transactionService.recordTransaction(purchaseTransaction);
                ProductView.afterBuyProduct();

                ReviewView.addReview();

                int a = scanner.nextInt();
                if (a == 1){
                    addReview();
                }
            } else if (choice == 4) {
                ProductView.addShoppingCart();
                Long productId = scanner.nextLong();

                shoppingCartService.addProduct(id, productId, 1L);

                ProductView.afterAddProductInShoppingCart();
            } else if (choice == 5) {
                MainPageView.rollBackToMainPage();
                break;
            } else {
                System.out.println("로그아웃 합니다.");
                System.exit(0);
            }
        }
    }

    private void likePage(){
        ArrayList<LikeInfo> ret =likeInfoService.getLikeInfos(id);

        ArrayList<Product> products = new ArrayList<>();

        for(int i = 0; i < ret.size(); i++){
            products.add(productService.getProductById(ret.get(i).getProductId()));
        }

        LikeView.checkLikeList(products, id);
    }

    private void reviewPage(){
        ReviewView.reviewPage();
        ReviewView.printReviews(reviewService.getReviewInfos(id), id);
    }

    private void addReview(){
        ReviewView.productReviewId();
        Long productId = scanner.nextLong();
        ReviewView.createReview();
        String reviewDESC = scanner.nextLine();
        LocalDateTime t = LocalDateTime.now();
        reviewService.addReview(new Review((long)(id + productId.toString() + t.toString()).hashCode(), id, productId, reviewDESC, Time.valueOf(t.toLocalTime())));
        ReviewView.afterAddReview();
    }

    private void shoppingCartPage(){
        ShoppingCartView.shoppingCartPageStart();
        int choice = scanner.nextInt();
        if (choice == 1){
            ShoppingCartView.deleteProduct();
            Long productId = scanner.nextLong();
            shoppingCartService.deleteProduct(id);
        }
        else if (choice == 2){
            ArrayList<ShoppingCart> arr = shoppingCartService.checkProductsInShoppingCart(id);

            for (ShoppingCart item : arr){
                Long productId = item.getProductId();
                Product product = productService.getProductById(productId);
                LocalDateTime t = LocalDateTime.now();
                PurchaseTransaction purchaseTransaction = new PurchaseTransaction((long) (t.toString() + id).hashCode(),
                        id, productId, product.getProductPrice(),
                        PaymentType.CARD, Time.valueOf(t.toLocalTime()));
                transactionService.recordTransaction(purchaseTransaction);
            }
            ShoppingCartView.buyProductsInShoppingCart();
        }
        else {
            ArrayList<ShoppingCart> arr = shoppingCartService.checkProductsInShoppingCart(id);
            ShoppingCartView.printInfos(arr, id);
        }
    }
}
