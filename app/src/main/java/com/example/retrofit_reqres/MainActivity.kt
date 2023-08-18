package com.example.retrofit_reqres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        //BOTÃO DE GET
        findViewById<Button>(R.id.btnget).setOnClickListener(
            {
                getUserByID()
            }

        )

        //BOTÃO DE PUT
        findViewById<Button>(R.id.btnput)
        findViewById<Button>(R.id.btnput).setOnClickListener(
            {
                updateUserByID()
            }

        )

        //BOTÃO DE DELETE
        findViewById<Button>(R.id.btndelete)
        findViewById<Button>(R.id.btndelete).setOnClickListener(
            {
                deleteUserByID()
            }

        )

        //BOTÃO DE POST
        findViewById<Button>(R.id.btnpost)
        findViewById<Button>(R.id.btnpost).setOnClickListener{
            createUser()
        }




    }


    //Insere Usuario
    private fun createUser() {
        lifecycleScope.launch {
            val body = JsonObject().apply{
                addProperty("name", "Felipe")
                addProperty("job", "Developer")
            }
            val result = apiService.createUser(body)
            if(result.isSuccessful){
                Log.e("Create data", "${result.body()}")
            }else{
                Log.e("Create data", "${result.message()}")
            }
        }
    }


    //Listagem de Usuario
    private fun getUserByID() {
        lifecycleScope.launch {
            val result = apiService.getUserbyID("2")

            if(result.isSuccessful){
                Log.e("Deu certo", "${result.body()?.data}")
            }else{
                Log.e("Deu errado", "${result.message()}")
            }
        }
    }

    //Atalizar Usuario
    private fun updateUserByID(){
        lifecycleScope.launch {
            val body = JsonObject().apply {
                addProperty("name", "Felipe")
                addProperty("job", "Developer")
            }
            val result = apiService.updateUser("2", body)
            if(result.isSuccessful){
                Log.e("Update data", "${result.body()}")
            }else{
                Log.e("Update data", "${result.message()}")
            }
        }
    }

    //Deletar Usuario
    private  fun deleteUserByID(){
        lifecycleScope.launch {
            val result = apiService.deleteUser("2")

            if(result.isSuccessful){
                Log.e("Deletou", "${result}")
            }else{
                Log.e("Deletou", "${result.message()}")
            }
        }
    }
}