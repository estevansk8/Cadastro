package com.estevan.cadastro

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.estevan.cadastro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        emailFocusListener()
        phoneFocusListener()

        binding.save.setOnClickListener { submitForm() }
        binding.clear.setOnClickListener { resetForm() }
    }

    private fun submitForm() {
        binding.emailContainer.helperText = validEmail()
        binding.phoneContainer.helperText = validPhone()

        val validEmail = binding.emailContainer.helperText == null
        val validPhone = binding.phoneContainer.helperText == null

        if (validEmail && validPhone)
            validForm()
        else
            invalidForm()
    }


    private fun invalidForm() {
        var message = ""
        if(binding.emailContainer.helperText != null)
            message += "\n\nEmail: " + binding.emailContainer.helperText
        if(binding.phoneContainer.helperText != null)
            message += "\n\nPhone: " + binding.phoneContainer.helperText

        AlertDialog.Builder(this)
            .setTitle("Invalid Form")
            .setMessage(message)
            .setPositiveButton("Okay"){ _,_ ->
                // do nothing
            }
            .show()
    }

    private fun validForm() {

        val forms = Formulario()

        forms.name = binding.nameEditText.text.toString()
        forms.email = binding.emailEditText.text.toString()
        forms.phone = binding.phoneEditText.text.toString()
        forms.city = binding.cityEditText.text.toString()
        forms.state = binding.stateSpinner.selectedItem.toString()
        forms.sexo = if (binding.masculinoRb.isChecked) "Masculino" else "Feminino"
        forms.check = if (binding.check.isChecked) "In list emails!!" else "Not in list of emails"

        Toast.makeText(this, forms.toString(), Toast.LENGTH_SHORT).show()
        println(forms.toString())

        AlertDialog.Builder(this)
            .setTitle("Form submitted")
            .setMessage(forms.toString())
            .setPositiveButton("Okay") { _, _ ->
                // do nothing
            }
            .show()

    }


    private fun resetForm() {
        binding.nameEditText.text = null
        binding.emailEditText.text = null
        binding.phoneEditText.text = null
        binding.cityEditText.text = null

        binding.nameContainer.helperText = getString(R.string.required)
        binding.emailContainer.helperText = getString(R.string.required)
        binding.phoneContainer.helperText = getString(R.string.required)
        binding.city.helperText = getString(R.string.required)
    }


    private fun emailFocusListener() {
        binding.emailEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.emailEditText.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
        {
            return "Invalid Email Address"
        }
        return null
    }


    private fun phoneFocusListener()
    {
        binding.phoneEditText.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.phoneContainer.helperText = validPhone()
            }
        }
    }


    private fun validPhone(): String?
    {
        val phoneText = binding.phoneEditText.text.toString()
        if(!phoneText.matches(".*[0-9].*".toRegex()))
        {
            return "Must be all Digits"
        }
        if(phoneText.length != 11)
        {
            return "Must be 11 Digits"
        }
        return null
    }
}