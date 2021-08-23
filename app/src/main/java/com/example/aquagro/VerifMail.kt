package com.example.aquagro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class VerifMail : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verif_mail)

        val verifMail = findViewById<TextView>(R.id.verifMail)
        val affiMail = findViewById<TextView>(R.id.affiMail)
        val btnEnvMailDeVerif = findViewById<Button>(R.id.btnEnvMailDeVerif)
        val btnDeconnexion = findViewById<Button>(R.id.btnDeconnexion)
        auth = Firebase.auth
        val user = auth.currentUser
        affiMail.text = user?.email

        if(user!!.isEmailVerified){
            verifMail.text = "Mail vérifié vous pouvez vous reconnecter"
        }

//        btnEnvMailDeVerif.setOnClickListener{
//            val emailVerified = user?.isEmailVerified
//            if (emailVerified == true) {
//                affiMail.text = user?.email
//                verifMail.text = "Mail Vérifié"
//            }
//            else {
//                Toast.makeText(
//                    baseContext, "Verifiez vos mails puis validez",
//                    Toast.LENGTH_SHORT
//                ).show()
//                val RecomIntent = Intent(this, VerifMail::class.java)
//                startActivity(RecomIntent)
//            }
//        }

        btnDeconnexion.setOnClickListener {
            auth.signOut()
            val RetourIntent = Intent(this, Login::class.java)
            startActivity(RetourIntent)
        }

    }
}

