package android.example.chatappkotlin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var edtEmail : EditText
    private lateinit var edtPassword : EditText
    private lateinit var edtName : EditText
    private lateinit var btnSignUp : CardView

    private lateinit var mAuth : FirebaseAuth
    private lateinit var mDbReference: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        edtEmail = findViewById(R.id.email)
        edtPassword = findViewById(R.id.password)
        edtName = findViewById(R.id.name)
        btnSignUp = findViewById(R.id.sign_up)

        mAuth = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            val name = edtName.text.toString()

            signUp(name, email, password)
        }

    }

    private fun signUp(name:String, email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!)
                    val intent = Intent(this, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                }else{
                    Toast.makeText(this, "Some Error Occurred", Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun addUserToDatabase(name: String, email: String, uid: String) {
        mDbReference = FirebaseDatabase.getInstance().getReference()
        mDbReference.child("User").child(uid).setValue(User(name, email, uid))
    }
}