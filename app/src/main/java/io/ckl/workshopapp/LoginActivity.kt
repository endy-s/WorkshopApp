package io.ckl.workshopapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginActivity: AppCompatActivity() {

    private var loginSubscription: Subscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginButton.setOnClickListener { onLogicButtonClicked() }
    }

    override fun onDestroy() {
        loginSubscription?.unsubscribe()
        super.onDestroy()
    }

    fun onLogicButtonClicked() {
        val emailText = emailEditText.text.toString()
        val passwordText = passwordEditText.text.toString()
        performLogin(emailText, passwordText)
    }

    private fun performLogin(emailText: String, passwordText: String) {
        loginSubscription = RetrofitHelper.retrofit.create(GreetingsService::class.java)
                .login(LoginUser(emailText, passwordText))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    startActivity(Intent(this, MainActivity::class.java))
                }, { error ->
                    Toast.makeText(baseContext, error.message, Toast.LENGTH_SHORT).show()
                })
    }
}
