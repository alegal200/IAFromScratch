package PECEPTRON;


import java.util.Arrays;

public class PerceptronDG {
    private static final int MAX_ITERATION = 50;

    private double[] Weights = {0, 0, 0};
    private double[] D_Weights = {0, 0, 0};
    private double Learning_Rate = 0.2; // ]0, 1]
    private double Error_Threshold = 0.125001;
    private double Threshold = 0.0;



    public void Perceptron(int[][] Input, int[] OutputExpected) {

        int CurrentCompleteIteration = 1;
        double AVG_ERROR = 0;


        do {
            System.out.println("CurrentCompleteIteration : "+CurrentCompleteIteration); //Affiche l'iterationComplete actuel

            AVG_ERROR = 0; //Reinitialisation de l'erreur quadratique moyenne pour l'iterationComplete
            Arrays.fill(D_Weights, 0); //Réinitialise d_Weights pour l'iterationComplete
            double[] Output = new double[Input.length]; //Plus haute porté car necessaire au calcul de l'erreur moyenne

            for (int k = 0; k < Input.length; k++) {
                double Potential = 0;
                int s;

                //Calcul du potentiel pour l'iteration k
                for (int i = 0; i < Input[k].length; i++) {
                    Potential = Potential + (Weights[i] * Input[k][i]);
                }


                //Calcul de la sortie du neurone
                Output[k] = Potential;
                System.out.println("  k"+k+" Sortie:"+Output[k]);

                //Calcul des s du neurone en fonction du seuil
                if (Output[k] >= Threshold) {
                    s = 1;
                } else {
                    s = -1;
                }
                System.out.println("  k"+k+" Valeur s:"+s);

                //Calcul de l'erreur commise par le neuronne
                double Error = OutputExpected[k] - Output[k];
                System.out.println("  k"+k+" ErreurCommise:"+Error);

                //Si le neuronne commet une erreur, modification des d_poids
                if (Error != 0) {
                    for (int i = 0; i < Input[k].length; i++) {
                        D_Weights[i] = D_Weights[i] + Learning_Rate * (Error) * Input[k][i];
                    }
                    System.out.println("  k"+k+" Nouveau D_Weight:"+ Arrays.toString(D_Weights));
                }

            }

            //Modification des poids synaptiques a chaque iterationComplete
            for (int i = 0; i < Weights.length; i++) {
                Weights[i] = Weights[i] + D_Weights[i];
            }
            System.out.println("  Fin iteration"+CurrentCompleteIteration+" NouveauPoid Pour tour suivant:"+ Arrays.toString(Weights));

            //Calcul de l'erreur quadratique moyenne
            for (int i = 0; i < OutputExpected.length; i++) {
                AVG_ERROR = AVG_ERROR + Math.pow(OutputExpected[i]-Output[i],2);
            }
            AVG_ERROR = AVG_ERROR/(2*(OutputExpected.length));
            System.out.println("  Fin iteration"+CurrentCompleteIteration+" erreurQuadMoyenne:"+ AVG_ERROR );

            CurrentCompleteIteration++;

        } while ( AVG_ERROR > Error_Threshold && CurrentCompleteIteration < MAX_ITERATION); //Arret si Erreur quad moy inferieur a un certain seuil ou si on depasse le nbr max d'iteration



    }


}
