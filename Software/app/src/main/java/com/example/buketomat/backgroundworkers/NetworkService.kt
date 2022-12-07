package com.example.buketomat.backgroundworkers

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.buketomat.entites.User
import com.example.buketomat.models.Order
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


object NetworkService {
    private const val  baseurl: String = "https://buketomatdb.000webhostapp.com/"                       //server url

    fun getUsers(callback: UsersSync, context : Context)                            //callback param is used to await data before doing something with it
    {
        val queue  = Volley.newRequestQueue(context)                                    //this is list of all request - it should probably be global in the future (btw requests can be canceled)
        val url = baseurl + "Test.php"
        val users : MutableList<User> = mutableListOf()
        val  jsonRequest = JsonArrayRequest(Request.Method.GET ,url,null,     //defines new request(method,url,success and failure callback functions)
            { response ->
                try {                                                                   //try-catch is here to prevent crashes caused by wrong data format
                    for (i in 0 until response.length()) {
                        val userRaw = response.getJSONObject(i)
                        users.add(User(userRaw))
                    }
                    callback.onUsersReceived(users)                                           //tell parent that data is ready
                }
                catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            {Log.d("API", "Something went wrong while establishing connection to server") })
        queue.add(jsonRequest)
    }


    fun getOrders(korisnikId : Int ,callback: OrdersSync, context : Context)            //callback param is used to await data before doing something with it
    {
        val queue  = Volley.newRequestQueue(context)                                    //this is list of all request - it should probably be global in the future (btw requests can be canceled)
        val url = baseurl + "GetOrders.php"
        val orders : MutableList<Order> = mutableListOf()

        val jsonUser = JSONObject().put("korisnik_id",korisnikId)
        val requestBody  = JSONArray().put(jsonUser)
        Log.d("JSON", requestBody.toString())

        val  jsonRequest = JsonArrayRequest(Request.Method.POST ,url,requestBody,
            { response ->
                Log.d("API", response.toString())
                try {                                                                   //try-catch is here to prevent crashes caused by wrong data format
                    for (i in 0 until response.length()) {
                        val orderRaw = response.getJSONObject(i)
                        orders.add(Order(orderRaw))
                    }
                    callback.onOrdersReceived(orders)                                           //tell parent that data is ready
                }
                catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            {   error ->
                Log.d("API", "Something went wrong while establishing connection to server")
                Log.e("Volly Error", error.toString());
            })
        queue.add(jsonRequest)
    }


}