package peaksoft;

import peaksoft.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserServiceImpl userService = new UserServiceImpl();
     //   userService.createUsersTable();

//        userService.saveUser("Asan","Asanov",(byte)19);
//        userService.saveUser("Aidar","Bakytov", (byte) 18);

       //     userService.saveUser("Jazqul","Kadyrova", (byte) 21);
       //     userService.saveUser("Bermet","Nurdinova", (byte) 17);

      //  userService.dropUsersTable();
      //  userService.removeUserById(1);
         userService.cleanUsersTable();


    }
}
