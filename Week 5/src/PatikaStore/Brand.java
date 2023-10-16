package PatikaStore;

import java.util.ArrayList;
import java.util.Collections;

public class Brand {
    private int id;
    private String brandName;
    private static ArrayList<Brand> brandList = new ArrayList<>();

    public Brand() {
    }
    static {
        addBrand("Samsung");
        addBrand("Lenovo");
        addBrand("Apple");
        addBrand("Huawei");
        addBrand("Casper");
        addBrand("Asus");
        addBrand("HP");
        addBrand("Xiaomi");
        addBrand("Monster");

    }
    public Brand(String brandName){
        this.id=brandList.size();
        this.brandName=brandName;
    }


    public static void getBrandList() {
        Collections.sort(brandList,new BrandComparator());
        System.out.println("Markalarımız\n-------------");
        for(Brand b : brandList) {
            System.out.println("("+b.getId()+")"+" "+b.getBrandName());
        }
        System.out.println();
    }
    public static int getBrandIdByName(String s) {
        //int id=-1;
        for(Brand b:brandList) {
            if(b.getBrandName().toLowerCase().equals(s.toLowerCase())) {
                return b.getId();
            }
        }
        return -1;
    }
    public static void addBrand(String brand) {
        brandList.add(new Brand(brand));
    }
    public static String getBrandById(int i) {
        return brandList.get(i).getBrandName();
    }

    public int getListSize() {
        return brandList.size();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

	/*@Override
	public int compareTo(Brand o) {
		return o.getBrandName().compareTo(o.getBrandName());
	}*/
}
