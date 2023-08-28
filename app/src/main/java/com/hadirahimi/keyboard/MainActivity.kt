package com.hadirahimi.keyboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hadirahimi.keyboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            //check keyboard status
            if(isKeyboardEnabled())
                btnEnableKeyboard.isEnabled = false
            
            btnEnableKeyboard.setOnClickListener {
                if (!isKeyboardEnabled())
                    openKeyboardSettings()
            }
            btnChooseKeyboard.setOnClickListener {
                if (isKeyboardEnabled()){
                    openKeyboardChooserSettings()
                }else Toast.makeText(this@MainActivity , "Choose the keyboard activation button" , Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun openKeyboardSettings() {
        val intent = Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)
        startActivity(intent)
    }
    
    // Function to check if a specific keyboard is enabled
    private fun isKeyboardEnabled(): Boolean {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val enabledInputMethodIds = inputMethodManager.enabledInputMethodList.map { it.id }
        return enabledInputMethodIds.contains("com.hadirahimi.keyboard/.MyKeyboard")
    }
    
    // Function to open the keyboard settings to choose a keyboard
    private fun openKeyboardChooserSettings() {
        val im = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        im.showInputMethodPicker()
    }
    
}