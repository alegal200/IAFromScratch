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
        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\CSVReader\\CSV\\"+table;
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

    public static double[] getOutput(String table) throws IOException {
        //Delimiter
        String line = "";
        final String delimiter = ",";

        //Initilisation des variables relatives au fichier
        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\CSVReader\\CSV\\"+table;
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
                Output[currLine] = Double.parseDouble(token[token.length-1]);

                currLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(Output));
        return Output;
    }


}
