package qarmall.co.ke;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {
    private static boolean ALREADY_ADDED_TO_WISH_LIST;
    private ViewPager productImagesViewPager;
    private TabLayout viewPagerIndicators;
    private FloatingActionButton addToWishListBtn;
    private ViewPager productDescriptionViewPager;
    private TabLayout productDescriptionTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //todo products details images
        productImagesViewPager = findViewById(R.id.product_images_viewPager);
        viewPagerIndicators = findViewById(R.id.viewPager_indicator);
        List<Integer> productsImages = new ArrayList<>();
        productsImages.add(R.drawable.honda);
        productsImages.add(R.drawable.toyota);
        productsImages.add(R.drawable.honda);
        productsImages.add(R.drawable.honda);
        ProductsImagesAdapter productsImagesAdapter = new ProductsImagesAdapter(productsImages);
        productImagesViewPager.setAdapter(productsImagesAdapter);
        viewPagerIndicators.setupWithViewPager(productImagesViewPager, true);

        //todo add to wishList
        addToWishListBtn = findViewById(R.id.add_to_wishListBtn);
        addToWishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ALREADY_ADDED_TO_WISH_LIST) {
                    ALREADY_ADDED_TO_WISH_LIST = false;
                    addToWishListBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9E9E9E")));
                } else {
                    ALREADY_ADDED_TO_WISH_LIST = true;
                    addToWishListBtn.setSupportImageTintList(getResources().getColorStateList(R.color.colorPrimary));
                }
            }
        });

        //todo products description layout
        productDescriptionViewPager = findViewById(R.id.product_description_viewpager);
        productDescriptionTabLayout = findViewById(R.id.product_description_tabLayout);

        productDescriptionViewPager.setAdapter(new ProductDescriptionAdapter(getSupportFragmentManager(), productDescriptionTabLayout.getTabCount()));
        productDescriptionViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDescriptionTabLayout));
        productDescriptionTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDescriptionViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Handle action bar item clicks

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.main_search_icon) {
            //todo: search
            return true;
        } else if (id == R.id.main_cart_icon) {
            //todo: cart
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}