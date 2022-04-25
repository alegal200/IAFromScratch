package CSVReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class CSVReader {


    public CSVReader() {
    }


    public static double[][] getInput(String table) throws IOException {
        //Delimiter
        String line = "";
        final String delimiter = ",";

        //Initilisation des variables relatives au fichier
        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\CSVReader\\CSV\\" + table;
        FileReader fileReader = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(fileReader);

        //Calcul de variables statiques grace au fichier
        long number_of_examp = Files.lines(Paths.get(filePath)).count(); // nombre d'exemples
        //Calcul du nbr d'element par ligne du fichier
        reader.mark(50);
        String ligne = reader.readLine();
        String[] tok = ligne.split(delimiter);
        reader.reset();
        int number_of_entry = tok.length; //+1 pour l'entré fictive mais -1 car il y'a l'output dans la ligne


        //Variables qui serra retourné
        double[][] Input = new double[(int) number_of_examp][number_of_entry];

        //Initialisation des entré fictive
        for (int i = 0; i < number_of_examp; i++) {
            Input[i][0] = 1;
        }


        try {

            int currLine = 0;
            while ((line = reader.readLine()) != null) {
                String[] token = line.split(delimiter);
                for (int j = 1; j < number_of_entry; j++) //Départ a 1 car on a deja initilisation l'entré fictive
                {
                    Input[currLine][j] = Double.parseDouble(token[j - 1]);
                }

                currLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.deepToString(Input));
        return Input;
    }

    public static double[][] getInputRegression(String table) throws IOException {
        //Delimiter
        String line = "";
        final String delimiter = ",";

        //Initilisation des variables relatives au fichier
        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\CSVReader\\CSV\\" + table;
        FileReader fileReader = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(fileReader);

        //Calcul de variables statiques grace au fichier
        long number_of_examp = Files.lines(Paths.get(filePath)).count(); // nombre d'exemples
        //Calcul du nbr d'element par ligne du fichier
        reader.mark(50);
        String ligne = reader.readLine();
        String[] tok = ligne.split(delimiter);
        reader.reset();
        int number_of_entry = 2;


        //Variables qui serra retourné
        double[][] Input = new double[(int) number_of_examp][number_of_entry];


        try {

            int currLine = 0;
            while ((line = reader.readLine()) != null) {
                String[] token = line.split(delimiter);

                Input[currLine][0] = Double.parseDouble(token[0]);
                Input[currLine][0] = Double.parseDouble(token[1]);

                currLine++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.deepToString(Input));
        return Input;


    }


    public static double[][] getInput(String table, int nbr_of_classes) throws IOException {
        //Delimiter
        String line = "";
        final String delimiter = ",";

        //Initilisation des variables relatives au fichier
        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\CSVReader\\CSV\\" + table;
        FileReader fileReader = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(fileReader);

        //Calcul de variables statiques grace au fichier
        long number_of_examp = Files.lines(Paths.get(filePath)).count(); // nombre d'exemples
        //Calcul du nbr d'element par ligne du fichier
        reader.mark(50);
        String ligne = reader.readLine();
        String[] tok = ligne.split(delimiter);
        reader.reset();
        int number_of_entry = tok.length - (nbr_of_classes - 1); //+1 pour l'entré fictive mais -1 car il y'a l'output dans la ligne
        //On retire le nombre d'output (-1 car on a deja enlevé la premiere output)
        //On garde uniquement les entré, ici il y'a plusieurs classe dont plusieurs sorties => number_of_entry ne vaut donc pas le nbr de token par ligne


        //Variables qui serra retourné
        double[][] Input = new double[(int) number_of_examp][number_of_entry];

        //Initialisation des entré fictive
        for (int i = 0; i < number_of_examp; i++) {
            Input[i][0] = 1;
        }


        try {

            int currLine = 0;
            while ((line = reader.readLine()) != null) {
                String[] token = line.split(delimiter);
                for (int j = 1; j < number_of_entry; j++) //Départ a 1 car on a deja initilisation l'entré fictive
                {
                    Input[currLine][j] = Double.parseDouble(token[j - 1]);
                }

                currLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.deepToString(Input));
        return Input;
    }


    public static double[] getOutput(String table) throws IOException {
        //Delimiter
        String line = "";
        final String delimiter = ",";

        //Initilisation des variables relatives au fichier
        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\CSVReader\\CSV\\" + table;
        FileReader fileReader = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(fileReader);

        //Calcul de variables grace au fichier
        long number_of_examp = Files.lines(Paths.get(filePath)).count(); // nombre d'exemples

        //Variable qui serra retourné
        double[] Output = new double[(int) number_of_examp];

        try {

            int currLine = 0;
            while ((line = reader.readLine()) != null) {

                String[] token = line.split(delimiter);
                Output[currLine] = Double.parseDouble(token[token.length - 1]);

                currLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(Output));
        return Output;
    }

    public static double[] getOutput(String table, int nbr_of_classes, int Output_Index) throws IOException {
        //Delimiter
        String line = "";
        final String delimiter = ",";

        //Initilisation des variables relatives au fichier
        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\CSVReader\\CSV\\" + table;
        FileReader fileReader = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(fileReader);

        //Calcul de variables grace au fichier
        long number_of_examp = Files.lines(Paths.get(filePath)).count(); // nombre d'exemples

        //Variable qui serra retourné
        double[] Output = new double[(int) number_of_examp];

        try {

            int currLine = 0;

            while ((line = reader.readLine()) != null) {

                String[] token = line.split(delimiter);
                int WantedOutputIndex_Tab = token.length - 1 - nbr_of_classes + Output_Index;
                Output[currLine] = Double.parseDouble(token[WantedOutputIndex_Tab]);

                currLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(Output));
        return Output;
    }

    public static double[][] getOutput(String table, int nbr_of_classes) throws IOException {
        //Delimiter
        String line = "";
        final String delimiter = ",";

        //Initilisation des variables relatives au fichier
        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\CSVReader\\CSV\\" + table;
        FileReader fileReader = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(fileReader);

        //Calcul de variables grace au fichier
        long number_of_examp = Files.lines(Paths.get(filePath)).count(); // nombre d'exemples

        //Variable qui serra retourné
        double[][] Output = new double[(int) number_of_examp][nbr_of_classes];

        try {

            int currLine = 0;

            while ((line = reader.readLine()) != null) {

                String[] token = line.split(delimiter);
                for (int j = (token.length - nbr_of_classes), i = 0; j < token.length && i < nbr_of_classes; j++, i++) //On rempli a partir des output
                {
                    Output[currLine][i] = Double.parseDouble(token[j]);
                }

                currLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(Output));
        return Output;
    }

    public static double[] getOutput(double[][] Output, int ClassIndex) throws IOException {

        //Variable qui serra retourné
        double[] Out = new double[Output.length];

        for (int i = 0; i < Out.length; i++) {
            Out[i] = Output[i][ClassIndex - 1]; //Si on entre 1 on recoit l'item 0
        }

        return Out;
    }

    public static double[] getOutputMulti(){

    }

    public static double[] getInputMulti(){

    }


}
