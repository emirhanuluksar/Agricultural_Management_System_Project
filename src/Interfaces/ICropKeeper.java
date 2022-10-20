package Interfaces;

import Crops.Crop;
import Crops.Fruit;

public interface ICropKeeper {
    void importCrop(Fruit crop);
    void exportCrop(Crop crop);
    void bilgileriGoster();
    boolean canBeStored(Crop crop);
}
