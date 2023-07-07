package com.diana.ec2_aliaga_farro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.diana.ec2_aliaga_farro.databinding.ActivityInicioBinding

class InicioActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInicioBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnlogin.setOnClickListener{
            Toast.makeText(this,"Inicio de Sesión", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,LogeoActivity::class.java)
            startActivity(intent)
        }
    }
}