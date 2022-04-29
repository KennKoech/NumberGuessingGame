package com.example.numberguessinggame

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    private var r = Random().nextInt(1000)
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.ok_button).setOnClickListener{checkUserEntry(it)}
        findViewById<Button>(R.id.playAgain_button).setOnClickListener{playAgain(it)}
        // When the app runs, create a random number generator and store it in a variable
    }
    // Listen for enter button
    private fun checkUserEntry(view: View){
        val editText = findViewById<EditText>(R.id.edit_number)
        val winnerMessage = findViewById<TextView>(R.id.congrats_textView)
        val triesCount = findViewById<TextView>(R.id.tries_textView)
        val num = editText.text.toString().toInt()
    //  Check if the user number is the same as the computer number
        when {
            // If, lower, hint higher and vice versa until the user gets the correct number
            num < r -> {
                Toast.makeText(this, "Guess a little bit higher!", Toast.LENGTH_SHORT). show()
                editText.text = null
                count++
            }
            num > r -> {
                Toast.makeText(this,"Guess a little bit lower!", Toast.LENGTH_SHORT).show()
                editText.text = null
                count++
            }
            // Display victory message and game summary when user gets the correct number
            num == r -> {
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                Toast.makeText(this,"You won, siuuuuuuuuuuuuu", Toast.LENGTH_SHORT).show()
                count++
                winnerMessage.visibility = View.VISIBLE
                triesCount.text = "Number of tries: $count"
                triesCount.visibility = View.VISIBLE
            }
        }
    }
    // Give the user the option of playing again
    private fun playAgain(view: View){
        val editText = findViewById<EditText>(R.id.edit_number)
        val winnerMessage = findViewById<TextView>(R.id.congrats_textView)
        val triesCount = findViewById<TextView>(R.id.tries_textView)
        Toast.makeText(this, "Start guessing again! Fewer guesses is the goal!!", Toast.LENGTH_SHORT).show()
        r = Random().nextInt(1000)
        winnerMessage.visibility = View.GONE
        triesCount.visibility = View.GONE
        editText.text = null
        count = 0
    }

}