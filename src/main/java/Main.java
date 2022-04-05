
import CSVReader.CSVReader;
import PECEPTRON.*;
import java.io.IOException;
import java.util.Arrays;
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
                case 8:
                    Essai8();
                    break;
                case 9:
                    Essai9();
                    break;
                case 10:
                    Essai10();
                    break;
                case 11:
                    Essai11();
                    break;
                case 12:
                    Essai12();
                    break;
                case 13:
                    Essai13();
                    break;
                case 14:
                    Essai14();
                    break;
                case 15:
                    Essai15();
                    break;
                case 20:

                    

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
        System.out.println("1. Perceptron Mise au point (table ET)");
        System.out.println("2. Perceptron Descente du Gradiant (table ET)");
        System.out.println("3. Perceptron ADALINE(table ET)");
        System.out.println("   **********Linea Separable**********");
        System.out.println("4. Perceptron ADALINE classif. linea. sep.");
        System.out.println("5. Perceptron Descente du Gradiant classif. linea. sep.");
        System.out.println("   **********Non Linea Separable**********");
        System.out.println("6. Perceptron ADALINE classif. non linea. sep.");
        System.out.println("7. Perceptron Descente du Gradiant classif. non linea. sep.");
        System.out.println("   **********REGRESSION**********");
        System.out.println("8. Perceptron ADALINE Regression lineaire");
        System.out.println("9. Perceptron Descente du Gradiant Regression lineaire");
        System.out.println("**************Monocouche*********");
        System.out.println("10. Perceptron Monocouche 3 classes Adal-SeuilNombreErreur");
        System.out.println("11. Perceptron Monocouche 3 classes Adal-SeuilErrQuad");
        System.out.println("12. Perceptron Monocouche 3 classes DG-SeuilNombreErreur");
        System.out.println("13. Perceptron Monocouche 3 classes DG-SeuilErrQuad");
        System.out.println("14. Perceptron Monocouche 4 classes Adal-SeuilNombreErreur");
        System.out.println("15. Perceptron Monocouche 4 classes Adal-SeuilErrQuad");
        System.out.print("choix :");
        Scanner sc = new Scanner(System.in);
        choix = sc.nextInt();
        return choix;
    }

    private static void Essai1() {
        
        System.out.println("1. Perceptron Mise au point (table ET)");
        int[][] Input = {{1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}}; //Le premier 1 est l'entrée fictive
        int[] Output = {0, 0, 0, 1};

        PerceptronSimple p = new PerceptronSimple();
        p.Perceptron(Input, Output);
    }

    private static void Essai2() {
        System.out.println("2. Perceptron Descente du Gradiant (table ET)");
        double[][] Input = {{1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}}; //Le premier 1 est l'entrée fictive
        double[] Output = {-1, -1, -1, 1};

        PerceptronDG p = new PerceptronDG();
        p.Perceptron(Input, Output);
    }

    private static void Essai3() {
        System.out.println("3. Perceptron ADALINE(table ET)");
        double[][] Input = {{1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}}; //Le premier 1 est l'entrée fictive
        double[] Output = {-1, -1, -1, 1};

        PerceptronADALINE p = new PerceptronADALINE();
        p.Perceptron(Input, Output);
    }

    private static void Essai4() throws IOException {
        System.out.println("4. Perceptron ADALINE classif. linea. sep.");
        PerceptronADALINE p = new PerceptronADALINE(0.0012, 1000);
        p.Classification(CSVReader.getInput("table_2_9.csv"), CSVReader.getOutput("table_2_9.csv"));
    }

    private static void Essai5() throws IOException {
        System.out.println("5. Perceptron Descente du Gradiant classif. linea. sep.");
        PerceptronDG p = new PerceptronDG(0.0011, 1000);
        p.Classification(CSVReader.getInput("table_2_9.csv"), CSVReader.getOutput("table_2_9.csv"));
    }

    private static void Essai6() throws IOException {
        System.out.println("6. Perceptron ADALINE classif. non linea. sep.");
        PerceptronADALINE p = new PerceptronADALINE(0.0015, 1000);
        p.Classification(CSVReader.getInput("table_2_10.csv"), CSVReader.getOutput("table_2_10.csv"));
    }

    private static void Essai7() throws IOException {
        System.out.println("7. Perceptron Descente du Gradiant classif. non linea. sep.");
        PerceptronDG p = new PerceptronDG(0.0015, 1000);
        p.Classification(CSVReader.getInput("table_2_10.csv"), CSVReader.getOutput("table_2_10.csv"));
    }

    private static void Essai8() throws IOException {
        System.out.println("8. Perceptron ADALINE Regression lineaire");
        double[] Weight = new double[2];
        Arrays.fill(Weight, 0);
        PerceptronADALINE p = new PerceptronADALINE(Weight, 0.00014, 10000, 0.56);
        p.Perceptron(CSVReader.getInput("table_2_11.csv"), CSVReader.getOutput("table_2_11.csv"));
    }

    private static void Essai9() throws IOException {
        System.out.println("9. Perceptron Descente du Gradiant Regression lineaire");
        double[] Weight = new double[2];
        Arrays.fill(Weight, 0);
        PerceptronDG p = new PerceptronDG(Weight, 0.000167, 10000, 0.56);
        p.Perceptron(CSVReader.getInput("table_2_11.csv"), CSVReader.getOutput("table_2_11.csv"));
    }

    private static void Essai10() throws IOException {
        System.out.println("10. Perceptron Monocouche 3 classes Adal-SeuilNombreErreur");
        PerceptronMonocouche p = new PerceptronMonocouche(3, "adaline", 0.001, 200, 0);
        p.Perceptron(CSVReader.getInput("table_3_1.csv", 3), CSVReader.getOutput("table_3_1.csv", 3));
    }

    private static void Essai11() throws IOException {
        System.out.println("11. Perceptron Monocouche 3 classes Adal-SeuilErrQuad");
        PerceptronMonocouche p = new PerceptronMonocouche(3, "adaline", 0.001, 300, 0.01);
        p.Perceptron(CSVReader.getInput("table_3_1.csv", 3), CSVReader.getOutput("table_3_1.csv", 3));

    }

    private static void Essai12() throws IOException {
        System.out.println("12. Perceptron Monocouche 3 classes DG-SeuilNombreErreur");
        PerceptronMonocouche p = new PerceptronMonocouche(3, "dg", 0.0001, 200, 0);
        p.Perceptron(CSVReader.getInput("table_3_1.csv", 3), CSVReader.getOutput("table_3_1.csv", 3));

    }

    private static void Essai13() throws IOException {
        System.out.println("13. Perceptron Monocouche 3 classes DG-SeuilErrQuad");
        PerceptronMonocouche p = new PerceptronMonocouche(3, "dg", 0.0001, 300, 0.01);
        p.Perceptron(CSVReader.getInput("table_3_1.csv", 3), CSVReader.getOutput("table_3_1.csv", 3));

    }

    private static void Essai14() throws IOException {

        PerceptronMonocouche p = new PerceptronMonocouche(4, "adaline", 0.001, 1000, 0);
        p.Perceptron(CSVReader.getInput("table_3_5.csv", 4), CSVReader.getOutput("table_3_5.csv", 4));

    }

    private static void Essai15() throws IOException {

        PerceptronMonocouche p = new PerceptronMonocouche(4, "adaline", 0.001, 1000, 0.05);
        p.Perceptron(CSVReader.getInput("table_3_5.csv", 4), CSVReader.getOutput("table_3_5.csv", 4));

    }

}
