package qarmall.co.ke;

public class ProductSpecificationModel {
    public static final int SPECIFICATION_TITLE = 0;
    public static final int SPECIFICATION_BODY = 1;
    private int type;
    /// todo specification title
    private String title;
    /// todo specification body
    private String model;
    private String year;

    public ProductSpecificationModel(int type, String title) {
        this.type = type;
        this.title = title;
    }

    public ProductSpecificationModel(int type, String model, String year) {
        this.type = type;
        this.model = model;
        this.year = year;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
