package gr.panostr.vivatask.io.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Panagiotis Triantafyllou on 04/Ιουλ/2022.
 */

@Entity(tableName = "product")
public class Product implements Parcelable {

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


    protected Product(Parcel in) {
        Id = in.readInt();
        Name = in.readString();
        Price = in.readString();
        Thumbnail = in.readString();
        Image = in.readString();
        Description = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id);
        parcel.writeString(Name);
        parcel.writeString(Price);
        parcel.writeString(Thumbnail);
        parcel.writeString(Image);
        parcel.writeString(Description);
    }
}
