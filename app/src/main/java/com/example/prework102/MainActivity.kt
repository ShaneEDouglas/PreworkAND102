package com.example.prework102

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    private lateinit var imgcard: CardView
    private lateinit var textcard: CardView
    private lateinit var flipbtn: AppCompatButton
    private var isImgCardAtFront = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Making card stack animation

        imgcard = findViewById<CardView>(R.id.imgcard)

        textcard = findViewById<CardView>(R.id.contentcard)

        flipbtn = findViewById<AppCompatButton>(R.id.animatebutton)

        imgcard.visibility = View.VISIBLE
        textcard.visibility = View.INVISIBLE

        flipbtn.setOnClickListener {
            animateCard()
            Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show()
        }
    }



    private fun animateCard() {

        val distance = 8000 * resources.displayMetrics.density
        imgcard.cameraDistance = distance
        textcard.cameraDistance = distance


        val cardToFlip = if (isImgCardAtFront) textcard else imgcard
        val endRotationY =  180f


        val flipAnimator = ObjectAnimator.ofFloat(cardToFlip, "rotationY", cardToFlip.rotationY, endRotationY).apply {
            duration = 500
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    // Swap the visibility of the cards at the end of the flip
                    if (isImgCardAtFront) {
                        imgcard.visibility = View.VISIBLE
                        textcard.visibility = View.INVISIBLE
                    } else {
                        imgcard.visibility = View.INVISIBLE
                        textcard.visibility = View.VISIBLE
                    }

                    cardToFlip.rotationY = 0f
                    isImgCardAtFront = !isImgCardAtFront
                }
            })
        }

        flipAnimator.start()
    }
}



