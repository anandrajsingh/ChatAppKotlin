package android.example.chatappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class LoginActivity : AppCompatActivity() {

    private lateinit var edtEmail :EditText
    private lateinit var edtPassword :EditText
    private lateinit var btnLogin :EditText
    private lateinit var btnSignUp :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}