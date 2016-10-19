package com.zcf.coolweather.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zcf.coolweather.app.model.City;
import com.zcf.coolweather.app.model.County;
import com.zcf.coolweather.app.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/19.
 */
public class CoolWeatherDB {
    public static final String DB_NAME = "cool_weather";
    //数据库版本
    public static final int VERSION = 1;
    private static CoolWeatherDB sCoolWeatherDB;
    private SQLiteDatabase db;

    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();

    }
    
    public synchronized static CoolWeatherDB getInstance(Context context){
        if (sCoolWeatherDB == null){
            sCoolWeatherDB = new CoolWeatherDB(context);
        }
        return sCoolWeatherDB;
    }

    //将Province实例存进数据库。
    public void saveProvine(Province province){
        if (province!=null){
            ContentValues values = new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);
        }
    }
    //从数据库读取省份信息
    public List<Province> loadProvince(){
        ArrayList<Province> list = new ArrayList<>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                list.add(province);

            }while (cursor.moveToNext());

        }

        return list;
    }

    public void saveCity(City city){
        if (city!=null){
            ContentValues values = new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            db.insert("City",null,values);
        }
    }

    public List<City> loadCities(){
        ArrayList<City> list = new ArrayList<>();
        Cursor cursor = db.query("City", null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                City province = new City();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                province.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                list.add(province);

            }while (cursor.moveToNext());

        }

        return list;
    }

    public void saveCounty(County city){
        if (city!=null){
            ContentValues values = new ContentValues();
            values.put("county_name",city.getCountyName());
            values.put("county_code",city.getCountyCode());
            db.insert("County",null,values);
        }
    }

    public List<County> loadCounties(){
        ArrayList<County> list = new ArrayList<>();
        Cursor cursor = db.query("County", null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                County province = new County();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                province.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                list.add(province);

            }while (cursor.moveToNext());

        }

        return list;
    }

}
