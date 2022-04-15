package PECEPTRON;

public class PerceptronMulticouches {

    private int Nbr_Of_Neurones_DummyLayer;
    private int Nbr_Of_Neurones_HiddenLayer;
    private int Nbr_Of_Neurones_OutputLayer;

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

    public void Perceptron(double[][] Input, double[][] Output)
    {

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
