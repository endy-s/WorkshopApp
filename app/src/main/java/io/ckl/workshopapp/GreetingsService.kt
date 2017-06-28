package io.ckl.workshopapp

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable

/**
 * Created by endysilveira on 27/06/17.
 */
interface GreetingsService {
    @GET("/greetings")
    fun fetchGreetings(): Observable<Greetings>

    @POST("/login")
    fun login(@Body login: LoginUser): Observable<User>
}

class User {
    var name: String? = null
    var job: String? = null
}

class LoginUser(
        var email: String? = null,
        var password: String? = null
)

class Greetings {
    var greetings: List<String> = listOf()
}
