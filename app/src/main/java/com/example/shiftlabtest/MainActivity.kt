package com.example.shiftlabtest

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MainActivity : AppCompatActivity() {

    //creating a variable for button "Register"
    private lateinit var registerButton: Button

    //creating variables for validation
    private lateinit var userName: EditText
    private lateinit var userSurname: EditText
    private lateinit var userBirthday: EditText
    private lateinit var userPassword: EditText
    private lateinit var userConfirmedPassword: EditText

    val myCalendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userName = findViewById(R.id.nameText)
        userSurname = findViewById(R.id.surnameText)
        userBirthday = findViewById(R.id.birthText)
        userPassword = findViewById(R.id.passwordText)
        userConfirmedPassword = findViewById(R.id.passwordConfirmText)

        val date =
            OnDateSetListener { _, year, month, day ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, day)
                updateLabel()
            }
        userBirthday.setOnClickListener {
            DatePickerDialog(this@MainActivity, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show()
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
        userBirthday.setText(dateFormat.format(myCalendar.time))
    }

    private fun validate(): Boolean {
        var name: String = userName.text.toString()
        var surname: String = userSurname.text.toString()
        var birthday: String = userBirthday.text.toString()
        var password: String = userPassword.text.toString()
        var confirmedPassword: String = userConfirmedPassword.text.toString()


        if (name.isEmpty() || name.length < 2) {
            showHint(userName, "Имя должно содержать более 2 символов")
            return false
        } else if (surname.isEmpty() || surname.length < 2) {
            showHint(userSurname, "Фамилия должна содержать более 2 символов")
            return false
        } else if (birthday.isEmpty()) {
            showHint(userBirthday, "Введите значение")
            return false
        } else if (password.isEmpty() || !password.contains(".")) {
            showHint(userPassword, "Слабый пароль. Добавьте цифры и другие символы")
            return false
        } else if (confirmedPassword.isEmpty() || confirmedPassword != password) {
            showHint(userConfirmedPassword, "Пароли не сходятся")
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


