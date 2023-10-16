package PatikaStore;

public class Phone extends ProductType{

    private int storage;
    private double screenSize;
    private int ram;
    private int battery;
    private String color;
    private int camera;

    public Phone(int storage, double screenSize, int ram, int battery, String color, int camera) {
        super("Cep Telefonu");
        this.storage = storage;
        this.screenSize = screenSize;
        this.ram = ram;
        this.battery = battery;
        this.color = color;
        this.camera = camera;
    }
    public Phone() {
        super("Cep Telefonu");
    }

    @Override
    public ProductType printAddProduct() {
        System.out.print("Hafıza : ");
        int storage = sc.nextInt();

        System.out.print("Ekran : ");
        double screenSize= sc.nextDouble();

        System.out.print("RAM : ");
        int ram= sc.nextInt();

        System.out.print("Batarya : ");
        int battery= sc.nextInt();

        System.out.print("Renk : ");
        String color= sc.next();

        System.out.print("Kamera : ");
        int camera= sc.nextInt();

        return new Phone(storage,screenSize,ram,battery,color,camera);
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
    public int getBattery() {
        return battery;
    }
    public void setBattery(int battery) {
        this.battery = battery;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getCamera() {
        return camera;
    }
    public void setCamera(int camera) {
        this.camera = camera;
    }
}
