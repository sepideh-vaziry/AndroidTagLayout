package com.seva.taglib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.seva.taglib.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAddTag.setOnClickListener {

            if (binding.editText.text.isNotEmpty()) {
                binding.tagLayout.addTag(binding.editText.text.toString())

                binding.editText.setText("")
            }

        }
    }

}