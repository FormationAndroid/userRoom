package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.database.User
import com.example.myapplication.database.UserDao
import com.example.myapplication.database.getDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_user.view.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val userDao: UserDao? by lazy { getDatabase(this)?.userDao() }
    lateinit var adapter: UsersRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
            showAlertDialog()
        }

        thread {
            val users = userDao?.getUsers()
            runOnUiThread {
                users?.let {
                    adapter = UsersRecyclerAdapter(users.toMutableList()) {
                        thread {
                            userDao?.deleteUser(it)
                        }
                    }
                    recyclerView.adapter = adapter
                }
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
            thread {
                val user = User(name = customLayout.editName.text.toString(), age = 42)
                userDao?.insertUsers(user)
                runOnUiThread {
                    adapter.addUser(user)
                    dialog.dismiss()
                }
            }
        }

        dialog.show()
    }
}




