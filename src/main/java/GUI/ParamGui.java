package GUI;

import CSVReader.CSVReader;
import PECEPTRON.PerceptronADALINE;
import PECEPTRON.PerceptronDG;
import PECEPTRON.PerceptronSimple;

import javax.swing.*;
import java.util.Arrays;

public class ParamGui {
    private String table ;
    private String algo ; // 2 choix
    private String typePeceptron ;
    private String critereArret ;
    private double valerreurdouble ;
    private  int valerreurint ;
    private double learningrate ;
    private  int maxiteration;
    private  int nbrclass ;
    private JPanel ChartPanel ;
    //private

    public ParamGui(){
        table = null ;
        algo = null ;
        critereArret = null ;
        typePeceptron = null ;
        valerreurdouble = 0 ;
        valerreurint = 0 ;
        learningrate = 0 ;
        maxiteration = 0 ;
    }

    public boolean CanBeLaunch(){
        if(typePeceptron.equalsIgnoreCase("Perceptron simple")) {
            return true ;
        }else if( (typePeceptron.equalsIgnoreCase("Perceptron DG") || typePeceptron.equalsIgnoreCase("Perceptron ADALINE")) && critereArret != null
        && learningrate > 0 && maxiteration > 2 ){
            return  true ;
        }else if(typePeceptron.equalsIgnoreCase("Perceptron mono-couche" ) && nbrclass > 0 && critereArret != null && learningrate > 0 && maxiteration > 2 ){
            return true ;
        }else
            return false ;


    }


    public boolean CanBeValidate(){
        System.out.println("type per ="+typePeceptron+" table"+table);
        if( table != null && typePeceptron != null ){ //   Perceptron DG    Perceptron ADALINE   Perceptron mono-couche
            if(typePeceptron.equalsIgnoreCase("Perceptron simple")) {
                return true;
            }else if( (typePeceptron.equalsIgnoreCase("Perceptron DG") || typePeceptron.equalsIgnoreCase("Perceptron ADALINE")) && critereArret != null ){
                return true ;
            }else if(typePeceptron.equalsIgnoreCase("Perceptron mono-couche" )  && critereArret != null ){
                return true ;
            }

            else
                return  false ;
        }
        else {
            return  false ;
        }

    }

    // getteur et setter
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getAlgo() {
        return algo;
    }

    public void setAlgo(String algo) {
        this.algo = algo;
    }

    public String getCritereArret() {
        return critereArret;
    }

    public void setCritereArret(String critereArret) {
        this.critereArret = critereArret;
    }

    public double getValerreurdouble() {
        return valerreurdouble;
    }

    public void setValerreurdouble(double valerreurdouble) {
        this.valerreurdouble = valerreurdouble;
    }

    public int getValerreurint() {
        return valerreurint;
    }

    public void setValerreurint(int valerreurint) {
        this.valerreurint = valerreurint;
    }

    public double getLearningrate() {
        return learningrate;
    }

    public void setLearningrate(double learningrate) {
        this.learningrate = learningrate;
    }

    public int getMaxiteration() {
        return maxiteration;
    }

    public void setMaxiteration(int maxiteration) {
        this.maxiteration = maxiteration;
    }

    public int getNbrclass() {
        return nbrclass;
    }

    public void setNbrclass(int nbrclass) {
        this.nbrclass = nbrclass;
    }

    public String getTypePeceptron() {
        return typePeceptron;
    }

    public void setTypePeceptron(String typePeceptron) {
        this.typePeceptron = typePeceptron;
    }

    public double[] lauchALGO() {
        try {
            // ici qu'on initialise/lance l'algorythme
            if (typePeceptron.equalsIgnoreCase("Perceptron simple")) {
                System.out.println("1. Perceptron Mise au point (table ET)");
                double[][] Input = {{1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}}; //Le premier 1 est l'entrée fictive
                double[] Output = {0, 0, 0, 1};

                PerceptronSimple p = new PerceptronSimple();
                return p.Perceptron(CSVReader.getInput(table),  CSVReader.getOutput(table));
        ///////fin perecptron simple
            } else if (typePeceptron.equalsIgnoreCase("Perceptron DG")) {
                if (table.equalsIgnoreCase("table_2_1.csv")) {
                    double[][] Input = {{1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}}; //Le premier 1 est l'entrée fictive
                    double[] Output = {-1, -1, -1, 1};

                    PerceptronDG p = new PerceptronDG();
                    return p.Perceptron(CSVReader.getInput(table), CSVReader.getOutput(table));
                } else {
                    if (valerreurint == 0 && valerreurdouble == 0.0) {
                        PerceptronDG p = new PerceptronDG(learningrate, maxiteration);
                        p.Classification(CSVReader.getInput(table), CSVReader.getOutput(table));
                    }else{
                        double[] Weight = new double[this.nbrclass];
                        Arrays.fill(Weight, 0);
                        PerceptronDG p = new PerceptronDG(Weight, learningrate, maxiteration, valerreurdouble);
                        p.Classification(CSVReader.getInput(table), CSVReader.getOutput(table));

                    }

                }
            }
        ///////fin perceptron dg
            else if (typePeceptron.equalsIgnoreCase("Perceptron ADALINE")) {
                if (table.equalsIgnoreCase("table_2_1.csv")) {
                    System.out.println("3. Perceptron ADALINE(table ET)");
                    double[][] Input = {{1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}}; //Le premier 1 est l'entrée fictive
                    double[] Output = {-1, -1, -1, 1};

                    PerceptronADALINE p = new PerceptronADALINE();
                    return p.Perceptron(CSVReader.getInput(table), CSVReader.getOutput(table));
                } else {
                    // adaline autre tables
                    System.out.println("5. Perceptron adaline classif. linea. sep.");
                    if (valerreurint == 0 && valerreurdouble == 0.0) {
                        PerceptronADALINE p = new PerceptronADALINE(learningrate, maxiteration);
                        return p.Classification(CSVReader.getInput(table), CSVReader.getOutput(table));
                    }else {
                        double[] Weight = new double[this.nbrclass];
                        Arrays.fill(Weight, 0);
                        PerceptronADALINE p = new PerceptronADALINE(Weight, learningrate, maxiteration, valerreurdouble);
                        p.Classification(CSVReader.getInput(table), CSVReader.getOutput(table));
                    }

                }


            }
        ////////fin peceptron adaline
            // todo monocouchee
            else if (typePeceptron.equalsIgnoreCase("Perceptron mono-couche")) {



            }
            // todo multicouche

        }catch (Exception e) {
            System.out.println("error " + e);
        }
        return null;
    }

    public JPanel getChartPanel() {
        return ChartPanel;
    }

    public void setChartPanel(JPanel chartPanel) {
        ChartPanel = chartPanel;
    }
}
