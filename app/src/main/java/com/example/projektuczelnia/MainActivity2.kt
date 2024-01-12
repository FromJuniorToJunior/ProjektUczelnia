package com.example.projektuczelnia


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projektuczelnia.adapters.EmployeesAdapter
import com.example.projektuczelnia.data.company
import com.example.projektuczelnia.databinding.CompanyBinding
import com.example.projektuczelnia.remote.RetrofitClient
import com.example.projektuczelnia.viewmodel.MainActivityViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.net.URL
import java.util.Base64
import java.util.Objects


class MainActivity2 : AppCompatActivity() {
    private lateinit var mainVm: MainActivityViewModel
    private lateinit var binding: CompanyBinding
    private val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainVm = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        val apiService = RetrofitClient.apiService

        val call: Call<company> = apiService.getPost()

        call.enqueue(object : Callback<company> {
            override fun onResponse(call: Call<company>, response: Response<company>) {
                if (response.isSuccessful) {
                    val post: company? = response.body()
                    if (post != null) {
                        mainVm.updateData(post)
                        Thread {
                            val base =
                                getByteArrayFromImageURL(mainVm.myData.value?.record?.image.toString())
                            val dec = Base64.getMimeDecoder().decode(base)

                            handler.post {
                                post.record?.image = base.toString()
                                val bitMap: Bitmap = BitmapFactory.decodeByteArray(
                                    dec,
                                    0,
                                    dec.size
                                )
                                binding.imageView2.setScaleType(ImageView.ScaleType.FIT_XY)
                                binding.imageView2.setImageBitmap(bitMap)
                                serializeObjectToFile(
                                    applicationContext,
                                    mainVm.myData.value,
                                    "test.json"
                                )

                            }
                        }.start()

                    }
                }
            }

            override fun onFailure(call: Call<company>, t: Throwable) {
                deserialize(applicationContext, "test.json")
                Log.e("API", "Nie udało się wysłać żądania", t)
            }
        })

        mainVm.myData.observe(this, Observer { newData ->
            val cnmae: String = "Nazwa przedsięiorstwa: " + mainVm.myData.value?.record?.name
            val cowner: String = "Właściciel: " + mainVm.myData.value?.record?.owner
            val cseniority: String = "Staż: " + mainVm.myData.value?.record?.seniority.toString()
            val base: String = mainVm.myData.value?.record?.image.toString()

            binding.cname.text = cnmae
            binding.cowner.text = cowner
            binding.seniority.text = cseniority
            if (!isNetworkAvailable()) {
                val dec = Base64.getMimeDecoder().decode(base)
                val bitMap: Bitmap = BitmapFactory.decodeByteArray(
                    dec,
                    0,
                    dec.size
                )
                binding.imageView2.setScaleType(ImageView.ScaleType.FIT_XY)
                binding.imageView2.setImageBitmap(bitMap)
            }


            val adapter2 = mainVm.myData.value?.record?.employees?.let { EmployeesAdapter(it) }
            binding.rec1.apply {
                adapter = adapter2
                layoutManager =
                    LinearLayoutManager(
                        applicationContext,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
            }
        })


    }


    fun serializeObjectToFile(context: Context, company: company?, fileName: String) {
        val gson = Gson()

        val json = gson.toJson(company)

        // Write JSON to a file
        try {
            FileWriter(context.filesDir.toString() + "/" + fileName).use { fileWriter ->
                fileWriter.write(json)
                Log.d(
                    "Serialization",
                    "Object serialized and written to file: $fileName"
                )
            }
        } catch (e: IOException) {
            Log.e("Serialization", "Error writing to file: " + e.message)
            e.printStackTrace()
        }
    }

    fun deserialize(context: Context, fileName: String) {
        val gson = Gson()
        try {
            FileReader(context.filesDir.toString() + "/" + fileName).use { fileReader ->

                mainVm.updateData(
                    gson.fromJson<company>(
                        fileReader.readText(),
                        company::class.java
                    )
                )
                Log.d(
                    "Deserialization",
                    "Object deserialized"
                )
            }
        } catch (e: IOException) {
            Log.e("Deserialization", "Error deserialize: " + e.message)
            e.printStackTrace()
        }
    }

    private fun getByteArrayFromImageURL(url: String): String? {
        try {
            val imageUrl = URL(url)
            val ucon = imageUrl.openConnection()
            val `is` = ucon.getInputStream()
            val baos = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var read = 0
            while (`is`.read(buffer, 0, buffer.size).also { read = it } != -1) {
                baos.write(buffer, 0, read)
            }
            baos.flush()
            return android.util.Base64.encodeToString(
                baos.toByteArray(),
                android.util.Base64.DEFAULT
            )
        } catch (e: Exception) {
            Log.d("Error", e.toString())
        }
        return ""
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }
}