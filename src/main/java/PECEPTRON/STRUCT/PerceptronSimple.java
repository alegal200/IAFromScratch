package PECEPTRON.STRUCT;

public class PerceptronSimple {

    private int [] x_entre;
    private double [] w_poidSynaptique;
    private double seuil;
    private double potentiel;
    private int sortie;
    private double tauxApprentissage = 1;

    public PerceptronSimple()
    {
    }

    public double calculePotentiel()
    {
        double buff =0;
        for(int i=0 ; i<x_entre.length ; i++ )
        {
            buff = buff + (x_entre[i]*w_poidSynaptique[i]);
        }
        setPotentiel(buff);

        return getPotentiel();
    }

    public double calculeSortie()
    {
        double buffSortie = calculePotentiel();
        if(buffSortie > 0)
        {
            sortie = 1;
            return sortie;
        }else if(buffSortie < 0)
        {
            sortie = -1;
            return sortie;
        }else
        {
            sortie = 0;
            return sortie;
        }
    }

    public void ajustement()
    {
        for (int i =0 ; i < w_poidSynaptique.length ;i++)
        {
            w_poidSynaptique[i]=w_poidSynaptique + tauxApprentissage*()*x_entre[i];
        }



    }


    public double getPotentiel() {
        return potentiel;
    }

    public void setPotentiel(double potentiel) {
        this.potentiel = potentiel;
    }
}
