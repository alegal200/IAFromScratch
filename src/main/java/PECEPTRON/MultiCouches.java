package PECEPTRON;

import java.util.Arrays;

public class MultiCouches {

    private int nb_Entry;


    private int nb_Peceptrons_Fictive = 2;
    private int nb_Peceptrons_Cache = 2;
    private int nb_Peceptrons_Sortie = 1;


    private double learning_Rate;
    private int nb_inte_max;
    private double erreur_Quad_Thread;
    private double[][] entry;
    private double[][] exit;
    private double[][] weight_Cache;
    private double[][] weight_Exit;
    private int numLigneEntry;

    public MultiCouches(int nb_Entry, int nb_Peceptrons_Cahe, int nb_Peceptrons_Sortie, double learning_Rate, int nb_inte_max, double erreur_Quad_Thread) {
        setNb_Entry(nb_Entry);
        setErreur_Quad_Thread(erreur_Quad_Thread);
        setLearning_Rate(learning_Rate);
        setNb_inte_max(nb_inte_max);
        setNb_Peceptrons_Cahe(nb_Peceptrons_Cahe);
        setNb_Peceptrons_Sortie(nb_Peceptrons_Sortie);
        // numLigneEntry =0 ;
    }


    public void PeceptronStocha() {

        double entry[][] = {{1, 0, 0},
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 1}};
        double outputExpected[] = {0,
                1,
                1,
                0};

        double poid_Cache[][] = {{0, -0.5088670970235333, -0.6012675831264768},
                {0, -0.08196000568575071, -0.28808720192743303}};
/*
        double poid_Cache[][] = {{0, 0, 0},
                {0, 0, 0}};
*/


        double poid_Sortie[] = {0,0.4559320104974871, -0.537300823988438};
        //double poid_Sortie[] = {0,0, 0};

        int CurrentCompleteIteration = 1;
        double AVG_ERROR = 0;
        double ERROR_ITER_COMP =0;


        do {
            System.out.println("CurrentCompleteIteration : " + CurrentCompleteIteration); //Affiche l'iterationComplete actuel
            AVG_ERROR = 0; //Reinitialisation de l'erreur quadratique moyenne pour l'iterationComplete
            ERROR_ITER_COMP=0;
            double[][] tab_sorties_couche_sortie = new double[entry.length][nb_Peceptrons_Sortie]; //On doit stocker les sortie de chaque neur sortie pour chaque iteration

            for (int k = 0; k < entry.length; k++) {
                //1.Propagation des données d'entrée (x1, x2, ..., xE) à travers le réseau :
                //1.a) Calcul des potentiels kc et sortie yc de chaque neurone de la couche caché
                double[] potentiel_Cache = {0, 0};

                for (int j = 0; j < potentiel_Cache.length; j++) { //2
                    for (int i = 0; i < entry[k].length; i++) { //3
                        potentiel_Cache[j] = potentiel_Cache[j] + (poid_Cache[j][i] * entry[k][i]);
                    }
                    System.out.println("  k" + k + " potentiel_Cache[" + j + "]:" + potentiel_Cache[j]);
                }


                double sortie_Cache[] = {0, 0};
                for (int i = 0; i < sortie_Cache.length; i++) {
                    sortie_Cache[i] = 1 / (1 + Math.pow(Math.E, -potentiel_Cache[i]));
                    System.out.println("  k" + k + " Sortie_Cache" + i + ":" + sortie_Cache[i]);
                }

                //1.b) Calcul des potentiels ps et sories zs de chaque neurone de la couche de sortie (fonction logistique comme fonction d'activation)
                //Attention pour le calcul potentiel_Sortie on a besoin des sortie de la caché qui sont les entré de la couche srtoe (ATTENTION il faut ajouter entré fictive)
                double[] entre_couche_sortie = new double[sortie_Cache.length + 1];
                entre_couche_sortie[0] = 1; //Entre fictive
                for (int i = 1; i < entre_couche_sortie.length; i++) { //Remplissage de entre_couche_sortie avec les sortie de la couche cache
                    entre_couche_sortie[i] = sortie_Cache[i - 1];
                }

/*
                for(int i = 0; i < entre_couche_sortie.length; i++){
                    System.out.println("ARRAY"+i+" "+entre_couche_sortie[i]);
                }
*/
                double[] potentiel_Sortie = {0}; //1seul neur sortie
                for (int i = 0; i < potentiel_Sortie.length; i++) { //1

                    for (int j = 0; j < entre_couche_sortie.length; j++) { //3
                        potentiel_Sortie[i] = potentiel_Sortie[i] + (poid_Sortie[j] * entre_couche_sortie[j]);
                    }
                    System.out.println("  k" + k + " potentiel_Sortie[" + i + "]:" + potentiel_Sortie[i]);
                }

                double[] sorties_couche_sortie = {0}; //1seul neur sortie
                for (int i = 0; i < nb_Peceptrons_Sortie; i++) { //1
                    sorties_couche_sortie[i] = 1 / (1 + Math.pow(Math.E, -1*potentiel_Sortie[i]));
                    tab_sorties_couche_sortie[k][i]=sorties_couche_sortie[i];
                    System.out.println("  k" + k + " sorties_couche_sortie[" + i + "]:" + sorties_couche_sortie[i]);
                }

                //Calcul de l'erreur pour l'exemple en cours
                double ERROR = 0;
                for (int i = 0; i < nb_Peceptrons_Sortie; i++) { //1 , on calcul l'erreur pour l'iteration (on peut avoir plusieurs sorties)
                    ERROR = ERROR + Math.pow(outputExpected[k] - sorties_couche_sortie[i], 2);
                }
                ERROR = ERROR / 2;
                System.out.println("  k" + k + " ERROR commise:" + ERROR);
                ERROR_ITER_COMP = ERROR_ITER_COMP + ERROR;

                //Calcul signal erreur couche sortie
                double[] signal_err_sortie = {0};
                for (int i = 0; i < nb_Peceptrons_Sortie; i++) { //1
                    signal_err_sortie[i] = (outputExpected[k] - sorties_couche_sortie[i]) * sorties_couche_sortie[i] * (1 - sorties_couche_sortie[i]);
                    System.out.println("  k" + k + " signal_err_sortie[" + i + "]:" + signal_err_sortie[i]);
                }
                //Calcul signal erreur couche cachee
                double[] signal_err_cache = {0, 0};
                for (int i = 0; i < nb_Peceptrons_Cache; i++) { //2
                    signal_err_cache[i] = sortie_Cache[i] * (1 - sortie_Cache[i]);
                    System.out.println("  k" + k + " signal_err_cache[" + i + "]:" + signal_err_cache[i]);
                }

                //Calcul signal erreur cumule couche cachee
                double[] signal_err_cumul_cache = {0, 0};
                for (int i = 0; i < nb_Peceptrons_Cache; i++) { //2
                    signal_err_cumul_cache[i] = signal_err_cache[i] * (signal_err_sortie[0] * poid_Sortie[i +1]);
                    System.out.println("  k" + k + " signal_err_cumul_cache[" + i + "]:" + signal_err_cumul_cache[i]);
                }

                //Correction poid couche sortie
                for (int i = 0; i < poid_Sortie.length; i++) { //3 un simple tableau sinon comme le point suivant
                    //Devrait etre bouclé le nbr de fois qu'on neur sortie mais comme juste 1 pas boucle
                    poid_Sortie[i] = poid_Sortie[i] + (getLearning_Rate() * (signal_err_sortie[0]) * entre_couche_sortie[i]); //entre_couche_sortie[0] vaut 1
                    System.out.println("  k" + k + " poid_Sortie[" + i + "]:" + poid_Sortie[i]);
                }

                //Correction poid couche cache
                for (int i = 0; i < nb_Peceptrons_Cache; i++) { //2
                    for (int j = 0; j < poid_Cache[i].length; j++) { //3
                        poid_Cache[i][j] = poid_Cache[i][j] + (getLearning_Rate() * (signal_err_cumul_cache[i]) * entry[k][j]); //entre_couche_sortie[0] vaut 1

                    }
                }
                System.out.println("  k" + k + " poid_Cache:" + Arrays.deepToString(poid_Cache));


            }

            //Calcul de l'erreur quadratique moyenne
            AVG_ERROR = ERROR_ITER_COMP/(outputExpected.length);
            System.out.println("  Fin iteration" + CurrentCompleteIteration + " erreurQuadMoyenne:" + AVG_ERROR);


            CurrentCompleteIteration++;
        } while (AVG_ERROR > erreur_Quad_Thread && CurrentCompleteIteration < nb_inte_max); //Arret si Erreur quad moy inferieur a un certain seuil ou si on depasse le nbr max d'iteration


    }


    public void PeceptronFullBack() {

        double entry[][] = {{1, 0, 0},
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 1}};
        double outputExpected[] = {0,
                1,
                1,
                0};

        double poid_Cache[][] = {{0, 0.15, 0.05},
                {0, 0.18, 0.08}};
        double d_poid_Cache[][] = {{0, 0, 0},
                {0, 0, 0}};

        double poid_Sortie[] = {0, 0.1, 0.14};
        double d_poid_Sortie[] = {0, 0, 0};

        int CurrentCompleteIteration = 1;
        double AVG_ERROR = 0;

        do {
            System.out.println("CurrentCompleteIteration : " + CurrentCompleteIteration); //Affiche l'iterationComplete actuel

            double[][] tab_sorties_couche_sortie = new double[entry.length][nb_Peceptrons_Sortie]; //On doit stocker les sortie de chaque neur sortie pour chaque iteration
            AVG_ERROR = 0; //Reinitialisation de l'erreur quadratique moyenne pour l'iterationComplete

            //Réinitialise d_Weights cache pour l'iterationComplete
            for (double[] row : d_poid_Cache)
                Arrays.fill(row, 0);

            Arrays.fill(d_poid_Sortie, 0); //Réinitialise d_Weights sortie pour l'iterationComplete


            for (int k = 0; k < entry.length; k++) {
                //1.Propagation des données d'entrée (x1, x2, ..., xE) à travers le réseau :
                //1.a) Calcul des potentiels kc et sortie yc de chaque neurone de la couche caché
                double[] potentiel_Cache = {0, 0};


                for (int j = 0; j < potentiel_Cache.length; j++) { //2
                    for (int i = 0; i < entry[k].length; i++) { //3
                        potentiel_Cache[j] = potentiel_Cache[j] + (poid_Cache[j][i] * entry[k][i]);
                    }
                    System.out.println("  k" + k + " potentiel_Cache[" + j + "]:" + potentiel_Cache[j]);
                }


                double sortie_Cache[] = {0, 0};
                for (int i = 0; i < sortie_Cache.length; i++) {
                    sortie_Cache[i] = 1 / (1 + Math.pow(Math.E, -potentiel_Cache[i]));
                    System.out.println("  k" + k + " Sortie_Cache" + i + ":" + sortie_Cache[i]);
                }

                //1.b) Calcul des potentiels ps et sories zs de chaque neurone de la couche de sortie (fonction logistique comme fonction d'activation)
                //Attention pour le calcul potentiel_Sortie on a besoin des sortie de la caché qui sont les entré de la couche srtoe (ATTENTION il faut ajouter entré fictive)
                double[] entre_couche_sortie = new double[sortie_Cache.length + 1];
                entre_couche_sortie[0] = 1; //Entre fictive
                for (int i = 1; i < entre_couche_sortie.length; i++) { //Remplissage de entre_couche_sortie avec les sortie de la couche cache
                    entre_couche_sortie[i] = sortie_Cache[i - 1];
                }


                double[] potentiel_Sortie = {0}; //1seul neur sortie
                for (int i = 0; i < potentiel_Sortie.length; i++) { //1

                    for (int j = 0; j < entre_couche_sortie.length; j++) { //3
                        potentiel_Sortie[i] = potentiel_Sortie[i] + (poid_Sortie[j] * entre_couche_sortie[j]);
                    }
                    System.out.println("  k" + k + " potentiel_Sortie[" + i + "]:" + potentiel_Cache[i]);
                }

                double[] sorties_couche_sortie = {0}; //1seul neur sortie
                for (int i = 0; i < nb_Peceptrons_Sortie; i++) { //1
                    sorties_couche_sortie[i] = 1 / (1 + Math.pow(Math.E, -potentiel_Sortie[i]));
                    tab_sorties_couche_sortie[k][i] = sorties_couche_sortie[i];
                    System.out.println("  k" + k + " sorties_couche_sortie[" + i + "]:" + sorties_couche_sortie[i]);
                }

                //Calcul signaux err etc mais tout pour les d_weight
                //Pas besoin calculer erreur commise (reviens a calculer nbr d erreur ds DG)

                //Calcul signal erreur couche sortie
                double[] signal_err_sortie = {0};
                for (int i = 0; i < nb_Peceptrons_Sortie; i++) { //1
                    signal_err_sortie[i] = (outputExpected[k] - sorties_couche_sortie[i]) * sorties_couche_sortie[i] * (1 - sorties_couche_sortie[i]);
                    System.out.println("  k" + k + " signal_err_sortie[" + i + "]:" + signal_err_sortie[i]);
                }
                //Calcul signal erreur couche cachee
                double[] signal_err_cache = {0, 0};
                for (int i = 0; i < nb_Peceptrons_Cache; i++) { //2
                    signal_err_cache[i] = sortie_Cache[i] * (1 - sortie_Cache[i]);
                    System.out.println("  k" + k + " signal_err_cache[" + i + "]:" + signal_err_cache[i]);
                }

                //Calcul signal erreur cumule couche cachee
                double[] signal_err_cumul_cache = {0, 0};
                for (int i = 0; i < nb_Peceptrons_Cache; i++) { //2
                    signal_err_cumul_cache[i] = signal_err_cache[i] * (signal_err_sortie[0] * poid_Sortie[i +1]);
                    System.out.println("  k" + k + " signal_err_cumul_cache[" + i + "]:" + signal_err_cumul_cache[i]);
                }

                //Correction poid couche sortie
                for (int i = 0; i < d_poid_Sortie.length; i++) { //3 un simple tableau sinon comme le point suivant
                    //Devrait etre bouclé le nbr de fois qu'on neur sortie mais comme juste 1 pas boucle
                    d_poid_Sortie[i] = d_poid_Sortie[i] + (getLearning_Rate() * (signal_err_sortie[0]) * entre_couche_sortie[i]); //entre_couche_sortie[0] vaut 1
                    System.out.println("  k" + k + " d_poid_Sortie[" + i + "]:" + d_poid_Sortie[i]);
                }


                //Correction poid couche cache
                for (int i = 0; i < nb_Peceptrons_Cache; i++) { //2
                    for (int j = 0; j < d_poid_Cache[i].length; j++) { //3
                        d_poid_Cache[i][j] = d_poid_Cache[i][j] + (getLearning_Rate() * (signal_err_cumul_cache[i]) * entry[k][j]); //entre_couche_sortie[0] vaut 1

                    }
                }
                System.out.println("  k" + k + " d_poid_Cache:" + Arrays.deepToString(d_poid_Cache));


            }

            //Modification des vrais poids
            //Correction poid couche sortie
            for (int i = 0; i < poid_Sortie.length; i++) { //3 un simple tableau sinon comme le point suivant
                //Devrait etre bouclé le nbr de fois qu'on neur sortie mais comme juste 1 pas boucle
                poid_Sortie[i] = poid_Sortie[i] + d_poid_Sortie[i];
                System.out.println("  Fin iteration" + CurrentCompleteIteration + " nouveau poid_Sortie[" + i + "]:" + poid_Sortie[i]);
            }

            //Correction poid couche cache
            for (int i = 0; i < nb_Peceptrons_Cache; i++) { //2
                for (int j = 0; j < poid_Cache[i].length; j++) { //3
                    poid_Cache[i][j] = poid_Cache[i][j] + d_poid_Cache[i][j];
                }
            }
            System.out.println("  Fin iteration" + CurrentCompleteIteration + " nouveau poid_Cache:" + Arrays.deepToString(d_poid_Cache));

            //Err quad moy
            //Calcul de l'erreur quadratique moyenne
            for (int i = 0; i < outputExpected.length; i++) {
                AVG_ERROR = AVG_ERROR + Math.pow(outputExpected[i] - tab_sorties_couche_sortie[i][0], 2); //tab_sorties_couche_sortie[i][0] car on a que un output pour un neurone couche sortie
            }
            AVG_ERROR = AVG_ERROR / (2 * (outputExpected.length));
            System.out.println("  Fin iteration" + CurrentCompleteIteration + " erreurQuadMoyenne:" + AVG_ERROR);


            CurrentCompleteIteration++;
        } while (AVG_ERROR > erreur_Quad_Thread && CurrentCompleteIteration < nb_inte_max); //Arret si Erreur quad moy inferieur a un certain seuil ou si on depasse le nbr max d'iteration


    }

    public int getNb_Entry() {
        return nb_Entry;
    }

    public void setNb_Entry(int nb_Entry) {
        this.nb_Entry = nb_Entry;
    }

    public int getNb_Peceptrons_Cahe() {
        return nb_Peceptrons_Cache;
    }

    public void setNb_Peceptrons_Cahe(int nb_Peceptrons_Cahe) {
        this.nb_Peceptrons_Cache = nb_Peceptrons_Cahe;
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
