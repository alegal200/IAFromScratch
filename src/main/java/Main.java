import CSVReader.CSVReader;
import PECEPTRON.*;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    static int choix = 0;

    public static void main(String[] args) throws IOException {
        boolean fini = false;
        while (!fini) {
            switch (Menu()) {
                case 1:
                    Essai1();
                    break;
                case 2:
                    Essai2();
                    break;
                case 3:
                    Essai3();
                    break;
                case 4:
                    Essai4();
                    break;
                case 5:
                    Essai5();
                    break;
                case 6:
                    Essai6();
                    break;
                case 7:
                    Essai7();
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
        System.out.println("2. Perceptron Descente du Gradiant");
        System.out.println("3. Perceptron ADALINE");
        System.out.println("4. Perceptron ADALINE classif. linea. sep.");
        System.out.println("5. Perceptron Descente du Gradiant classif. linea. sep.");
        System.out.println("6. Perceptron ADALINE classif. non linea. sep.");
        System.out.println("7. Perceptron Descente du Gradiant classif. non linea. sep.");
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

    private static void Essai2() {
        double[][] Input = {{1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}}; //Le premier 1 est l'entrée fictive
        double[] Output = {-1, -1, -1, 1};

        PerceptronDG p = new PerceptronDG();
        p.Perceptron(Input, Output, false);
    }

    private static void Essai3() {
        double[][] Input = {{1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}}; //Le premier 1 est l'entrée fictive
        double[] Output = {-1, -1, -1, 1};

        PerceptronADALINE p = new PerceptronADALINE();
        p.Perceptron(Input, Output, false);
    }

    private static void Essai4() throws IOException {

        PerceptronADALINE p = new PerceptronADALINE(0.012, 100);
        p.Perceptron(CSVReader.getInput("table_2_9.csv"), CSVReader.getOutput("table_2_9.csv"), true);
    }

    private static void Essai5() throws IOException {

        PerceptronDG p = new PerceptronDG(0.0011, 1000);
        p.Perceptron(CSVReader.getInput("table_2_9.csv"), CSVReader.getOutput("table_2_9.csv"), true);
    }

    private static void Essai6() throws IOException {

        PerceptronADALINE p = new PerceptronADALINE(0.0011, 1000);
        p.Perceptron(CSVReader.getInput("table_2_10.csv"), CSVReader.getOutput("table_2_10.csv"), true);
    }

    private static void Essai7() throws IOException {

        PerceptronDG p = new PerceptronDG(0.0015, 1000);
        p.Perceptron(CSVReader.getInput("table_2_10.csv"), CSVReader.getOutput("table_2_10.csv"), true);
    }

}


