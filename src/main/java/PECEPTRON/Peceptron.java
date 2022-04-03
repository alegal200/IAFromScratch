package PECEPTRON;


import Exceptions.OutOfTableException;

import java.util.Arrays;

public class Peceptron {

    private double [] poidSynatique ; // all W
    private boolean [] entry ;
    private  boolean exit ;


    // CONSTRUCTOR
    // @PARAMS  num_entry  = numbers of real entry
     Peceptron(int num_entry) throws OutOfTableException {
         if ( num_entry <=0 )
             throw  new OutOfTableException("to create a peceptron the param value must higher than 0 but the value is"+num_entry) ;
        exit = false ;
        poidSynatique = new  double[num_entry+1] ;
        entry = new boolean[num_entry+1 ] ;

        poidSynatique[0] = 0 ;
        entry[0] = true ;

        for (int i = 1; i < num_entry; i++) {
           poidSynatique[i] = 0 ;           // it is better if we use gaussienne f(x) center on 0
           entry[i] = true ;
        }

    }
    ////////////// SETTER AND GETTER /////////////////////////////////


    //// ENTRY
    public boolean[] getEntry() {
         return entry;
    }

    public void setEntry(boolean[] entry) throws OutOfTableException {

         if( entry == null || entry.length != this.entry.length ) {

             throw new OutOfTableException("your tabel has the size of "+entry.length +" but the real value is "+(this.entry.length));
         }

         this.entry= entry;
         // block the entry[0] bc is always 1
         this.entry[0] = true ;

    }

    public boolean getEntry(int num ) throws OutOfTableException {
         if(  num > this.entry.length || num <0 )
            throw new OutOfTableException( num +"is not on the correct range : 0-"+entry.length);
         return  this.entry[num] ;
    }


    ///// EXIT
    public boolean isExit() { // somme of wheigh synaptics what tere is the correct entry
        double ex = 0.0 ;
        for (int i = 0; i < entry.length; i++) {
            if( entry[i])
                ex += poidSynatique[i] ;
        }
        return (ex > 0);
    }

    /*
    private void setExit(boolean exit) { // disabel bc it s useless
        this.exit = exit;
    }
    */
    ////////// POIDSYNAPTIQUE
    public double[] getPoidSynatique() {
        return poidSynatique;
    }

    public double getPoidSynatique(int pos) {
        return poidSynatique[pos];
    }

    public void setPoidSynatique(double[] poidSynatique) {
        this.poidSynatique = poidSynatique;
    }

    /////////////SETTER AND GETTER END //////////////////////////////////////


    @Override
    public String toString() {
        return "Peceptron{" + "poidSynatique=" + Arrays.toString(poidSynatique) +
                ", entry=" + Arrays.toString(entry) + ", exit=" + exit + '}';
    }




}
