import java.sql.*;

public class ApplicationEtudiant {
    private static PreparedStatement seConnecter;
    private static PreparedStatement ajouterUneUePae;
    private static PreparedStatement enleverUneUePae;
    private static PreparedStatement validerPae;
    private static PreparedStatement visualiserdiffUePae;
    private static PreparedStatement visualiserPae;
    private static PreparedStatement reinitialiserPae;

    public ApplicationEtudiant() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver PostgreSQL manquant !");
            System.exit(1);
        }

        String url="jdbc:postgresql://localhost:5432/postgres";
        Connection conn=null;
        try {
            conn= DriverManager.getConnection(url,"postgres","azerty");
        } catch (SQLException e) {
            System.out.println("Impossible de joindre le server !");
            System.exit(1);
        }

        /* connexion ordi de l'ecole
        String url="jdbc:postgresql://172.24.2.6:5432/dbaminenemmaoui";
        Connection conn=null;
        try {
            conn= DriverManager.getConnection(url,"aminenemmaoui","UPG8ILC7T");
        } catch (SQLException e) {
            System.out.println("Impossible de joindre le server !");
            System.exit(1);
        }
        */

        try {
            seConnecter = conn.prepareStatement("SELECT * FROM projet.etudiants e WHERE e.email = ?");
            ajouterUneUePae = conn.prepareStatement("SELECT * FROM projet.ajouterUePae(?, ?)");
            enleverUneUePae = conn.prepareStatement("SELECT * FROM projet.enleverUePae(?, ?)");
            validerPae = conn.prepareStatement("SELECT * FROM projet.validerPae(?)");
            visualiserdiffUePae = conn.prepareStatement("SELECT * FROM projet.ueDispoPae u WHERE u.email = ?");
            visualiserPae = conn.prepareStatement("SELECT * FROM projet.ueDuPae u WHERE u.email = ?");
            reinitialiserPae = conn.prepareStatement("SELECT * FROM projet.reinitPae(?)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean seConnecter(String email, String mdp) {
        try {
            seConnecter.setString(1,email);
            try(ResultSet rs = seConnecter.executeQuery()) {
                while(rs.next()) {
                    if(BCrypt.checkpw(mdp,rs.getString(5))) {
                        return true;
                    }
                }
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Veuillez vérifier vos données");
            return false;
        }
    }

    public void ajouterUneUePae(String code, String email) {
        try {
            ajouterUneUePae.setString(1,code);
            ajouterUneUePae.setString(2,email);
            ajouterUneUePae.executeQuery();
            System.out.println("Votre UE a bien été ajouté à votre PAE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void enleverUneUePae(String code, String email) {
        try {
            enleverUneUePae.setString(1,code);
            enleverUneUePae.setString(2,email);
            enleverUneUePae.executeQuery();
            System.out.println("Votre UE a bien été retiré de votre PAE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void validerPae(String email) {
        try {
            validerPae.setString(1,email);
            validerPae.executeQuery();
            System.out.println("Votre PAE a bien été validé");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void visualiserdiffUePae(String email) {
        try {
            visualiserdiffUePae.setString(1,email);
            try (ResultSet rs = visualiserdiffUePae.executeQuery()) {
                while(rs.next()) {
                    System.out.println("Code de l'UE : " + rs.getString(2) + "\nNom de l'UE : " + rs.getString(3)
                                       + "\nNombre de crédits : " + rs.getInt(4) + "\nBloc : " + rs.getInt(5));
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void visualiserPae(String email) {
        try {
            visualiserPae.setString(1,email);
            try (ResultSet rs = visualiserPae.executeQuery()) {
                while(rs.next()) {
                    System.out.println("Code de l'UE : " + rs.getString(2) + "\nNom de l'UE : " + rs.getString(3)
                            + "\nNombre de crédits : " + rs.getInt(4) + "\nBloc : " + rs.getInt(5));
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void reinitialiserPae(String email) {
        try {
            reinitialiserPae.setString(1,email);
            reinitialiserPae.executeQuery();
            System.out.println("Votre PAE a bien été réinitialiser");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
