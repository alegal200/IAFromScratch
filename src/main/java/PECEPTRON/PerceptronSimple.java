package PECEPTRON;


        import java.util.Arrays;

public class PerceptronSimple {

    private int[] Weights = {0, 0, 0};
    private double Threshold = 0.0;
    private int Learning_Rate = 1; // ]0, 1]


    public PerceptronSimple() {
    }


    public void Perceptron(int[][] Input, int[] OutputExpected) {

        int CurrentCompleteIteration = 1;
        int NBR_ERRORS = 0;

        do {
            System.out.println("CurrentCompleteIteration : "+CurrentCompleteIteration); //Affiche l'iterationComplete actuel

            NBR_ERRORS = 0; //Réinitialiser pour chaque itérationComplete (1 itérationComplete = k itération)

            for (int k = 0; k < Input.length; k++) {
                int Potential = 0;
                int Output;

                //Calcul du potentiel pour l'iteration k
                for (int i = 0; i < Input[k].length; i++) {
                    Potential = Potential + (Weights[i] * Input[k][i]);
                }
                System.out.println("  k"+k+" Potentiel:"+Potential);

                //Calcul de la sortie du neurone en fonction du seuil
                if (Potential >= Threshold) {
                    Output = 1;
                } else {
                    Output = 0;
                }
                System.out.println("  k"+k+" Sortie:"+Output);

                //Calcul de l'erreur commise par le neuronne
                int Error = OutputExpected[k] - Output;
                System.out.println("  k"+k+" ErreurCommise:"+Error);

                //Si le neuronne commet une erreur, modification des poids + incrementation du nbr d'erreur pour une iterationComplete
                if (Error != 0) {
                    for (int i = 0; i < Input[k].length; i++) {
                        Weights[i] = Weights[i] + Learning_Rate * (Error) * Input[k][i];
                    }
                    System.out.println("  k"+k+" NouveauPoid:"+ Arrays.toString(Weights));

                    NBR_ERRORS++;
                }

            }

            System.out.println("  Nombre d'erreur pour l'iterationComplete "+CurrentCompleteIteration+" :"+NBR_ERRORS);

            CurrentCompleteIteration++;
        } while (NBR_ERRORS > 0); //Si le nbr d'erreur pour une iterationComplete = 0 c'est qu'il s'est bien entrainé


    }


}
