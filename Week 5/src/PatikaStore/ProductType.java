package PatikaStore;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductType {
    private int id;
    private String typeName;
    private static ArrayList<ProductType> productTypes = new ArrayList<>();
    public Scanner sc = new Scanner(System.in);


    static{
        productTypes.add(new Notebook());
        productTypes.add(new Phone());
    }

    public ProductType(String typeName){
        this.id=productTypes.size();
        this.typeName=typeName;


    }
    public ProductType() {

    }


    public ProductType printAddProduct() {
        return null;
    }
    public ArrayList<ProductType> getProductTypes() {

        return productTypes;

    }
    public void addProductType(String typeName) {
        productTypes.add(new ProductType(typeName));
    }

    public void setProduct() {

    }
    public ProductType getProductTypeById(int id) {
        return productTypes.get(id);
    }
    public int getProductTypeIdByType(String type) {
        for(ProductType p : productTypes) {
            if(p.getTypeName()==type) {
                return p.getId();
            }
        }
        return -1;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
