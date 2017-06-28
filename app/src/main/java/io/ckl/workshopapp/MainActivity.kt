package io.ckl.workshopapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity: AppCompatActivity(), MainItemListener {

    private var subscription: Subscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainAdapter(this)
        recyclerView.adapter = adapter

//        adapter.contentList = listOf("Workshop", "do", "Márcio", "=", "10")

        val greetingsService = RetrofitHelper.retrofit.create(GreetingsService::class.java)
        subscription = greetingsService.fetchGreetings()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    adapter.contentList = response.greetings
                }, { error ->
                    Toast.makeText(baseContext, error.message, Toast.LENGTH_SHORT).show()
                })
    }

    override fun onDestroy() {
        subscription?.unsubscribe()
        super.onDestroy()
    }

    override fun onItemClicked(content: String) {
        Toast.makeText(baseContext, content, Toast.LENGTH_LONG).show()
    }
}
