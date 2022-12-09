package tugas.pmobile.banksampah.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import tugas.pmobile.banksampah.Model.*;

public interface ApiEndpoint {
    @POST("login")
    Call<Response<Boolean>> login(@Body LoginRequest loginRequest);

    @POST("signup")
    Call<Response<String>> signUp(@Body SignUpRequest signUpRequest);


}
