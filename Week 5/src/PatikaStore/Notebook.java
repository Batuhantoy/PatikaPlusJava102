package PatikaStore;


public class Notebook extends ProductType{

    private int storage;
    private double screenSize;
    private int ram;

    public Notebook(int storage, double screenSize, int ram) {
        super("Notebook");
        this.storage = storage;
        this.screenSize = screenSize;
        this.ram = ram;
    }


    public Notebook() {
        super("Notebook");
    }

    @Override
    public ProductType printAddProduct() {
        System.out.print("Hafıza : ");
        int storage = sc.nextInt();

        System.out.print("Ekran : ");
        double screenSize= sc.nextDouble();

        System.out.print("RAM : ");
        int ram= sc.nextInt();


        return new Notebook(storage,screenSize,ram);
    }


    public int getStorage() {
        return storage;
    }


    public void setStorage(int storage) {
        this.storage = storage;
    }


    public double getScreenSize() {
        return screenSize;
    }


    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }


    public int getRam() {
        return ram;
    }


    public void setRam(int ram) {
        this.ram = ram;
    }


}

