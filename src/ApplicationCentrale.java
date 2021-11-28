import java.sql.*;

public class ApplicationCentrale {

    public static void test() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver PostgreSQL manquant !");
            System.exit(1);
        }

        String url="jdbc:postgresql://localhost:5432/postgres";
        Connection conn=null;
        try {
            conn=DriverManager.getConnection(url,"postgres","azerty");
        } catch (SQLException e) {
            System.out.println("Impossible de joindre le server !");
            System.exit(1);
        }

        try {
            PreparedStatement ajouterUneUe = conn.prepareStatement("SELECT * FROM projet.ajouterUnUe(?, ?, ?, ?);");
            PreparedStatement ajouterPrerequis = conn.prepareStatement("SELECT * FROM projet.ajouterPrerequis(?, ?);");
            PreparedStatement ajouterEtudiant = conn.prepareStatement("SELECT * FROM projet.ajouterEtudiant(?, ?, ?, ?);");
            PreparedStatement ajouterUeValide = conn.prepareStatement("SELECT * FROM projet.ajouterUeValide(?, ?);");
            PreparedStatement visualiserEtudiantBloc = conn.prepareStatement("SELECT e.nom, e.prenom, e.credits_pae FROM projet.etudiants e WHERE e.bloc = ? ORDER BY e.credits_pae;");
            PreparedStatement visualiserToutEtudiants = conn.prepareStatement("SELECT e.nom, e.prenom, e.bloc, e.credits_pae FROM projet.etudiants e ORDER BY e.credits_pae;");
            PreparedStatement visualiserEtudiantsPaeNonValide = conn.prepareStatement("SELECT e.nom, e.prenom, e.credits_valides FROM projet.etudiants e WHERE e.pae_valide = 0;");
            PreparedStatement visualiserUeBloc = conn.prepareStatement("SELECT u.code_ue, u.titre, u.nombre_inscrits FROM projet.ues u WHERE u.bloc = ? ORDER BY u.nombre_inscrits;");
        } catch (SQLException e) {
            System.out.println(e.getMessage().split("\n")[0]);
            System.exit(1);
        }
    }


}


