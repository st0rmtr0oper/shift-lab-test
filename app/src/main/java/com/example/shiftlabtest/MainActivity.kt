package com.example.shiftlabtest

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MainActivity : AppCompatActivity() {

    //creating a variable for button "Register"
    private lateinit var registerButton: Button

    //creating variables for validation
    private lateinit var userName: TextInputLayout
    private lateinit var userSurname: TextInputLayout
    private lateinit var userBirthday: TextInputLayout
    private lateinit var userPassword: TextInputLayout
    private lateinit var userConfirmedPassword: TextInputLayout

    val myCalendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userName = findViewById(R.id.nameText)
        userSurname = findViewById(R.id.surnameText)
        userBirthday = findViewById(R.id.birthdayText)
        userPassword = findViewById(R.id.passwordText)
        userConfirmedPassword = findViewById(R.id.passwordConfirmText)

        val date =
            OnDateSetListener { _, year, month, day ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, day)
                updateLabel()
            }
        userBirthday.editText?.setOnClickListener {
            DatePickerDialog(
                this@MainActivity,
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        registerButton = findViewById(R.id.regButton)
        registerButton.setOnClickListener {
            var check: Boolean = validate()
            if (check) {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun updateLabel() {
        val myFormat = "MM.dd.yyyy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        userBirthday.editText?.setText(dateFormat.format(myCalendar.time))
    }

    private fun validate(): Boolean {
        var name: String = userName.editText?.text.toString()
        var surname: String = userSurname.editText?.text.toString()
        var birthday: String = userBirthday.editText?.text.toString()
        var password: String = userPassword.editText?.text.toString()
        var confirmedPassword: String = userConfirmedPassword.editText?.text.toString()


        if (name.isEmpty() || name.length < 2) {
            showHint(userName.editText, "Имя должно содержать более 2 символов")
            return false
        } else if (name.contains("[0-9]".toRegex()) || name.contains("[!\"#$%&'()*+,./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())) {
            showHint(userName.editText, "Имя должно содержать только буквы")
            return false
        } else if (surname.isEmpty() || surname.length < 2) {
            showHint(userSurname.editText, "Фамилия должна содержать более 2 символов")
            return false
        } else if (surname.contains("[0-9]".toRegex()) || surname.contains("[!\"#$%&'()*+,./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())) {
            showHint(userSurname.editText, "Фамилия должно содержать только буквы")
            return false
        } else if (birthday.isEmpty()) {
            showHint(userBirthday.editText, "Введите значение")
            return false
        } else if (password.length < 5) {
            showHint(userPassword.editText, "Пароль должен содержать более 5 символов")
            return false
        } else if (password.isEmpty() || !password.contains("[0-9]".toRegex()) || !password.contains("[!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())) {
            showHint(userPassword.editText, "Слабый пароль. Добавьте цифры и другие символы")
            return false
        } else if (confirmedPassword.isEmpty() || confirmedPassword != password) {
            showHint(userConfirmedPassword.editText, "Пароли не сходятся")
            return false
        } else {
            return true
        }

    }

    private fun showHint(text: EditText?, hint: String) {
        text?.error = hint
        text?.requestFocus()
    }
}


