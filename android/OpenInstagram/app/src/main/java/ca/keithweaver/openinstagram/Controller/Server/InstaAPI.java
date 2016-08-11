package ca.keithweaver.openinstagram.Controller.Server;

import ca.keithweaver.openinstagram.Model.ServerResponse.ResGeneral;
import ca.keithweaver.openinstagram.Model.ServerResponse.ResLogIn;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by keithweaver on 16-08-11.
 */
public interface InstaAPI {

    String ENDPOINT = "";


    @FormUrlEncoded
    @POST("/login/signup")
    void signUp(@Field("apikey") String apiKey, @Field("email") String email, @Field("password") String password, Callback<ResGeneral> callback);

    @FormUrlEncoded
    @POST("/login/login")
    void logIn(@Field("apikey") String apiKey, @Field("email") String email, @Field("password") String password, Callback<ResLogIn> callback);

    @FormUrlEncoded
    @POST("/login/update-username")
    void updateUserName(@Field("apikey") String apiKey, @Field("email") String email, @Field("password") String password, @Field("username") String newUserName, Callback<ResGeneral> callback);

    @FormUrlEncoded
    @POST("/login/register-device")
    void registerDevice(@Field("apikey") String apiKey, @Field("email") String email, @Field("token") String token, @Field("device") String device, Callback<ResGeneral> callback);

    @FormUrlEncoded
    @POST("/login/check-login-token")
    void checkLogInToken(@Field("apikey") String apiKey, @Field("email") String email, @Field("token") String token, @Field("username") String username, Callback<ResGeneral> callback);

    @FormUrlEncoded
    @POST("/login/check-taken-username")
    void checkTakenUserName(@Field("apikey") String apiKey, @Field("username") String username, Callback<ResGeneral> callback);

}
