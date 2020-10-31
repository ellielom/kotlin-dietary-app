package com.example.dietdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val KEY_RESULT = "result"
    var result = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dietaryRestrictionNames = arrayOf("Vegan", "Vegetarian", "Non-Vegetarian")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dietaryRestrictionNames)
        spinnerDietary.adapter = arrayAdapter

        switchDietaryRestriction.isChecked = true


        if(savedInstanceState != null) {
            textResults.text = savedInstanceState.getString(KEY_RESULT)
        }



        switchDietaryRestriction.setOnClickListener {
            if (switchDietaryRestriction.isChecked) {
                toggleRestrictions(true)
            } else {

                this.clearSelection()
                toggleRestrictions(false)
            }
        }

        buttonSubmit.setOnClickListener{
            saveRestrictions()
        }

        buttonClear.setOnClickListener{
            this.clearSelection()
            switchDietaryRestriction.isChecked = true
        }


    }


    fun clearSelection() {
        spinnerDietary.setSelection(0)
        checkBoxGluten.isChecked = false
        checkBoxLactose.isChecked = false
        result = ""
        textResults.text = ""

        toggleRestrictions(true)
    }


    fun saveRestrictions() {
        if (switchDietaryRestriction.isChecked) {
            result = "Dietary Restriction: " + spinnerDietary.selectedItem


            if (checkBoxGluten.isChecked)
                result += "\n" + "Gluten-Free"

            if (checkBoxLactose.isChecked)
                result += "\n" + "Lactose-Free"


        }
        else {
            result = "No dietary restrictions"

        }
        textResults.text = result

    }

    fun toggleRestrictions(toggle: Boolean) {
        spinnerDietary.isEnabled = toggle
        checkBoxGluten.isEnabled = toggle
        checkBoxLactose.isEnabled = toggle
        textSpinnerFlavour.isEnabled = toggle
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putString(KEY_RESULT, result)
    }

    fun onClickNext(view: View) {
        this.saveRestrictions()
        Log.i("ivy", "a")
        val newIntent = Intent(this, SecondActivity::class.java)
        Log.i("ivy", "b")
        newIntent.putExtra("result", result)
        Log.i("ivy", "c")
        startActivity(newIntent)

    }
}
