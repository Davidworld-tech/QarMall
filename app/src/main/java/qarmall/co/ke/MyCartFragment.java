package qarmall.co.ke;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {

    private RecyclerView cartRecyclerItemsView;

    public MyCartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);
        cartRecyclerItemsView = view.findViewById(R.id.cart_items_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        cartRecyclerItemsView.setLayoutManager(layoutManager);
        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0, R.drawable.honda, "Honda Accord", "Ksh 3995", "Ksh 4950", 2));
        cartItemModelList.add(new CartItemModel(0, R.drawable.honda, "Honda Accord", "Ksh 3995", "Ksh 4950", 5));
        cartItemModelList.add(new CartItemModel(0, R.drawable.honda, "Honda Accord", "Ksh 3995", "Ksh 4950", 2));
        cartItemModelList.add(new CartItemModel(0, R.drawable.honda, "Honda Accord", "Ksh 3995", "Ksh 4950", 7));
        cartItemModelList.add(new CartItemModel(0, R.drawable.honda, "Honda Accord", "Ksh 3995", "Ksh 4950", 2));
        cartItemModelList.add(new CartItemModel(1, "3", "435637", "400", "3000", "342452"));
        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        cartRecyclerItemsView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();


        return view;
    }
}