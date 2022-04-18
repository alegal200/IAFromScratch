package PECEPTRON;

import java.util.Arrays;

public class PerceptronMulticouches {
    // nb couchée entree
    // bb cachée
    // nb sortie
    // learning rate
    // entre
    // sortie souhaitée
    // nombre interation max
    // erreur quadr threshold
    // poids
    private int nb_Entry;
    private int nb_Peceptrons_Cahe;
    private int nb_Peceptrons_Sortie;
    private double learning_Rate ;
    private int nb_inte_max ;
    private double erreur_Quad_Thread ;
    private double[] []entry ;
    private double[] [] exit ;
    private double[][] weight_Cache ;
    private double[][] weight_Exit ;
    private int numLigneEntry ;

    public PerceptronMulticouches(int nb_Entry , int nb_Peceptrons_Cahe , int nb_Peceptrons_Sortie, double learning_Rate , int nb_inte_max , double erreur_Quad_Thread){
        setNb_Entry(nb_Entry);
        setErreur_Quad_Thread(erreur_Quad_Thread);
        setLearning_Rate(learning_Rate);
        setNb_inte_max(nb_inte_max);
        setNb_Peceptrons_Cahe(nb_Peceptrons_Cahe);
        setNb_Peceptrons_Sortie(nb_Peceptrons_Sortie);
        numLigneEntry =0 ;
    }
  //  public void Peceptron(double[][] en , double[][] ex ){peceptron(en , ex , null , null);}
    public void Peceptron(double[][] eentry , double[][] eexit ,double[][] wweight_Cache , double[][] wweight_Exit ) {
        entry = eentry;
        exit = eexit;
        weight_Cache = wweight_Cache;
        weight_Exit = wweight_Exit;

        int currentCompleteIteration = 1;
        double AVG_ERROR = 0.0;

         do {
             for (int numLigneEntry = 0; numLigneEntry < entry[0].length; numLigneEntry++) {


                 System.out.println("************************************tour n: " + currentCompleteIteration+"**************");
                 double potentiel_C[] = new double[nb_Peceptrons_Cahe];
                 double sortie_C[] = new double[nb_Peceptrons_Cahe + 1];
                 sortie_C[0] = 1;
                 //1.Propagation des données d'entrée (x1, x2, ..., xE) à travers le réseau :
                 //1.a) Calcul des potentiels kc et sortie yc de chaque neurone de la couche caché
                 sortie_C[0]=1;
                 for (int j = 0; j <= potentiel_C.length - 1; j++) {
                     for (int i = 0; i <= entry.length - 1; i++) {
                         potentiel_C[j] += weight_Cache[j][i] * entry[i][numLigneEntry];//
                     }
                     System.out.println("potentiel_C" + j + " " + potentiel_C[j]);
                     sortie_C[j + 1] = 1 / (1 + Math.pow(Math.E, -potentiel_C[j]));
                     System.out.println("sortie_c" + j + " " + sortie_C[j]);
                 }

                 ////1.b) Calcul des potentiels ps et sories zs de chaque neurone de la couche de sortie (fonction logistique comme fonction d'activation)
                 double potentiel_S[] = new double[nb_Peceptrons_Sortie];
                 double sortie_S[] = new double[nb_Peceptrons_Sortie];
                 for (int j = 0; j < potentiel_S.length; j++) {

                     for (int i = 0; i < weight_Exit.length; i++) {
                         potentiel_S[j] += weight_Exit[j][i] * sortie_C[i];

                     }
                     System.out.println("potentiel_S" + j + " " + potentiel_S[j]);
                     sortie_S[j] = 1 / (1 + Math.pow(Math.E, -potentiel_S[j]));
                     System.out.println("sortie_S" + j + " " + sortie_S[j]);
                 }
                 //2 Calcul de l'erreur et retropropagation de l'erreur a travers le reseau
                 //Calcul de l'erreur quadratique moyenne
                 AVG_ERROR = 0.0;
                 for (int i = 0; i < exit.length; i++) {
                     AVG_ERROR = AVG_ERROR + Math.pow(exit[i][numLigneEntry] - sortie_S[i], 2);
                 }
                 AVG_ERROR = AVG_ERROR / 2;
                 System.out.println("AVG_ERROR:" + AVG_ERROR); //Vaut 0.226
                 if (AVG_ERROR < erreur_Quad_Thread)
                     break;

                 ////2.a  Calcul des signaux d erreur de chaque neurone de la couche de sortie
                 double[] signal_erreur_S = new double[sortie_S.length];
                 for (int i = 0; i < sortie_S.length; i++) {
                     signal_erreur_S[i] = (exit[i][numLigneEntry] - sortie_S[i]) * sortie_S[i] * (1 - sortie_S[i]);
                     System.out.println("signal_erreur_S[" + i + "]" + signal_erreur_S[i]);
                 }

                 //2.b  Calcul des signaux d erreur de chaque neurone de la couche caché
                 double signal_erreur_C[] = new double[sortie_C.length];
                 for (int i = 0; i < sortie_C.length; i++) {
                     signal_erreur_C[i] = sortie_C[i] * (1 - sortie_C[i]);
                     System.out.println("signal_erreur_C:" + i + " " + signal_erreur_C[i]);
                 }
                 ///////
                 double signal_erreur_c_cumule[] = new double[signal_erreur_C.length];
                 for (int i = 0; i < signal_erreur_C.length; i++) {  // par sigal erreur caché
                     for (int j = 0; j < signal_erreur_S.length; j++) { // par signal erreur sortie

                         signal_erreur_c_cumule[i] += signal_erreur_C[i] * signal_erreur_S[j] * weight_Exit[j][i];
                         //    System.out.println("cal"+signal_erreur_C[i]+" * "+ signal_erreur_S[j]+" * "+weight_Exit[j][i] );

                     }

                     System.out.println("signal_erreur_c1_cumule [" + i + "] " + signal_erreur_c_cumule[i]);
                 }
                 // 3
                 // 3.a correction des poids synaptiques de la couche de sortie


                 for (int j = 0; j < signal_erreur_S.length; j++) {
                     for (int i = 0; i < sortie_C.length; i++) {

                       // System.out.println("weight_Exit.length"+weight_Exit.length+" sortie_C.length"+sortie_C.length);
                         weight_Exit[j][i] += learning_Rate * signal_erreur_S[j] * sortie_C[i];
                         System.out.print(" S" + j + "weights[" + i + "] " + weight_Exit[j][i]);
                     }

                 }
                 System.out.println(" ");

                 // 3.b correction des poids de la couche cachée
                 for (int i = 0; i < signal_erreur_c_cumule.length- 1; i++) {//2
                     for (int j = 0; j < weight_Cache.length ; j++) {
                        // System.out.println("weight_Cache.length "+weight_Cache.length +" signal_erreur_c_cumule.length "+signal_erreur_c_cumule.length);
                        // System.out.println("cal :"+ weight_Cache[i][j] +"+"+ learning_Rate+"*"+ signal_erreur_c_cumule[i+1]+"*"+entry[j][numLigneEntry]);
                         weight_Cache[i][j] += learning_Rate * signal_erreur_c_cumule[i + 1] * entry[j][numLigneEntry];
                         System.out.print(" weight_Cache[" + i + "][" + j + "] " + weight_Cache[i][j]);
                     }
                     System.out.println(" ");
                 }

                 currentCompleteIteration++;
             }
             if (AVG_ERROR < erreur_Quad_Thread)
                 break;
        } while (currentCompleteIteration < nb_inte_max); // true si
        System.out.println("fin de l algo ");


    }

    public void PeceptronFullBack(double[][] eentry , double[][] eexit , double[][] wweight_Cache , double[][] wweight_Exit ){

        entry = eentry;
        exit = eexit;
        weight_Cache = wweight_Cache;
        weight_Exit = wweight_Exit;

        int currentCompleteIteration = 1;
        double AVG_ERROR = 0.0;
        double tmp_Weight_Cache[][] = weight_Cache ;
        double tmp_Weight_Exit[][] = weight_Exit ;
        double tmp_sortie_S[][] = new double[entry[0].length+1][nb_Peceptrons_Sortie];
        do {

            for (int i = 0; i < tmp_Weight_Cache.length; i++) {
                Arrays.fill(tmp_Weight_Cache[i], 0);
            }
            for (int i = 0; i < weight_Exit.length; i++) {
                Arrays.fill(tmp_Weight_Exit[i],0);
            }

            for (int numLigneEntry = 0; numLigneEntry < entry[0].length; numLigneEntry++) {


                System.out.println("************************************tour n: " + currentCompleteIteration+"**************");
                double potentiel_C[] = new double[nb_Peceptrons_Cahe];
                double sortie_C[] = new double[nb_Peceptrons_Cahe + 1];
                sortie_C[0] = 1;
                //1.Propagation des données d'entrée (x1, x2, ..., xE) à travers le réseau :
                //1.a) Calcul des potentiels kc et sortie yc de chaque neurone de la couche caché
                sortie_C[0]=1;
                for (int j = 0; j <= potentiel_C.length - 1; j++) {
                    for (int i = 0; i <= entry.length - 1; i++) {
                        potentiel_C[j] += weight_Cache[j][i] * entry[i][numLigneEntry];//
                    }
                    System.out.println("potentiel_C" + j + " " + potentiel_C[j]);
                    sortie_C[j + 1] = 1 / (1 + Math.pow(Math.E, -potentiel_C[j]));
                    System.out.println("sortie_c" + j + " " + sortie_C[j]);
                }

                ////1.b) Calcul des potentiels ps et sories zs de chaque neurone de la couche de sortie (fonction logistique comme fonction d'activation)
                double potentiel_S[] = new double[nb_Peceptrons_Sortie];
                double sortie_S[] = new double[nb_Peceptrons_Sortie];
                for (int j = 0; j < potentiel_S.length; j++) {

                    for (int i = 0; i < weight_Exit.length; i++) {
                        potentiel_S[j] += weight_Exit[j][i] * sortie_C[i];

                    }
                    System.out.println("potentiel_S" + j + " " + potentiel_S[j]);
                    sortie_S[j] = 1 / (1 + Math.pow(Math.E, -potentiel_S[j]));
                    tmp_sortie_S[numLigneEntry][j] = sortie_S[j];
                    System.out.println("sortie_S" + j + " " + sortie_S[j]);
                }
                //2 Calcul de l'erreur et retropropagation de l'erreur a travers le reseau




                ////2.a  Calcul des signaux d erreur de chaque neurone de la couche de sortie
                double[] signal_erreur_S = new double[sortie_S.length];
                for (int i = 0; i < sortie_S.length; i++) {
                    signal_erreur_S[i] = (exit[i][numLigneEntry] - sortie_S[i]) * sortie_S[i] * (1 - sortie_S[i]);
                    System.out.println("signal_erreur_S[" + i + "]" + signal_erreur_S[i]);
                }

                //2.b  Calcul des signaux d erreur de chaque neurone de la couche caché
                double signal_erreur_C[] = new double[sortie_C.length];
                for (int i = 0; i < sortie_C.length; i++) {
                    signal_erreur_C[i] = sortie_C[i] * (1 - sortie_C[i]);
                    System.out.println("signal_erreur_C:" + i + " " + signal_erreur_C[i]);
                }
                ///////
                double signal_erreur_c_cumule[] = new double[signal_erreur_C.length];
                for (int i = 0; i < signal_erreur_C.length; i++) {  // par sigal erreur caché
                    for (int j = 0; j < signal_erreur_S.length; j++) { // par signal erreur sortie

                        signal_erreur_c_cumule[i] += signal_erreur_C[i] * signal_erreur_S[j] * weight_Exit[j][i];
                        //    System.out.println("cal"+signal_erreur_C[i]+" * "+ signal_erreur_S[j]+" * "+weight_Exit[j][i] );

                    }

                    System.out.println("signal_erreur_c1_cumule [" + i + "] " + signal_erreur_c_cumule[i]);
                }
                // 3
                // 3.a correction des poids synaptiques de la couche de sortie


                for (int j = 0; j < signal_erreur_S.length; j++) {
                    for (int i = 0; i < sortie_C.length; i++) {

                        // System.out.println("weight_Exit.length"+weight_Exit.length+" sortie_C.length"+sortie_C.length);

                        tmp_Weight_Exit[j][i] += learning_Rate * signal_erreur_S[j] * sortie_C[i];
                        System.out.print(" S" + j + "weights[" + i + "] " + tmp_Weight_Exit[j][i]);
                    }

                }
                System.out.println(" ");

                // 3.b correction des poids de la couche cachée
                for (int i = 0; i < signal_erreur_c_cumule.length- 1; i++) {//2
                    for (int j = 0; j < weight_Cache.length ; j++) {
                        // System.out.println("weight_Cache.length "+weight_Cache.length +" signal_erreur_c_cumule.length "+signal_erreur_c_cumule.length);
                        // System.out.println("cal :"+ weight_Cache[i][j] +"+"+ learning_Rate+"*"+ signal_erreur_c_cumule[i+1]+"*"+entry[j][numLigneEntry]);

                       tmp_Weight_Cache[i][j] += learning_Rate * signal_erreur_c_cumule[i + 1] * entry[j][numLigneEntry];
                        System.out.print(" weight_Cache[" + i + "][" + j + "] " + tmp_Weight_Cache[i][j]);
                    }
                    System.out.println(" ");
                }

                currentCompleteIteration++;
            }

            // boucle calcul weight exit
            for (int i = 0; i < (nb_Peceptrons_Sortie); i++) {
                for (int j = 0; j <(nb_Peceptrons_Cahe + 1) ; j++) { // equiv sortic.length
                    weight_Exit[i][j] += tmp_Weight_Exit[i][j] ;
                }
            }
            // boucle calcul weight cache
            for (int i = 0; i < (nb_Peceptrons_Cahe ) ; i++) {
                for (int j = 0; j < weight_Cache.length; j++) {
                    weight_Cache[i][j] += tmp_Weight_Cache[i][j] ;
                }
            }

            /// calcul erreur
            AVG_ERROR = 0.0;
            //Calcul de l'erreur quadratique moyenne
            for (int numL = 0; numL < exit[0].length; numL++) {
                for (int i = 0; i < exit.length; i++) {
                   
                    AVG_ERROR += Math.pow(exit[i][numL] - tmp_sortie_S[numL][i], 2);
                }
            }

            AVG_ERROR = AVG_ERROR / 2 / nb_Peceptrons_Sortie+1;
            System.out.println("AVG_ERROR:" + AVG_ERROR); //Vaut 0.226


            if (AVG_ERROR < erreur_Quad_Thread)
                break;
        } while (currentCompleteIteration < nb_inte_max); // true si
        System.out.println("fin de l algo ");


    }

    /////////////////////////////////////setter + getter ///////////////////////////////////////////////////////
    public int getNb_Entry() {
        return nb_Entry;
    }

    public void setNb_Entry(int nb_Entry) {
        this.nb_Entry = nb_Entry;
    }

    public int getNb_Peceptrons_Cahe() {
        return nb_Peceptrons_Cahe;
    }

    public void setNb_Peceptrons_Cahe(int nb_Peceptrons_Cahe) {
        this.nb_Peceptrons_Cahe = nb_Peceptrons_Cahe;
    }

    public int getNb_Peceptrons_Sortie() {
        return nb_Peceptrons_Sortie;
    }

    public void setNb_Peceptrons_Sortie(int nb_Peceptrons_Sortie) {
        this.nb_Peceptrons_Sortie = nb_Peceptrons_Sortie;
    }

    public double getLearning_Rate() {
        return learning_Rate;
    }

    public void setLearning_Rate(double learning_Rate) {
        this.learning_Rate = learning_Rate;
    }

    public int getNb_inte_max() {
        return nb_inte_max;
    }

    public void setNb_inte_max(int nb_inte_max) {
        this.nb_inte_max = nb_inte_max;
    }

    public double getErreur_Quad_Thread() {
        return erreur_Quad_Thread;
    }

    public void setErreur_Quad_Thread(double erreur_Quad_Thread) {
        this.erreur_Quad_Thread = erreur_Quad_Thread;
    }

    public double[][] getEntry() {
        return entry;
    }

    public void setEntry(double[][] entry) {
        this.entry = entry;
    }

    public double[][] getExit() {
        return exit;
    }

    public void setExit(double[][] exit) {
        this.exit = exit;
    }

    public double[][] getWeight_Cache() {
        return weight_Cache;
    }

    public void setWeight_Cache(double[][] weight_Cache) {
        this.weight_Cache = weight_Cache;
    }

    public double[][] getWeight_Exit() {
        return weight_Exit;
    }

    public void setWeight_Exit(double[][] weight_Exit) {
        this.weight_Exit = weight_Exit;
    }
}
