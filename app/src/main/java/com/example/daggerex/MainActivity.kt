package com.example.daggerex

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    @Inject lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).netComponent.injectMainActivity(this)

        createObserver()

    }


    fun createObserver(){

        val observable = retrofit.create(RestApi::class.java).getPost()

        //Observer which will take result from observable object
        val observer = object : Observer<List<Post>> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onComplete() {
                println("All data emitted.")
            }

            override fun onError(e: Throwable) {
                println("Error received: " + e.message)
            }

            override fun onNext(t: List<Post>) {
                for (post in t) {
                    Log.i("TEST", post.title) //Write post title at log
                }
            }
        }



        //Subscribe observer to observable and make request api

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(observer)


    }
}
