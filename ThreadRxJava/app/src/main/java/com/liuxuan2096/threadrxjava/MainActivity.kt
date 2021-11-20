package com.liuxuan2096.threadrxjava

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Single.just(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        Observable.interval(0, 1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long?> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(t: Long?) {
                    textView.text = t.toString()
                }

                override fun onError(e: Throwable?) {
                }
            })
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }
}