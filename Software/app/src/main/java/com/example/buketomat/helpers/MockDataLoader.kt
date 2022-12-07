package com.example.buketomat.helpers


import com.example.buketomat.entites.User
import com.example.buketomat.models.Bouquet
import com.example.buketomat.models.Flower
import com.example.buketomat.models.Order
import java.util.Date

object MockDataLoader {

    var list : MutableList<User> = mutableListOf(User(1,"mmarkic1@gmail.com", "mmarkic", "1234"),
        User(2,"iivic1@gmail.com", "iivic", "5678"),
        User(3,"ggabric1@gmail.com", "ggabric", "1111"))

    fun getDemoDataUsers(): MutableList<User> = list

    fun addDemoUsers(user: User){

        list.add(user)
    }

    fun getDemoDataOrders() : ArrayList<Order>
    {
        val orders : ArrayList<Order> = arrayListOf()
        orders.add(Order(752,258.0,null,User(1,"tomic@gmail.com","ttomic","test")))
        orders.add(Order(12,158.0,null,User(2,"tomic@gmail.com","ttomic","test") ))
        orders.add(Order(287,20.0,null,User(3,"tomic@gmail.com","ttomic","test") ))
        return orders

    }

    fun getBouquets() : ArrayList<Bouquet>
    {
        val bouquets : ArrayList<Bouquet>  = arrayListOf()
        bouquets.add(Bouquet(11,"Ruže za valentinovo","Predivan buket ruža za vaše najmilije",
            getFlowers(),15.00))
        bouquets.add(Bouquet(15,"Suncoketi","Predivan buket suncokreta koji ce vam ravedriti dan",
            getFlowers(),15.00))
        return bouquets
    }

    fun getFlowers() : ArrayList<Flower> {
        val colors : ArrayList<String>  = arrayListOf()
        colors.add("red")
        val flowers : ArrayList<Flower>  = arrayListOf()
        flowers.add(Flower(1,"Ruža crvena",colors,"",10.00))
        colors.add("yellow")
        flowers.add(Flower(2,"Žuta crvena",colors,"",5.00))
        return  flowers;

    }
}