import com.my.campingcuy.LoginActivity
import com.my.campingcuy.LoginResponseModel
import com.my.campingcuy.RegisterActivity
import com.my.campingcuy.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login") // Replace with your actual login endpoint
    fun loginUser(@Body loginRequest: LoginActivity.LoginRequest): Call<LoginResponseModel>

    @POST("register") // Replace with your actual registration endpoint
    fun registerUser(@Body registerRequest: RegisterActivity.RegisterRequest): Call<RegisterResponse>
}
