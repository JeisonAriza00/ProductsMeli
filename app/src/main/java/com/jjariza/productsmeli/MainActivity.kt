package com.jjariza.productsmeli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.HandlerCompat.postDelayed
import com.jjariza.productsmeli.activities.SearchActivity
import java.util.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private var timeStart: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeStart = Calendar.getInstance().timeInMillis
        Handler().postDelayed({
            continuar()
        }, 3000)
    }

    fun continuar(){
        SearchActivity.startActivity(this)
        finish()
    }
}