package PECEPTRON;


import java.util.Arrays;

public class PerceptronDG {

    private double[] Weights; //Suivant une fonction Gaussienne de preference
    private double Output_Threshold;
    private double Learning_Rate; // ]0, 1]
    private int Number_Error_Threshold;
    private double Quad_Error_Value_Threshold;
    private int MAX_ITERATION;
    private double[] D_Weights;


    public PerceptronDG() {
        setWeights(new double[3]);
        Arrays.fill(getWeights(), 0);
        setOutput_Threshold(0.0);
        setLearning_Rate(0.2);
        setNumber_Error_Threshold(0);
        setQuad_Error_Value_Threshold(0.125001);
        setMAX_ITERATION(100);
        setD_Weights(new double[3]);
        Arrays.fill(getD_Weights(), 0);

    }

    public PerceptronDG(double learning_Rate, int MAX_ITERATION) {
        setWeights(new double[3]);
        Arrays.fill(getWeights(), 0);
        setOutput_Threshold(0.0);
        setLearning_Rate(learning_Rate);
        setNumber_Error_Threshold(0);
        setQuad_Error_Value_Threshold(0.125001);
        setMAX_ITERATION(MAX_ITERATION);
        setD_Weights(new double[3]);
        Arrays.fill(getD_Weights(), 0);
    }

    public PerceptronDG(double[] weights,double learning_Rate, int MAX_ITERATION) {
        setWeights(new double[weights.length]);
        setWeights(Arrays.copyOf(weights, weights.length));
        setOutput_Threshold(0.0);
        setLearning_Rate(learning_Rate);
        setNumber_Error_Threshold(0);
        setQuad_Error_Value_Threshold(0.125001);
        setMAX_ITERATION(MAX_ITERATION);
        setD_Weights(new double[weights.length]);
        setD_Weights(Arrays.copyOf(weights, weights.length));
    }

    public PerceptronDG(double learning_Rate, int MAX_ITERATION,double Quad_Error_Value_Threshold) {
        setWeights(new double[3]);
        Arrays.fill(getWeights(), 0);
        setOutput_Threshold(0.0);
        setLearning_Rate(learning_Rate);
        setNumber_Error_Threshold(0);
        setQuad_Error_Value_Threshold(Quad_Error_Value_Threshold);
        setMAX_ITERATION(MAX_ITERATION);
        setD_Weights(new double[3]);
        Arrays.fill(getD_Weights(), 0);
    }

    public PerceptronDG(double learning_Rate, int MAX_ITERATION,int Number_Error_Threshold ) {
        setWeights(new double[3]);
        Arrays.fill(getWeights(), 0);
        setOutput_Threshold(0.0);
        setLearning_Rate(learning_Rate);
        setNumber_Error_Threshold(Number_Error_Threshold);
        setQuad_Error_Value_Threshold(0.125001);
        setMAX_ITERATION(MAX_ITERATION);
        setD_Weights(new double[3]);
        Arrays.fill(getD_Weights(), 0);
    }



    public PerceptronDG(double[] weights,double learning_Rate, int MAX_ITERATION,double Quad_Error_Value_Threshold) {
        setWeights(new double[weights.length]);
        setWeights(Arrays.copyOf(weights, weights.length));
        setOutput_Threshold(0.0);
        setLearning_Rate(learning_Rate);
        setNumber_Error_Threshold(0);
        setQuad_Error_Value_Threshold(Quad_Error_Value_Threshold);
        setMAX_ITERATION(MAX_ITERATION);
        setD_Weights(new double[weights.length]);
        setD_Weights(Arrays.copyOf(weights, weights.length));
    }

    public PerceptronDG(double[] weights,double learning_Rate, int MAX_ITERATION,int Number_Error_Threshold) {
        setWeights(new double[weights.length]);
        setWeights(Arrays.copyOf(weights, weights.length));
        setOutput_Threshold(0.0);
        setLearning_Rate(learning_Rate);
        setNumber_Error_Threshold(Number_Error_Threshold);
        setQuad_Error_Value_Threshold(0);
        setMAX_ITERATION(MAX_ITERATION);
        setD_Weights(new double[weights.length]);
        setD_Weights(Arrays.copyOf(weights, weights.length));
    }

    public PerceptronDG(double[] weights, double t, double learning_Rate, double Quad_Error_Value_Threshold, int MAX_ITERATION) {
        setWeights(new double[weights.length]);
        setWeights(Arrays.copyOf(weights, weights.length));
        setOutput_Threshold(t);
        setLearning_Rate(learning_Rate);
        setNumber_Error_Threshold(0);
        setQuad_Error_Value_Threshold(Quad_Error_Value_Threshold);
        setMAX_ITERATION(MAX_ITERATION);
        setD_Weights(new double[weights.length]);
        setD_Weights(Arrays.copyOf(weights, weights.length));
    }


    public double[] Perceptron(double[][] Input, double[] OutputExpected) {

        int CurrentCompleteIteration = 1;
        double AVG_ERROR = 0;

        do {
            System.out.println("CurrentCompleteIteration : " + CurrentCompleteIteration); //Affiche l'iterationComplete actuel

            AVG_ERROR = 0; //Reinitialisation de l'erreur quadratique moyenne pour l'iterationComplete
            Arrays.fill(getD_Weights(), 0); //Réinitialise d_Weights pour l'iterationComplete
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

                //Si le neuronne commet une erreur, modification des d_poids
                if (Error != 0) {
                    for (int i = 0; i < Input[k].length; i++) {
                        getD_Weights()[i] = getD_Weights()[i] + getLearning_Rate() * (Error) * Input[k][i];
                    }
                    System.out.println("  k" + k + " Nouveau D_Weight:" + Arrays.toString(getD_Weights()));
                }

            }

            //Modification des poids synaptiques a chaque iterationComplete
            for (int i = 0; i < getWeights().length; i++) {
                getWeights()[i] = getWeights()[i] + getD_Weights()[i];
            }
            System.out.println("  Fin iteration" + CurrentCompleteIteration + " NouveauPoid Pour tour suivant:" + Arrays.toString(getWeights()));

            //Calcul de l'erreur quadratique moyenne
            for (int i = 0; i < OutputExpected.length; i++) {
                AVG_ERROR = AVG_ERROR + Math.pow(OutputExpected[i] - Output[i], 2);
            }
            AVG_ERROR = AVG_ERROR / (2 * (OutputExpected.length));
            System.out.println("  Fin iteration" + CurrentCompleteIteration + " erreurQuadMoyenne:" + AVG_ERROR);

            CurrentCompleteIteration++;

        } while (AVG_ERROR > getQuad_Error_Value_Threshold() && CurrentCompleteIteration < getMAX_ITERATION()); //Arret si Erreur quad moy inferieur a un certain seuil ou si on depasse le nbr max d'iteration


        return getWeights();
    }

    public double[] Classification(double[][] Input, double[] OutputExpected) {

        int CurrentCompleteIteration = 1;
        double AVG_ERROR = 0;
        int NBR_ERRORS = 0;

        do {
            System.out.println("CurrentCompleteIteration : " + CurrentCompleteIteration); //Affiche l'iterationComplete actuel

            AVG_ERROR = 0; //Reinitialisation de l'erreur quadratique moyenne pour l'iterationComplete
            NBR_ERRORS = 0; //Réinitialiser pour chaque itérationComplete (1 itérationComplete = k itération)
            Arrays.fill(getD_Weights(), 0); //Réinitialise d_Weights pour l'iterationComplete
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
                //System.out.println("  k" + k + " Valeur s:" + s);

                //Calcul de l'erreur commise par le neuronne
                double Error = OutputExpected[k] - Output[k];
                System.out.println("  k" + k + " ErreurCommise:" + Error);

                if (s==OutputExpected[k]) {

                } else {
                    NBR_ERRORS++;
                }

                //Si le neuronne commet une erreur, modification des d_poids
                if (Error > 0.00001 || Error < -0.00001) {
                    for (int i = 0; i < Input[k].length; i++) {
                        getD_Weights()[i] = getD_Weights()[i] + getLearning_Rate() * (Error) * Input[k][i];
                    }
                    System.out.println("  k" + k + " Nouveau D_Weight:" + Arrays.toString(getD_Weights()));
                }

            }

            //Modification des poids synaptiques a chaque iterationComplete
            for (int i = 0; i < getWeights().length; i++) {
                getWeights()[i] = getWeights()[i] + getD_Weights()[i];
            }
            System.out.println("  Fin iteration" + CurrentCompleteIteration + " NouveauPoid Pour tour suivant:" + Arrays.toString(getWeights()));

            //Calcul de l'erreur quadratique moyenne
            for (int i = 0; i < OutputExpected.length; i++) {
                AVG_ERROR = AVG_ERROR + Math.pow(OutputExpected[i] - Output[i], 2);
            }
            //AVG_ERROR = AVG_ERROR / (2 * (OutputExpected.length));
            AVG_ERROR = AVG_ERROR / 1;
            System.out.println("  Fin iteration" + CurrentCompleteIteration + " erreurQuadMoyenne:" + AVG_ERROR);
            System.out.println("  Nombre d'erreur pour l'iterationComplete " + CurrentCompleteIteration + " :" + NBR_ERRORS);

            CurrentCompleteIteration++;
        } while (NBR_ERRORS > getNumber_Error_Threshold() && CurrentCompleteIteration < getMAX_ITERATION()); //Arret si Erreur quad moy inferieur a un certain seuil ou si on depasse le nbr max d'iteration

        return getWeights();
    }

    public double[] Regression(double[][] Input, double[] OutputExpected) {

        int CurrentCompleteIteration = 1;
        double AVG_ERROR = 0;

        do {
            System.out.println("CurrentCompleteIteration : " + CurrentCompleteIteration); //Affiche l'iterationComplete actuel

            AVG_ERROR = 0; //Reinitialisation de l'erreur quadratique moyenne pour l'iterationComplete
            Arrays.fill(getD_Weights(), 0); //Réinitialise d_Weights pour l'iterationComplete
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

                //Si le neuronne commet une erreur, modification des d_poids
                if (Error != 0) {
                    for (int i = 0; i < Input[k].length; i++) {
                        getD_Weights()[i] = getD_Weights()[i] + getLearning_Rate() * (Error) * Input[k][i];
                    }
                    System.out.println("  k" + k + " Nouveau D_Weight:" + Arrays.toString(getD_Weights()));
                }

            }

            //Modification des poids synaptiques a chaque iterationComplete
            for (int i = 0; i < getWeights().length; i++) {
                getWeights()[i] = getWeights()[i] + getD_Weights()[i];
            }
            System.out.println("  Fin iteration" + CurrentCompleteIteration + " NouveauPoid Pour tour suivant:" + Arrays.toString(getWeights()));

            //Calcul de l'erreur quadratique moyenne
            for (int i = 0; i < OutputExpected.length; i++) {
                AVG_ERROR = AVG_ERROR + Math.pow(OutputExpected[i] - getWeights()[0] - (getWeights()[1]* Input[i][1]), 2);
            }
            AVG_ERROR = AVG_ERROR / (2 * (OutputExpected.length));
            System.out.println("  Fin iteration" + CurrentCompleteIteration + " erreurQuadMoyenne:" + AVG_ERROR);

            CurrentCompleteIteration++;

        } while (AVG_ERROR > getQuad_Error_Value_Threshold() && CurrentCompleteIteration < getMAX_ITERATION()); //Arret si Erreur quad moy inferieur a un certain seuil ou si on depasse le nbr max d'iteration


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

    public double getQuad_Error_Value_Threshold() {
        return Quad_Error_Value_Threshold;
    }

    public void setQuad_Error_Value_Threshold(double quad_Error_Value_Threshold) {
        Quad_Error_Value_Threshold = quad_Error_Value_Threshold;
    }

    public int getMAX_ITERATION() {
        return MAX_ITERATION;
    }

    public void setMAX_ITERATION(int MAX_ITERATION) {
        this.MAX_ITERATION = MAX_ITERATION;
    }

    public double[] getD_Weights() {
        return D_Weights;
    }

    public void setD_Weights(double[] d_Weights) {
        D_Weights = d_Weights;
    }

    public int getNumber_Error_Threshold() {
        return Number_Error_Threshold;
    }

    public void setNumber_Error_Threshold(int number_Error_Threshold) {
        Number_Error_Threshold = number_Error_Threshold;
    }
}
