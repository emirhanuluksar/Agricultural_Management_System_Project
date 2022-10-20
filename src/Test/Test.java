package Test;

import CropKeepers.Store;
import CropKeepers.Supplier;
import Crops.Crop;
import Crops.Fruit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public void start(File file1, File file2, File file3) {
        List<Crop> croplar = new ArrayList<>();
        List<Supplier> supplierlar = new ArrayList<>();
        List<Store> storeler = new ArrayList<>();
        try {
            Scanner tedarikcireader = new Scanner(file1);
            Scanner mahsulreader = new Scanner(file2);
            Scanner magazareader = new Scanner(file3);
            while (mahsulreader.hasNextLine()) {
                String line = mahsulreader.nextLine();
                String[] mahsuller = line.split(", ");
                if (mahsuller[1].equals("fruit")) {
                    Crop crop = new Crop(mahsuller[0], mahsuller[1], Integer.valueOf(mahsuller[2]).intValue(), Integer.valueOf(mahsuller[5]).intValue(), Integer.valueOf(mahsuller[6]).intValue());
                    croplar.add(crop);
                } else if (mahsuller[1].equals("vegetable")) {
                    Crop crop = new Crop(mahsuller[0], mahsuller[1], Integer.valueOf(mahsuller[2]), 0, Integer.valueOf(mahsuller[4]).intValue());
                    croplar.add(crop);
                }
            }
            int toplam = 0;
            while (tedarikcireader.hasNextLine()) {
                String line = tedarikcireader.nextLine();
                String[] tedarikciler = line.split(", ");
                Supplier supplier = new Supplier(tedarikciler[0], Integer.valueOf(tedarikciler[1]).intValue(), Integer.valueOf(tedarikciler[2]), new ArrayList<>());
                supplierlar.add(supplier);
            }

            while (magazareader.hasNextLine()) {
                String line = magazareader.nextLine();
                String[] magazalar = line.split(", ");
                Store store = new Store(magazalar[0], Integer.valueOf(magazalar[1]).intValue(), Integer.valueOf(magazalar[2]).intValue(), 0, Integer.valueOf(magazalar[3]).intValue(), new ArrayList<>());
                storeler.add(store);
            }

            for (int i = 0; i < supplierlar.size(); i++) {
                for (int j = 0; j < croplar.size(); j++) {
                    if (croplar.get(j).getCropKeeperId() == supplierlar.get(i).getId()) {
                        supplierlar.get(i).getCropList().add(croplar.get(j));
                    }
                }
            }

            for (int i = 0; i < storeler.size(); i++) {
                for (int j = 0; j < croplar.size(); j++) {
                    if (croplar.get(j).getCropKeeperId() == storeler.get(i).getId()) {
                        storeler.get(i).getFruitList().add(croplar.get(j));
                    }
                }
            }
            for (int i = 0; i < storeler.size(); i++) {
                for (int j = 0; j < croplar.size(); j++) {
                    if (croplar.get(j).getCropKeeperId() == storeler.get(i).getId()) {
                        toplam += croplar.get(j).getWeight();
                    }
                }
                toplam *= storeler.get(i).getKgperSquareMeter();
                storeler.get(i).setUsedCapacityArea(toplam);
                toplam = 0;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Agricultural Management System projesi");
        Scanner islem = new Scanner(System.in);
        String secilenIslem;
        System.out.println("*****************************");
        String transactions = """
                1. Tedarikçi bilgilerini görüntüle
                2. Mağaza bilgilerini görüntüle
                3. Tedarikçi için meyve satın al tedarikçinin bütçesini aldığı ürüne göre azalt ve mağazanın listesinden çıkart tedarikçinin listesine ekle mağazanın kullanılan kapasitesinden satılan ürünün kilosunu çıkar
                4. Tedarikçi için meyve sat tedarikçinin bütçesini sattığı ürünün fiyatına göre arttır tedarikçinin listesinden çıkart mağazanın listesine ekle mağazanın kullanılan kapasitesine alınan ürünün kilosunu ekle
                5. Mağazadan bir meyve çıkartın.
                6. Tedarikçiden mahsül çıkart.
                7. Mağazaya veya tedarikçiye mahsul ekle gerekli bilgileri al
                8. Tedarikçinin kalan bütçesini görüntüle.
                9. Mağazanın kalan kapasitesini göster
                0. Uygulamadan çıkış yap
                """;
        System.out.println(transactions);
        System.out.println("*****************************");
        String urunad;
        String uruntur;
        String uruntad;
        int urunkilo;
        int urunfiyat;
        int control = 0;
        while (true) {
            System.out.println("İşleminizi seçin: ");
            secilenIslem = islem.nextLine();
            if (secilenIslem.equals("1")) {
                /**
                 * Tedarikçilerin bulunduğu listede döner
                 * Bilgileri alır ve bilgileriGoster metoduna gönderir.
                 * **/
                for (int tedarikciler = 0; tedarikciler < supplierlar.size(); tedarikciler++) {
                    supplierlar.get(tedarikciler).bilgileriGoster();
                }
            } else if (secilenIslem.equals("2")) {
                for (int magazalar = 0; magazalar < storeler.size(); magazalar++) {
                    storeler.get(magazalar).bilgileriGoster();
                }
            } else if (secilenIslem.equals("3")) {
                System.out.println("Hangi tedarikçi için ürün alınacak?: ");
                secilenIslem = islem.nextLine();
                for(int tedarikciVarMi=0; tedarikciVarMi < supplierlar.size(); tedarikciVarMi++) {
                    if(supplierlar.get(tedarikciVarMi).getName().equals(secilenIslem)) {
                        System.out.println("Hangi mağazadan ürün alınacak?: ");
                        secilenIslem = islem.nextLine();
                        for(int magazaVarMi=0; magazaVarMi < storeler.size(); magazaVarMi++) {
                            if(storeler.get(magazaVarMi).getName().equals(secilenIslem)) {
                                System.out.println("Alınacak ürün: ");
                                urunad = islem.nextLine();
                                System.out.println("Kaç kilo alınacak?: ");
                                urunkilo = islem.nextInt();
                                if(urunkilo <= 0) {
                                    System.out.println("Ürünün kilosu sıfır veya negatif bir değer olamaz!");
                                    break;
                                }
                                islem.nextLine();
                                supplierlar.get(tedarikciVarMi).buyCrop(storeler.get(magazaVarMi),urunad,urunkilo);
                            }
                        }
                    }
                }
            } else if (secilenIslem.equals("4")) {
                System.out.println("Hangi tedarikçiden ürün satılacak?: ");
                secilenIslem = islem.nextLine();
                for (int tedarikciVarMi = 0; tedarikciVarMi < supplierlar.size(); tedarikciVarMi++) {
                    if (supplierlar.get(tedarikciVarMi).getName().equals(secilenIslem)) {
                        System.out.println("Hangi mağazaya ürün satılacak?: ");
                        secilenIslem = islem.nextLine();
                        for(int magazaVarMi=0; magazaVarMi < storeler.size(); magazaVarMi++) {
                            if(storeler.get(magazaVarMi).getName().equals(secilenIslem)) {
                                System.out.println("Satılacak Ürün: ");
                                urunad = islem.nextLine();
                                System.out.println("Kaç kilo satılacak?: ");
                                urunkilo = islem.nextInt();
                                if(urunkilo <= 0) {
                                    System.out.println("Ürünün kilosu sıfır veya negatif bir değer olamaz!");
                                    break;
                                }
                                islem.nextLine();
                                supplierlar.get(tedarikciVarMi).sellCrop(storeler.get(magazaVarMi),urunad,urunkilo);
                            }
                        }
                    }
                }
            } else if (secilenIslem.equals("5")) {
                for (int magazalar = 0; magazalar < storeler.size(); magazalar++) {
                    storeler.get(magazalar).bilgileriGoster();
                }
                System.out.println("Hangi mağazadan ürün çıkartılacak?: ");
                secilenIslem = islem.nextLine();
                for (int magazaVarMi = 0; magazaVarMi < storeler.size(); magazaVarMi++) {
                    if (storeler.get(magazaVarMi).getName().equals(secilenIslem)) {
                        System.out.println("Çıkarılacak ürünün adı: ");
                        urunad = islem.nextLine();
                        for (int urunVarMi = 0; urunVarMi < storeler.size(); urunVarMi++) {
                            if (storeler.get(magazaVarMi).getFruitList().get(urunVarMi).getName().equals(urunad)) {
                                storeler.get(magazaVarMi).exportCrop(storeler.get(magazaVarMi).getFruitList().get(urunVarMi));
                                break;
                            }

                        }
                    }
                }
            } else if (secilenIslem.equals("6")) {
                System.out.println("Mevcut Tedarikçiler");
                for (int mevcutTedarikciler = 0; mevcutTedarikciler < supplierlar.size(); mevcutTedarikciler++) {
                    supplierlar.get(mevcutTedarikciler).bilgileriGoster();
                }
                System.out.println("Hangi tedarikçiden ürün çıkartılacak?: ");
                secilenIslem = islem.nextLine();
                for (int tedarikciVarMi = 0; tedarikciVarMi < supplierlar.size(); tedarikciVarMi++) {
                    if (supplierlar.get(tedarikciVarMi).getName().equals(secilenIslem)) {
                        System.out.println("Çıkarılacak ürünü girin: ");
                        urunad = islem.nextLine();
                        for (int urunTedarikcideVarMi = 0; urunTedarikcideVarMi < supplierlar.get(tedarikciVarMi).getCropList().size(); urunTedarikcideVarMi++) {
                            if (supplierlar.get(tedarikciVarMi).getCropList().get(urunTedarikcideVarMi).getName().equals(urunad)) {
                                supplierlar.get(tedarikciVarMi).exportCrop(supplierlar.get(tedarikciVarMi).getCropList().get(urunTedarikcideVarMi));
                            }
                        }
                    }
                }
            } else if (secilenIslem.equals("7")) {
                System.out.println("Mahsul nereye eklenecek (Tedarikçi-1 Mağaza-2): ");
                secilenIslem = islem.nextLine();
                if (secilenIslem.equals("1")) {
                    System.out.println("Mevcut Tedarikçiler");
                    System.out.println();
                    for (int mevcutTedarikciler = 0; mevcutTedarikciler < supplierlar.size(); mevcutTedarikciler++) {
                        System.out.println(supplierlar.get(mevcutTedarikciler).getName());
                    }
                    System.out.println();
                    System.out.println("Hangi tedarikçiye mahsul eklenecek?: ");
                    secilenIslem = islem.nextLine();
                    for (int tedarikciVarMi = 0; tedarikciVarMi < supplierlar.size(); tedarikciVarMi++) {
                        if (supplierlar.get(tedarikciVarMi).getName().equals(secilenIslem)) {
                            System.out.println("Ürün bilgileri");
                            System.out.println("Ürünün adı:");
                            urunad = islem.nextLine();
                            System.out.println("Ürünün türü(fruit/vegetable): ");
                            uruntur = islem.nextLine();
                            System.out.println("Ürünün kilosu: ");
                            urunkilo = islem.nextInt();
                            if(urunkilo <= 0) {
                                System.out.println("Ürünün kilosu sıfır veya negatif bir değer olamaz!");
                                break;
                            }
                            islem.nextLine();
                            System.out.println("Ürünün tadı: ");
                            uruntad = islem.nextLine();
                            System.out.println("Ürünün fiyatı: ");
                            urunfiyat = islem.nextInt();
                            if(urunfiyat <= 0) {
                                System.out.println("Ürünün fiyatı sıfır veya negatif bir değer olamaz!");
                                break;
                            }
                            islem.nextLine();
                            Fruit fruit = new Fruit(urunad, uruntur, urunkilo, urunfiyat, supplierlar.get(tedarikciVarMi).getId(), uruntad);
                            if (supplierlar.get(tedarikciVarMi).canBeStored(fruit)) {
                                if (!fruit.karsilastir(fruit)) {
                                    supplierlar.get(tedarikciVarMi).importCrop(fruit);
                                } else if (fruit.karsilastir(fruit)) {
                                    supplierlar.get(tedarikciVarMi).importCrop(fruit);
                                }
                            }
                        }
                    }
                } else if (secilenIslem.equals("2")) {
                    System.out.println("Mevcut Mağazalar");
                    System.out.println();
                    for (int mevcutMagazalar = 0; mevcutMagazalar < storeler.size(); mevcutMagazalar++) {
                        System.out.println(storeler.get(mevcutMagazalar).getName());
                    }
                    System.out.println();
                    System.out.println("Hangi mağazaya mahsul eklenecek?: ");
                    secilenIslem = islem.nextLine();
                    for (int magazaVarMi = 0; magazaVarMi < storeler.size(); magazaVarMi++) {
                        if (storeler.get(magazaVarMi).getName().equals(secilenIslem)) {
                            System.out.println("Ürün bilgileri");
                            System.out.println("Ürünün adı: ");
                            urunad = islem.nextLine();
                            System.out.println("Ürünün türü(fruit/vegetable): ");
                            uruntur = islem.nextLine();
                            System.out.println("Ürünün kilosu: ");
                            urunkilo = islem.nextInt();
                            if(urunkilo <= 0) {
                                System.out.println("Ürünün kilosu sıfır veya negatif bir değer olamaz!");
                                break;
                            }
                            islem.nextLine();
                            System.out.println("Ürünün tadı: ");
                            uruntad = islem.nextLine();
                            System.out.println("Ürünün fiyatı: ");
                            urunfiyat = islem.nextInt();
                            if(urunfiyat <= 0) {
                                System.out.println("Ürünün fiyatı sıfır veya negatif bir değer olamaz!");
                                break;
                            }
                            islem.nextLine();
                            Fruit fruit = new Fruit(urunad, uruntur, urunkilo, urunfiyat, storeler.get(magazaVarMi).getId(), uruntad);
                            if (storeler.get(magazaVarMi).canBeStored(fruit)) {
                                if (!fruit.karsilastir(fruit)) {
                                    storeler.get(magazaVarMi).importCrop(fruit);
                                } else if (fruit.karsilastir(fruit)) {
                                    storeler.get(magazaVarMi).importCrop(fruit);
                                }
                            }
                        }
                    }
                }
            } else if(secilenIslem.equals("0")) {
                return;
            }
        }
    }
}