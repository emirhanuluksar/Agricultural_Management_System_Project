package CropKeepers;

import Crops.Crop;
import Crops.Fruit;
import Interfaces.ICropKeeper;

import java.util.List;
import java.util.TreeMap;

public class Store implements ICropKeeper {
    private int id;
    private String name;
    private int maxCapacityArea;
    private int usedCapacityArea;
    private int kgperSquareMeter;
    private List<Crop> fruitList;

    public Store(String name, int id, int maxCapacityArea, int usedCapacityArea, int kgperSquareMeter, List<Crop> fruitList) {
        this.name = name;
        this.id = id;
        this.maxCapacityArea = maxCapacityArea;
        this.usedCapacityArea = usedCapacityArea;
        this.kgperSquareMeter = kgperSquareMeter;
        this.fruitList = fruitList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxCapacityArea() {
        return maxCapacityArea;
    }

    public void setMaxCapacityArea(int maxCapacityArea) {
        this.maxCapacityArea = maxCapacityArea;
    }

    public int getUsedCapacityArea() {
        return usedCapacityArea;
    }

    public void setUsedCapacityArea(int usedCapacityArea) {
        this.usedCapacityArea = usedCapacityArea;
    }

    public int getKgperSquareMeter() {
        return kgperSquareMeter;
    }

    public void setKgperSquareMeter(int kgperSquareMeter) {
        this.kgperSquareMeter = kgperSquareMeter;
    }

    public List<Crop> getFruitList() {
        return fruitList;
    }

    public void setFruitList(List<Crop> fruitList) {
        this.fruitList = fruitList;
    }


    @Override
    public void importCrop(Fruit crop) {
        /**
         * Mağazada saklanabilirliğini kontrol edicez
         * gerekli bilgileri alıcaz
         * idsi mağazanın idsine eşit olmak zorunda
         * eğer mağazada bulunan bir ürün eklenirse ürünün kilosuna eklenenin kilosu eklenecek
         * eğer mağazanın kapasitesi doluysa ürün eklenmeyecektir.
         * **/
        if(!crop.karsilastir(crop)) {
            for(int magazadaVarMi=0; magazadaVarMi < getFruitList().size(); magazadaVarMi++) {
                if(getFruitList().get(magazadaVarMi).getName().equals(crop.getName())) {
                    if(availableCapacity() >= (crop.getWeight() * getKgperSquareMeter())) {
                        int eklenecekAlan = crop.getWeight() * getKgperSquareMeter();
                        int eklenecekKilo = getFruitList().get(magazadaVarMi).getWeight() + crop.getWeight();
                        int yeniAlan = eklenecekAlan + getUsedCapacityArea();
                        getFruitList().get(magazadaVarMi).setWeight(eklenecekKilo);
                        setUsedCapacityArea(yeniAlan);
                        System.out.println("Ürün başarıyla eklendi!");
                        return;
                    }
                }
            }
            if(availableCapacity() >= crop.getWeight() * getKgperSquareMeter()) {
                int eklenecekAlan = crop.getWeight() * getKgperSquareMeter();
                int yeniAlan = eklenecekAlan + getUsedCapacityArea();
                setUsedCapacityArea(yeniAlan);
                getFruitList().add(crop);
                System.out.println("Ürün başarıyla eklendi!");
                return;
            }
            System.out.println("Ürün eklenemedi!, boş alan yeterli değil");
        }
    }

    @Override
    public void exportCrop(Crop crop) {
        if(!(crop.getName().contains(crop.getName()))) {
            System.out.println("Böyle bir ürün bulunamadı!3");
        }
        int kalanKapasite = getUsedCapacityArea() - (crop.getWeight() * getKgperSquareMeter());
        setUsedCapacityArea(kalanKapasite);
        getFruitList().remove(crop);
        System.out.println(crop.getName() + " Adlı ürün mağazadan başarıyla kaldırıldı!");
    }

    public int availableCapacity() {
        int storeEmptyArea = getMaxCapacityArea() - getUsedCapacityArea();
        return storeEmptyArea;
    }

    @Override
    public void bilgileriGoster() {
        System.out.println(getName() + " Adlı mağazanın ürün listesi");
        for(int magazaUrunListesi=0; magazaUrunListesi < getFruitList().size(); magazaUrunListesi++) {
            getFruitList().get(magazaUrunListesi).setCropKeeperId(getId());
            System.out.println(getFruitList().get(magazaUrunListesi).getName() + " Kilo: " + getFruitList().get(magazaUrunListesi).getWeight());
        }
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("Maksimum Depolama Alanı: " + getMaxCapacityArea());
        System.out.println("Kullanılan alan: " + getUsedCapacityArea());
        System.out.println("Mevcut boş alan: " + availableCapacity());
        System.out.println("---------------------------");
    }


    @Override
    public boolean canBeStored(Crop crop) {
        if(crop.getType().equals("vegetable")) {
            System.out.println("Mağazaya sadece meyveler eklenebilir!");
            return false;
        }
        return true;
    }
}
