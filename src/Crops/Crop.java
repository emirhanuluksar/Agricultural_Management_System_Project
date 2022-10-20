package Crops;

public class Crop {
    private String name;
    private String type;
    private int weight;
    private int price;
    private int cropKeeperId;

    public Crop(String name, String type, int weight, int price, int cropKeeperId) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.price = price;
        this.cropKeeperId = cropKeeperId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCropKeeperId() {
        return cropKeeperId;
    }

    public void setCropKeeperId(int cropKeeperId) {
        this.cropKeeperId = cropKeeperId;
    }
}
