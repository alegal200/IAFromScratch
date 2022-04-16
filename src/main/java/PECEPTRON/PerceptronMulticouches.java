package PECEPTRON;

import java.util.Arrays;

public class PerceptronMulticouches {

    private int Nbr_Of_Neurones_DummyLayer;
    private int Nbr_Of_Neurones_HiddenLayer;
    private int Nbr_Of_Neurones_OutputLayer;
    private double[] Entry ={1,0.9,0.1,0.9}; //Entré x0 a 1 comme d'habitude
    private double[] Out ={0.1,0.9,0.9};

    public PerceptronMulticouches()
    {
        setNbr_Of_Neurones_DummyLayer(0);
        setNbr_Of_Neurones_HiddenLayer(0);
        setNbr_Of_Neurones_OutputLayer(0);


    }

    public PerceptronMulticouches(int Nbr_Of_Neurones_DummyLayer,int Nbr_Of_Neurones_HiddenLayer,int Nbr_Of_Neurones_OutputLayer)
    {
        setNbr_Of_Neurones_DummyLayer(Nbr_Of_Neurones_DummyLayer);
        setNbr_Of_Neurones_HiddenLayer(Nbr_Of_Neurones_HiddenLayer);
        setNbr_Of_Neurones_OutputLayer(Nbr_Of_Neurones_OutputLayer);
    }

    public void Perceptron()
    {
        //Exemple pour un multi-couches 3-2-3

        //Initialisation des poids des neurones de la couche caché
        double[] C1_Weights ={0,0.1,0.15,0.05};
        double[] C2_Weights ={0,0.12,0.18,0.08};
        //Initialisation des poids des neuronnes couche sortie
        double[] S1_Weights ={0,0.1,0.14};
        double[] S2_Weights ={0,0.125,0.21};
        double[] S3_Weights ={0,0.13,0.07};

        //1.Propagation des données d'entrée (x1, x2, ..., xE) à travers le réseau :
        //1.a) Calcul des potentiels kc et sortie yc de chaque neurone de la couche caché
        double potentiel_C1=0;
        for (int i = 0; i < Entry.length; i++) {
            potentiel_C1 = potentiel_C1 + (C1_Weights[i] * Entry[i]);
        }

        double potentiel_C2=0;
        for (int i = 0; i < Entry.length; i++) {
            potentiel_C2 = potentiel_C2 + (C2_Weights[i] * Entry[i]);
        }
        System.out.println("potentiel_C1:"+potentiel_C1);
        System.out.println("potentiel_C2:"+potentiel_C2);

        double sortie_C1 = 1/(1+Math.pow(Math.E,-potentiel_C1));
        double sortie_C2 = 1/(1+Math.pow(Math.E,-potentiel_C2));
        System.out.println("sortie_C1:"+sortie_C1);
        System.out.println("sortie_C2:"+sortie_C2);

        double tab_sortie_HiddenLayer[]={0,sortie_C1,sortie_C2}; //Rajout statique du 0 pour que ca match avec calcul potentiel 1.b pour la premier boucle(voir pdf prof)
        //1.b) Calcul des potentiels ps et sories zs de chaque neurone de la couche de sortie (fonction logistique comme fonction d'activation)
        double potentiel_S1=0;
        for (int i = 0; i < S1_Weights.length; i++) {
            potentiel_S1 = potentiel_S1 + (S1_Weights[i] * tab_sortie_HiddenLayer[i]);
        }
        System.out.println("potentiel_S1:"+potentiel_S1);

        double potentiel_S2=0;
        for (int i = 0; i < S1_Weights.length; i++) {
            potentiel_S2 = potentiel_S2 + (S2_Weights[i] * tab_sortie_HiddenLayer[i]);
        }
        System.out.println("potentiel_S2:"+potentiel_S2);

        double potentiel_S3=0;
        for (int i = 0; i < S1_Weights.length; i++) {
            potentiel_S3 = potentiel_S3 + (S3_Weights[i] * tab_sortie_HiddenLayer[i]);
        }
        System.out.println("potentiel_S3:"+potentiel_S3);


        double sortie_S1 = 1/(1+Math.pow(Math.E,-potentiel_S1));
        double sortie_S2 = 1/(1+Math.pow(Math.E,-potentiel_S2));
        double sortie_S3 = 1/(1+Math.pow(Math.E,-potentiel_S3));
        double tab_sortie_OutputLayer[]={sortie_S1,sortie_S2,sortie_S3};
        System.out.println("sortie_S1:"+sortie_S1);
        System.out.println("sortie_S2:"+sortie_S2);
        System.out.println("sortie_S3:"+sortie_S3);


        //2 Calcul de l'erreur et retropropagation de l'erreur a travers le reseau
        //Calcul de l'erreur quadratique moyenne
        double AVG_ERROR = 0;
        for (int i = 0; i < Out.length; i++) {
            AVG_ERROR = AVG_ERROR + Math.pow(Out[i] - tab_sortie_OutputLayer[i], 2);
        }
        AVG_ERROR = AVG_ERROR / 2;
        System.out.println("AVG_ERROR:"+AVG_ERROR); //Vaut 0.226

        //2.a  Calcul des signaux d erreur de chaque neurone de la couche de sortie
        if(AVG_ERROR>0.200){

            double signal_erreur_S1=(Out[0]-tab_sortie_OutputLayer[0])*tab_sortie_OutputLayer[0]*(1-tab_sortie_OutputLayer[0]);
            double signal_erreur_S2=(Out[1]-tab_sortie_OutputLayer[1])*tab_sortie_OutputLayer[1]*(1-tab_sortie_OutputLayer[1]);
            double signal_erreur_S3=(Out[2]-tab_sortie_OutputLayer[2])*tab_sortie_OutputLayer[2]*(1-tab_sortie_OutputLayer[2]);
            System.out.println("signal_erreur_S1:"+signal_erreur_S1);
            System.out.println("signal_erreur_S2:"+signal_erreur_S2);
            System.out.println("signal_erreur_S3:"+signal_erreur_S3);

            //2.b  Calcul des signaux d erreur de chaque neurone de la couche caché
            double signal_erreur_C1=sortie_C1*(1-sortie_C1);
            double signal_erreur_C2=sortie_C2*(1-sortie_C2);
            System.out.println("signal_erreur_C1:"+signal_erreur_C1);
            System.out.println("signal_erreur_C2:"+signal_erreur_C2);
            //2.b.. Cumulé avec des les signaux d'erreur de la couche de sortie ponderé par les poids synaptiques





        }

    }




    public int getNbr_Of_Neurones_DummyLayer() {
        return Nbr_Of_Neurones_DummyLayer;
    }

    public void setNbr_Of_Neurones_DummyLayer(int nbr_Of_Neurones_DummyLayer) {
        Nbr_Of_Neurones_DummyLayer = nbr_Of_Neurones_DummyLayer;
    }

    public int getNbr_Of_Neurones_HiddenLayer() {
        return Nbr_Of_Neurones_HiddenLayer;
    }

    public void setNbr_Of_Neurones_HiddenLayer(int nbr_Of_Neurones_HiddenLayer) {
        Nbr_Of_Neurones_HiddenLayer = nbr_Of_Neurones_HiddenLayer;
    }

    public int getNbr_Of_Neurones_OutputLayer() {
        return Nbr_Of_Neurones_OutputLayer;
    }

    public void setNbr_Of_Neurones_OutputLayer(int nbr_Of_Neurones_OutputLayer) {
        Nbr_Of_Neurones_OutputLayer = nbr_Of_Neurones_OutputLayer;
    }


}
