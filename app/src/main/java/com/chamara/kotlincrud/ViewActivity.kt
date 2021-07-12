package com.chamara.kotlincrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONObject

class ViewActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        //val btnRefresh:FloatingActionButton=findViewById(R.id.floatingActionButton3)
        val geturl:String="https://studentmanagementcrud.000webhostapp.com/institute.php"

        //btnRefresh.setOnClickListener {
            var requestQueue:RequestQueue= Volley.newRequestQueue(this)
            var jsonObjectRequest:JsonObjectRequest= JsonObjectRequest(Request.Method.GET,geturl,null, Response.Listener { response ->
                var jsonArray:JSONArray=response.getJSONArray("institute")
                for (x in 0..jsonArray.length()-1){
                    var jsonObject:JSONObject=jsonArray.getJSONObject(x)
                    var name:String=jsonObject.getString("name")
                    var address:String=jsonObject.getString("address")
                    var contact:String=jsonObject.getString("contact")
                    cards(name,address,contact)
                }
            },
                Response.ErrorListener { error ->
                    Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
                })
            requestQueue.add(jsonObjectRequest)
        //}

    }

    private fun cards(name: String, address: String, contact: String) {

        val mainLinear:LinearLayout=findViewById(R.id.mainLinear)

        var layoutParams:LinearLayout.LayoutParams= LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(30,30,30,30)

        var cardView:CardView= CardView(this)
        cardView.radius= 18.0F

        var linearLayout:LinearLayout= LinearLayout(this)
        linearLayout.orientation=LinearLayout.VERTICAL
        linearLayout.setPadding(15,15,15,15)

        var txtName:TextView= TextView(this)
        txtName.text=name

        var txtAddress:TextView= TextView(this)
        txtAddress.text=address

        var txtContact:TextView= TextView(this)
        txtContact.text=contact

        linearLayout.addView(txtName)
        linearLayout.addView(txtAddress)
        linearLayout.addView(txtContact)

        cardView.addView(linearLayout)

        mainLinear.addView(cardView,layoutParams)
    }

}