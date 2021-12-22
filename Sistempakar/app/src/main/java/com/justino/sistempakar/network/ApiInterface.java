package com.justino.sistempakar.network;


import com.justino.sistempakar.auth.ConfirmDeleteUsers;
import com.justino.sistempakar.auth.GetUsers;
import com.justino.sistempakar.auth.Login;
import com.justino.sistempakar.auth.LoginRegisterUsers;
import com.justino.sistempakar.berita.GetBerita;
import com.justino.sistempakar.berita.GetResult;
import com.justino.sistempakar.berita.PostPutDelBerita;
import com.justino.sistempakar.berita.Result;
import com.justino.sistempakar.gejala.GetGejala;
import com.justino.sistempakar.gejala.PostGejala;
import com.justino.sistempakar.penyakit.GetPenyakit;
import com.justino.sistempakar.penyakit.PostPutDelPenyakit;
import com.justino.sistempakar.riwayat.GetRiwayat;
import com.justino.sistempakar.riwayat.PostRiwayat;
import com.justino.sistempakar.user.PostUser;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {


    //===========================Users==================================//

    @GET("users")
    Call<GetUsers> getUsers();

    @POST("users/getusername")
    @FormUrlEncoded
    Call<LoginRegisterUsers> getusername(@Field("nama_user") String nama_user);

    @POST("users/getemail")
    @FormUrlEncoded
    Call<LoginRegisterUsers> getemail(@Field("email_user") String email_user);

    @POST("users/searchuser")
    @FormUrlEncoded
    Call<PostUser> searchuser(@Field("nama_user") String nama_user);

    @POST("users/forgotpass")
    @FormUrlEncoded
    Call<LoginRegisterUsers> forgotpass(@Field("email_user") String email_user);

    @POST("users/resetpassword")
    @FormUrlEncoded
    Call<LoginRegisterUsers> resetpass(@Field("nama_user") String nama_user,
                                       @Field("password_user") String password_user);

    @POST("users/verifikasikode")
    @FormUrlEncoded
    Call<LoginRegisterUsers> verifikasikode(@Field("password_user") String password_user);

    @POST("users/tampiluser")
    @FormUrlEncoded
    Call<PostUser> showuser(@Field("status_user") String status_user);

    @POST("users/login")
    @FormUrlEncoded
    Call<Login> loginUsers(@Field("nama_user") String nama_user,
                           @Field("password_user") String password_user);


    @POST("users/register")
    @FormUrlEncoded
    Call<LoginRegisterUsers> regisUser(@Field("nama_user") String nama_user,
                                       @Field("nohp_user") String nohp_user,
                                       @Field("email_user") String email_user,
                                       @Field("password_user") String password_user,
                                       @Field("jabatan_user") int jabatan_user);

    @POST("users/updateregistrasi")
    @FormUrlEncoded
    Call<ConfirmDeleteUsers> konfirmasiuser(@Field("id_user") String id_user,
                                            @Field("nama_user") String nama_user);

    @POST("users/deleteregistrasi")
    @FormUrlEncoded
    Call<ConfirmDeleteUsers> deletekonfirmasiuser(@Field("id_user") String id_user,
                                                  @Field("nama_user") String nama_user);

    @POST("users/tampilregistrasi")
    @FormUrlEncoded
    Call<LoginRegisterUsers> tampilregistrasiuser(@Field("status_user") String status_user);


    @POST("users/updateuser")
    @FormUrlEncoded
    Call<PostUser> updateuser(@Field("id_user") String id_user,
                              @Field("nama_user") String nama_user,
                              @Field("nohp_user") String nohp_user,
                              @Field("email_user") String email_user);

    @POST("users/deleteuser")
    @FormUrlEncoded
    Call<PostUser> deleteuser(@Field("id_user") String id_user,
                              @Field("nama_user") String nama_user);

    //===========================Gejala==================================//

    @GET("gejala")
    Call<GetGejala> getGejala();

    @POST("gejala/updategejala")
    @FormUrlEncoded
    Call<PostGejala> updategejala(@Field("kode_gejala") String kode_gejala,
                                  @Field("nama_gejala") String nama_gejala);

    @POST("gejala/deletegejala")
    @FormUrlEncoded
    Call<PostGejala> deletegejala(@Field("kode_gejala") String kode_gejala,
                                  @Field("nama_gejala") String nama_gejala);

    @POST("gejala/tampilgejala")
    @FormUrlEncoded
    Call<PostGejala> showgejala(@Field("kode_gejala") String kode_gejala,
                                @Field("nama_gejala") String nama_gejala);

    @POST("gejala/searchgejala")
    @FormUrlEncoded
    Call<PostGejala> searchgejala(@Field("nama_gejala") String nama_gejala);

    @POST("gejala/tambahgejala")
    @FormUrlEncoded
    Call<PostGejala> tambahgejala(@Field("nama_gejala") String nama_gejala);

    //===========================Penyakit==================================//
    @GET("penyakit/result")
    Call<GetResult> getResult();

    @GET("penyakit/deleteResult")
    Call<GetResult> deleteResult();

    @POST("penyakit/addresult")
    @FormUrlEncoded
    Call<GetResult> tambahResult(@Field("namapenyakit") String namapenyakit,
                             @Field("hasilpenyakit") String hasilpenyakit);

    @POST("penyakit/addriwayat")
    @FormUrlEncoded
    Call<GetResult> tambahriwayat(@Field("namapenyakit") String namapenyakit,
                                 @Field("hasilpenyakit") String hasilpenyakit);
    @GET("penyakit")
    Call<GetPenyakit> getPenyakit();

    @POST("penyakit/searchpenyakit")
    @FormUrlEncoded
    Call<GetPenyakit> searchpenyakit(@Field("nama_penyakit") String nama_penyakit);

    @Multipart
    @POST("penyakit")
    Call<PostPutDelPenyakit> postPenyakit(@Part MultipartBody.Part gambar,
                                          @Part("nama_penyakit") RequestBody nama_penyakit,
                                          @Part("det_penyakit") RequestBody det_penyakit,
                                          @Part("srn_penyakit") RequestBody srn_penyakit,
                                          @Part("flag") RequestBody flag);
    @Multipart
    @POST("penyakit")
    Call<PostPutDelPenyakit> postUpdatePenyakit(@Part MultipartBody.Part gambar,
                                                @Part("kode_penyakit") RequestBody kode_penyakit,
                                                @Part("nama_penyakit") RequestBody nama_penyakit,
                                                @Part("det_penyakit") RequestBody det_penyakit,
                                                @Part("srn_penyakit") RequestBody srn_penyakit,
                                                @Part("flag") RequestBody flag);

    @Multipart
    @POST("penyakit")
    Call<PostPutDelPenyakit> postUpdatePenyakitt(@Part("kode_penyakit") RequestBody kode_penyakit,
                                                @Part("nama_penyakit") RequestBody nama_penyakit,
                                                @Part("det_penyakit") RequestBody det_penyakit,
                                                @Part("srn_penyakit") RequestBody srn_penyakit,
                                                @Part("flag") RequestBody flag);

    @POST("penyakit/updatepenyakit")
    @FormUrlEncoded
    Call<PostPutDelPenyakit> postUpdatePenyakitNoPhoto (@Field("kode_penyakit") String kode_penyakit,
                                                        @Field("nama_penyakit") String nama_penyakit,
                                                        @Field("det_penyakit") String det_penyakit,
                                                        @Field("srn_penyakit") String srn_penyakit);


    //@HTTP(method = "DELETE", path = "penyakit", hasBody = true)
    @POST("penyakit/deletepenyakit")
    @FormUrlEncoded
    Call<PostPutDelPenyakit> deletePenyakit(@Field("kode_penyakit") String kode_penyakit);

    //===========================Berita==================================//

    @GET("berita")
    Call<GetBerita> getBerita();

    @POST("berita/searchberita")
    @FormUrlEncoded
    Call<GetBerita> searchberita(@Field("nama_berita") String nama_berita);

    @Multipart
    @POST("berita")
    Call<PostPutDelBerita> postBerita(@Part MultipartBody.Part gambar_berita,
                                      @Part("nama_berita") RequestBody nama_berita,
                                      @Part("det_berita") RequestBody det_berita,
                                      @Part("penerbit") RequestBody penerbit,
                                      @Part("flag") RequestBody flag);
    @Multipart
    @POST("berita")
    Call<PostPutDelBerita> postUpdateBerita(@Part MultipartBody.Part gambar_berita,
                                            @Part("kode_berita") RequestBody kode_berita,
                                            @Part("nama_berita") RequestBody nama_berita,
                                            @Part("det_berita") RequestBody det_berita,
                                            @Part("penerbit") RequestBody penerbit,
                                            @Part("flag") RequestBody flag);

    @Multipart
    @POST("berita")
    Call<PostPutDelBerita> postUpdateBeritat(@Part("kode_berita") RequestBody kode_berita,
                                             @Part("nama_berita") RequestBody nama_berita,
                                             @Part("det_berita") RequestBody det_berita,
                                             @Part("flag") RequestBody flag);

    @POST("berita/updateberita")
    @FormUrlEncoded
    Call<PostPutDelBerita> postUpdateBeritaNoPhoto (@Field("kode_berita") String kode_berita,
                                                    @Field("nama_berita") String nama_berita,
                                                    @Field("penerbit") String penerbit,
                                                    @Field("det_berita") String det_berita);


    //@HTTP(method = "DELETE", path = "berita", hasBody = true)
    @POST("berita/deleteberita")
    @FormUrlEncoded
    Call<PostPutDelBerita> deleteBerita(@Field("kode_berita") String kode_berita);


    @POST("riwayat/tampilriwayat")
    @FormUrlEncoded
    Call<PostRiwayat> tampilriwayat(@Field("id") String id,
                                    @Field("namapenyakit") String namapenyakit,
                                    @Field("hasilpenyakit") String hasilpenyakit);

    @GET("riwayat")
    Call<GetRiwayat> getRiwayat();
}
