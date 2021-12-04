import java.sql.*;

public class ApplicationCentrale {
    private static PreparedStatement ajouterUneUe;
    private static PreparedStatement ajouterPrerequis;
    private static PreparedStatement ajouterEtudiant;
    private static PreparedStatement ajouterUeValide;
    private static PreparedStatement visualiserEtudiantBloc;
    private static PreparedStatement visualiserToutEtudiants;
    private static PreparedStatement visualiserEtudiantsPaeNonValide;
    private static PreparedStatement visualiserUeBloc;

    public ApplicationCentrale() {

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
            ajouterUneUe = conn.prepareStatement("SELECT * FROM projet.ajouterUneUe(?, ?, ?, ?);");
            ajouterPrerequis = conn.prepareStatement("SELECT * FROM projet.ajouterPrerequis(?, ?);");
            ajouterEtudiant = conn.prepareStatement("SELECT * FROM projet.ajouterEtudiant(?, ?, ?, ?);");
            ajouterUeValide = conn.prepareStatement("SELECT * FROM projet.ajouterUeValide(?, ?);");
            visualiserEtudiantBloc = conn.prepareStatement("SELECT e.nom, e.prenom, e.credits_pae FROM projet.etudiants e WHERE e.bloc = ? ORDER BY e.credits_pae;");
            visualiserToutEtudiants = conn.prepareStatement("SELECT e.nom, e.prenom, e.bloc, e.credits_pae FROM projet.etudiants e ORDER BY e.credits_pae;");
            visualiserEtudiantsPaeNonValide = conn.prepareStatement("SELECT e.nom, e.prenom, e.credits_valides FROM projet.etudiants e WHERE e.pae_valide = 0;");
            visualiserUeBloc = conn.prepareStatement("SELECT u.code_ue, u.titre, u.nombre_inscrits FROM projet.ues u WHERE u.bloc = ? ORDER BY u.nombre_inscrits;");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }

    public void ajouterUneUe(String code, String nom, int bloc, int nbrCred) {
        try {
            ajouterUneUe.setString(1, code);
            ajouterUneUe.setString(2,nom);
            ajouterUneUe.setInt(3,bloc);
            ajouterUneUe.setInt(4,nbrCred);
            ajouterUneUe.executeQuery();
            System.out.println("L'UE a été ajouté avec succès");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("L'UE n'a pas été ajouté");
        }
    }

    public void ajouterPrerequis(String code1, String code2) {
        try {
            ajouterPrerequis.setString(1,code1);
            ajouterPrerequis.setString(2,code2);
            ajouterPrerequis.executeQuery();
            System.out.println("Le prérequis a bien été ajouté à l'UE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Le prérequis n'a pas été ajouté à l'UE");
        }
    }

    public void ajouterEtudiant(String nom, String prenom, String email, String mdp) {
        String sel = BCrypt.gensalt();
        mdp = BCrypt.hashpw(mdp,sel);
        try {
            ajouterEtudiant.setString(1,nom);
            ajouterEtudiant.setString(2,prenom);
            ajouterEtudiant.setString(3,email);
            ajouterEtudiant.setString(4,mdp);
            ajouterEtudiant.executeQuery();
            System.out.println("L'étudiant a bien été ajouté");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("L'étudiant n'a pas été ajouté");
        }
    }

    public void ajouterUeValide(String email, String codeUe) {
        try {
            ajouterUeValide.setString(1,email);
            ajouterUeValide.setString(2,codeUe);
            ajouterUeValide.executeQuery();
            System.out.println("L'étudiant a bien validé cette UE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Verfier si vous avez correctement écrit l'email ou le code de l'UE");
        }
    }

    public void visualiserEtudiantBloc(int bloc) {
        try {
            visualiserEtudiantBloc.setInt(1,bloc);
            try (ResultSet rs = visualiserEtudiantBloc.executeQuery()) {
                while(rs.next()) {
                    System.out.println("Nom de l'étudiant : " + rs.getString(1) + "\nPrénom de l'étudiant : " + rs.getString(2)
                    + "\nNombre de crédit PAE : " + rs.getInt(3));
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void visualiserToutEtudiants() {
        try {
            try(ResultSet rs = visualiserToutEtudiants.executeQuery()) {
                while(rs.next()) {
                    System.out.println("Nom de l'étudiant : " + rs.getString(1) + "\nPrénom de l'étudiant : " + rs.getString(2)
                    + "\nBloc : " + rs.getInt(3) + "\nNombre de crédit PAE : " + rs.getInt(4));
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void visualiserEtudiantsPaeNonValide() {
        try {
            try(ResultSet rs = visualiserEtudiantsPaeNonValide.executeQuery()) {
                while(rs.next()) {
                    System.out.println("Nom de l'étudiant : " + rs.getString(1) + "\nPrénom de l'étudiant : " + rs.getString(2)
                            + "\nNombre de crédit validés : " + rs.getInt(3));
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void visualiserUeBloc(int bloc) {
        try {
            visualiserUeBloc.setInt(1,bloc);
            try (ResultSet rs = visualiserUeBloc.executeQuery()) {
                while(rs.next()) {
                    System.out.println("Code de l'UE : " + rs.getString(1) + "\nNom de l'UE : " + rs.getString(2)
                    + "\nNombre d'inscrits : " + rs.getInt(3));
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}


