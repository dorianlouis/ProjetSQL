import java.sql.*;

public class ApplicationCentrale {

    /*
    private String url = "jdbc:postgresql://172.24.2.6:5432/dbcdamas14";
    Connection conn=null;
    */

    public static void test() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver PostgreSQL manquant !");
            System.exit(1);
        }

        String url="jdbc:postgresql://@localhost/postgres";
        Connection conn=null;
        try {
            conn=DriverManager.getConnection(url,"cdamas14","azerty");
        } catch (SQLException e) {
            System.out.println("Impossible de joindre le server !");
            System.exit(1);
        }
    }
}


