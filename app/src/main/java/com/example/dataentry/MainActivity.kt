package com.example.dataentry

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private var imageUri: Uri?=null

    private lateinit var repo: Repo
    private lateinit var viewmodelFactory: ViewModelFactory
    private lateinit var contactviewModel:ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repo=Repo()
        viewmodelFactory= ViewModelFactory(repo)
        contactviewModel=ViewModelProvider(this,viewmodelFactory).get(ContactViewModel::class.java)



//        val listofcontact= mutableListOf<contact_data>()
        val rv:RecyclerView=findViewById(R.id.rv)


        rv.layoutManager=LinearLayoutManager(this)


        contactviewModel.listOfContactLiveData.observe(this)
        {
            val adapter=contact_list_adapter(it)
            rv.adapter=adapter
        }

        val dialog_btn:FloatingActionButton=findViewById(R.id.btn)
        dialog_btn.setOnClickListener {
            val dialog=Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.dialog_layout)
            dialog.show()

            val image_btn=dialog.findViewById<Button>(R.id.btn_image)
            val create_btn=dialog.findViewById<Button>(R.id.btn_create)

            image_btn.setOnClickListener {
                 val ImageIntent= Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(ImageIntent,101)
            }



            create_btn.setOnClickListener {
                val person_name:EditText=dialog.findViewById<EditText>(R.id.newname)
                val phone_number:EditText=dialog.findViewById<EditText>(R.id.newphone)
                val curr_contact=contact_data(
                    imageres = imageUri,
                    curr_name = person_name.text.toString(),
                    curr_phone = phone_number.text.toString()
                )


                contactviewModel.addContact(curr_contact)
                rv.layoutManager=LinearLayoutManager(this)
                
                dialog.cancel()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            imageUri=data?.data
        }


    }
}