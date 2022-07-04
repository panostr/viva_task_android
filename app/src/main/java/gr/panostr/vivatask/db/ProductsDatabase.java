package gr.panostr.vivatask.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import gr.panostr.vivatask.io.models.Product;

/**
 * Created by Panagiotis Triantafyllou on 04/Ιουλ/2022.
 */

@Database(entities = Product.class, exportSchema = false, version = 1)
public abstract class ProductsDatabase extends RoomDatabase {

    // ===========================================================
    // Constants
    // ===========================================================

    private static final String DB_NAME = "product_db";
    private static ProductsDatabase instance;

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    public static synchronized ProductsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ProductsDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

    public abstract ProductDAO productDAO();

    // ===========================================================
    // Getters
    // ===========================================================

}
