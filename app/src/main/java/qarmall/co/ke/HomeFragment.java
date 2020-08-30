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

public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //todo category recycler view
        RecyclerView categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(linearLayoutManager);
        final List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        categoryModelList.add(new CategoryModel("", "Home"));
        categoryModelList.add(new CategoryModel("", "Audi"));
        categoryModelList.add(new CategoryModel("", "Mazda"));
        categoryModelList.add(new CategoryModel("", "subaru"));
        categoryModelList.add(new CategoryModel("", "lexus"));
        categoryModelList.add(new CategoryModel("", "land rover"));
        categoryModelList.add(new CategoryModel("", "Range Rover"));
        categoryModelList.add(new CategoryModel("", "BMW"));
        categoryModelList.add(new CategoryModel("", "VOLKSWAGEN"));
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        //todo banner slider
        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();
        sliderModelList.add(new SliderModel(R.drawable.banner, "#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.banner2, "#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.toyota, "#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.banner, "#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.banner2, "#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.toyota, "#FFFFFF"));

        //todo horizontal product layout
        List<HorizontalProductModel> horizontalProductModelList = new ArrayList<>();
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.honda, "Honda Accord Steering Tie Rod End", "Honda cars", "3830"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.honda, "Honda Accord Steering Tie Rod End", "Honda cars", "3830"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.honda, "Honda Accord Steering Tie Rod End", "Honda cars", "3830"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.honda, "Honda Accord Steering Tie Rod End", "Honda cars", "3830"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.honda, "Honda Accord Steering Tie Rod End", "Honda cars", "3830"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.honda, "Honda Accord Steering Tie Rod End", "Honda cars", "3830"));
        horizontalProductModelList.add(new HorizontalProductModel(R.drawable.honda, "Honda Accord Steering Tie Rod End", "Honda cars", "3830"));
        //todo home recyclerView
        RecyclerView home_recycler_view = view.findViewById(R.id.homeRecyclerView);
        LinearLayoutManager homeLayoutManager = new LinearLayoutManager(getContext());
        homeLayoutManager.setOrientation(RecyclerView.VERTICAL);
        home_recycler_view.setLayoutManager(homeLayoutManager);
        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.toyota, "#000000"));
        homePageModelList.add(new HomePageModel(2, "Deals of the week", horizontalProductModelList));
        homePageModelList.add(new HomePageModel(3, "Trending", horizontalProductModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.toyota, "#00E0F0"));
        homePageModelList.add(new HomePageModel(3, "Trending this week", horizontalProductModelList));
        homePageModelList.add(new HomePageModel(2, "Deals of the day", horizontalProductModelList));
        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        home_recycler_view.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }
}