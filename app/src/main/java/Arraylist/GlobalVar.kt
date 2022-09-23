package Arraylist

import Model.Animal
import Model.Ayam
import Model.Kambing
import Model.Sapi

class GlobalVar {
    companion object{
        val STORAGE_PERMISSION_CODE : Int = 100
        val listDataAnimal = ArrayList<Animal>()

        //untuk filter
        val kambing = ArrayList<Animal>()
        val sapi = ArrayList<Animal>()
        val ayam = ArrayList<Animal>()

    }
}