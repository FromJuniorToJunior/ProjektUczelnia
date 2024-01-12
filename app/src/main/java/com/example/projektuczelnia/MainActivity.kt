/*
package com.example.projektuczelnia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.projektuczelnia.databinding.ActivityMainBinding
import com.example.projektuczelnia.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainVm by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView.text = mainVm.click.toString()


        val explicitIntent = Intent(applicationContext, MainActivity2::class.java)
        explicitIntent.putExtra("test", 1)
        binding.button.setOnClickListener {
            mainVm.click += 1;
            binding.textView.text = mainVm.click.toString()
        }
        binding.clicker.setOnClickListener {
            */
/* mainVm.click += 1;
             binding.textView.text = mainVm.click.toString()*//*

            startActivity(explicitIntent)
        }
        Log.d("LIFECYCLE", "onCreate")

    }

    */
/*   override fun onStart() {
           super.onStart()
           Log.d("LIFECYCLE", "onStart")
       }

       override fun onResume() {
           super.onResume()
           Log.d("LIFECYCLE", "onResume")
       }

       override fun onStop() {
           super.onStop()
           Log.d("LIFECYCLE", "onStop")
       }

       override fun onRestart() {
           super.onRestart()
           Log.d("LIFECYCLE", "onRestart")
       }

       override fun onDestroy() {
           super.onDestroy()
           Log.d("LIFECYCLE", "onDestroy")
       }

       override fun onPause() {
           super.onPause()
           Log.d("LIFECYCLE", "onStart")
       }*//*

}*/
