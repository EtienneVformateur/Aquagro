package com.example.aquagro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
        val currentUser = auth.currentUser


        val btnConnexion = findViewById<Button>(R.id.bntconnexion)
        val creerCompte = findViewById<Button>(R.id.btnCreationCompte)
        val identifant = findViewById<EditText>(R.id.indetifiantMail)
        val motDePasse = findViewById<EditText>(R.id.motDePasse)
        val motDePasseOublie = findViewById<Button>(R.id.btnPasseOublie)



        //Connexion à mon compte créé sur la page register
        btnConnexion.setOnClickListener {
            val email = identifant.text.toString()
            val password = motDePasse.text.toString()
            if ((email.isNotEmpty()) and (password.isNotEmpty())) { //etEmail.text.isNotEmpty
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        val user = auth.currentUser
                        if ((task.isSuccessful) and (user!!.isEmailVerified)) {
                            val AccueilIntent = Intent(this, VerifMail::class.java)
                            startActivity(AccueilIntent)
                        }else if (task.isSuccessful) {
                            Toast.makeText(
                                baseContext, "Connexion réussie",
                                Toast.LENGTH_SHORT
                            ).show()
                            val articlesIntent = Intent(this,ListeArticles::class.java)
                            startActivity(articlesIntent)

                        } else {
                            Toast.makeText(
                                baseContext, "Erreur de connexion",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
            else{
                Toast.makeText(
                    baseContext, "Veuillez remplir tous les champs",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        //Ouvre la page d'enregistrement
        creerCompte.setOnClickListener {
            val RegisterIntent = Intent(this,CreateUser::class.java)
            startActivity(RegisterIntent)
        }

        //Ouvre la page de mot de passe oublié
        motDePasseOublie.setOnClickListener {
            val ForgotIntent = Intent(this,MainActivity::class.java)
            startActivity(ForgotIntent)
        }
    }

//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        if(currentUser != null){
//            val AccueilIntent = Intent(this,MainActivity::class.java)
//            startActivity(AccueilIntent)
//
//        }
//    }

}
