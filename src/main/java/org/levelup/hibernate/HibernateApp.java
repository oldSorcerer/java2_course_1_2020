package org.levelup.hibernate;

import org.hibernate.SessionFactory;
import org.levelup.hibernate.servise.UserService;

public class HibernateApp {
    public static void main(String[] args) {
        SessionFactory factory = new JobSessionFactoryConfiguration().configure();

        UserService userService = new UserService(factory);
//        User user = userService.createUserPersist("Oleg", "Olegov", "4568 852654");
//        System.out.println(user);

//        Integer id = userService.createUserSave("bvz", "nbzh", "2558 669854");
//        System.out.println(id);

        //Integer cloneId  = userService.cloneUser(38, "2548 548586");
        //System.out.println(cloneid);

//        User user = userService.updateUserNameWithMerge(38, "Kolya");
//        System.out.println("original user: " + Integer.toHexString(user.hashCode()));

//        User user = userService.mergeNewUser("Uyt", "Ujgy", "548 548965");
//        System.out.println(user);

        userService.updateUser("tr", "fr", "5485");

        factory.close();
    }
}
