package com.example.manager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // DECLARATIONS OF ALL ELEMENTS
        val stopBtn = findViewById<Button>(R.id.stopBtn)
        val commentText = findViewById<EditText>(R.id.commentText)
        commentText.visibility = View.GONE
        val nameText = findViewById<EditText>(R.id.nameText)
        nameText.visibility = View.GONE
        val quantityText = findViewById<EditText>(R.id.quantityText)
        quantityText.visibility = View.GONE
        val categorySpin = findViewById<Spinner>(R.id.CategorySpin)
        categorySpin.visibility = View.GONE
        val nextView = findViewById<TextView>(R.id.nextView)
        nextView.visibility = View.GONE
        val addBtn = findViewById<Button>(R.id.addBtn)
        addBtn.visibility = View.GONE
        val startBtn = findViewById<Button>(R.id.startBtn)
        val nextBtn = findViewById<Button>(R.id.NextBtn)
        nextBtn.visibility = View.GONE
        val saveView = findViewById<TextView>(R.id.saveView)
        saveView.visibility = View.GONE
        val addView = findViewById<TextView>(R.id.addView)
        val promptView = findViewById<TextView>(R.id.pomptView)
        val insertPrompt = findViewById<TextView>(R.id.insertPrompt)
        insertPrompt.visibility = View.GONE

        // CLICK LISTENER FOR THE START BUTTON
        startBtn.setOnClickListener {
            commentText.visibility = View.VISIBLE
            nameText.visibility = View.VISIBLE
            quantityText.visibility = View.VISIBLE
            categorySpin.visibility = View.VISIBLE
            nextView.visibility = View.VISIBLE
            nextBtn.visibility = View.VISIBLE
            addBtn.visibility = View.VISIBLE
            saveView.visibility = View.VISIBLE
            startBtn.visibility = View.GONE
            promptView.visibility = View.GONE
            insertPrompt.visibility = View.VISIBLE
            addView.visibility = View.GONE
        }
        // DECLARATIONS OF ALL ARRAYS
        val items = Array(50) {""}
        val category = Array(50) {""}
        val quantity = Array(50) {""}
        val comment = Array(50) {""}
        // creating array of categories
        val catItem = arrayListOf(
            "choose a category",
            "Clothing",
            "Toiletries",
            "Stationary",
            "Foodstuffs",
        )
        // creating the adapter variable that will be used to populate the spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,
            catItem)
        // setting the adapter to the spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpin.adapter = adapter

        // CLICK LISTENER FOR THE NEXT BUTTON
        // opens the next activity
        nextBtn.setOnClickListener {
            val intent = Intent(this, List::class.java)
            // SENDING ARRAYS DATA TO THE NEXT ACTIVITY
            intent.putExtra("items", items)
            intent.putExtra("category", category)
            intent.putExtra("quantity", quantity)
            intent.putExtra("comment", comment)

            startActivity(intent)
        }
        //CLICK LISTENER FOR CLOSE BUTTON
        // closes the activity
        stopBtn.setOnClickListener {
            finishAffinity()
        }
        var counter = 0
        // CLICK LISTENER FOR THE ADD BUTTON
        addBtn.setOnClickListener {
            val qty = quantityText.text.toString().toIntOrNull()
            // IF statement handling empty fields
            if (nameText.text.toString().isEmpty() ||
                qty == null ||
                qty <= 0 ||
                categorySpin.selectedItem.toString().isEmpty()){
                nameText.error = "Required"
                quantityText.error = "Required"
                return@setOnClickListener
            }
            else{
                if (counter >= 50) {
                    return@setOnClickListener
                }
                // FOR loop that will populate the arrays
                    items[counter] = nameText.text.toString()
                    category[counter] = categorySpin.selectedItem.toString()
                    quantity[counter] = quantityText.text.toString()
                    comment[counter] = commentText.text.toString()
                    nameText.text.clear()
                    quantityText.text.clear()
                    commentText.text.clear()

                counter++
                }
        }





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}