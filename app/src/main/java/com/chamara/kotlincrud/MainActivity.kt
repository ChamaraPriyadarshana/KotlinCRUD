package com.chamara.kotlincrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtName=findViewById<TextInputEditText>(R.id.txtName)
        val txtContact=findViewById<TextInputEditText>(R.id.txtContact)
        val txtAddress=findViewById<TextInputEditText>(R.id.txtAddress)
        val btnSave=findViewById<Button>(R.id.button)
        var btnNext=findViewById<FloatingActionButton>(R.id.floatingActionButton2)

        var name:String
        var address:String
        var contact:String

        btnSave.setOnClickListener {
            name=txtName.text.toString()
            address=txtAddress.text.toString()
            contact=txtContact.text.toString()

            postRequest(name,address,contact)
        }

        btnNext.setOnClickListener {
            var intent:Intent=Intent(this,ViewActivity::class.java)
            startActivity(intent)
        }
    }

    private fun postRequest(name: String, address: String, contact: String) {
        var posturl:String="https://studentmanagementcrud.000webhostapp.com/addinstitute.php"
        var rq:RequestQueue=Volley.newRequestQueue(this)
        var sr:StringRequest= object:StringRequest(Request.Method.POST,posturl, Response.Listener { response ->
            Toast.makeText(this,response.trim().toString(),Toast.LENGTH_LONG).show()
            if (response.trim().contains("Item Added")){
                Toast.makeText(this,"Success",Toast.LENGTH_LONG).show()
            }
        }, Response.ErrorListener { error ->
            Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
        }){
            override fun getParams(): MutableMap<String, String>? {
                val params=HashMap<String,String>()
                params.put("name",name)
                params.put("address",address)
                params.put("contact",contact)
                return params
            }
        }
        rq.add(sr)
    }
}