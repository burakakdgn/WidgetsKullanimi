package com.example.widgetskullanimi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.example.widgetskullanimi.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var kontrol = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    binding.buttonOku.setOnClickListener {
        val alinanVeri = binding.editTextGirdi.text.toString()
        binding.textViewSonuc.text = alinanVeri
    }

      binding.buttonResim1.setOnClickListener {
          binding.imageView.setImageResource(R.drawable.resim1)
      }

      binding.buttonResim2.setOnClickListener {

          binding.imageView.setImageResource(resources.getIdentifier("resim2","drawable",packageName))
      }

      binding.switch1.setOnCheckedChangeListener { compoundButton, b ->
          if(b) {
              Log.e("Sonuc","Switch : ON")
          }else {
              Log.e("Sonuc","Switch : OFF")
          }
      }

        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
            kontrol = isChecked
            if (kontrol){
                val secilenButton = findViewById<Button>(binding.toggleButton.checkedButtonId)
                val butonYazi = secilenButton.text.toString()
                Log.e("Sonuc",butonYazi)
            }

        }
        val ulkeler = ArrayList<String>()
        ulkeler.add("Türkiye")
        ulkeler.add("İtalya")
        ulkeler.add("Japonya")

        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,ulkeler)
        binding.autocompleteTextview.setAdapter(arrayAdapter)

        binding.buttonBasla.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
        }
        binding.buttonDur.setOnClickListener {
            binding.progressBar.visibility = View.INVISIBLE
        }

        binding.textViewSlider.text = binding.slider.progress.toString()

        binding.slider.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.textViewSlider.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })


        binding.buttonSaat.setOnClickListener {
            val tp = MaterialTimePicker.Builder()
                .setTitleText("Saat Seç")
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            tp.show(supportFragmentManager,"Saat")

            tp.addOnPositiveButtonClickListener{
                binding.editTextTextSaat.setText("${tp.hour} : ${tp.minute}")
            }
        }

        binding.buttonTarih.setOnClickListener {
            val dp = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Tarih Seç")

                .build()
            dp.show(supportFragmentManager,"Tarih")

            dp.addOnPositiveButtonClickListener{
                val df = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val tarih = df.format(it)
                binding.editTextTextTarih.setText(tarih)
            }
        }

        binding.buttonGoster.setOnClickListener {
            Log.e("Sonuc","Switch Durum: ${binding.switch1.isChecked}")
            if (kontrol){
                val secilenButton = findViewById<Button>(binding.toggleButton.checkedButtonId)
                val butonYazi = secilenButton.text.toString()
                Log.e("Sonuc","Toggle Durum : ${butonYazi}")
            }
            val ulke = binding.autocompleteTextview.text.toString()
            Log.e("Sonuc","Ülke: ${ulke}")

            Log.e("Sonuc","Slider Durum ${binding.slider.progress.toString()}")
        }



    }
}