package gr.panostr.vivatask.views.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import gr.panostr.vivatask.R;
import gr.panostr.vivatask.io.models.Product;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private Context mContext;
    private List<Product> mProducts;
    private final PublishSubject<Product> onClickSubject = PublishSubject.create();

    public ProductsAdapter(Context mContext, List<Product> productsList) {
        this.mContext = mContext;
        this.mProducts = productsList;
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivProductLogo;
        private TextView tvProductName, tvProductDescription, tvProductPrice;

        public ProductsViewHolder(View view) {
            super(view);
            ivProductLogo = view.findViewById(R.id.iv_product_logo);
            tvProductName = view.findViewById(R.id.tv_product_name);
            tvProductDescription = view.findViewById(R.id.tv_product_description);
            tvProductPrice = view.findViewById(R.id.tv_product_price);
        }
    }

    @Override
    public int getItemCount() {
        if (mProducts == null)
            return 0;
        else
            return mProducts.size();
    }


    public Product getItem(int id) {
        return mProducts.get(id);
    }

    public void updateData(List<Product> productList) {
        mProducts.clear();
        mProducts.addAll(productList);
        notifyDataSetChanged();
    }


    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_layout, parent, false);
        return new ProductsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ProductsViewHolder holder, int position) {
        Product productItem = mProducts.get(position);

        if (productItem != null) {
            if (!TextUtils.isEmpty(productItem.getThumbnail())) {
                Glide.with(mContext)
//                        .setDefaultRequestOptions(
//                                RequestOptions
//                                        .placeholderOf(R.drawable.ic_default_contact_photo)
//                                        .error(R.drawable.ic_default_contact_photo))
                        .load(productItem.getThumbnail())
                        .diskCacheStrategy(DiskCacheStrategy.DATA)
                        .into(holder.ivProductLogo);
            } else {
                Glide.with(mContext)
                        .load(R.drawable.ic_viva_logo)
                        .into(holder.ivProductLogo);
            }

            holder.tvProductName.setText(productItem.getName());
            holder.tvProductDescription.setText(productItem.getDescription());
            holder.tvProductPrice.setText(productItem.getPrice());
            holder.itemView.setOnClickListener(v -> onClickSubject.onNext(productItem));
        }
    }

    public Observable<Product> getPositionClicks() {
        return onClickSubject.cache();
    }
}
