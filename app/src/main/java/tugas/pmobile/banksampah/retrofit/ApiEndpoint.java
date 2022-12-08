package tugas.pmobile.banksampah.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import tugas.pmobile.banksampah.Model.Login;
import tugas.pmobile.banksampah.Model.Response;

public interface ApiEndpoint {
    @POST("login")
    Call<Response<Boolean>> login(@Body Login login);
}
