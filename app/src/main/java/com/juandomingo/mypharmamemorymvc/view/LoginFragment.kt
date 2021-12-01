package com.juandomingo.mypharmamemorymvc.view


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.juandomingo.mypharmamemorymvc.R
import com.juandomingo.mypharmamemorymvc.controller.AppHomeFragment
import com.juandomingo.mypharmamemorymvc.controller.MainActivity
import com.juandomingo.mypharmamemorymvc.databinding.FragmentLoginBinding
import com.juandomingo.mypharmamemorymvc.model.Context
import java.util.regex.Pattern

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    // Firebase Firestore.
    //private val firestoreDB: FirebaseFirestore = FirebaseFirestore.getInstance()
    // User account Firebase.
    private lateinit var auth: FirebaseAuth
    private val TAG = LoginFragment.javaClass.simpleName
    companion object {
        fun newInstance() = LoginFragment()
    }
    

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        binding.etLogEmail.requestFocus()
        auth = Firebase.auth
        login()
        logup()
        logout()

    }


    private fun login() {
        binding.btnLogAccess.setOnClickListener {
            val etEmail = binding.etLogEmail.text.toString()
            val etPassword = binding.etLogPassword.text.toString()
            if (emailPasswordCheck(etEmail, etPassword)) {
                auth.signInWithEmailAndPassword(etEmail, etPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "signInWithEmailAndPassword:success")
                            Toast.makeText(
                                Context.context,
                                "Usuario $etEmail iniciando sesión....",
                                Toast.LENGTH_LONG
                            ).show()
                            navigateFromLoginToAppHome()
                        } else {
                            Log.w(TAG, "signInWithEmailAndPassword:failure", task.exception)
                            Toast.makeText(
                                Context.context,
                                "Fallo en la autenticación",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(
                    Context.context,
                    "Correo o contraseña incorrectos",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    /*public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null)
            reload()
    }*/

    private fun logup(){
        binding.btnLogRegister.setOnClickListener {
            val etEmail = binding.etLogEmail.text.toString()
            val etPassword = binding.etLogPassword.text.toString()
            if (emailPasswordCheck(etEmail, etPassword))
                createUserAccount(etEmail, etPassword)
        }
    }
    private fun emailPasswordCheck(etEmail: String, etPassword: String): Boolean {
        var areCorrect: Boolean = false
        // Patrón : > 6 carácteres y 1 carácter especial
        val passwordRegex = Pattern
            .compile("^" +
                    "(?=.*[-@#$%^&+=])" +
                    ".{6,}" +
                    "$")
        if (etEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(etEmail).matches()) {
            Toast.makeText(
                Context.context,
                "Debe introducir un email válido",
                Toast.LENGTH_LONG
            ).show()
        } else if (etPassword.isEmpty() || !passwordRegex.matcher(etPassword).matches()) {
            Toast.makeText(
                Context.context,
                "Introduzca una contraseña correcta",
                Toast.LENGTH_LONG
            ).show()
        } else {
            areCorrect = true
        }
        return areCorrect
    }
    private fun createUserAccount(etEmail: String, etPassword: String) {
        // User account create firebase.
        auth.createUserWithEmailAndPassword(etEmail, etPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        Context.context,
                        "Usuario registrado con éxito",
                        Toast.LENGTH_LONG
                    ).show()
                    // 2ª forma que funciona.
                    navigateFromLoginToAppHome()
                    // 1ª forma que NO funciona.
                    //findNavController().navigate(R.id.action_loginFragment_to_appHomeFragment)
                } else {
                    Toast.makeText(
                        Context.context,
                        "Error al registrar usuario",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun logout() {
        binding.btnLogLogout.setOnClickListener {
            auth.signOut()
            val etEmail = binding.etLogEmail.text.toString()
            binding.etLogEmail.setText("")
            binding.etLogPassword.setText("")
            Toast.makeText(
                Context.context,
                "Usuario $etEmail cerrando sesión....",
                Toast.LENGTH_LONG
            ).show()
            reload()
        }
    }
    private fun reload() {
        val intent = Intent(Context.context, MainActivity::class.java)
        this.startActivity(intent)
    }

    private fun navigateFromLoginToAppHome(){
        val fragment = AppHomeFragment()
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.navHostFragment, fragment)?.commit()
    }

    // TODO Hide Keyboard !!!!!
    /*private fun hideKeyboard() {
        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.loginLayout.windowToken, 0)
    }*/
}