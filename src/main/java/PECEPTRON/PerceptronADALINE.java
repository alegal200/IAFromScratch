package PECEPTRON;

import java.util.Arrays;

public class PerceptronADALINE {

    private double[] Weights; //Suivant une fonction Gaussienne de preference
    private double Output_Threshold;
    private double Learning_Rate; // ]0, 1]
    private int Number_Error_Threshold;
    private double Quad_Error_Value_Threshold;
    private int MAX_ITERATION;

    public PerceptronADALINE() {
        setWeights(new double[3]);
        Arrays.fill(getWeights(), 0);
        setOutput_Threshold(0.0);
        setLearning_Rate(0.03);
        setNumber_Error_Threshold(0);
        setQuad_Error_Value_Threshold(0.1251);
        setMAX_ITERATION(10000);
    }

    public PerceptronADALINE(double learning_Rate, int MAX_ITERATION) {
        setWeights(new double[3]);
        Arrays.fill(getWeights(), 0);
        setOutput_Threshold(0.0);
        setLearning_Rate(learning_Rate);
        setNumber_Error_Threshold(0);
        setQuad_Error_Value_Threshold(0.1251);
        setMAX_ITERATION(MAX_ITERATION);
    }


    public PerceptronADALINE(double learning_Rate, int MAX_ITERATION, double Quad_Error_Value_Threshold) {
        setWeights(new double[3]);
        Arrays.fill(getWeights(), 0);
        setOutput_Threshold(0.0);
        setLearning_Rate(learning_Rate);
        setNumber_Error_Threshold(0);
        setQuad_Error_Value_Threshold(Quad_Error_Value_Threshold);
        setMAX_ITERATION(MAX_ITERATION);
    }

    public PerceptronADALINE(double[] weights, double learning_Rate, int MAX_ITERATION, double Quad_Error_Value_Threshold) {
        setWeights(new double[weights.length]);
        setWeights(Arrays.copyOf(weights, weights.length));
        setOutput_Threshold(0.0);
        setLearning_Rate(learning_Rate);
        setNumber_Error_Threshold(0);
        setQuad_Error_Value_Threshold(Quad_Error_Value_Threshold);
        setMAX_ITERATION(MAX_ITERATION);
    }


    public PerceptronADALINE(double[] weights, double t, double learning_Rate, double Quad_Error_Value_Threshold, int MAX_ITERATION) {
        setWeights(new double[weights.length]);
        setWeights(Arrays.copyOf(weights, weights.length));
        setOutput_Threshold(t);
        setLearning_Rate(learning_Rate);
        setNumber_Error_Threshold(0);
        setQuad_Error_Value_Threshold(Quad_Error_Value_Threshold);
        setMAX_ITERATION(MAX_ITERATION);
    }


    public void Perceptron(double[][] Input, double[] OutputExpected) {

        int CurrentCompleteIteration = 1;
        double AVG_ERROR = 0;


        do {
            System.out.println("CurrentCompleteIteration : " + CurrentCompleteIteration); //Affiche l'iterationComplete actuel
            AVG_ERROR = 0; //Reinitialisation de l'erreur quadratique moyenne pour l'iterationComplete
            double[] Output = new double[Input.length]; //Plus haute porté car necessaire au calcul de l'erreur moyenne

            for (int k = 0; k < Input.length; k++) {
                double Potential = 0;
                int s;

                //Calcul du potentiel pour l'iteration k
                for (int i = 0; i < Input[k].length; i++) {
                    Potential = Potential + (getWeights()[i] * Input[k][i]);
                }

                //Calcul de la sortie du neurone
                Output[k] = Potential;
                System.out.println("  k" + k + " Sortie:" + Output[k]);

                //Calcul des s du neurone en fonction du seuil
                if (Output[k] >= getOutput_Threshold()) {
                    s = 1;
                } else {
                    s = -1;
                }
                System.out.println("  k" + k + " Valeur s:" + s);

                //Calcul de l'erreur commise par le neuronne
                double Error = OutputExpected[k] - Output[k];
                System.out.println("  k" + k + " ErreurCommise:" + Error);

                //Si le neuronne commet une erreur, modification des poids + incrementation du nbr d'erreur pour une iterationComplete
                if (Error != 0) {
                    for (int i = 0; i < Input[k].length; i++) {
                        getWeights()[i] = getWeights()[i] + getLearning_Rate() * (Error) * Input[k][i];
                    }
                    System.out.println("  k" + k + " NouveauPoid:" + Arrays.toString(getWeights()));
                }

            }

            //Calcul des nouveaux output pour l'erreur quadratique moyenne avec les dernier poids synaptiques
            double[] NewOutput_ErrQuad = new double[Input.length];
            for (int k = 0; k < Input.length; k++) {
                for (int i = 0; i < Input[k].length; i++) {
                    NewOutput_ErrQuad[k] = NewOutput_ErrQuad[k] + (getWeights()[i] * Input[k][i]);
                }
            }

            //Calcul de l'erreur quadratique moyenne
            for (int i = 0; i < OutputExpected.length; i++) {
                AVG_ERROR = AVG_ERROR + Math.pow(OutputExpected[i] - NewOutput_ErrQuad[i], 2);
            }
            AVG_ERROR = AVG_ERROR / (2 * (OutputExpected.length));
            System.out.println("  Fin iteration" + CurrentCompleteIteration + " erreurQuadMoyenne:" + AVG_ERROR);

            CurrentCompleteIteration++;
        } while (AVG_ERROR > getQuad_Error_Value_Threshold() && CurrentCompleteIteration < getMAX_ITERATION());

    }

    public void Classification(double[][] Input, double[] OutputExpected) {
        int CurrentCompleteIteration = 1;
        double AVG_ERROR = 0;
        int NBR_ERRORS = 0;


        do {
            System.out.println("CurrentCompleteIteration : " + CurrentCompleteIteration); //Affiche l'iterationComplete actuel
            AVG_ERROR = 0; //Reinitialisation de l'erreur quadratique moyenne pour l'iterationComplete
            NBR_ERRORS = 0; //Réinitialiser pour chaque itérationComplete (1 itérationComplete = k itération)
            double[] Output = new double[Input.length]; //Plus haute porté car necessaire au calcul de l'erreur moyenne

            for (int k = 0; k < Input.length; k++) {
                double Potential = 0;
                int s;

                //Calcul du potentiel pour l'iteration k
                for (int i = 0; i < Input[k].length; i++) {
                    Potential = Potential + (getWeights()[i] * Input[k][i]);
                }

                //Calcul de la sortie du neurone
                Output[k] = Potential;
                System.out.println("  k" + k + " Sortie:" + Output[k]);

                //Calcul des s du neurone en fonction du seuil
                if (Output[k] >= getOutput_Threshold()) {
                    s = 1;
                } else {
                    s = -1;
                }
                System.out.println("  k" + k + " Valeur s:" + s);

                //Calcul de l'erreur commise par le neuronne
                double Error = OutputExpected[k] - Output[k];
                System.out.println("  k" + k + " ErreurCommise:" + Error);


                if (s==OutputExpected[k]) {

                } else {
                    NBR_ERRORS++;
                }


                //Si le neuronne commet une erreur, modification des poids + incrementation du nbr d'erreur pour une iterationComplete
                if (Error > 0.00001 || Error < -0.00001) {
                    for (int i = 0; i < Input[k].length; i++) {
                        getWeights()[i] = getWeights()[i] + getLearning_Rate() * (Error) * Input[k][i];
                    }
                    System.out.println("  k" + k + " NouveauPoid:" + Arrays.toString(getWeights()));
                }

            }

            //Calcul des nouveaux output pour l'erreur quadratique moyenne avec les dernier poids synaptiques
            double[] NewOutput_ErrQuad = new double[Input.length];
            for (int k = 0; k < Input.length; k++) {
                for (int i = 0; i < Input[k].length; i++) {
                    NewOutput_ErrQuad[k] = NewOutput_ErrQuad[k] + (getWeights()[i] * Input[k][i]);
                }
            }

            //Calcul de l'erreur quadratique moyenne
            for (int i = 0; i < OutputExpected.length; i++) {
                AVG_ERROR = AVG_ERROR + Math.pow(OutputExpected[i] - NewOutput_ErrQuad[i], 2);
            }
            //AVG_ERROR = AVG_ERROR / (2 * (OutputExpected.length));
            AVG_ERROR = AVG_ERROR / 1;

            System.out.println("  Fin iteration" + CurrentCompleteIteration + " erreurQuadMoyenne:" + AVG_ERROR);
            System.out.println("  Nombre d'erreur pour l'iterationComplete " + CurrentCompleteIteration + " :" + NBR_ERRORS);

            CurrentCompleteIteration++;
        } while (NBR_ERRORS > getNumber_Error_Threshold() && CurrentCompleteIteration < getMAX_ITERATION());
    }




    public double[] getWeights() {
        return Weights;
    }

    public void setWeights(double[] weights) {
        Weights = weights;
    }

    public double getLearning_Rate() {
        return Learning_Rate;
    }

    public void setLearning_Rate(double learning_Rate) {
        Learning_Rate = learning_Rate;
    }

    public double getQuad_Error_Value_Threshold() {
        return Quad_Error_Value_Threshold;
    }

    public void setQuad_Error_Value_Threshold(double quad_Error_Value_Threshold) {
        Quad_Error_Value_Threshold = quad_Error_Value_Threshold;
    }

    public double getOutput_Threshold() {
        return Output_Threshold;
    }

    public void setOutput_Threshold(double output_Threshold) {
        Output_Threshold = output_Threshold;
    }

    public int getMAX_ITERATION() {
        return MAX_ITERATION;
    }

    public void setMAX_ITERATION(int MAX_ITERATION) {
        this.MAX_ITERATION = MAX_ITERATION;
    }

    public int getNumber_Error_Threshold() {
        return Number_Error_Threshold;
    }

    public void setNumber_Error_Threshold(int number_Error_Threshold) {
        Number_Error_Threshold = number_Error_Threshold;
    }
}
