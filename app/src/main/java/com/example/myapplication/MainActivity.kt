package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.database.User
import com.example.myapplication.database.getDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_user.view.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showAlertDialog()

        val user = User(name = "john", age = 42)
        val userDao = getDatabase(this)?.userDao()

        thread {
            userDao?.insertUsers(user)

            val users = userDao?.getUsers()

            runOnUiThread {
                textTest.text = users.toString()
            }

        }

    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Name")
        val customLayout = layoutInflater.inflate(R.layout.dialog_add_user, null);
        builder.setView(customLayout)
        val dialog = builder.create()

        customLayout.btnOk.setOnClickListener {
            Toast.makeText(applicationContext, customLayout.editName.text.toString(), Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.show()
    }
}




