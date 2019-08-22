package com.example.android.projet
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_fragment1.*
import java.util.*

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

          commencer.setOnClickListener { view ->
              //view.findNavController().navigate(R.id.action_fragment1_to_listPharmacieFragment)
          }

       /* lateinit var timer: Timer
        val noDelay = 0L
        val everyFiveSeconds = 5000L

        val timerTask = object : TimerTask() {
            override fun run() {
                view!!.findNavController().navigate(R.id.action_homeFragment_to_fragment1)
            }
        }

        timer = Timer()
        timer.schedule(timerTask, everyFiveSeconds)
        val timer: Timer = Timer()
        timer.schedule( TimerTask(){
           fun run(){
               view!!.findNavController().navigate(R.id.action_homeFragment_to_fragment1)
            }
        } , 5000)
      }*/

    }
}


