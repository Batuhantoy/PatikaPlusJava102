package PatikaStore;

import java.util.Comparator;

public class BrandComparator implements Comparator<Brand>{

    @Override
    public int compare(Brand o1, Brand o2) {
        return o1.getBrandName().toLowerCase().compareTo(o2.getBrandName().toLowerCase());
    }

}
