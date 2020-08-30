package qarmall.co.ke;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter {
    private List<HomePageModel> homePageModelList;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (homePageModelList.get(position).getType()) {
            case 0:
                return HomePageModel.BANNER_SLIDER;

            case 1:
                return HomePageModel.STRIP_AD_BANNER;
            case 2:
                return HomePageModel.HORIZONTAL_PRODUCT_VIEW;
            case 3:
                return HomePageModel.GRID_PRODUCT_VIEW;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case HomePageModel.BANNER_SLIDER:
                View bannerSliderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliding_ad_layout, parent, false);
                return new BannerSliderViewHolder(bannerSliderView);
            case HomePageModel.STRIP_AD_BANNER:
                View stripAdView = LayoutInflater.from(parent.getContext()).inflate(R.layout.strip_ad_layout, parent, false);
                return new StripAdBannerViewHolder(stripAdView);
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                View horizontalProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_layout, parent, false);
                return new HorizontalProductViewHolder(horizontalProductView);
            case HomePageModel.GRID_PRODUCT_VIEW:
                View gridProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_product_layout, parent, false);
                return new GridProductViewHolder(gridProductView);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (homePageModelList.get(position).getType()) {
            case HomePageModel.BANNER_SLIDER:
                List<SliderModel> sliderModelList = homePageModelList.get(position).getSliderModelList();
                ((BannerSliderViewHolder) holder).setBannerSliderViewPager(sliderModelList);
                break;
            case HomePageModel.STRIP_AD_BANNER:
                int resource = homePageModelList.get(position).getResource();
                String color = homePageModelList.get(position).getBackground();

                ((StripAdBannerViewHolder) holder).setStripAd(resource, color);
                break;
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                String horizontalLayoutTitle = homePageModelList.get(position).getTitle();
                List<HorizontalProductModel> horizontalProductModelList = homePageModelList.get(position).getHorizontalProductModelList();
                ((HorizontalProductViewHolder) holder).setHorizontalProductLayout(horizontalProductModelList, horizontalLayoutTitle);
                break;
            case HomePageModel.GRID_PRODUCT_VIEW:
                String gridLayoutTitle = homePageModelList.get(position).getTitle();
                List<HorizontalProductModel> gridProductModelList = homePageModelList.get(position).getHorizontalProductModelList();
                ((GridProductViewHolder) holder).setGridProductLayout(gridProductModelList, gridLayoutTitle);
            default:

        }

    }

    @Override
    public int getItemCount() {

        return homePageModelList.size();
    }

    public class BannerSliderViewHolder extends RecyclerView.ViewHolder {
        ViewPager bannerSliderViewPager;
        private List<SliderModel> sliderModelList;

        public BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            //todo banner slider
            bannerSliderViewPager = itemView.findViewById(R.id.banner_slider_view_pager);
        }

        private void setBannerSliderViewPager(List<SliderModel> sliderModelList) {
            sliderModelList = new ArrayList<SliderModel>();
            sliderModelList.add(new SliderModel(R.drawable.banner, "#FFFFFF"));
            sliderModelList.add(new SliderModel(R.drawable.banner2, "#FFFFFF"));
            sliderModelList.add(new SliderModel(R.drawable.toyota, "#FFFFFF"));
            sliderModelList.add(new SliderModel(R.drawable.banner, "#FFFFFF"));
            sliderModelList.add(new SliderModel(R.drawable.banner2, "#FFFFFF"));
            sliderModelList.add(new SliderModel(R.drawable.toyota, "#FFFFFF"));
            SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
            bannerSliderViewPager.setAdapter(sliderAdapter);
            bannerSliderViewPager.setClipToPadding(false);
            bannerSliderViewPager.setPageMargin(30);
        }


    }

    public class StripAdBannerViewHolder extends RecyclerView.ViewHolder {
        private ImageView stripImage;
        private ConstraintLayout stripAdContainer;

        public StripAdBannerViewHolder(@NonNull View itemView) {
            super(itemView);
            stripImage = itemView.findViewById(R.id.strip_ad_image);
            stripAdContainer = itemView.findViewById(R.id.strip_ad_container);
        }

        private void setStripAd(int resource, String color) {
            stripImage.setImageResource(resource);
            stripAdContainer.setBackgroundColor(Color.parseColor(color));
        }
    }

    public class HorizontalProductViewHolder extends RecyclerView.ViewHolder {
        TextView horizontalLayoutTitle;
        Button viewAllButton;
        RecyclerView horizontalRecyclerView;


        public HorizontalProductViewHolder(@NonNull View itemView) {
            super(itemView);
            horizontalLayoutTitle = itemView.findViewById(R.id.horizontal_scroll_layout_title);
            viewAllButton = itemView.findViewById(R.id.horizontal_scroll_layout_button);
            horizontalRecyclerView = itemView.findViewById(R.id.horizontal_scroll_layout_recyclerView);
        }

        private void setHorizontalProductLayout(List<HorizontalProductModel> horizontalProductModelList, String title) {
            horizontalLayoutTitle.setText(title);
            if (horizontalProductModelList.size() > 0) {
                viewAllButton.setVisibility(View.VISIBLE);
            } else {
                viewAllButton.setVisibility(View.INVISIBLE);
            }
            HorizontalProductAdapter horizontalProductAdapter = new HorizontalProductAdapter(horizontalProductModelList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext());
            layoutManager.setOrientation(RecyclerView.HORIZONTAL);
            horizontalRecyclerView.setLayoutManager(layoutManager);
            horizontalRecyclerView.setAdapter(horizontalProductAdapter);
            horizontalProductAdapter.notifyDataSetChanged();
        }
    }

    public class GridProductViewHolder extends RecyclerView.ViewHolder {
        TextView gridLayoutTitle;
        Button gridViewAllButton;
        GridView gridView;

        public GridProductViewHolder(@NonNull View itemView) {
            super(itemView);
            gridLayoutTitle = itemView.findViewById(R.id.grid_product_layoutTitle);
            gridViewAllButton = itemView.findViewById(R.id.grid_product_viewAllButton);
            gridView = itemView.findViewById(R.id.grid_product_layoutGridView);
        }

        private void setGridProductLayout(List<HorizontalProductModel> horizontalProductModelList, String title) {
            if (horizontalProductModelList.size() > 0) {
                gridViewAllButton.setVisibility(View.VISIBLE);
            } else {
                gridViewAllButton.setVisibility(View.INVISIBLE);
            }

            gridLayoutTitle.setText(title);
            gridView.setAdapter(new GridProductAdapter(horizontalProductModelList));

        }
    }

}
