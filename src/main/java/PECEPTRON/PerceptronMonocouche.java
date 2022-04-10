package PECEPTRON;

import CSVReader.CSVReader;
import JfreeChartPack.Chart;
import org.jfree.ui.RefineryUtilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    2 constructeurs different : avec Quad_Error_Value_Threshold ou avec Number_Error_Threshold
    Si Quad_Error_Value_Threshold vaut 0 on sait qu'on veut faire une classification en fonction du nombre d'erreur
    Sinon en fonction du seuil pour l'erreur quad moyenne

 */


public class PerceptronMonocouche {

    private int Number_of_classes = 3;
    private String type_Algo; //adaline ou dg
    private double Learning_Rate; // ]0, 1]
    private double Quad_Error_Value_Threshold = 0;
    private int Number_Error_Threshold;
    private int MAX_ITERATION;


    public PerceptronMonocouche() {
    }

    public PerceptronMonocouche(int Number_of_classes, String type_Algo, double Learning_Rate, int MAX_ITERATION, int Number_Error_Threshold) {
        setNumber_of_classes(Number_of_classes);
        setType_Algo(type_Algo);
        setLearning_Rate(Learning_Rate);
        setMAX_ITERATION(MAX_ITERATION);
        setNumber_Error_Threshold(Number_Error_Threshold);

    }

    public PerceptronMonocouche(int Number_of_classes, String type_Algo, double Learning_Rate, int MAX_ITERATION, double Quad_Error_Value_Threshold) {
        setNumber_of_classes(Number_of_classes);
        setType_Algo(type_Algo);
        setLearning_Rate(Learning_Rate);
        setMAX_ITERATION(MAX_ITERATION);
        setQuad_Error_Value_Threshold(Quad_Error_Value_Threshold);

    }

    public void Perceptron(double[][] Input, double[][] Output) throws IOException {

        //Remplissage des points uniquement sans l entre fictive
        double[][] listPoints = new double[Input.length][2];
        for(int i=0;i<Input.length;i++)
        {
            listPoints[i][0]=Input[i][1];
            listPoints[i][1]=Input[i][2];
        }

        double[][] listWeights=new double[getNumber_of_classes()][Input[0].length];

        switch (getNumber_of_classes()) {
            case 3:
                if (getType_Algo().equals("adaline")) {
                    if (getQuad_Error_Value_Threshold() != 0) { //Perceptron Monocouche 3 classes Adal-SeuilErrQuad
                        for (int i = 1; i <= getNumber_of_classes(); i++) {
                            System.out.println("Entrainement du neuronnes " + i);
                            PerceptronADALINE p = new PerceptronADALINE(getLearning_Rate(), getMAX_ITERATION(), getQuad_Error_Value_Threshold());
                            double[] w=p.Perceptron(Input, CSVReader.getOutput(Output, i));
                            for(int j=0;j< w.length;j++)
                            {
                                listWeights[i-1][j]=w[j];
                            }
                            System.out.println("Fin de l entrainement du neuronnes " + i);
                        }
                    } else { //Perceptron Monocouche 3 classes Adal-SeuilNombreErreur
                        for (int i = 1; i <= getNumber_of_classes(); i++) {
                            System.out.println("Entrainement du neuronnes " + i);
                            PerceptronADALINE p = new PerceptronADALINE(getLearning_Rate(), getMAX_ITERATION(), getNumber_Error_Threshold());
                            double[] w=p.Classification(Input, CSVReader.getOutput(Output, i));
                            for(int j=0;j< w.length;j++)
                            {
                                listWeights[i-1][j]=w[j];
                            }
                            System.out.println("Fin de l entrainement du neuronnes " + i);
                        }
                    }


                } else {
                    if (getQuad_Error_Value_Threshold() != 0) {
                        for (int i = 1; i <= getNumber_of_classes(); i++) {
                            System.out.println("Entrainement du neuronnes " + i);
                            PerceptronDG p = new PerceptronDG(getLearning_Rate(), getMAX_ITERATION(), getQuad_Error_Value_Threshold());
                            double[] w=p.Perceptron(Input, CSVReader.getOutput(Output, i));
                            for(int j=0;j< w.length;j++)
                            {
                                listWeights[i-1][j]=w[j];
                            }
                            System.out.println("Fin de l entrainement du neuronnes " + i);
                        }
                    } else {
                        for (int i = 1; i <= getNumber_of_classes(); i++) {
                            System.out.println("Entrainement du neuronnes " + i);
                            PerceptronDG p = new PerceptronDG(getLearning_Rate(), getMAX_ITERATION(), getNumber_Error_Threshold());
                            double[] w=p.Perceptron(Input, CSVReader.getOutput(Output, i));
                            for(int j=0;j< w.length;j++)
                            {
                                listWeights[i-1][j]=w[j];
                            }
                            System.out.println("Fin de l entrainement du neuronnes " + i);
                        }
                    }

                }
                break;
            case 4: //On envisage que adaline

                double[] Weights=new double[Input[0].length]; //Vaut 26, manque de dynamisme
                Arrays.fill(Weights, 0);

                if (getQuad_Error_Value_Threshold() != 0) {
                    for (int i = 1; i <= getNumber_of_classes(); i++) {
                        System.out.println("Entrainement du neuronnes " + i);
                        PerceptronADALINE p = new PerceptronADALINE(Weights,getLearning_Rate(), getMAX_ITERATION(), getQuad_Error_Value_Threshold());
                        p.Perceptron(Input, CSVReader.getOutput(Output, i));
                        System.out.println("Fin de l entrainement du neuronnes " + i);
                    }
                } else {
                    for (int i = 1; i <= getNumber_of_classes(); i++) {
                        System.out.println("Entrainement du neuronnes " + i);
                        PerceptronADALINE p = new PerceptronADALINE(Weights,getLearning_Rate(), getMAX_ITERATION(), getNumber_Error_Threshold());
                        p.Classification(Input, CSVReader.getOutput(Output, i));
                        System.out.println("Fin de l entrainement du neuronnes " + i);
                    }
                }

                break;
            default:
                System.out.println("Mauvais nombre de classe");
                break;


        }
        displayChart(listPoints,listWeights);

    }


    public int getNumber_of_classes() {
        return Number_of_classes;
    }

    public void setNumber_of_classes(int number_of_classes) {
        Number_of_classes = number_of_classes;
    }

    public String getType_Algo() {
        return type_Algo;
    }

    public void setType_Algo(String type_Algo) {
        this.type_Algo = type_Algo;
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

    public int getNumber_Error_Threshold() {
        return Number_Error_Threshold;
    }

    public void setNumber_Error_Threshold(int number_Error_Threshold) {
        Number_Error_Threshold = number_Error_Threshold;
    }


    public void transferWeight(double[][] lw,double[] w){
        for(int i=0;i<w.length;i++)
        {
            lw[lw.length][i]=w[i];
        }

    }

    public void displayChart(double[][]points,double[][] weights)
    {
        System.out.println("Points:" + Arrays.deepToString(points));
        System.out.println("Poid:" + Arrays.deepToString(weights));
        Chart chart = new Chart("Graphique",points,weights);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

}
