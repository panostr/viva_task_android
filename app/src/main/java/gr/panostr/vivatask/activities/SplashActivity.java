package gr.panostr.vivatask.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import gr.panostr.vivatask.databinding.ActivitySplashBinding;
import gr.panostr.vivatask.db.ProductsDatabase;
import gr.panostr.vivatask.io.models.Product;
import gr.panostr.vivatask.service.APIClient;
import gr.panostr.vivatask.service.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Panagiotis Triantafyllou on 03/Ιουλ/2022.
 */
public class SplashActivity extends BaseActivity implements Callback<ArrayList<Product>> {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private ActivitySplashBinding binding;

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        ProductsDatabase database = ProductsDatabase.getInstance(this);

        new Thread(() -> {
            if (!Thread.currentThread().isInterrupted()) {
                int dbSize = database.productDAO().getProductList().size();
                if (dbSize == 0) {
                    database.close();
                    APIService service = APIClient.getRetrofitInstance().create(APIService.class);
                    Call<ArrayList<Product>> call = service.getData();
                    call.enqueue(this);
                } else {
                    database.close();
                    runOnUiThread(() -> {
                        Intent intent = new Intent(SplashActivity.this, ProductsActivity.class);
                        startActivity(intent);
                    });
                }
            }
            Thread.currentThread().interrupt();
        }).start();
    }

    @Override
    public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
        ArrayList<Product> products = response.body();
        new Thread(() -> {
            if (!Thread.currentThread().isInterrupted()) {
                ProductsDatabase database = ProductsDatabase.getInstance(this);
//                if (database.isOpen()) {
                    for (Product productItem : products) {
                        database.productDAO().insertProduct(productItem);
                    }

                    database.close();
//                }
            }
            Thread.currentThread().interrupt();
        }).start();

        runOnUiThread(() -> {
            Intent intent = new Intent(SplashActivity.this, ProductsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
        runOnUiThread(() -> {
            Toast.makeText(SplashActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
        });
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
