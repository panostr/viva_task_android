package gr.panostr.vivatask.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import gr.panostr.vivatask.databinding.ActivityProductsBinding;
import gr.panostr.vivatask.databinding.ActivitySplashBinding;

/**
 * Created by Panagiotis Triantafyllou on 03/Ιουλ/2022.
 */
public class ProductsActivity extends BaseActivity {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private ActivityProductsBinding binding;

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
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
