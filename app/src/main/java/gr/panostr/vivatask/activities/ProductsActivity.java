package gr.panostr.vivatask.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import gr.panostr.vivatask.databinding.ActivityProductsBinding;
import gr.panostr.vivatask.db.ProductsDatabase;
import gr.panostr.vivatask.io.models.Product;
import gr.panostr.vivatask.service.APIClient;
import gr.panostr.vivatask.service.APIService;
import gr.panostr.vivatask.views.adapters.ProductsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Panagiotis Triantafyllou on 03/Ιουλ/2022.
 */
public class ProductsActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        Callback<ArrayList<Product>> {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private ActivityProductsBinding binding;
    private RecyclerView mRecyclerProducts;
    private ProductsAdapter mAdapterProducts;
    private List<Product> mData = new ArrayList<>();
    private SwipeRefreshLayout mRefreshProducts;

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mRecyclerProducts = binding.rvProducts;
        mRefreshProducts = binding.swRefreshProducts;
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Thread(() -> {
            if (!Thread.currentThread().isInterrupted()) {
                ProductsDatabase database = ProductsDatabase.getInstance(this);
//                if (database.isOpen()) {
                    int dbSize = database.productDAO().getProductList().size();
                    if (dbSize > 0) {
                        mData = database.productDAO().getProductList();
                        database.close();
                        runOnUiThread(this::initViews);
                    }
//                }
            }
            Thread.currentThread().interrupt();
        }).start();
    }

    @Override
    public void onRefresh() {
        new Thread(() -> {
            if (!Thread.currentThread().isInterrupted()) {
                APIService service = APIClient.getRetrofitInstance().create(APIService.class);
                Call<ArrayList<Product>> call = service.getData();
                call.enqueue(this);
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
            mRefreshProducts.setRefreshing(false);
            mAdapterProducts.updateData(products);
        });
    }

    @Override
    public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
        runOnUiThread(() -> {
            Toast.makeText(ProductsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            mRefreshProducts.setRefreshing(false);
        });
    }

    // ===========================================================
    // Methods
    // ===========================================================

    private void initViews() {
        mRecyclerProducts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapterProducts = new ProductsAdapter(this, mData);
        mRecyclerProducts.setAdapter(mAdapterProducts);
        mAdapterProducts.getPositionClicks()
                .doOnNext(productItem -> {
                    Intent intent = new Intent(ProductsActivity.this, ProductItemActivity.class);
                    intent.putExtra("product", productItem);
                    startActivity(intent);
                })
                .doOnError(error -> {
                })
                .subscribe();

        mRefreshProducts.setOnRefreshListener(this);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
