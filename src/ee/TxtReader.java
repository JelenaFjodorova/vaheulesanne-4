package ee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TxtReader {
    public static final String inputPath = (System.getProperty("user.dir") + "//src//ee//input.txt");
    public ArrayList<String> CityList() throws FileNotFoundException {
        Scanner s = new Scanner(new File(inputPath));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNextLine()){
            list.add(s.nextLine());
        }
        s.close();
        return list;
    }


    public static void main(String[] args) throws FileNotFoundException {
        TxtReader reader = new TxtReader();
        ArrayList<String> cityList = reader.CityList();
        System.out.println(cityList);
    }
}
