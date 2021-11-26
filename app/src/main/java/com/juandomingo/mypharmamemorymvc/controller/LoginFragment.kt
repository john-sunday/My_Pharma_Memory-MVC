package com.juandomingo.mypharmamemorymvc.controller

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.juandomingo.mypharmamemorymvc.R
import com.juandomingo.mypharmamemorymvc.databinding.FragmentLoginBinding
import com.juandomingo.mypharmamemorymvc.model.Context
import com.juandomingo.mypharmamemorymvc.model.LoginViewModel

class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel
    private val firestoreDB: FirebaseFirestore = FirebaseFirestore.getInstance()
    companion object {
        fun newInstance() = LoginFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.etLogEmail.requestFocus()
        login()
        logup()
        logout()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun login() {
        binding.btnLogAccess.setOnClickListener {
            val etEmail = binding.etLogEmail.text.toString()
            val etPassword = binding.etLogPassword.text.toString()
            if (etEmail != "" && etPassword != "") {
                if (etPassword.length >= 6) {

                } else{
                  Toast.makeText(Context.context ,"Debe introducir al menos 6 carácteres para la contraseña", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    fun logup() {
        binding.btnLogRegister.setOnClickListener {
            val etEmail = binding.etLogEmail.text.toString()
            val etPassword = binding.etLogPassword.text.toString()
            if (etEmail != "" && etPassword != "") {
                if (etPassword.length >= 6) {
                    val userHash: HashMap<String, String> = hashMapOf(
                        "email" to etEmail,
                        "password" to etPassword
                    )
                    // Firebase Firestore.
                    firestoreDB.collection("users").document(etEmail)
                        .set(userHash).addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    Context.context,
                                    "Usuario guardado con éxito",
                                    Toast.LENGTH_LONG
                                ).show()
                                val intentToMainAct = Intent(Context.context, MainActivity::class.java)
                                startActivity(intentToMainAct)
                            } else {
                                Toast.makeText(
                                    Context.context,
                                    "Error al guardar usuario",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(
                        Context.context,
                        "Debe introducir al menos 6 carácteres para la contraseña",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    fun logout() {
        binding.btnLogLogout.setOnClickListener {
            val etEmail = binding.etLogEmail.text.toString()
            val etPassword = binding.etLogPassword.text.toString()
            if (etEmail != "" && etPassword != "") {
                if (etPassword.length >= 6) {

                } else {
                    Toast.makeText(
                        Context.context,
                        "Debe introducir al menos 6 carácteres para la contraseña",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}