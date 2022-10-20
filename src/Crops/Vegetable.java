package Crops;

import Interfaces.IComparable;

public class Vegetable extends Crop implements IComparable {

    private String cultivatedRegion;

    public Vegetable(String name, String type, int weight, int price, int cropKeeperId, String cultivatedRegion) {
        super(name, type, weight, price, cropKeeperId);
        this.cultivatedRegion = cultivatedRegion;
    }

    @Override
    public boolean karsilastir(Crop eklenecek) {
        return false;
    }
}
