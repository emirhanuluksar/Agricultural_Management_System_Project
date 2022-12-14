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
                1. Tedarik??i bilgilerini g??r??nt??le
                2. Ma??aza bilgilerini g??r??nt??le
                3. Tedarik??i i??in meyve sat??n al tedarik??inin b??t??esini ald?????? ??r??ne g??re azalt ve ma??azan??n listesinden ????kart tedarik??inin listesine ekle ma??azan??n kullan??lan kapasitesinden sat??lan ??r??n??n kilosunu ????kar
                4. Tedarik??i i??in meyve sat tedarik??inin b??t??esini satt?????? ??r??n??n fiyat??na g??re artt??r tedarik??inin listesinden ????kart ma??azan??n listesine ekle ma??azan??n kullan??lan kapasitesine al??nan ??r??n??n kilosunu ekle
                5. Ma??azadan bir meyve ????kart??n.
                6. Tedarik??iden mahs??l ????kart.
                7. Ma??azaya veya tedarik??iye mahsul ekle gerekli bilgileri al
                8. Tedarik??inin kalan b??t??esini g??r??nt??le.
                9. Ma??azan??n kalan kapasitesini g??ster
                0. Uygulamadan ????k???? yap
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
            System.out.println("????leminizi se??in: ");
            secilenIslem = islem.nextLine();
            if (secilenIslem.equals("1")) {
                /**
                 * Tedarik??ilerin bulundu??u listede d??ner
                 * Bilgileri al??r ve bilgileriGoster metoduna g??nderir.
                 * **/
                for (int tedarikciler = 0; tedarikciler < supplierlar.size(); tedarikciler++) {
                    supplierlar.get(tedarikciler).bilgileriGoster();
                }
            } else if (secilenIslem.equals("2")) {
                for (int magazalar = 0; magazalar < storeler.size(); magazalar++) {
                    storeler.get(magazalar).bilgileriGoster();
                }
            } else if (secilenIslem.equals("3")) {
                System.out.println("Hangi tedarik??i i??in ??r??n al??nacak?: ");
                secilenIslem = islem.nextLine();
                for(int tedarikciVarMi=0; tedarikciVarMi < supplierlar.size(); tedarikciVarMi++) {
                    if(supplierlar.get(tedarikciVarMi).getName().equals(secilenIslem)) {
                        System.out.println("Hangi ma??azadan ??r??n al??nacak?: ");
                        secilenIslem = islem.nextLine();
                        for(int magazaVarMi=0; magazaVarMi < storeler.size(); magazaVarMi++) {
                            if(storeler.get(magazaVarMi).getName().equals(secilenIslem)) {
                                System.out.println("Al??nacak ??r??n: ");
                                urunad = islem.nextLine();
                                System.out.println("Ka?? kilo al??nacak?: ");
                                urunkilo = islem.nextInt();
                                if(urunkilo <= 0) {
                                    System.out.println("??r??n??n kilosu s??f??r veya negatif bir de??er olamaz!");
                                    break;
                                }
                                islem.nextLine();
                                supplierlar.get(tedarikciVarMi).buyCrop(storeler.get(magazaVarMi),urunad,urunkilo);
                            }
                        }
                    }
                }
            } else if (secilenIslem.equals("4")) {
                System.out.println("Hangi tedarik??iden ??r??n sat??lacak?: ");
                secilenIslem = islem.nextLine();
                for (int tedarikciVarMi = 0; tedarikciVarMi < supplierlar.size(); tedarikciVarMi++) {
                    if (supplierlar.get(tedarikciVarMi).getName().equals(secilenIslem)) {
                        System.out.println("Hangi ma??azaya ??r??n sat??lacak?: ");
                        secilenIslem = islem.nextLine();
                        for(int magazaVarMi=0; magazaVarMi < storeler.size(); magazaVarMi++) {
                            if(storeler.get(magazaVarMi).getName().equals(secilenIslem)) {
                                System.out.println("Sat??lacak ??r??n: ");
                                urunad = islem.nextLine();
                                System.out.println("Ka?? kilo sat??lacak?: ");
                                urunkilo = islem.nextInt();
                                if(urunkilo <= 0) {
                                    System.out.println("??r??n??n kilosu s??f??r veya negatif bir de??er olamaz!");
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
                System.out.println("Hangi ma??azadan ??r??n ????kart??lacak?: ");
                secilenIslem = islem.nextLine();
                for (int magazaVarMi = 0; magazaVarMi < storeler.size(); magazaVarMi++) {
                    if (storeler.get(magazaVarMi).getName().equals(secilenIslem)) {
                        System.out.println("????kar??lacak ??r??n??n ad??: ");
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
                System.out.println("Mevcut Tedarik??iler");
                for (int mevcutTedarikciler = 0; mevcutTedarikciler < supplierlar.size(); mevcutTedarikciler++) {
                    supplierlar.get(mevcutTedarikciler).bilgileriGoster();
                }
                System.out.println("Hangi tedarik??iden ??r??n ????kart??lacak?: ");
                secilenIslem = islem.nextLine();
                for (int tedarikciVarMi = 0; tedarikciVarMi < supplierlar.size(); tedarikciVarMi++) {
                    if (supplierlar.get(tedarikciVarMi).getName().equals(secilenIslem)) {
                        System.out.println("????kar??lacak ??r??n?? girin: ");
                        urunad = islem.nextLine();
                        for (int urunTedarikcideVarMi = 0; urunTedarikcideVarMi < supplierlar.get(tedarikciVarMi).getCropList().size(); urunTedarikcideVarMi++) {
                            if (supplierlar.get(tedarikciVarMi).getCropList().get(urunTedarikcideVarMi).getName().equals(urunad)) {
                                supplierlar.get(tedarikciVarMi).exportCrop(supplierlar.get(tedarikciVarMi).getCropList().get(urunTedarikcideVarMi));
                            }
                        }
                    }
                }
            } else if (secilenIslem.equals("7")) {
                System.out.println("Mahsul nereye eklenecek (Tedarik??i-1 Ma??aza-2): ");
                secilenIslem = islem.nextLine();
                if (secilenIslem.equals("1")) {
                    System.out.println("Mevcut Tedarik??iler");
                    System.out.println();
                    for (int mevcutTedarikciler = 0; mevcutTedarikciler < supplierlar.size(); mevcutTedarikciler++) {
                        System.out.println(supplierlar.get(mevcutTedarikciler).getName());
                    }
                    System.out.println();
                    System.out.println("Hangi tedarik??iye mahsul eklenecek?: ");
                    secilenIslem = islem.nextLine();
                    for (int tedarikciVarMi = 0; tedarikciVarMi < supplierlar.size(); tedarikciVarMi++) {
                        if (supplierlar.get(tedarikciVarMi).getName().equals(secilenIslem)) {
                            System.out.println("??r??n bilgileri");
                            System.out.println("??r??n??n ad??:");
                            urunad = islem.nextLine();
                            System.out.println("??r??n??n t??r??(fruit/vegetable): ");
                            uruntur = islem.nextLine();
                            System.out.println("??r??n??n kilosu: ");
                            urunkilo = islem.nextInt();
                            if(urunkilo <= 0) {
                                System.out.println("??r??n??n kilosu s??f??r veya negatif bir de??er olamaz!");
                                break;
                            }
                            islem.nextLine();
                            System.out.println("??r??n??n tad??: ");
                            uruntad = islem.nextLine();
                            System.out.println("??r??n??n fiyat??: ");
                            urunfiyat = islem.nextInt();
                            if(urunfiyat <= 0) {
                                System.out.println("??r??n??n fiyat?? s??f??r veya negatif bir de??er olamaz!");
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
                    System.out.println("Mevcut Ma??azalar");
                    System.out.println();
                    for (int mevcutMagazalar = 0; mevcutMagazalar < storeler.size(); mevcutMagazalar++) {
                        System.out.println(storeler.get(mevcutMagazalar).getName());
                    }
                    System.out.println();
                    System.out.println("Hangi ma??azaya mahsul eklenecek?: ");
                    secilenIslem = islem.nextLine();
                    for (int magazaVarMi = 0; magazaVarMi < storeler.size(); magazaVarMi++) {
                        if (storeler.get(magazaVarMi).getName().equals(secilenIslem)) {
                            System.out.println("??r??n bilgileri");
                            System.out.println("??r??n??n ad??: ");
                            urunad = islem.nextLine();
                            System.out.println("??r??n??n t??r??(fruit/vegetable): ");
                            uruntur = islem.nextLine();
                            System.out.println("??r??n??n kilosu: ");
                            urunkilo = islem.nextInt();
                            if(urunkilo <= 0) {
                                System.out.println("??r??n??n kilosu s??f??r veya negatif bir de??er olamaz!");
                                break;
                            }
                            islem.nextLine();
                            System.out.println("??r??n??n tad??: ");
                            uruntad = islem.nextLine();
                            System.out.println("??r??n??n fiyat??: ");
                            urunfiyat = islem.nextInt();
                            if(urunfiyat <= 0) {
                                System.out.println("??r??n??n fiyat?? s??f??r veya negatif bir de??er olamaz!");
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