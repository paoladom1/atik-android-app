package com.pdm.atikapp.entity

data class Product(var id: Int, var name: String, var price: Int, var description: String, var flavor: String, var size: Int, var indications: String, var imageUrl: String, var categories: List<Categories>)