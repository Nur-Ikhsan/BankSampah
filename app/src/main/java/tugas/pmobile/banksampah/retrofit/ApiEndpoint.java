package tugas.pmobile.banksampah.retrofit;

import com.google.gson.JsonElement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import tugas.pmobile.banksampah.Model.Location;
import tugas.pmobile.banksampah.Model.LoginRequest;
import tugas.pmobile.banksampah.Model.Response;
import tugas.pmobile.banksampah.Model.SignUpRequest;
import tugas.pmobile.banksampah.Model.StoringTrash;
import tugas.pmobile.banksampah.Model.StoringTrashRequest;
import tugas.pmobile.banksampah.Model.Trash;

public interface ApiEndpoint {
    @POST("api/login")
    Call<Response<JsonElement>> login(@Body LoginRequest loginRequest);

    @POST("api/signup")
    Call<Response<String>> signUp(@Body SignUpRequest signUpRequest);

    @GET("api/locations")
    Call<Response<List<Location>>> getLocations();

    @GET("api/trash")
    Call<Response<List<Trash>>> getTrash();

    @POST("api/storing-trash")
    Call<Response<StoringTrash>> storingTrash(@Body StoringTrashRequest storingTrashRequest);

    @GET("api/storing-trash/account/{accountId}")
    Call<Response<List<StoringTrash>>> getStoringTrashByAccountId(@Path("accountId") int id);
}
