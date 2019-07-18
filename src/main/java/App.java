import data.UserDB;
import data.business.User;

public class App {

    public static void main(String[] args) {
//        User user = new User.Builder()
//                .setUserEmail("test@mail.com")
//                .setUserName("name")
//                .setUserPassword("ohMyPassword")
//                .setUserSurname("SecondName")
//                .build();
//
//        UserDB.insert(user);

        System.out.println(UserDB.selectUserMailAndPass("test@mail.com", "ohMyPassword"));
    }

}
