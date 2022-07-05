package gr.panostr.vivatask.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import gr.panostr.vivatask.R;
import gr.panostr.vivatask.databinding.ActivityProductItemBinding;
import gr.panostr.vivatask.io.models.Product;

/**
 * Created by Panagiotis Triantafyllou on 05/Ιουλ/2022.
 */
public class ProductItemActivity extends BaseActivity implements View.OnClickListener {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private ActivityProductItemBinding binding;
    private Product mProductItem = new Product();
    private ImageView mProductLogo, mBack;
    private TextView mProductName, mProductPrice, mProductDescription;

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mProductLogo = binding.ivProductItemLogo;
        mProductName = binding.tvProductItemName;
        mProductPrice = binding.tvProductItemPrice;
        mProductDescription = binding.tvProductItemDescription;
        mBack = binding.ivBack;

        mProductItem = getIntent().getParcelableExtra("product");

        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }

    // ===========================================================
    // Methods
    // ===========================================================

    private void initViews() {
        mBack.setOnClickListener(this);

        if (mProductItem != null) {
            if (!TextUtils.isEmpty(mProductItem.getImage())) {
                Glide.with(this)
//                        .setDefaultRequestOptions(
//                                RequestOptions
//                                        .placeholderOf(R.drawable.ic_default_contact_photo)
//                                        .error(R.drawable.ic_default_contact_photo))
                        .load(mProductItem.getImage())
                        .diskCacheStrategy(DiskCacheStrategy.DATA)
                        .into(mProductLogo);
            } else {
                Glide.with(this)
                        .load(R.drawable.ic_viva_logo)
                        .into(mProductLogo);
            }

            mProductName.setText(mProductItem.getName());
            mProductDescription.setText(mProductItem.getDescription());
            mProductPrice.setText(mProductItem.getPrice());
        }
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}
