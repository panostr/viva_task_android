package gr.panostr.vivatask.io.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Panagiotis Triantafyllou on 04/Ιουλ/2022.
 */

@Entity(tableName = "product")
public class Product {

    // ===========================================================
    // Constants
    // ===========================================================

    private static final String ID = "Id";
    private static final String NAME = "Name";
    private static final String PRICE = "Price";
    private static final String THUMBNAIL = "Thumbnail";
    private static final String IMAGE = "Image";
    private static final String DESCRIPTION = "Description";

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @SerializedName(ID)
    @Expose
    @PrimaryKey
    private int Id;
    @SerializedName(NAME)
    @Expose
    @ColumnInfo(name = "Name")
    private String Name;
    @SerializedName(PRICE)
    @Expose
    @ColumnInfo(name = "Price")
    private String Price;
    @SerializedName(THUMBNAIL)
    @Expose
    @ColumnInfo(name = "Thumbnail")
    private String Thumbnail;
    @SerializedName(IMAGE)
    @Expose
    @ColumnInfo(name = "Image")
    private String Image;
    @SerializedName(DESCRIPTION)
    @Expose
    @ColumnInfo(name = "Description")
    private String Description;

    // ===========================================================
    // Methods
    // ===========================================================

    public Product() {
        this.Id = 0;
        this.Name = "";
        this.Price = "";
        this.Thumbnail = "";
        this.Image = "";
        this.Description = "";
    }

    // ================================================================================
    // Getters
    // ================================================================================


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
