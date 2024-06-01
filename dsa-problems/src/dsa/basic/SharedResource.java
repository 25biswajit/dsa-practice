package dsa.basic;


import java.sql.SQLException;

public class SharedResource {

}
class Parent{
    protected Parent m() throws SQLException {
        System.out.println("Parent");
        return new Parent();
    }
}

class Child extends Parent{
    public Child  m() throws RuntimeException {
        System.out.println("Child");
        return new Child();
    }
}

