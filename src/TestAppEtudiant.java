import java.util.Scanner;

public class TestAppEtudiant {
        private final static Scanner scanner = new Scanner(System.in);
        private final static ApplicationEtudiant app = new ApplicationEtudiant();
        private static String email;

        public static void main(String[] args) {

            boolean estConnecte = false;
            while(!estConnecte) {
                System.out.print("Entrez votre email : ");
                email = scanner.nextLine();
                System.out.print("Entrez votre mot de passe : ");
                String mdp = scanner.nextLine();
                estConnecte = app.seConnecter(email,mdp);
                if(!estConnecte)
                    System.out.println("Vos email et/ou votre mot de passe est incorrect");
                System.out.println();
            }

            System.out.println("Bienvenue sur l'application étudiants");
            System.out.println("Quel action voulez vous effectuer ?");
            int choix = 0;
            do {
                System.out.println();
                System.out.println("1 -> ajouter une UE à votre PAE");
                System.out.println("2 -> enlever une UE à votre PAE");
                System.out.println("3 -> valider votre PAE");
                System.out.println("4 -> viusaliser les différents UE que vous pouvez ajouter à votre PAE");
                System.out.println("5 -> visualiser votre PAE");
                System.out.println("6 -> réinitialiser votre PAE");
                System.out.println();
                System.out.print("Votre choix : ");
                choix = scanner.nextInt();
                scanner.nextLine();
                switch (choix) {
                    case 1:
                        ajouterUneUePae();
                        break;
                    case 2:
                        enleverUneUePae();
                        break;
                    case 3:
                        validerPae();
                        break;
                    case 4:
                        visualiserDiffUePae();
                        break;
                    case 5:
                        visualiserUePae();
                        break;
                    case 6:
                        reinitialiserPae();
                        break;
                }

            } while (choix >= 1 && choix <= 6);

        }

        private static void ajouterUneUePae() {
            System.out.print("Entrez le code de l'UE que vous voulez ajouter à votre PAE : ");
            String code = scanner.nextLine();
            app.ajouterUneUePae(code,email);
        }

        private static void enleverUneUePae() {
            System.out.print("Entrez le code de l'UE que vous voulez supprimer de votre PAE : ");
            String code = scanner.nextLine();
            app.enleverUneUePae(code,email);
        }

        private static void validerPae() {
            app.validerPae(email);
        }

        private static void visualiserDiffUePae() {
            app.visualiserdiffUePae(email);
        }

        private static void visualiserUePae() {
            app.visualiserPae(email);
        }

        private static void reinitialiserPae() {
            app.reinitialiserPae(email);
        }
}
