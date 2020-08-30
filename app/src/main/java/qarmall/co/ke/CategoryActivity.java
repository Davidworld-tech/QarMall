package qarmall.co.ke;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView categoryRecyclerView;
    private RecyclerView home_recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("categoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        categoryRecyclerView = findViewById(R.id.category_RecyclerView);
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

        LinearLayoutManager homeLayoutManager = new LinearLayoutManager(this);
        homeLayoutManager.setOrientation(RecyclerView.VERTICAL);
        categoryRecyclerView.setLayoutManager(homeLayoutManager);
        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.toyota, "#000000"));
        homePageModelList.add(new HomePageModel(3, "Trending", horizontalProductModelList));
        homePageModelList.add(new HomePageModel(3, "Trending this week", horizontalProductModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.toyota, "#00E0F0"));
        homePageModelList.add(new HomePageModel(2, "Deals of the day", horizontalProductModelList));
        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(2, "Deals of the week", horizontalProductModelList));
        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        categoryRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Handle action bar item clicks

        int id = item.getItemId();

        if (id == R.id.main_search_icon) {
            //todo: search
            return true;
        } else if (id == android.R.id.home) {
            finish();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}