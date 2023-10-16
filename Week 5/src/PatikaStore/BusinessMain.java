package PatikaStore;

import java.util.Scanner;

public class BusinessMain {

    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        ProductType notebook = new Phone(128,14.2,6,4000,"Mavi",48);
        ProductType notebook2 = new Phone(64,17.2,12,5000,"Beyaz",78);
        Product p1 = new Product(0, notebook, "Galaxy S21", 8499.0, 20, 0);
        Product.addProduct(p1);
        Product p2 = new Product(7, notebook2, "Redmi Note 8", 6499.0, 20, 0);
        Product.addProduct(p2);

        Scanner sc = new Scanner(System.in);
        ProductType p = new ProductType();
        while(true) {
            System.out.println("PatikaStore Ürün Yönetim Paneli !\n1 - Ürünleri Göster\n2 - Ürün İşlemleri\n3 - Marka Listele\n0 - Çıkış Yap\n");
            System.out.print("Tercihiniz : ");
            int c = sc.nextInt();

            switch(c) {
                case 1:
                    System.out.println("Tüm Ürünleri Göster");
                    Product.printProducts();
                    break;
                case 2:
                    productManager();
                    break;
                case 3:
                    Brand.getBrandList();
                    break;
                case 0:
                    System.out.println("Çıkış yapıldı");
                    return;
            }
            System.out.println("-------------------------------\n");
        }

    }

    public static void productManager() {//Aslında ayrı bir class olarak yapılması lazım bu tür işlemlerin
        ProductType p=new ProductType();
        System.out.println("1 - Ürün Ekle\n2 - Ürün Sil\n");
        int selected = sc.nextInt();
        switch(selected) {
            case 1:
                System.out.println("ürün ekle");
                for(ProductType pp : p.getProductTypes()) {
                    System.out.println("("+pp.getId()+")"+" "+pp.getTypeName());
                }
                System.out.println("Eklemek istediğiniz ürün çeşidi id : ");//önce ProductType sonra Product
                int id = sc.nextInt();
                p = p.getProductTypes().get(id);
                p=p.printAddProduct();
                Product.pAddProduct(p);
                break;
            case 2:
                Product.printProducts();
                System.out.println("Silinecek ürün id : ");
                int i =sc.nextInt();
                Product.delete(i);

        }

    }
}
