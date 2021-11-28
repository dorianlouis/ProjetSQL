import java.util.Scanner;

public class TestAppCentrale {
    private final static Scanner scanner = new Scanner(System.in);
    private final static ApplicationCentrale app = new ApplicationCentrale();

    public static void main(String[] args) {
        System.out.println("Bienvenue sur l'application centrale");
        System.out.println("Quel action voulez vous effectuer ?");
        int choix = 0;
        do {
            System.out.println();
            System.out.println("1 -> ajouter une UE");
            System.out.println("2 -> ajouter un prérequis a une UE");
            System.out.println("3 -> ajouter un étudiant");
            System.out.println("4 -> ajouter une UE validé pour un étudiant");
            System.out.println("5 -> visualiser tout les étudiants d'un bloc particulier");
            System.out.println("6 -> visualiser tout les étudiants");
            System.out.println("7 -> visualiser tout les étudiants qui n'ont pas validé leur PAE");
            System.out.println("8 -> visualiser toutes les UE d'un bloc particulier");
            System.out.println();
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    ajouterUe();
                    break;
                case 2:
                    ajouterPrerequis();
                    break;
                case 3:
                    ajouterEtudiant();
                    break;
                case 4:
                    ajouterUeValide();
                    break;
                case 5:
                    visualiserEtudiantBloc();
                    break;
                case 6:
                    visualiserToutEtudiants();
                    break;
                case 7:
                    visualiserEtudiantsPaeNonValide();
                    break;
                case 8:
                    visualiserUeBloc();
                    break;
            }

        } while (choix >= 1 && choix <= 8);

    }

    private static void ajouterUe(){
        System.out.print("Veuillez entrer le code de l'UE : ");
        String code = scanner.nextLine();
        System.out.print("Veuillez entrer le nom de cette UE : ");
        String nom = scanner.nextLine();
        System.out.print("Veuillez entrer le bloc de cette UE : ");
        int bloc = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Veuillez entrer le nombre de crédits de cette UE : ");
        int nbrCred = scanner.nextInt();
        scanner.nextLine();
        app.ajouterUneUe(code,nom,bloc,nbrCred);
    }

    private static void ajouterPrerequis(){
        System.out.print("Veuillez entrer le code de l'UE : ");
        String code1 = scanner.nextLine();
        System.out.print("Veuillez entrer le code de son prérequis : ");
        String code2 = scanner.nextLine();
        app.ajouterPrerequis(code1,code2);
    }

    private static void ajouterEtudiant(){
        System.out.print("Veuillez entrer le nom de l'étudiant : ");
        String nom = scanner.nextLine();
        System.out.print("Veuillez entrer le prénom de l'étudiant : ");
        String prenom = scanner.nextLine();
        System.out.print("Veuillez entrer l'email de l'étudiant : ");
        String email = scanner.nextLine();
        System.out.print("Veuillez entrer le mot de passe de l'étudiant : ");
        String mdp = scanner.nextLine();
        app.ajouterEtudiant(nom,prenom,email,mdp);
    }

    private static void ajouterUeValide(){
        System.out.print("Veuillez entrer l'email de l'étudiant : ");
        String email = scanner.nextLine();
        System.out.print("Veuillez entrer le code de l'UE : ");
        String code = scanner.nextLine();
        app.ajouterUeValide(email,code);
    }

    private static void visualiserEtudiantBloc() {
        System.out.print("Veuillez entrer le bloc des étudiants que vous voulez voir : ");
        int bloc = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        app.visualiserEtudiantBloc(bloc);
    }

    private static void visualiserToutEtudiants() {
        System.out.println();
        app.visualiserToutEtudiants();
    }

    private static void visualiserEtudiantsPaeNonValide() {
        System.out.println();
        app.visualiserEtudiantsPaeNonValide();
    }

    private static void visualiserUeBloc() {
        System.out.print("Veuillez le bloc des UEs que vous voulez voir : ");
        int bloc = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        app.visualiserUeBloc(bloc);
    }
}
