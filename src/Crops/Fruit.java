package Crops;

import Interfaces.IComparable;

public class Fruit extends Crop implements IComparable {
    private String taste;

    public Fruit(String name, String type, int weight, int price ,int cropKeeperId, String taste) {
        super(name, type, weight, price, cropKeeperId);
        this.setTaste(taste);
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    @Override
    public boolean karsilastir(Crop urun) {
        if(urun.getName().equals(getName()) && urun.getType().equals(getType())) {
            return false;
        }
        return true;
    }
}
