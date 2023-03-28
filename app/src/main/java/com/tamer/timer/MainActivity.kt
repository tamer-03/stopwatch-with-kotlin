package com.tamer.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.tamer.timer.databinding.ActivityMainBinding
import kotlinx.coroutines.Runnable

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var runnable = Runnable {  }
    val handler = Handler()
    var number = 0
    var ten = 0
    var minute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.pause.visibility = View.INVISIBLE

    }

    fun startClick(view: View){

        binding.imageView.setImageDrawable(getDrawable(R.drawable.pause))
        binding.start.visibility = View.INVISIBLE
        binding.pause.visibility = View.VISIBLE

        runnable = object : Runnable {
            override fun run() {
                number = number + 1
                binding.textView.text = "0$minute:$ten$number"
                if(number == 9){
                    ten = ten + 1
                    number = -1
                }
                if (ten == 6){
                    minute = minute + 1
                    ten = 0
                }

                handler.postDelayed(runnable,1000)
            }

        }
        handler.post(runnable)


    }
    fun pauseClick(view: View){

        binding.imageView.setImageDrawable(getDrawable(R.drawable.start))
        binding.pause.visibility = View.INVISIBLE
        binding.start.visibility = View.VISIBLE
        handler.removeCallbacks(runnable)
    }
    fun restartClick(view: View){

        handler.removeCallbacks(runnable)
        binding.textView.text = "00:00"
        binding.imageView.setImageDrawable(getDrawable(R.drawable.start))
        binding.pause.visibility = View.INVISIBLE
        binding.start.visibility = View.VISIBLE



    }

}