package com.example.week2_0706012110034


import Arraylist.GlobalVar
import Model.Animal
import Model.Ayam
import Model.Kambing
import Model.Sapi
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_animal_add.*
import kotlinx.android.synthetic.main.activity_animal_add.view.*
import kotlinx.android.synthetic.main.card_animal.*


class AnimalAddActivity : AppCompatActivity() {
    private lateinit var  animal: Animal
    private lateinit var  Kambing: Animal
    private lateinit var  Ayam: Animal
    private lateinit var  Sapi: Animal
    private var position= -1
    private var uri: Uri = Uri.EMPTY

//    private var getpos= intent.getIntExtra("getpos", -1)


    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){   // APLIKASI GALLERY SUKSES MENDAPATKAN IMAGE
            uri = it.data?.data!! // GET PATH TO IMAGE FROM GALLEY
            if (uri != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    baseContext.getContentResolver().takePersistableUriPermission(uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }
            }
            fotohewan.setImageURI(uri) // MENAMPILKAN DI IMAGE VIEW
            if (position!=-1)
                GlobalVar.listDataAnimal[position].imageUri = uri.toString()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_add)
        //untuk mengecek edit atau add yang dilakukan
        val et = intent.getIntExtra("edit", 0)
        if (et==1){
            textviewedit.visibility= View.VISIBLE
            textViewadd.visibility= View.INVISIBLE
            editButton1.visibility= View.VISIBLE
            addButton.visibility= View.INVISIBLE
        }

        GetIntent()
        Listener()
    }

    private fun GetIntent() {
        position = intent.getIntExtra("position", -1)
        if (position != -1){
            val animal = GlobalVar.listDataAnimal[position]
            Display(animal)
        }
    }
    private fun Display(animal: Animal){
        namaHewanText.setText(animal.namaHewan)

//        if (sapi.isChecked){
//
//        }
        radioGroup.clearCheck()

//        jenisHewanText.setText(animal.jenisHewan)
      if (animal.jenisHewan== "Sapi"){
//          sapi.radioGroup.checkedRadio
      //          ButtonId
          sapi.setChecked(true)
      }else if(animal.jenisHewan == "Kambing") {
//          kambing.radioGroup.checkedRadioButtonId
          kambing.setChecked(true)
      }
      else if(animal.jenisHewan == "Ayam"){
//          ayam.radioGroup.checkedRadioButtonId
          //radioGroup.clearCheck()
          ayam.setChecked(true)
             }

        usiaHewanText.setText(animal.usiaHewan)

        if (animal.imageUri!!.isNotEmpty()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                baseContext.getContentResolver().takePersistableUriPermission(
                    Uri.parse(animal.imageUri),
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
            }
            fotohewan.setImageURI(Uri.parse(animal.imageUri))

        }

    }
    private fun Listener() {
        addfotohewan.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }
        fotohewan.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }
        addButton.setOnClickListener {

            var namaHewan = namaHewanText.text.toString().trim()
            //var jenisHewan = jenisHewanText.text.toString().trim()
            if (sapi.isChecked){

                var jenisHewan = "Sapi"
                var usiaHewan = usiaHewanText.text.toString().trim()

                Sapi = Sapi(namaHewan, jenisHewan, usiaHewan, uri.toString())
                checkersapi()

            }else if (kambing.isChecked){
                var jenisHewan = "Kambing"
                var usiaHewan = usiaHewanText.text.toString().trim()

                Kambing = Kambing(namaHewan, jenisHewan, usiaHewan, uri.toString())
                checkerkambing()

            }else if (ayam.isChecked){
                var jenisHewan = "Ayam"
                var usiaHewan = usiaHewanText.text.toString().trim()

                Ayam = Ayam(namaHewan, jenisHewan, usiaHewan, uri.toString())
                checkerayam()

            }else{
                Toast.makeText(this, "Choose Animal Type", Toast.LENGTH_SHORT).show()
            }


//            if (Globalvar.listDataAnimal.isNotEmpty()){
//                val myIntent = Intent(this, MainActivity::class.java).apply {
//                    val et = 1;
//
//                    putExtra("a", et)
//
//
//                }
//
//                startActivity(myIntent)
//            }


        }
        editButton1.setOnClickListener {
            var namaHewan = namaHewanText.text.toString().trim()
            if (sapi.isChecked){
                var jenisHewan = "Sapi"
                var usiaHewan = usiaHewanText.text.toString().trim()

                Sapi = Sapi(namaHewan, jenisHewan, usiaHewan, uri.toString())
                checkersapi()

            }else if (kambing.isChecked){
                var jenisHewan = "Kambing"
                var usiaHewan = usiaHewanText.text.toString().trim()

                Kambing = Kambing(namaHewan, jenisHewan, usiaHewan, uri.toString())
                checkerkambing()

            }else if (ayam.isChecked){
                var jenisHewan = "Ayam"
                var usiaHewan = usiaHewanText.text.toString().trim()

                Ayam = Ayam(namaHewan, jenisHewan, usiaHewan, uri.toString())
                checkerayam()

            }else{
                Toast.makeText(this, "Choose Animal Type", Toast.LENGTH_SHORT).show()
            }
//finish()
            //Toast.makeText(this, "Animal has been edited", Toast.LENGTH_SHORT).show()
        }


        backbutton.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }
    }

    private fun checkerkambing() {
        //masih belum lengkap!!!
        var isCompleted: Boolean = true


        if (Kambing.imageUri!!.isEmpty()) { //bug
            Toast.makeText(baseContext, "Please pick picture and input again!!", Toast.LENGTH_LONG).show()
            isCompleted = false
            if (Kambing.namaHewan!!.isEmpty()) {
                namaHewanText.error = "Please enter animal name!!"
                isCompleted = false

                if (Kambing.jenisHewan!!.isEmpty()) {

                    radioGroup.kambing.error = "Please enter animal type!!"
                    isCompleted = false

                    if (Kambing.usiaHewan!!.isEmpty()) {
                        usiaHewanText.error = "Please enter animal age!!"
                        isCompleted = false
                    }

                }


            }
            //rating
            if (Kambing.jenisHewan!!.isEmpty()) {
                radioGroup.kambing.error = "Please enter animal type!!"
                isCompleted = false
            }
            //genre
            if (Kambing.usiaHewan!!.isEmpty()) {
                usiaHewanText.error = "Please enter animal age!!"
                isCompleted = false
            }

            //product= Product(id,user_id,productname,category,expdate,quantity,"","")
        } else {

            fotohewan.setImageURI((Uri.parse(Kambing.imageUri)))
            if (Kambing.imageUri!!.isNotEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    baseContext.getContentResolver().takePersistableUriPermission(
                        Uri.parse(Kambing.imageUri),
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }
                fotohewan.setImageURI((Uri.parse(Kambing.imageUri)))

            }

        }
        if (Kambing.namaHewan!!.isEmpty()) {
            namaHewanText.error = "Please enter animal name!!"
            isCompleted = false
        }
        //rating
        if (Kambing.jenisHewan!!.isEmpty()) {
            radioGroup.sapi.error = "Please enter animal type!!"
            isCompleted = false
        }
        //genre
        if (Kambing.usiaHewan!!.isEmpty()) {
            usiaHewanText.error = "Please enter animal age!!"
            isCompleted = false
        }



        if (isCompleted) {

            if (position == -1) {

                GlobalVar.listDataAnimal.add(Kambing)
                val myIntent = Intent(this, MainActivity::class.java).apply {
                    val et = 1;

                    putExtra("a", et)


                }

                startActivity(myIntent)

            } else {

                GlobalVar.listDataAnimal[position] = Kambing
                val myIntent = Intent(this, MainActivity::class.java).apply {
                    val et = 1;

                    putExtra("a", et)


                }

                startActivity(myIntent)

            }
//finish()

        }
    }
    private fun checkerayam() {
        //masih belum lengkap!!!
        var isCompleted: Boolean = true


        if (Ayam.imageUri!!.isEmpty()) { //bug
            Toast.makeText(baseContext, "Please pick picture and input again!!", Toast.LENGTH_LONG).show()
            isCompleted = false
            if (Ayam.namaHewan!!.isEmpty()) {
                namaHewanText.error = "Please enter animal name!!"
                isCompleted = false

                if (Ayam.jenisHewan!!.isEmpty()) {

                    radioGroup.ayam.error = "Please enter animal type!!"
                    isCompleted = false

                    if (Ayam.usiaHewan!!.isEmpty()) {
                        usiaHewanText.error = "Please enter animal age!!"
                        isCompleted = false
                    }

                }


            }
            //rating
            if (Ayam.jenisHewan!!.isEmpty()) {
                radioGroup.ayam.error = "Please enter animal type!!"
                isCompleted = false
            }
            //genre
            if (Ayam.usiaHewan!!.isEmpty()) {
                usiaHewanText.error = "Please enter animal age!!"
                isCompleted = false
            }

            //product= Product(id,user_id,productname,category,expdate,quantity,"","")
        } else {

            fotohewan.setImageURI((Uri.parse(Ayam.imageUri)))
            if (Ayam.imageUri!!.isNotEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    baseContext.getContentResolver().takePersistableUriPermission(
                        Uri.parse(Ayam.imageUri),
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }
                fotohewan.setImageURI((Uri.parse(Ayam.imageUri)))

            }

        }
        if (Ayam.namaHewan!!.isEmpty()) {
            namaHewanText.error = "Please enter animal name!!"
            isCompleted = false
        }
        //rating
        if (Ayam.jenisHewan!!.isEmpty()) {
            radioGroup.ayam.error = "Please enter animal type!!"
            isCompleted = false
        }
        //genre
        if (Ayam.usiaHewan!!.isEmpty()) {
            usiaHewanText.error = "Please enter animal age!!"
            isCompleted = false
        }



        if (isCompleted) {

            if (position == -1) {

                GlobalVar.listDataAnimal.add(Ayam)
                val myIntent = Intent(this, MainActivity::class.java).apply {
                    val et = 1;

                    putExtra("a", et)


                }

                startActivity(myIntent)

            } else {

                GlobalVar.listDataAnimal[position] = Ayam
                val myIntent = Intent(this, MainActivity::class.java).apply {
                    val et = 1;

                    putExtra("a", et)


                }

                startActivity(myIntent)

            }
//finish()

        }
    }
    private fun checkersapi() {
        //masih belum lengkap!!!
        var isCompleted: Boolean = true


        if (Sapi.imageUri!!.isEmpty()) { //bug
            Toast.makeText(baseContext, "Please pick picture and input again!!", Toast.LENGTH_LONG).show()
            isCompleted = false
            if (Sapi.namaHewan!!.isEmpty()) {
                namaHewanText.error = "Please enter animal name!!"
                isCompleted = false

                if (Sapi.jenisHewan!!.isEmpty()) {

                    radioGroup.sapi.error = "Please enter animal type!!"
                    isCompleted = false

                    if (Sapi.usiaHewan!!.isEmpty()) {
                        usiaHewanText.error = "Please enter animal age!!"
                        isCompleted = false
                    }

                }


            }
            //rating
            if (Sapi.jenisHewan!!.isEmpty()) {
                radioGroup.sapi.error = "Please enter animal type!!"
                isCompleted = false
            }
            //genre
            if (Sapi.usiaHewan!!.isEmpty()) {
                usiaHewanText.error = "Please enter animal age!!"
                isCompleted = false
            }

            //product= Product(id,user_id,productname,category,expdate,quantity,"","")
        } else {

            fotohewan.setImageURI((Uri.parse(Sapi.imageUri)))
            if (Sapi.imageUri!!.isNotEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    baseContext.getContentResolver().takePersistableUriPermission(
                        Uri.parse(Sapi.imageUri),
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }
                fotohewan.setImageURI((Uri.parse(Sapi.imageUri)))

            }

        }
        if (Sapi.namaHewan!!.isEmpty()) {
            namaHewanText.error = "Please enter animal name!!"
            isCompleted = false
        }
        //rating
        if (Sapi.jenisHewan!!.isEmpty()) {
            radioGroup.sapi.error = "Please enter animal type!!"
            isCompleted = false
        }
        //genre
        if (Sapi.usiaHewan!!.isEmpty()) {
            usiaHewanText.error = "Please enter animal age!!"
            isCompleted = false
        }



        if (isCompleted) {

            if (position == -1) {

                GlobalVar.listDataAnimal.add(Sapi)
                val myIntent = Intent(this, MainActivity::class.java).apply {
                    val et = 1;

                    putExtra("a", et)


                }

                startActivity(myIntent)

            } else {

                GlobalVar.listDataAnimal[position] = Sapi
                val myIntent = Intent(this, MainActivity::class.java).apply {
                    val et = 1;

                    putExtra("a", et)


                }

                startActivity(myIntent)

            }
//finish()

        }
    }
    override fun onResume() {
        super.onResume()
        if (position!=-1){
            val ANIMALL = GlobalVar.listDataAnimal[position]
            Display(ANIMALL)
        }

    }

}