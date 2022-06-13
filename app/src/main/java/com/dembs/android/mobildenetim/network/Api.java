package com.dembs.android.mobildenetim.network;

import com.dembs.android.mobildenetim.models.AracLine;
import com.dembs.android.mobildenetim.models.AracReklam;
import com.dembs.android.mobildenetim.models.AracReklamResult;
import com.dembs.android.mobildenetim.models.BandrolLine;
import com.dembs.android.mobildenetim.models.BelgeLineResult;
import com.dembs.android.mobildenetim.models.BelgeOzetLine;
import com.dembs.android.mobildenetim.models.CezaLine;
import com.dembs.android.mobildenetim.models.Denetim;
import com.dembs.android.mobildenetim.models.DenetimDeleteKontrol;
import com.dembs.android.mobildenetim.models.DenetimDenetleyen;
import com.dembs.android.mobildenetim.models.DenetimDenetleyenRapor;
import com.dembs.android.mobildenetim.models.DenetimForm;
import com.dembs.android.mobildenetim.models.DenetimFormItem;
import com.dembs.android.mobildenetim.models.DenetimFormItemResult;
import com.dembs.android.mobildenetim.models.DenetimImageModel;
import com.dembs.android.mobildenetim.models.DenetimLine;
import com.dembs.android.mobildenetim.models.DenetimPersonel;
import com.dembs.android.mobildenetim.models.DenetleyenAdd;
import com.dembs.android.mobildenetim.models.DuzeltmeTalebi;
import com.dembs.android.mobildenetim.models.DuzeltmeTalebiAdd;
import com.dembs.android.mobildenetim.models.Guzergah;
import com.dembs.android.mobildenetim.models.Kisi;
import com.dembs.android.mobildenetim.models.RehberPersonelLine;
import com.dembs.android.mobildenetim.models.ReklamResim;
import com.dembs.android.mobildenetim.models.Ruhsat;
import com.dembs.android.mobildenetim.models.SoforBilgisi;
import com.dembs.android.mobildenetim.models.TasinanKurumLine;
import com.dembs.android.mobildenetim.models.Token;
import com.dembs.android.mobildenetim.models.User;
import com.dembs.android.mobildenetim.models.Varaka;
import com.dembs.android.mobildenetim.models.VarakaBekleyen;
import com.dembs.android.mobildenetim.models.VarakaPrint;
import com.dembs.android.mobildenetim.models.VarakaResim;
import com.dembs.android.mobildenetim.models.VarakaResult;
import com.dembs.android.mobildenetim.models.VarakaSms;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    //********************************************Authentication**************************************

    @POST("Authentication/v1/token")//YAPILDI
    Call<Token> Authentication(@Body User user);
    //********************************************Ruhsat*********************************************

    @GET("Ruhsat/v1/ruhsatlar/{plaka}")//YAPILDI
    Call<ArrayList<Ruhsat>> getRuhsat(@Path("plaka") String plakaNo);

    @GET("Ruhsat/v1/araclar/{plaka}")//YAPILDI
    Call<ArrayList<AracLine>> getAracBilgisi(@Path("plaka") String plaka);

    //ruhsata ait aracı getitirir.
    @GET("Ruhsat/v1/araclar/{aracId}")
    Call<AracLine> getAracBilgisi(@Path("aracId") int aracId);

    @GET("ruhsat/v1/araclar/{plaka}")
    Observable<ArrayList<AracLine>> getAracBilgisiObservable(@Path("plaka") String plaka);

    @GET("Ruhsat/v1/bandrol/{plaka}")
    Call<ArrayList<BandrolLine>> getBandrolList(@Path("plaka") String plaka);

    @GET("Ruhsat/v1/belgeler/{plaka}")//YAPILDI
    Call<ArrayList<BelgeOzetLine>> getBelgeList(@Path("plaka") String plaka);

    @GET("Ruhsat/v1/belge-tasinan-kurumlar/{belgeId}")//YAPILDI
    Call<ArrayList<TasinanKurumLine>> getTasinanKurumList(@Path("belgeId") int belgeId);

    @GET("Ruhsat/v1/belge-guzergah/{belgeId}")//YAPILDI
    Call<ArrayList<Guzergah>> getGuzergahList(@Path("belgeId") int belgeId);

    @GET("Ruhsat/v1/belge-rehber-personel/{belgeId}")//YAPILDI
    Call<ArrayList<RehberPersonelLine>> getRehberPersonelList(@Path("belgeId") int belgeId);


    @GET("Egitim/v1/sofor-egitim/{tcKimlikNo}/{denetimTurId}")
    Call<ArrayList<SoforBilgisi>> getSoforBilgisi(@Path("tcKimlikNo") String tcKimlikNo,
                                                  @Path("denetimTurId") int denetimTurId);
    @GET("Egitim/v1/sofor-egitim/{plaka}")
    Call<ArrayList<SoforBilgisi>> getSoforBilgisi(@Path("plaka") String plaka);
    //********************************************Denetim*********************************************
    @GET("Denetim/v1/denetimler/{plaka}")//YAPILDI
//Denetim Get
    Call<ArrayList<DenetimLine>> getDenetimler(@Path("plaka") String plaka);

    @POST("denetim/v1/denetimler/add")
//Denetim insert Link
    Call<Denetim> addDenetim(@Body Denetim denetim);

    @DELETE("denetim/v1/denetimler/{denetimId}")
//Denetim Delete
    Call<Void> DenetimDelete(@Path("denetimId") int denetimId);

    @PUT("denetim/v1/denetimler/update")//YAPILDI
//Denetim update
    Call<Void> updateDenetim(@Body Denetim denetim);

    @GET("denetim/v1/denetim-delete-kontrol/{denetimId}")
    Call<DenetimDeleteKontrol> getDenetimDeleteKontrol(@Path("denetimId") int denetimId);
    /***************Denetim Image*****************/
    @GET("Denetim/v1/denetim-image-list/{denetimId}/{loadImage}")//sadece isimler için loadimage i false gönder
    Call<ArrayList<DenetimImageModel>> getDenetimImageList(@Path("denetimId") int denetimId, @Path("loadImage") boolean loadImage);

    @GET("Denetim/v1/denetim-image/{imageId}")
    Call<DenetimImageModel> getDenetimImage(@Path("imageId") int imageId);

    @DELETE("Denetim/v1/denetim-image/{imageId}")
    Call<Void> deleteDenetimImage(@Path("imageId") int imageId);

    @POST("Denetim/v1/denetim-upload-resim")
    Call<Void> uploadDenetimImage(@Body DenetimImageModel imageModel);



    //********************************************DenetimForm*********************************************

    @GET("denetimform/v1/form-items/{denetimId}")
    Observable<ArrayList<DenetimFormItem>> getDenetimFormItems(@Path("denetimId") int denetimId);

    @GET("denetimform/v1/form/{formId}")
    Observable<ArrayList<DenetimForm>> getDenetimForms(@Path("formId") int formId);//denetim tip id alır

    @POST("denetimform/v1/form/add")
    Call<Void> denetimFormItemAdd(@Body DenetimFormItemResult denetimFormItemResult);


    //********************************************Varaka*********************************************

    @GET("Varaka/v1/varakalar/{denetimId}/{formItemId}")
//denetime ait tüm varakalar için itemid 0 gönder
    Call<List<VarakaResult>> getVarakaList( @Path("denetimId") int denetimId,@Path("formItemId") int itemId);

    @POST("Varaka/v1/varakalar/add")
//Varaka insert
    Call<Void> addVaraka(@Body Varaka varaka);

    @DELETE("Varaka/v1/varakalar/{varakaId}")
//Varaka Delete
    Call<Void> deleteVaraka(@Path("varakaId") int varakaId);

    @PUT("Varaka/v1/varakalar/update")
//Varaka update
    Call<Void> updateVaraka(@Body Varaka varaka);

    @POST("Varaka/v1/varaka-upload-resim")
//varaka upload image
    Call<Void> uploadVarakaImage(@Body VarakaResim varakaResim);

    @GET("Varaka/v1/varaka-image-list/{varakaId}/{loadImage}")//sadece isimler olarak resim almak istiyorsan false gönder
//Get Varaka Resim List
    Call<ArrayList<VarakaResim>> getVarakaImageList(@Path("varakaId") int varakaId, @Path("loadImage") boolean loadImage);//load Image servisle beraber resimlerde gelsinmi demek false ise sadece resim bilgileri gelir true iken resim de beraber gleir.

    @GET("Varaka/v1/varaka-image/{imageId}")//sadece image ı getitrir image ıd ye göre
//Get Varaka Resim by İmageID 19 nolu image
    Call<VarakaResim> getVarakaImage(@Path("imageId") int imageId);//load Image servisle beraber resimlerde gelsinmi demek false ise sadece resim bilgileri gelir true iken resim de beraber gleir.


    @DELETE("Varaka/v1/varaka-image/{imageId}")//sadece image ı getitrir image ıd ye göre
//Get Varaka Resim by İmageID 19 nolu image
    Call<Void> deleteVarakaImage(@Path("imageId") int imageId);//load Image servisle beraber resimlerde gelsinmi demek false ise sadece resim bilgileri gelir true iken resim de beraber gleir.

    @GET("Varaka/v1/varaka-print/{varakaId}")
    Call<VarakaPrint> getVarakaPrint(@Path("varakaId") int varakaId);

    @GET("Varaka/v1/varaka-bekleyenler-detay/{plaka}")
    Observable<ArrayList<VarakaBekleyen>> varakaBekleyenler2(@Path("plaka") String plaka);

    @GET("Varaka/v1/varaka-bekleyenler-detay/{plaka}")
//denetime ait tüm varakalar için itemid gönder
    Call<ArrayList<VarakaBekleyen>> varakaBekleyenler(@Path("plaka") String plaka);

    @GET("Varaka/v1/varaka-bekleyenler-detay/{plaka}")
//denetime ait tüm varakalar için itemid gönder
    Call<ArrayList<VarakaResult>> varakaBekleyenlerDetay(@Path("plaka") String ruhsatNo);

    //********************************************DuzeltmeTalebi*********************************************
    @GET("Denetim/v1/duzeltme-talebi-list/{denetimId}")
    Call<List<DuzeltmeTalebi>> getDuzeltmeTalebi(@Path("denetimId") int denetimId);

    @POST("Denetim/v1/duzeltme-talebi/add")
    Call<Void> addDuzeltmeTalebi(@Body DuzeltmeTalebiAdd duzeltmeTalebiAdd);

    @GET("Denetim/v1/duzeltme-bekleyenler/{plaka}")
    Observable<ArrayList<DuzeltmeTalebi>> getDuzeltmeTalebiBekleyen(@Path("plaka") String plaka);

    //********************************************Ceza*********************************************
    //@Headers({ "Content-Type: application/json;charset=UTF-8"})
    //GET /api/Ceza/v1/cezalar/{plaka}

//    @GET("Ceza/v1/cezalar/{ruhsatNo}")
//    Call<List<Ceza>> getCezaList(@Path("ruhsatNo") String ruhsatNo);

    @GET("Ceza/v1/cezalar/{plaka}")
    Call<ArrayList<CezaLine>> getCezaList(@Path("plaka") String plakaNo);


    //********************************************Reklam*********************************************
    @GET("Reklam/v1/reklam/{plaka}/{reklamId}")//YAPILDI
    Observable<ArrayList<AracReklamResult>> getReklamList(@Path("plaka") String plaka,
                                                          @Path("reklamId") int reklamId);//reklam id 0 ise tümünü döndürür.

    @GET("Reklam/v1/reklam/{plaka}/{reklamId}")
    Call<ArrayList<AracReklamResult>> getReklamListWithCall(@Path("plaka") String plaka,
                                                    @Path("reklamId") int reklamId);

    @GET("Reklam/v1/araclar/{plaka}")//YAPILDI
    Observable<ArrayList<AracLine>> getAracBilgisiReklam(@Path("plaka") String plaka);

    @DELETE("Reklam/v1/reklam/{reklamId}")//YAPILDI
    Call<Void> deleteReklam(@Path("reklamId") int reklamId);

    @PUT("Reklam/v1/reklam/update")
//reklam update
    Call<Void> updateReklam(@Body AracReklam aracReklam);

    @POST("Reklam/v1/reklam/add")
//reklam ekleme
    Call<AracReklam> addReklam(@Body AracReklam aracReklam);

    @POST("Reklam/v1/arac-reklam-upload-image")
//reklam ekleme
    Call<Void> uploadReklamResim(@Body ReklamResim reklamResim);

    @GET("Reklam/v1/reklam-image-list/{reklamId}/{loadImage}")
    Call<ArrayList<ReklamResim>> getReklamResimList(@Path("reklamId") int reklamId,@Path("loadImage") boolean loadImage);

    @GET("Reklam/v1/reklam-image/{imageId}")
    Call<ReklamResim> getReklamResim(@Path("imageId") int imageId);

    @DELETE("Reklam/v1/reklam-resim/{imageId}")
    Call<Void> deleteResim(@Path("imageId") int imageId);


    //****************************************SMS*****************************************************************
    @POST("Message/v1/send-sms")
//reklam ekleme
    Call<String> varakaSms(@Body VarakaSms varakaSms);

    //******************************************** Denetim Denetleyen*********************************************
    @GET("Denetim/v1/denetim-personel")
    Call<List<DenetimPersonel>> getDenetimDenetleyenAll();

        @GET("Denetim/v1/denetim-denetleyen/{denetimId}")
    Call<List<DenetimDenetleyen>> getDenetimDenetleyen(@Path("denetimId") int denetimId);

    @GET("Denetim/v1/denetleyen-rapor-ekip/{denetimTarihi}/{ekipId}")
    Call<List<DenetimDenetleyenRapor>> getDenetimDenetleyenRapor(@Path("denetimTarihi") String denetimTarihi,@Path("ekipId") int ekipId);
//Todo mernis servisi sorulacak
    @GET("Ruhsat/v1/kisi-bilgisi/{tcNo}/{dogumTarihi}")
    Call<Kisi> getKisiBilgiByTcNo(@Path("tcNo") String tcNo,@Path("dogumTarihi") String dogumTarihi);

    @DELETE("Denetim/v1/denetim-denetleyen/{denetimId}/{kullaniciId}")
    Call<Void> deleteDenetleyen(@Path("denetimId") int denetimId,
                                @Path("kullaniciId") int kullaniciId);
    @POST("Denetim/v1/denetim-denetleyen/add")
    Call<Void> denetimDenetleyenAdd(@Body DenetleyenAdd denetleyenAdd);


}