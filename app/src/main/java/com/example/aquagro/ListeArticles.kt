package com.example.aquagro

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.aquagro.databinding.ActivityListeArticlesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase



class ListeArticles : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityListeArticlesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_articles)
        binding = ActivityListeArticlesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth

//        val nom1 = findViewById<TextView>(R.id.nom1)
//        val def1 = findViewById<TextView>(R.id.def1)
//        val nom2 = findViewById<TextView>(R.id.nom2)
//        val def2 = findViewById<TextView>(R.id.def2)
//        val nom3 = findViewById<TextView>(R.id.nom3)
//        val def3 = findViewById<TextView>(R.id.def3)
        binding.btnListeDeconnexion.setOnClickListener {
            auth.signOut()
            val articlesIntent = Intent(this,MainActivity::class.java)
            startActivity(articlesIntent)
        }

        database = Firebase.database("https://aquagro-2fe01-default-rtdb.europe-west1.firebasedatabase.app/").reference

        // Read from the database
        val listeArticlesReference = database.child("ListeArticles")

        // My top posts by number of stars
        listeArticlesReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(listearticles: DataSnapshot) {
                for (articles in listearticles.child("articles").children){
                    // TODO: handle the post
                    binding.nom1.text = articles.key.toString()
                    for (def in articles.children) {
                        binding.def1.text = def.key.toString()+def.value.toString()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })

    }
}

