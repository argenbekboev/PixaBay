package com.example.pixabay

import android.graphics.pdf.PdfDocument.Page
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pixabay.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var adapter = ImageAdapter(listOf())
    var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
    }

    private fun initClickers() {
        with(binding) {
            btnChangePage.setOnClickListener {
                ++page
                doReqeust()
            }
            btnFind.setOnClickListener {
                doReqeust()
            }
        }
    }

    private fun ActivityMainBinding.doReqeust() {
        RetrofitService.api.getImage(etPhotoName.text.toString(), page = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                    if (response.isSuccessful) {
                        adapter = ImageAdapter(response.body()!!.hits)
                        binding.recyclerSearch.adapter = this@MainActivity.adapter
                    }
                    Log.e("ololo", "onResponse: ${response.body()?.hits!![0].largeImageURL}")
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("ololo", "onFailure: ${t.message}")
                }

            })
    }
}