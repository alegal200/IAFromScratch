package PECEPTRON;


        import java.util.Arrays;

public class PerceptronSimple {

    private double[] Weights ; //Suivant une fonction Gaussienne de preference
    private double Output_Threshold;
    private double Learning_Rate ; // ]0, 1]
    private int Number_Error_Threshold;


    public PerceptronSimple() {
        setWeights(new double[3]);
        Arrays.fill(Weights,0);
        setOutput_Threshold(0.0);
        setLearning_Rate(1);
        setNumber_Error_Threshold(0);
    }

    public PerceptronSimple(double[] w,double t,double lr,int Number_Error_Threshold) {
        setWeights(new double[w.length]);
        setWeights(Arrays.copyOf(w, w.length));
        setOutput_Threshold(t);
        setLearning_Rate(lr);
        setNumber_Error_Threshold(Number_Error_Threshold);
    }





    public double[] Perceptron(double[][] Input, double[] OutputExpected) {

        int CurrentCompleteIteration = 1;
        int NBR_ERRORS = 0;

        do {
            System.out.println("CurrentCompleteIteration : "+CurrentCompleteIteration); //Affiche l'iterationComplete actuel

            NBR_ERRORS = 0; //Réinitialiser pour chaque itérationComplete (1 itérationComplete = k itération)

            for (int k = 0; k < Input.length; k++) {
                double Potential = 0;
                double Output;

                //Calcul du potentiel pour l'iteration k
                for (int i = 0; i < Input[k].length; i++) {
                    Potential = Potential + (getWeights()[i] * Input[k][i]);
                }
                System.out.println("  k"+k+" Potentiel:"+Potential);

                //Calcul de la sortie du neurone en fonction du seuil
                if (Potential >= getOutput_Threshold()) {
                    Output = 1;
                } else {
                    Output = 0;
                }
                System.out.println("  k"+k+" Sortie:"+Output);

                //Calcul de l'erreur commise par le neuronne
                double Error = OutputExpected[k] - Output;
                System.out.println("  k"+k+" ErreurCommise:"+Error);

                //Si le neuronne commet une erreur, modification des poids + incrementation du nbr d'erreur pour une iterationComplete
                if (Error != 0) {
                    for (int i = 0; i < Input[k].length; i++) {
                        getWeights()[i] = getWeights()[i] + getLearning_Rate() * (Error) * Input[k][i];
                    }
                    System.out.println("  k"+k+" NouveauPoid:"+ Arrays.toString(getWeights()));

                    NBR_ERRORS++;
                }

            }

            System.out.println("  Nombre d'erreur pour l'iterationComplete "+CurrentCompleteIteration+" :"+NBR_ERRORS);

            CurrentCompleteIteration++;
        } while (NBR_ERRORS > getNumber_Error_Threshold()); //Si le nbr d'erreur pour une iterationComplete = 0 c'est qu'il s'est assez entrainé


        return getWeights();
    }


    public double[] getWeights() {
        return Weights;
    }

    public void setWeights(double[] weights) {
        Weights = weights;
    }

    public double getOutput_Threshold() {
        return Output_Threshold;
    }

    public void setOutput_Threshold(double output_Threshold) {
        Output_Threshold = output_Threshold;
    }

    public double getLearning_Rate() {
        return Learning_Rate;
    }

    public void setLearning_Rate(double learning_Rate) {
        Learning_Rate = learning_Rate;
    }

    public int getNumber_Error_Threshold() {
        return Number_Error_Threshold;
    }

    public void setNumber_Error_Threshold(int number_Error_Threshold) {
        Number_Error_Threshold = number_Error_Threshold;
    }
}
