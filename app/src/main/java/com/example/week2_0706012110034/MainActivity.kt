package com.example.week2_0706012110034

import Adapter.ListDataRVAdapter
import Arraylist.GlobalVar
import Interface.CardListener
import Model.Ayam
import Model.Kambing
import Model.Sapi
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_animal_add.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() , CardListener {
    // Use the FloatingActionButton for all the add person
    // and add alarm


    // Use the ExtendedFloatingActionButton to handle the
    // parent FAB

//assa
    // to check whether sub FABs are visible or not
//    var isAllFabsVisible: Boolean? = null

    private val adapter = ListDataRVAdapter(GlobalVar.listDataAnimal, this)
    private val adapterkambing = ListDataRVAdapter(GlobalVar.kambing, this)
    private val adapterayam = ListDataRVAdapter(GlobalVar.ayam, this)
    private val adaptersapi = ListDataRVAdapter(GlobalVar.sapi, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CheckPermissions()

        setupRecyclerView()

        listener()

    }
    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
       // adaptertemp.notifyDataSetChanged()
        filterbutton.setOnClickListener {
//  GlobalVar.kambing.clear()
            val layoutManager = LinearLayoutManager(baseContext)
            listDataRV.layoutManager = layoutManager
            listDataRV.adapter=  adapter
                //adapter.notifyDataSetChanged()
           // GlobalVar.ayam.clear()

           // listDataRV.adapter=  adapterayam
            kambingaa.visibility= View.VISIBLE
            ayamaa.visibility= View.VISIBLE
            sapiaa.visibility= View.VISIBLE

        }
        kambingaa.setOnClickListener {
//            GlobalVar.kambing.clear()
//            GlobalVar.sapi.clear()
            val layoutManager = LinearLayoutManager(baseContext)
            listDataRV.layoutManager = layoutManager

            listDataRV.adapter=  adapterkambing
            GlobalVar.kambing.clear()
            for (i in 0 until GlobalVar.listDataAnimal.size) {
                if (GlobalVar.listDataAnimal.get(i) is Kambing) {
                    //   listDataRV.adapter=adaptertemp
                    val filterkambing = Kambing(
                        GlobalVar.listDataAnimal.get(i).namaHewan.toString(),
                        GlobalVar.listDataAnimal.get(i).jenisHewan.toString(),
                        GlobalVar.listDataAnimal.get(i).usiaHewan.toString(),
                        GlobalVar.listDataAnimal.get(i).imageUri.toString()
                    )

                    GlobalVar.kambing.add(filterkambing)

                    Toast.makeText(this, "filter kambing", Toast.LENGTH_SHORT).show()
                }
            }
            adapterkambing.notifyDataSetChanged()
        }
        sapiaa.setOnClickListener {
//            GlobalVar.kambing.clear()
//            GlobalVar.sapi.clear()
            val layoutManager = LinearLayoutManager(baseContext)
            listDataRV.layoutManager = layoutManager

            listDataRV.adapter=  adaptersapi
            GlobalVar.sapi.clear()
            for (i in 0 until GlobalVar.listDataAnimal.size) {
                if (GlobalVar.listDataAnimal.get(i) is Sapi) {
                    //   listDataRV.adapter=adaptertemp
                    val filtersapi = Sapi(
                        GlobalVar.listDataAnimal.get(i).namaHewan.toString(),
                        GlobalVar.listDataAnimal.get(i).jenisHewan.toString(),
                        GlobalVar.listDataAnimal.get(i).usiaHewan.toString(),
                        GlobalVar.listDataAnimal.get(i).imageUri.toString()
                    )

                    GlobalVar.sapi.add(filtersapi)

                    Toast.makeText(this, "filter sapi", Toast.LENGTH_SHORT).show()
                }
            }
            adaptersapi.notifyDataSetChanged()
        }

        ayamaa.setOnClickListener {
//            GlobalVar.kambing.clear()
//            GlobalVar.sapi.clear()
            val layoutManager = LinearLayoutManager(baseContext)
            listDataRV.layoutManager = layoutManager

            listDataRV.adapter=  adapterayam
            GlobalVar.ayam.clear()
            for (i in 0 until GlobalVar.listDataAnimal.size) {
                if (GlobalVar.listDataAnimal.get(i) is Ayam) {
                    //   listDataRV.adapter=adaptertemp
                    val filterayam = Ayam(
                        GlobalVar.listDataAnimal.get(i).namaHewan.toString(),
                        GlobalVar.listDataAnimal.get(i).jenisHewan.toString(),
                        GlobalVar.listDataAnimal.get(i).usiaHewan.toString(),
                        GlobalVar.listDataAnimal.get(i).imageUri.toString()
                    )

                    GlobalVar.ayam.add(filterayam)

                    Toast.makeText(this, "filter ayam", Toast.LENGTH_SHORT).show()
                }
            }
            adapterayam.notifyDataSetChanged()
        }

    }
    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(baseContext)
        listDataRV.layoutManager = layoutManager //set layout
        listDataRV.adapter=  adapter //set adapter
//        listDataRV.adapter = adapterkambing //set adapter
//        listDataRV.adapter = adapterayam
//        listDataRV.adapter = adaptersapi

    }
    private fun listener(){
        fabaddbutton.setOnClickListener {
            val myIntent = Intent(this, AnimalAddActivity::class.java)
            startActivity(myIntent)
        }
//        isAllFabsVisible = false
//        filterbutton.shrink()
filterbutton.setOnClickListener {

        sapiaa.visibility= View.VISIBLE
        kambingaa.visibility= View.VISIBLE
        ayamaa.visibility= View.VISIBLE
//        filterbutton.extend()

    }

//        search.setOnSearchClickListener {
//            if (search.equals("jj")){
//                Toast.makeText(baseContext, "ayam", Toast.LENGTH_LONG).show()
//            }
//        }
    }
    private fun CheckPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), GlobalVar.STORAGE_PERMISSION_CODE)
        } else {
            //Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), GlobalVar.STORAGE_PERMISSION_CODE)
        } else {
            //Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == GlobalVar.STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == GlobalVar.STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCardClick(position: Int) {
//        val myIntent = Intent(this, DetailActivity::class.java).apply {
//            putExtra("position", position)
//
//        }
//        startActivity(myIntent)
//        MaterialAlertDialogBuilder(this)
    }


}