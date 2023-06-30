package com.myappcompany.rob.bikeshop2.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.myappcompany.rob.bikeshop2.dao.PartDAO;
import com.myappcompany.rob.bikeshop2.dao.ProductDAO;
import com.myappcompany.rob.bikeshop2.entities.Part;
import com.myappcompany.rob.bikeshop2.entities.Product;

@Database(entities = {Product.class, Part.class}, version = 2, exportSchema = false)
public abstract class BicycleDatabaseBuilder extends RoomDatabase {

    public abstract ProductDAO productDAO();
    public abstract PartDAO partDAO();

    private static volatile BicycleDatabaseBuilder INSTANCE;

    static BicycleDatabaseBuilder getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (BicycleDatabaseBuilder.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), BicycleDatabaseBuilder.class, "MyDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}