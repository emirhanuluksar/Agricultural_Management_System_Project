package CropKeepers;

import Crops.Crop;
import Crops.Fruit;
import Interfaces.ICropKeeper;

import java.util.List;

public class Supplier implements ICropKeeper {
    private String name;
    private int id;
    private int budget;
    private List<Crop> cropList;

    public Supplier(String name, int id, int budget, List<Crop> crop) {
        this.name = name;
        this.id = id;
        this.budget = budget;
        this.cropList = crop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public List<Crop> getCropList() {
        return cropList;
    }

    public void setCropList(List<Crop> cropList) {
        this.cropList = cropList;
    }

    public void buyCrop(Store store, String urunad, int urunkilo) {
        int fiyat = 0;
        int TedarikcimevcutKilo = 0;
        int TedarikciyeniKilo;
        int magazaMevcutKilo = 0;
        int magazaYeniKilo = 0;
        int eskiKapasite = 0;
        int yeniKapasite = 0;
        int yeniButce = 0;
        for (int urunVarMi = 0; urunVarMi < store.getFruitList().size(); urunVarMi++) {
            if (store.getFruitList().get(urunVarMi).getName().equals(urunad)) {
                if (store.getFruitList().get(urunVarMi).getWeight() > urunkilo) {
                    fiyat = (urunkilo * store.getFruitList().get(urunVarMi).getPrice());
                    if (getBudget() >= fiyat) {
                        for (int alicidaVarMi = 0; alicidaVarMi < getCropList().size(); alicidaVarMi++) {
                            if (getCropList().get(alicidaVarMi).getName().equals(urunad)) {
                                /*Mağaza işlemleri başladı*/
                                TedarikcimevcutKilo = getCropList().get(alicidaVarMi).getWeight();
                                TedarikciyeniKilo = TedarikcimevcutKilo + urunkilo;
                                getCropList().get(alicidaVarMi).setWeight(TedarikciyeniKilo);
                                /*Tedarikçi işlemleri bitti*/

                                /*Mağaza işlemleri başladı*/
                                eskiKapasite = store.getUsedCapacityArea();
                                yeniKapasite = eskiKapasite - (urunkilo * store.getKgperSquareMeter());
                                store.setUsedCapacityArea(yeniKapasite);
                                magazaMevcutKilo = store.getFruitList().get(urunVarMi).getWeight();
                                magazaYeniKilo = magazaMevcutKilo - urunkilo;
                                store.getFruitList().get(urunVarMi).setWeight(magazaYeniKilo);
                                /*Mağaza işlemleri bitti*/

                                /*Bütçe güncelleme başladı*/
                                yeniButce = getBudget() - fiyat;
                                setBudget(yeniButce);
                                /*Bütçe güncelleme bitti*/
                                return;
                            }
                        }
                    }
                }
            }
        }
        for (int urunVarMi = 0; urunVarMi < store.getFruitList().size(); urunVarMi++) {
            if (store.getFruitList().get(urunVarMi).getName().equals(urunad)) {
                if (store.getFruitList().get(urunVarMi).getWeight() > urunkilo) {
                    fiyat = (urunkilo * store.getFruitList().get(urunVarMi).getPrice());
                    if (getBudget() >= fiyat) {
                        Crop crop = new Crop(urunad, store.getFruitList().get(urunVarMi).getType(), urunkilo, store.getFruitList().get(urunVarMi).getPrice(), getId());
                        getCropList().add(crop);

                        /*Mağaza işlemleri başladı*/
                        eskiKapasite = store.getUsedCapacityArea();
                        yeniKapasite = eskiKapasite - (urunkilo * store.getKgperSquareMeter());
                        store.setUsedCapacityArea(yeniKapasite);
                        magazaMevcutKilo = store.getFruitList().get(urunVarMi).getWeight();
                        magazaYeniKilo = magazaMevcutKilo - urunkilo;
                        store.getFruitList().get(urunVarMi).setWeight(magazaYeniKilo);
                        /*Mağaza işlemleri bitti*/

                        /*Bütçe güncelleme başladı*/
                        yeniButce = getBudget() - fiyat;
                        setBudget(yeniButce);
                        /*Bütçe güncelleme bitti*/
                        System.out.println(store.getFruitList().get(urunVarMi).getName() + " Adlı ürün başarıyla satın alındı!");
                        return;
                    }
                }
            }
        }
        /*YUKARIDAKİ KODUN AYNISI BÜTÜN ÜRÜNÜ ALMAK İÇİN YAZILACAK*/

        for (int urunVarMi = 0; urunVarMi < store.getFruitList().size(); urunVarMi++) {
            if (store.getFruitList().get(urunVarMi).getName().equals(urunad)) {
                if (store.getFruitList().get(urunVarMi).getWeight() == urunkilo) {
                    fiyat = (urunkilo * store.getFruitList().get(urunVarMi).getPrice());
                    if (getBudget() >= fiyat) {
                        for (int alicidaVarMi = 0; alicidaVarMi < getCropList().size(); alicidaVarMi++) {
                            if (getCropList().get(alicidaVarMi).getName().equals(urunad)) {
                                /*Mağaza işlemleri başladı*/
                                TedarikcimevcutKilo = getCropList().get(alicidaVarMi).getWeight();
                                TedarikciyeniKilo = TedarikcimevcutKilo + urunkilo;
                                getCropList().get(alicidaVarMi).setWeight(TedarikciyeniKilo);
                                /*Tedarikçi işlemleri bitti*/

                                /*Mağaza işlemleri başladı*/
                                eskiKapasite = store.getUsedCapacityArea();
                                yeniKapasite = eskiKapasite - (urunkilo * store.getKgperSquareMeter());
                                store.setUsedCapacityArea(yeniKapasite);
                                store.getFruitList().remove(store.getFruitList().get(urunVarMi));
                                /*Mağaza işlemleri bitti*/

                                /*Bütçe güncelleme başladı*/
                                yeniButce = getBudget() - fiyat;
                                setBudget(yeniButce);
                                /*Bütçe güncelleme bitti*/
                                System.out.println(store.getFruitList().get(urunVarMi).getName() + " Adlı ürün başarıyla satın alınıp mağazadan kaldırıldı!");
                                return;
                            }
                        }
                    }
                }
            }
        }

        for (int urunVarMi = 0; urunVarMi < store.getFruitList().size(); urunVarMi++) {
            if (store.getFruitList().get(urunVarMi).getName().equals(urunad)) {
                if (store.getFruitList().get(urunVarMi).getWeight() == urunkilo) {
                    fiyat = (urunkilo * store.getFruitList().get(urunVarMi).getPrice());
                    if (getBudget() >= fiyat) {
                        Crop crop = new Crop(urunad, store.getFruitList().get(urunVarMi).getType(), urunkilo, store.getFruitList().get(urunVarMi).getPrice(), getId());
                        getCropList().add(crop);

                        /*Mağaza işlemleri başladı*/
                        eskiKapasite = store.getUsedCapacityArea();
                        yeniKapasite = eskiKapasite - (urunkilo * store.getKgperSquareMeter());
                        store.setUsedCapacityArea(yeniKapasite);

                        store.getFruitList().remove(store.getFruitList().get(urunVarMi));
                        /*Mağaza işlemleri bitti*/

                        /*Bütçe güncelleme başladı*/
                        yeniButce = getBudget() - fiyat;
                        setBudget(yeniButce);
                        /*Bütçe güncelleme bitti*/
                        System.out.println(crop.getName() + " Adlı ürün başarıyla satın alınıp mağazadan kaldırıldı!");
                        return;
                    }
                }
            }
        }
        System.out.println("Satın alım gerçekleştirilemedi eksik veya hatalı bilgi girdisi!");
    }

    public void sellCrop(Store store, String urunad, int urunkilo) {
        int fiyat = 0;
        int tedarikcimevcutKilo = 0;
        int tedarikciyeniKilo;
        int magazaMevcutKilo = 0;
        int magazaYeniKilo = 0;
        int eskiKapasite = 0;
        int yeniKapasite = 0;
        int yeniButce = 0;
        int kaplayacagiAlan = 0;
        for (int saticidaVarMi = 0; saticidaVarMi < getCropList().size(); saticidaVarMi++) {
            if (getCropList().get(saticidaVarMi).getName().equals(urunad)) {
                if (getCropList().get(saticidaVarMi).getWeight() > urunkilo) {
                    fiyat = urunkilo * getCropList().get(saticidaVarMi).getWeight();
                    for (int magazadaUrunVarMi = 0; magazadaUrunVarMi < store.getFruitList().size(); magazadaUrunVarMi++) {
                        if (store.getFruitList().get(magazadaUrunVarMi).getName().equals(urunad)) {
                            kaplayacagiAlan = urunkilo * store.getKgperSquareMeter();
                            if (store.availableCapacity() > kaplayacagiAlan) {
                                /*Tedarikçi işlemleri başladı*/
                                tedarikcimevcutKilo = getCropList().get(saticidaVarMi).getWeight();
                                tedarikciyeniKilo = tedarikcimevcutKilo - urunkilo;
                                getCropList().get(saticidaVarMi).setWeight(tedarikciyeniKilo);
                                /*Tedarikçi işlemleri bitti*/

                                /*Mağaza işlemleri başladı*/
                                eskiKapasite = store.getUsedCapacityArea();
                                yeniKapasite = eskiKapasite + (urunkilo * store.getKgperSquareMeter());
                                store.setUsedCapacityArea(yeniKapasite);
                                magazaMevcutKilo = store.getFruitList().get(magazadaUrunVarMi).getWeight();
                                magazaYeniKilo = magazaMevcutKilo + urunkilo;
                                store.getFruitList().get(magazadaUrunVarMi).setWeight(magazaYeniKilo);
                                /*Mağaza işlemleri bitti*/

                                /*Bütçe işlemleri başladı*/
                                yeniButce = getBudget() + fiyat;
                                setBudget(yeniButce);
                                /*Bütçe işlemleri bitti*/
                                System.out.println(getCropList().get(saticidaVarMi).getName() + " Adlı ürün başarıyla satıldı");
                                return;
                            }
                        }
                    }
                }
            }
        }
        /*Ürünün mağazada varsa yapılacak işlemler bitti*/

        /*Ürün mağazada yoksa yapılacak işlemler*/
        for (int saticidaVarMi = 0; saticidaVarMi < getCropList().size(); saticidaVarMi++) {
            if (getCropList().get(saticidaVarMi).getName().equals(urunad)) {
                if (getCropList().get(saticidaVarMi).getWeight() > urunkilo) {
                    fiyat = urunkilo * getCropList().get(saticidaVarMi).getWeight();
                    kaplayacagiAlan = urunkilo * store.getKgperSquareMeter();
                    if (store.availableCapacity() > kaplayacagiAlan) {
                        Fruit fruit = new Fruit(urunad, getCropList().get(saticidaVarMi).getType(), urunkilo, getCropList().get(saticidaVarMi).getPrice(), store.getId(), "sweet");
                        if (!(fruit.karsilastir(fruit))) {
                            store.getFruitList().add(fruit);

                            /*Tedarikçi işlemleri başladı*/
                            tedarikcimevcutKilo = getCropList().get(saticidaVarMi).getWeight();
                            tedarikciyeniKilo = tedarikcimevcutKilo - urunkilo;
                            getCropList().get(saticidaVarMi).setWeight(tedarikciyeniKilo);
                            /*Tedarikçi işlemleri bitti*/

                            /*Mağaza işlemleri başladı*/
                            eskiKapasite = store.getUsedCapacityArea();
                            yeniKapasite = eskiKapasite + (urunkilo * store.getKgperSquareMeter());
                            store.setUsedCapacityArea(yeniKapasite);
                            store.getFruitList().add(fruit);
                            /*Mağaza işlemleri bitti*/

                            /*Bütçe işlemleri*/
                            yeniButce = getBudget() + fiyat;
                            setBudget(yeniButce);
                            /*Bütçe işlemleri bitti*/

                            System.out.println("Satış işlemi başarıyla gerçekleştirildi");
                            return;
                        }
                    }
                }
            }
        }
        /*YUKARIDAKİ İŞLEMLERİN AYNISI TÜM ÜRÜNÜ SATMAK İÇİN KULLANILICAK*/
        for (int saticidaVarMi = 0; saticidaVarMi < getCropList().size(); saticidaVarMi++) {
            if (getCropList().get(saticidaVarMi).getName().equals(urunad)) {
                if (getCropList().get(saticidaVarMi).getWeight() == urunkilo) {
                    fiyat = urunkilo * getCropList().get(saticidaVarMi).getPrice();
                    for (int magazadaUrunVarMi = 0; magazadaUrunVarMi < store.getFruitList().size(); magazadaUrunVarMi++) {
                        if (store.getFruitList().get(magazadaUrunVarMi).getName().equals(urunad)) {
                            kaplayacagiAlan = urunkilo * store.getKgperSquareMeter();
                            if (store.availableCapacity() > kaplayacagiAlan) {
                                getCropList().remove(getCropList().get(saticidaVarMi));

                                /*Mağaza işlemleri başladı*/
                                eskiKapasite = store.getUsedCapacityArea();
                                yeniKapasite = eskiKapasite + (urunkilo * store.getKgperSquareMeter());
                                store.setUsedCapacityArea(yeniKapasite);
                                magazaMevcutKilo = store.getFruitList().get(magazadaUrunVarMi).getWeight();
                                magazaYeniKilo = magazaMevcutKilo + urunkilo;
                                store.getFruitList().get(magazadaUrunVarMi).setWeight(magazaYeniKilo);
                                /*Mağaza işlemleri bitti*/

                                /*Bütçe işlemleri başladı*/
                                yeniButce = getBudget() + fiyat;
                                setBudget(yeniButce);
                                /*Bütçe işlemleri bitti*/
                                System.out.println("satış işlemi başarıyla gerçekleştirildi!");
                                return;
                            }
                        }
                    }
                }
            }
        }

        for (int saticidaVarMi = 0; saticidaVarMi < getCropList().size(); saticidaVarMi++) {
            if (getCropList().get(saticidaVarMi).getName().equals(urunad)) {
                if (getCropList().get(saticidaVarMi).getWeight() == urunkilo) {
                    fiyat = urunkilo * getCropList().get(saticidaVarMi).getWeight();
                    kaplayacagiAlan = urunkilo * store.getKgperSquareMeter();
                    if (store.availableCapacity() > kaplayacagiAlan) {
                        Fruit fruit = new Fruit(urunad, getCropList().get(saticidaVarMi).getType(), urunkilo, getCropList().get(saticidaVarMi).getPrice(), store.getId(), "sweet");
                        if (!(fruit.karsilastir(fruit))) {
                            store.getFruitList().add(fruit);

                            getCropList().remove(getCropList().get(saticidaVarMi));

                            /*Mağaza işlemleri başladı*/
                            eskiKapasite = store.getUsedCapacityArea();
                            yeniKapasite = eskiKapasite + (urunkilo * store.getKgperSquareMeter());
                            store.setUsedCapacityArea(yeniKapasite);
                            store.getFruitList().add(fruit);
                            /*Mağaza işlemleri bitti*/
                            System.out.println("Satış işlemi başarıyla gerçekleştirildi!");
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("Satış işlemi gerçekleştirilemedi ürün bilgilerine dikkat edin");
    }

    @Override
    public void importCrop(Fruit crop) {
        if(!crop.karsilastir(crop)) {
            for(int tedarikcideVarMi=0; tedarikcideVarMi < getCropList().size(); tedarikcideVarMi++) {
                if(getCropList().get(tedarikcideVarMi).getName().equals(crop.getName())) {
                    int yeniKilo = getCropList().get(tedarikcideVarMi).getWeight() + crop.getWeight();
                    getCropList().get(tedarikcideVarMi).setWeight(yeniKilo);
                    System.out.println("Ürün başarıyla eklendi!");
                    return;
                }
            }
            getCropList().add(crop);
            System.out.println("Ürün başarıyla eklendi!");
        } else {
            System.out.println("Ürün eklenemez!");
        }
    }

    @Override
    public void exportCrop(Crop crop) {
        getCropList().remove(crop);
        System.out.println(crop.getName() + " Adlı ürün mağazadan başarıyla kaldırıldı!");
    }

    @Override
    public void bilgileriGoster() {
        System.out.println(getName() + " Adlı tedarikçinin ürün listesi");
        for (int urunListesi = 0; urunListesi < getCropList().size(); urunListesi++) {
            getCropList().get(urunListesi).setCropKeeperId(getId());
            System.out.println(getCropList().get(urunListesi).getName() + " kilo: " + getCropList().get(urunListesi).getWeight());
        }
        System.out.println("##############################");
        System.out.println("Tedarikçinin bütçesi: " + getBudget() + "₺");
        System.out.println("------------------------------");

    }

    @Override
    public boolean canBeStored(Crop crop) {
        if (crop.getType().equals("vegetable")) {
            System.out.println("Tedarikçilere sadece meyveler eklenebilir!");
            return false;
        }
        return true;
    }
}
