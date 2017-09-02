package com.doubtnut.doubtnutassigment.storage;

import android.content.Context;

import com.doubtnut.doubtnutassigment.model.CountryData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 2/9/17.
 */

public class ReadWriteDataFromLocal {

    public static void writeCountrysData(List<CountryData> countryDataList, Context context) {

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(context.openFileOutput("countryList.ser", Context.MODE_PRIVATE));
            objectOutputStream.writeObject(countryDataList);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static List<CountryData> getCountrysData(Context context) {
        List<CountryData> countryDataList ;

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(context.openFileInput("countryList.ser"));
             countryDataList = (List<CountryData>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            return null;
        }

        return countryDataList;
    }

}
