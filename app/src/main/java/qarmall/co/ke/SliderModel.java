package qarmall.co.ke;

public class SliderModel {
    //private String banner; for database feed
    private int banner;
    private String SliderBackgroundColor;

    public SliderModel(int banner, String sliderBackgroundColor) {
        this.banner = banner;
        SliderBackgroundColor = sliderBackgroundColor;
    }

    public int getBanner() {
        return banner;
    }

    public void setBanner(int banner) {
        this.banner = banner;
    }

    public String getSliderBackgroundColor() {
        return SliderBackgroundColor;
    }

    public void setSliderBackgroundColor(String sliderBackgroundColor) {
        SliderBackgroundColor = sliderBackgroundColor;
    }
}
