package GUI;

import javax.swing.*;

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
            }else if(typePeceptron.equalsIgnoreCase("Perceptron mono-couche" ) && nbrclass > 0 && critereArret != null ){
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

    public void lauchALGO() {
        // ici qu'on initialise/lance l'algorythme

    }

    public JPanel getChartPanel() {
        return ChartPanel;
    }

    public void setChartPanel(JPanel chartPanel) {
        ChartPanel = chartPanel;
    }
}
