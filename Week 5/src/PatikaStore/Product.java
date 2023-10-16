package PatikaStore;

import java.util.ArrayList;
import java.util.Scanner;

public class Product{
    private int productId;
    private int brandId;
    private ProductType productType;//Ürün Özellikleri
    private String productName;
    private double price;
    private int stock;
    private int discount;

    private static ArrayList<Product> productList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public Product(int brandId, ProductType productType, String productName, double price, int stock, int discount) {
        this.productId = productList.size();
        this.brandId = brandId;
        this.productType = productType;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.discount = discount;
    }

    public void addProduct(int brandId, ProductType productType, String productName, double price, int stock, int discount) {
        productList.add(new Product(brandId, productType, productName, price, stock,discount));
    }
    public static void addProduct(Product p) {
        productList.add(p);
    }
    public static void pAddProduct(ProductType pt) {
        Brand.getBrandList();
        System.out.println("Marka ismi : ");
        String brand = sc.nextLine();
        int brandId = Brand.getBrandIdByName(brand);

        System.out.println("Ürün İsmi : ");
        String productName = sc.nextLine();
        System.out.println("Fiyat : ");
        double price= sc.nextDouble();
        System.out.println("Stok : ");
        int stock = sc.nextInt();
        System.out.println("İndirim : ");
        int discount = sc.nextInt();

        productList.add(new Product(brandId, pt, productName, price, stock, discount));
        System.out.println("***************************************");
        for(Product p : productList) {
            System.out.println(p.getBrandId()+" "+p.getProductName()+" "+p.getProductType().getTypeName()+" "+p.getProductType());
        }

    }

    public static void printProducts() {
        System.out.println("Ürün sayısı : "+productList.size());
        for (Product p : productList) {
            System.out.println("("+p.getProductId()+") "+Brand.getBrandById(p.getBrandId())+" "+p.getProductName());

        }
    }
    public int getBrandId() {
        return brandId;
    }

    public static void delete(int i) {
        if(productList.contains(productList.get(i))) {
            productList.remove(i);
            System.out.println("Silme işlemi başarılı");
        }else {
            System.out.println("Hatalı id girdiniz!!");
        }
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

}