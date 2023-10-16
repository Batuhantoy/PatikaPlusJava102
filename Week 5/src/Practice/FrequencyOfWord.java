package Practice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FrequencyOfWord {
    // Büyük-küçük harf duyarlı, duyarsız yapmak için 'text.trim().toLowerCase().split(" ")' kullanırdık
    public static String frequency(String text){
        HashMap<String,Integer> freq = new HashMap<>();
        for(String str : text.trim().split(" ")){
            if(freq.containsKey(str)){
                freq.put(str,freq.get(str)+1);
            }else{
                freq.put(str,1);
            }
        }
        return Collections.max(freq.entrySet(), Map.Entry.comparingByValue()).getKey();

    }
}

