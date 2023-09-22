package com.example.shiftlabtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate

class MainActivity : AppCompatActivity() {

    //creating a variable for button "Register"
    private lateinit var registerButton: Button

    //creating variables for validation
    private lateinit var userName: EditText
    private lateinit var userSurname: EditText
    private lateinit var userBirthday: EditText
    private lateinit var userPassword: EditText
    private lateinit var userConfirmedPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userName = findViewById(R.id.nameText)
        userSurname = findViewById(R.id.surnameText)
        userBirthday = findViewById(R.id.birthText)
        userPassword = findViewById(R.id.passwordText)
        userConfirmedPassword = findViewById(R.id.passwordConfirmText)




        registerButton = findViewById(R.id.regButton)
        registerButton.setOnClickListener {
            var check: Boolean = validate()
            if (check) {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            } 
        }
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


