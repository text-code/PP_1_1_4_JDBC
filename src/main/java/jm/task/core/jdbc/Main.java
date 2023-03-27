package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl table = new UserServiceImpl();

        table.createUsersTable();
        table.saveUser("имя1", "фамилия1", (byte) 11);
        table.saveUser("имя2", "фамилия2", (byte) 22);
        table.saveUser("имя3", "фамилия3", (byte) 33);
        table.saveUser("имя4", "фамилия4", (byte) 44);

        System.out.println(table.getAllUsers());

//        table.removeUserById(1);
        table.cleanUsersTable();
        table.dropUsersTable();
    }
}
