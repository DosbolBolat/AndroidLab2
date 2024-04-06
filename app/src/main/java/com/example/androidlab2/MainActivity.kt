package com.example.androidlab2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
//Dosbol Bolatuly 22B030331
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlab2.model.Celebrity
import com.example.androidlab2.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val adapter by lazy {
        AdapterClass()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val bar = findViewById<EditText>(R.id.search_bar)
        val button = findViewById<Button>(R.id.search_button)


        button.setOnClickListener{
            val query: String = bar.text.toString()
            val client = ApiClient.instance
            val request= client.getCelebrity(query)

            request.enqueue(object : Callback<List<Celebrity>> {
                override fun onResponse(call: Call<List<Celebrity>>, response: Response<List<Celebrity>>) {
                    println("HttpResponse: ${response.body()}")

                    val list = response.body()

                    if(list != null) {
                        adapter?.setCelebrities(
                            celebrityList = list.map { it }
                        )
                    }
                }

                override fun onFailure(call: Call<List<Celebrity>>, t: Throwable) {
                    println("HttpResponse: ${t.message}")
                }

            })
        }

    }
}