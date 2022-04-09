package com.dembs.android.mobildenetim.network;

public class RetrofitCall  {
//    Context context;
//    String ruhsatNo;
//    public void RetrofitCall (Context context,String ruhsatNo)
//    {
//        this.context=context;
//        this.ruhsatNo=ruhsatNo;
//    }
//    List<Ruhsat> ruhsatList;
//    Api jsonPlaceHolderApi;
//    //Api jsonPlaceHolderApi;
//     Gson gson = new GsonBuilder().serializeNulls().create();
//
//    private Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("http://192.168.1.103:1133/api/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(new OkHttpClient())
//            .build();
//
//    public Api getJsonPlaceHolderApi() {
//        jsonPlaceHolderApi=retrofit.create(Api.class);
//        return jsonPlaceHolderApi;
//    }
//
//    public List<Ruhsat> getRuhsatList() {
//
//        Call<List<Ruhsat>> call = getJsonPlaceHolderApi().getRuhsat(ruhsatNo);
//        call.enqueue(new Callback<List<Ruhsat>>() {
//            @Override
//            public void onResponse(Call<List<Ruhsat>> call, Response<List<Ruhsat>> response) {
//
//                if (!response.isSuccessful()) {
//                    String temp = "Code: " + response.code();
//                    return;
//                }
//
//
//
//                ruhsatList = response.body();
//            }
//
//            @Override
//            public void onFailure(Call<List<Ruhsat>> call, Throwable t) {
//
//                Toast.makeText(context,String.valueOf(t),Toast.LENGTH_LONG).show();
//            }
//        });
//        return ruhsatList;
//    }
}

