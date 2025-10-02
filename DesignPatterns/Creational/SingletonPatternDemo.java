// SingletonPatternDemo.java
class Database {
    private static Database instance;
    private Database() {}
    public static Database getInstance() {
        if (instance == null) instance = new Database();
        return instance;
    }
    public void query(String sql) { System.out.println("Executing query: " + sql); }
}

public class SingletonPatternDemo {
    public static void main(String[] args) {
        Database db1 = Database.getInstance();
        Database db2 = Database.getInstance();

        db1.query("SELECT * FROM Students");
        System.out.println("db1 == db2: " + (db1 == db2));
    }
}
