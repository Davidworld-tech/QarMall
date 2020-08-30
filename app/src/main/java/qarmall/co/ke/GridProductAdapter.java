package qarmall.co.ke;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GridProductAdapter extends BaseAdapter {
    List<HorizontalProductModel> horizontalProductModelList;

    public GridProductAdapter(List<HorizontalProductModel> horizontalProductModelList) {
        this.horizontalProductModelList = horizontalProductModelList;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view;
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout, null);
            view.setElevation(0);
            view.setBackgroundColor(Color.parseColor("#ffffff"));

            ImageView productImage = view.findViewById(R.id.h_product_image);
            TextView productTitle = view.findViewById(R.id.h_product_title);
            TextView productPrice = view.findViewById(R.id.h_product_price);
            TextView productDescription = view.findViewById(R.id.h_productDescription);

            productImage.setImageResource(horizontalProductModelList.get(position).getProductImage());
            productTitle.setText(horizontalProductModelList.get(position).getProductTitle());
            productDescription.setText(horizontalProductModelList.get(position).getProductDescription());
            productPrice.setText(horizontalProductModelList.get(position).getProductPrice());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productsDetailsIntent = new Intent(view.getContext(), ProductDetailsActivity.class);
                    view.getContext().startActivity(productsDetailsIntent);
                }
            });
        } else {
            view = convertView;
        }
        return view;
    }
}
