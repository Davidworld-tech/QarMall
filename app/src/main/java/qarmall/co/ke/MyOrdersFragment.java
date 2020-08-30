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

public class MyOrdersFragment extends Fragment {

    private RecyclerView myOrdersRecyclerView;

    public MyOrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);
        myOrdersRecyclerView = view.findViewById(R.id.my_orders_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myOrdersRecyclerView.setLayoutManager(layoutManager);
        List<MyOrderModel> myOrderModelList = new ArrayList<>();
        myOrderModelList.add(new MyOrderModel(R.drawable.honda, "Honda Accord", "Delivered on Fri, 28th Aug 2020"));
        myOrderModelList.add(new MyOrderModel(R.drawable.honda, "Honda Accord", "Delivered on Fri, 29th Aug 2020"));
        myOrderModelList.add(new MyOrderModel(R.drawable.honda, "Honda Accord", "Delivered on Fri, 28th Aug 2020"));
        myOrderModelList.add(new MyOrderModel(R.drawable.honda, "Honda Accord", "Cancelled"));
        myOrderModelList.add(new MyOrderModel(R.drawable.honda, "Honda Accord", "Delivered on Fri, 28th Aug 2020"));
        myOrderModelList.add(new MyOrderModel(R.drawable.honda, "Honda Accord", "Delivered on Fri, 24th Aug 2020"));

        MyOrderAdapter adapter = new MyOrderAdapter(myOrderModelList);
        myOrdersRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        return view;
    }
}