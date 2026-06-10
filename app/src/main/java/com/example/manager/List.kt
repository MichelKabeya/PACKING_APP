package com.example.manager

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class List : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list)
        // DECLARATIONS OF ALL ELEMENTS
        val stopBtn2 = findViewById<Button>(R.id.stopBtn2)
        val backBtn = findViewById<Button>(R.id.backBtn)
        val nameView = findViewById<TextView>(R.id.nameView)
        val categoryView = findViewById<TextView>(R.id.categoryView)
        val quantityView = findViewById<TextView>(R.id.quantityView)
        val commentView = findViewById<TextView>(R.id.commentView)
        // CLICK LISTENER FOR THE STOP BUTTON
        // closes the activity
        stopBtn2.setOnClickListener {
            finishAffinity()
        }
        // CLICK LISTENER FOR THE BACK BUTTON
        // closes the activity
        backBtn.setOnClickListener {
            finish()
        }
        // GETTING DATA FROM THE MAIN ACTIVITY
        val names = intent.getStringArrayExtra("items")
        val types = intent.getStringArrayExtra("category")
        val number = intent.getStringArrayExtra("quantity")
        val comment = intent.getStringArrayExtra("comment")
        // INSERTING BUILDERS
        val namesText = StringBuilder()
        val typesText = StringBuilder()
        val quantityText = StringBuilder()
        val commentText = StringBuilder()


        // FOR loop that will populate the text views
        for (i in names!!.indices) {
            namesText.append(names[i]).append("\n")
            typesText.append(types!![i]).append("\n")
            quantityText.append(number!![i]).append("\n")
            commentText.append(comment!![i]).append("\n")
        }
        // DISPLAYING THE LIST
        nameView.text = namesText.toString()
        categoryView.text = typesText.toString()
        quantityView.text = quantityText.toString()
        commentView.text = commentText.toString()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}