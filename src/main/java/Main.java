import PECEPTRON.PerceptronSimple;

import java.util.Scanner;


public class Main {

    static int choix = 0;

    public static void main(String[] args) {
        boolean fini = false;
        while (!fini) {
            switch (Menu()) {
                case 1:
                    Essai1();
                    break;
                default:
                    System.out.println("Choix incorrect");
                    break;

            }
            fini = true;
        }
    }

    private static int Menu() {
        System.out.println("**********Menu*********");
        System.out.println("1. Perceptron Mise au point");
        System.out.println("***********************");
        System.out.print("choix :");
        Scanner sc = new Scanner(System.in);
        choix = sc.nextInt();
        return choix;
    }


    private static void Essai1() {
        int[][] Input = {{1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}}; //Le premier 1 est l'entrée fictive
        int[] Output = {0, 0, 0, 1};

        PerceptronSimple p = new PerceptronSimple();
        p.Perceptron(Input, Output);
    }


}


