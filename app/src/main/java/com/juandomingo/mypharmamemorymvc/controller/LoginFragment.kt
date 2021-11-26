package com.juandomingo.mypharmamemorymvc.controller


import android.os.Bundle
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.juandomingo.mypharmamemorymvc.R
import com.juandomingo.mypharmamemorymvc.databinding.FragmentLoginBinding
import com.juandomingo.mypharmamemorymvc.model.Context
import com.juandomingo.mypharmamemorymvc.model.LoginViewModel

class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel
    // Firebase Firestore.
    private val firestoreDB: FirebaseFirestore = FirebaseFirestore.getInstance()
    // User account Firebase.
    private lateinit var auth: FirebaseAuth
    companion object {
        fun newInstance() = LoginFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.etLogEmail.requestFocus()
        auth = Firebase.auth
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

    private fun login() {
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
    private fun logup() {
        binding.btnLogRegister.setOnClickListener {
            val etEmail = binding.etLogEmail.text.toString()
            val etPassword = binding.etLogPassword.text.toString()
            if (etEmail.isNotEmpty() && etPassword.isNotEmpty()) {
                if (etPassword.length >= 6) {
                    val userHash: HashMap<String, String> = hashMapOf(
                        "email" to etEmail,
                        "password" to etPassword
                    )
                    // Firebase Firestore.
                    /*firestoreDB.collection("users").document(etEmail)
                        .set(userHash).addOnCompleteListener {*/
                    // Account user firebase.
                    auth.createUserWithEmailAndPassword(etEmail, etPassword)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    Context.context,
                                    "Usuario guardado con éxito",
                                    Toast.LENGTH_LONG
                                ).show()
                                navigateToAppHome()
                                //NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_appHomeFragment, null)
                                //view?.findNavController()?.navigate(R.id.action_loginFragment_to_appHomeFragment, null)
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
    private fun logout() {
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
    private fun navigateToAppHome(){
        val fragment = AppHomeFragment()
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.navHostFragment, fragment)?.commit()
    }
}