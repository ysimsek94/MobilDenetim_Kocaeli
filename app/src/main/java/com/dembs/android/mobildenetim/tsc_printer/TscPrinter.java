//package com.dembs.android.mobildenetim.tsc_printer;
//
//import static com.dembs.android.mobildenetim.utils.Constants.userName;
//
//import android.graphics.Bitmap;
//
//import com.example.tscdll.TSCActivity;
//
//public class TscPrinter implements  IMobilePrinter{
//    TSCActivity BT = new TSCActivity();
//    @Override
//    public void connectBluetoothDevice() {
//
//
//        BT.setup(1000, 250, 4, 4, 0, 0, 0);
//        BT.sendcommand("SPEED 4\r\n");
//        BT.sendcommand("DENSITY 10\r\n");//yazının koyuluğunu yoğunlugu
//        BT.sendcommand("DIRECTION 0\r\n");
//        BT.clearbuffer();
//
//    }
//
//    @Override
//    public boolean isConnectBluetoothDevice(String deviceAdres) {
//        BT.openport(deviceAdres);
//        return   BT.IsConnected;
//    }
//
//    @Override
//    public void print(String deviceAdres,
//            Bitmap bitmap,
//                      String varakaNo,
//                      String tcNo,
//                      String adSoyad,
//                      String plaka,
//                      String ikametAdres,
//                      String ihlalAdres,
//                      String ihlalTarihSaat,
//                      String ihlalBendAdi,
//                      String ihlalMaddeAdi,
//                      String ihlalBendAciklama,
//                      String varakaAciklama,
//                      String sicil1,
//                      String sicil2,
//                      String sicil3,
//                      String tebligTarihsaat,
//                      String mudur,
//                      String ukomeMetin
//                      ) {
//BT.sendbitmap_resize(20, 1,bitmap,750,100);
//
//BT.sendcommand("TEXT 750,150,\"2\",0,1,1,3,\"   No : "+varakaNo+"\"\r\n"); //alignment örnek center
//
//BT.sendcommand("TEXT 40,200,\"2\",0,1,1,\"TC / Vergi No        : "+tcNo+"\"\r\n"); //alignment örnek center
//
//BT.sendcommand("TEXT 40,230,\"2\",0,1,1,\"Ilgilinin Adı Soyadı : "+adSoyad+"\"\r\n"); //alignment örnek center
//
//BT.sendcommand("TEXT 40,275,\"2\",0,1,1,\"Plaka                : "+plaka+"\"\r\n"); //alignment örnek center
//
//BT.sendcommand("BLOCK 40,320,750,100,\"2\",0,1,1,15,1,\"Ikamet Adresi        : " +ikametAdres+"\"\r\n");//750 width değeri 100 height değeri
//
//BT.sendcommand("BLOCK 40,410,790,100,\"2\",0,1,1,15,1,\"Ihlal Adresi         : " +ihlalAdres+"\"\r\n");
//
//BT.sendcommand("TEXT 40,500,\"2\",0,1,1,\"Ihlal Tarih/Saat     : "+ihlalTarihSaat+"\"\r\n");
//
//BT.sendcommand("BLOCK 40,560,750,150,\"2\",0,1,1,5,1,\"Yukarıda bilgileri yazılı kişinin ilgili yönetmeliğin " + ihlalMaddeAdi + " maddesinin " + ihlalBendAdi + " bendini ihlal ettiği görüldüğünden hakkında kanuni işlem yapılmak için iş bu varaka tanzim olundu."+"\"\r\n");
//
//BT.sendcommand("BLOCK 40,770,790,160,\"2\",0,1,1,5,1,\"Ihlal Bendi    : " +ihlalBendAciklama+"\"\r\n");
//
//BT.sendcommand("BLOCK 40,980,790,120,\"2\",0,1,1,5,1,\"Ihlal Açıklaması    : " +varakaAciklama+"\"\r\n");
//
//BT.sendcommand("TEXT 40,1130,\"2\",0,1,1,\"MUHATABIN ADI SOYADI\"\r\n");
//
////BT.sendcommand("TEXT 750,1130,\"2\",0,1,1,3,\"BELEDİYE DENETİM PERSONELİ\"\r\n");
//
//BT.sendcommand("TEXT 100,1160,\"2\",0,1,1,\"IMZASI\"\r\n");
//
//BT.sendcommand("TEXT 80,1190,\"2\",0,1,1,\""+adSoyad+"\"\r\n");
//
////BT.sendcommand("TEXT 700,1190,\"2\",0,1,1,3,\"Sicil No   : "+sicil1+"\"\r\n");
////BT.sendcommand("TEXT 700,1250,\"2\",0,1,1,3,\"Sicil No   : "+sicil2+"\"\r\n");
////BT.sendcommand("TEXT 700,1310,\"2\",0,1,1,3,\"Sicil No   : "+sicil3+"\"\r\n");
//
//BT.sendcommand("TEXT 40,1340,\"2\",0,1,1,\"Tebliğ Tarihi/Saati\"\r\n");
//BT.sendcommand("TEXT 60,1370,\"2\",0,1,1,\""+tebligTarihsaat+"\"\r\n");
//BT.sendcommand("TEXT 100,1430,\"2\",0,1,1,\""+userName+"\"\r\n");
//BT.sendcommand("TEXT 40,1460,\"2\",0,1,1,\"Görevli Personel\"\r\n");
////BT.sendcommand("TEXT 40,1460,\"2\",0,1,1,\"Toplu Taşıma Şube Müdürü\"\r\n");
////BT.sendcommand("BLOCK 40,1510,790,250,\"2\",0,1,1,5,1,\"+ukomeMetin+\"\r\n");
//BT.sendcommand("BLOCK 40,1510,790,250,\"2\",0,1,1,5,1,\" " +ukomeMetin+"\"\r\n");
//BT.sendcommand("PRINT 1\r\n");
//
//BT.closeport(2000);
////return BT.IsConnected;
//    }
//}
