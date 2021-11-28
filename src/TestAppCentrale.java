import java.util.Scanner;

public class TestAppCentrale {
    private final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        ApplicationCentrale app = new ApplicationCentrale();
        app.test();

        System.out.println("Bienvenue sur l'application étudiants");
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
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
            }

        } while (choix >= 1 && choix <= 8);

    }
}
