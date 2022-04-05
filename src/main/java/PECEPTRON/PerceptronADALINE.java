package PECEPTRON;

import java.text.DecimalFormat;
import java.util.Arrays;

public class PerceptronADALINE {

    private double[] Weights;
    private double Threshold;
    private double Learning_Rate; // ]0, 1]
    private double Error_Threshold;
    private int MAX_ITERATION ;

    public PerceptronADALINE() {
        setWeights(new double[3]);
        Arrays.fill(getWeights(),0);
        setThreshold(0.0);
        setLearning_Rate(0.03);
        setError_Threshold(0.1251);
        setMAX_ITERATION(10000);
    }

    public PerceptronADALINE(double lr,int mi) {
        setWeights(new double[3]);
        Arrays.fill(getWeights(),0);
        setThreshold(0.0);
        setLearning_Rate(lr);
        setError_Threshold(0.1251);
        setMAX_ITERATION(mi);
    }



    public PerceptronADALINE(double[] w,double t,double lr,double e_r,int m_i) {
        setWeights(new double[w.length]);
        setWeights(Arrays.copyOf(w, w.length));
        setThreshold(t);
        setLearning_Rate(lr);
        setError_Threshold(e_r);
        setMAX_ITERATION(m_i);
    }


    public void Perceptron(double[][] Input, double[] OutputExpected,boolean isClassification) {

        int CurrentCompleteIteration = 1;
        double AVG_ERROR = 0;


        if(isClassification==false)
        {

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
                    if (Output[k] >= getThreshold()) {
                        s = 1;
                    } else {
                        s = -1;
                    }
                    System.out.println("  k"+k+" Valeur s:"+s);

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
                for(int k = 0; k < Input.length; k++)
                {
                    for(int i = 0; i < Input[k].length; i++)
                    {
                        NewOutput_ErrQuad[k] = NewOutput_ErrQuad[k] + (getWeights()[i] * Input[k][i]);
                    }
                }

                //Calcul de l'erreur quadratique moyenne
                for (int i = 0; i < OutputExpected.length; i++) {
                    AVG_ERROR = AVG_ERROR + Math.pow(OutputExpected[i]-NewOutput_ErrQuad[i],2);
                }
                AVG_ERROR = AVG_ERROR/(2*(OutputExpected.length));
                System.out.println("  Fin iteration"+CurrentCompleteIteration+" erreurQuadMoyenne:"+ AVG_ERROR );

                CurrentCompleteIteration++;
            } while (AVG_ERROR > getError_Threshold() && CurrentCompleteIteration < getMAX_ITERATION());

        }else
        {

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
                    //Output[k] = Math.round(Output[k]*1000.0)/1000.0;
                    System.out.println("  k" + k + " Sortie:" + Output[k]);
                    System.out.println("  k" + k + " SortieAttendue:" +OutputExpected[k]);

                    //Calcul des s du neurone en fonction du seuil
                    if (Output[k] >= getThreshold()) {
                        s = 1;
                    } else {
                        s = -1;
                    }
                    //System.out.println("  k"+k+" Valeur s:"+s);

                    //Calcul de l'erreur commise par le neuronne
                    double Error = OutputExpected[k] - Output[k];
                    //Error = Math.round(Error*1000.0)/1000.0;
                    System.out.println("  k" + k + " ErreurCommise:" + Error);


                    if(OutputExpected[k]>0 && Output[k]>0)
                    {

                    }else if(OutputExpected[k]<0 && Output[k]<0)
                    {

                    }else if(OutputExpected[k]==Output[k])
                    {

                    }else
                    {
                        NBR_ERRORS++;
                    }


                    //Si le neuronne commet une erreur, modification des poids + incrementation du nbr d'erreur pour une iterationComplete
                    if (Error > 0.00001 || Error < -0.00001) {
                        for (int i = 0; i < Input[k].length; i++) {
                            getWeights()[i] = getWeights()[i] + getLearning_Rate() * (Error) * Input[k][i];
                            //getWeights()[i] = Math.round(getWeights()[i]*1000000.0)/1000000.0;
                        }
                        System.out.println("  k" + k + " NouveauPoid:" + Arrays.toString(getWeights()));
                    }

                }

                //Calcul des nouveaux output pour l'erreur quadratique moyenne avec les dernier poids synaptiques
                double[] NewOutput_ErrQuad = new double[Input.length];
                for(int k = 0; k < Input.length; k++)
                {
                    for(int i = 0; i < Input[k].length; i++)
                    {
                        NewOutput_ErrQuad[k] = NewOutput_ErrQuad[k] + (getWeights()[i] * Input[k][i]);
                    }
                }

                //Calcul de l'erreur quadratique moyenne
                for (int i = 0; i < OutputExpected.length; i++) {
                    AVG_ERROR = AVG_ERROR + Math.pow(OutputExpected[i]-NewOutput_ErrQuad[i],2);
                }
                AVG_ERROR = AVG_ERROR/(2*(OutputExpected.length));

                System.out.println("  Fin iteration"+CurrentCompleteIteration+" erreurQuadMoyenne:"+ AVG_ERROR );
                System.out.println("  Nombre d'erreur pour l'iterationComplete "+CurrentCompleteIteration+" :"+NBR_ERRORS);

                CurrentCompleteIteration++;
            } while (NBR_ERRORS > 0 && CurrentCompleteIteration < getMAX_ITERATION());
        }




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

    public double getError_Threshold() {
        return Error_Threshold;
    }

    public void setError_Threshold(double error_Threshold) {
        Error_Threshold = error_Threshold;
    }

    public double getThreshold() {
        return Threshold;
    }

    public void setThreshold(double threshold) {
        Threshold = threshold;
    }

    public int getMAX_ITERATION() {
        return MAX_ITERATION;
    }

    public void setMAX_ITERATION(int MAX_ITERATION) {
        this.MAX_ITERATION = MAX_ITERATION;
    }
}
