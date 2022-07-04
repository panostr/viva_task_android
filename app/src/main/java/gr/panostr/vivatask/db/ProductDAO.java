package gr.panostr.vivatask.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import gr.panostr.vivatask.io.models.Product;

/**
 * Created by Panagiotis Triantafyllou on 04/Ιουλ/2022.
 */

@Dao
public interface ProductDAO {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Query(" Select * from product")
    List<Product> getProductList();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProduct(Product product);

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Getters
    // ===========================================================

}
