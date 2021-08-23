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

class CreateUser : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        auth = Firebase.auth
        val mailCreationUser = findViewById<EditText>(R.id.mailCreationUser)
        val motDePasseCreation = findViewById<EditText>(R.id.motDePasseCreateUser)
        val confimMotDePasse = findViewById<EditText>(R.id.confirmMotDePasse)
        val btnEnregistrement = findViewById<Button>(R.id.btnEnregistrement)
        val btnAnnulation = findViewById<Button>(R.id.btnAnnulation)



        //Créer un compte utilisateur
        btnEnregistrement.setOnClickListener {
            val email  = mailCreationUser.text.toString()
            val password = motDePasseCreation.text.toString()
            val password2 = confimMotDePasse.text.toString()
            if (password != password2) //and ()
            {
                Toast.makeText(
                    baseContext, "Les mots de passe ne correspondent pas",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (password.length < 5) {
                Toast.makeText(
                    baseContext, "Entrez un mot de passe plus long",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (password == password2){
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->

                        if (task.isSuccessful) {
                            Toast.makeText(
                                baseContext,
                                "Vous êtes enregistré, veuillez vous reconnecter",
                                Toast.LENGTH_SHORT
                            ).show()
                            auth.signOut()
                            val ConnexionIntent = Intent(this,MainActivity::class.java)
                            startActivity(ConnexionIntent)
                        }
                    }

            }
            else {
            // If sign in fails, display a message to the user.
                Toast.makeText(
                    baseContext, "Erreur",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        btnAnnulation.setOnClickListener {
            val RetourAccueilIntent = Intent(this,MainActivity::class.java)
            startActivity(RetourAccueilIntent)

        }


    }
}

//a mettre dans le code pour envoie d'u mail de vérification

//user!!.sendEmailVerification()
//.addOnCompleteListener { task ->
//    if (task.isSuccessful) {
//        Toast.makeText(
//            baseContext,
//            "Un email de vérification vous a été envoyé",
//            Toast.LENGTH_SHORT
//        ).show()
//    }
//}