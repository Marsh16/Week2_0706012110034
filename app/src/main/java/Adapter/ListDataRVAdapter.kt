package Adapter
import Arraylist.GlobalVar
import Interface.CardListener
import Model.Animal
import Model.Ayam
import Model.Kambing
import Model.Sapi
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.week2_0706012110034.AnimalAddActivity
import com.example.week2_0706012110034.MainActivity
import com.example.week2_0706012110034.R
import kotlinx.android.synthetic.main.card_animal.view.*



class ListDataRVAdapter (val listAnimal: ArrayList<Animal>, val cardListener: CardListener):
    RecyclerView.Adapter<ListDataRVAdapter.viewHolder>() {
    //private var position = -1
//    private lateinit var kambing: Kambing
//    private lateinit var sapi: Sapi
//    private lateinit var ayam: Ayam

    class viewHolder(val itemView: View, val cardListener1: CardListener) :
        RecyclerView.ViewHolder(itemView) {

//manage card, membuat card view
//       val jenisanimal = GlobalVar.listDataAnimal is Kambing
        val namahewan_card = itemView.namaHewan
        val jenishewan_card = itemView.jenisHewan
        val usiahewan_card = itemView.usiaHewan
        val fotohewan_card = itemView.fotoHewan
        val tomboledit = itemView.editButton
        val tomboldelete = itemView.deleteButton
        val tombolsound = itemView.soundButton
        val tombolfood = itemView.foodButton




        fun setData(data: Animal) {
            tomboledit.setOnClickListener {
//              tomboledit.setText("nasjdn")

                    v ->
                val intent = Intent(v.context, AnimalAddActivity::class.java).apply {
                    val et = 1;
                    putExtra("position", position)
                    putExtra("edit", et)
                }
                v.context.startActivity(intent)
            }
            // tomboldelete
            // cardlistener1.OnDeleteClick(position)
            tombolsound.setOnClickListener { v ->
                //for
//                for (i in 0 until GlobalVar.listDataAnimal.size) {
                    if (GlobalVar.listDataAnimal.get(position) is Kambing) {
                        Toast.makeText(v.context, " Blehhh.....", Toast.LENGTH_SHORT).show()
                    } else if (GlobalVar.listDataAnimal.get(position) is Ayam) {
                        Toast.makeText(v.context, " Bock..... Bock...Bock...", Toast.LENGTH_SHORT)
                            .show()
                    } else if (GlobalVar.listDataAnimal.get(position) is Sapi) {
                        Toast.makeText(v.context, "Mooo....", Toast.LENGTH_SHORT).show()
                    }
//                }
            }
            tombolfood.setOnClickListener { v ->
//                for (i in 0 until GlobalVar.listDataAnimal.size) {
                    if (GlobalVar.listDataAnimal.get(position) is Kambing) {
                        Toast.makeText(
                            v.context,
                            "Kamu memberi makan hewan dengan rerumputan!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (GlobalVar.listDataAnimal.get(position) is Ayam) {
                        Toast.makeText(
                            v.context,
                            "Kamu memberi makan hewan dengan biji - bijian!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else if (GlobalVar.listDataAnimal.get(position) is Sapi) {
                        Toast.makeText(
                            v.context,
                            "Kamu memberi makan hewan dengan rerumputan!",
                            Toast.LENGTH_SHORT
                        ).show()
//                    }
                }
            }
            tomboldelete.setOnClickListener {
                    v ->
                val mAlertDialog = AlertDialog.Builder(v.context)
                //set alertdialog icon
                mAlertDialog.setTitle("Delete animal") //set alertdialog title
                mAlertDialog.setMessage("Are you sure you want to delete this animal?") //set alertdialog message
                mAlertDialog.setPositiveButton("Delete") { dialog, id ->
                    //perform some tasks here
                    Toast.makeText(v.context, GlobalVar.listDataAnimal.get(position).namaHewan + " has been deleted", Toast.LENGTH_SHORT).show()
                    GlobalVar.listDataAnimal.removeAt(position)

//                    finish()
                    if (GlobalVar.listDataAnimal.isNotEmpty()){

                        val myIntent = Intent(v.context, MainActivity::class.java).apply {
                            val et = 0;

                            putExtra("a", et)

                        }

                        v.context.startActivity(myIntent)
                    }else{
                        val myIntent = Intent(v.context, MainActivity::class.java).apply {
                            val et = 2;

                            putExtra("a", et)

                        }

                        v.context.startActivity(myIntent)
                    }

                }
                mAlertDialog.setNegativeButton("Cancel") { dialog, id ->

                }
                mAlertDialog.show()

            }
            namahewan_card.text= data.namaHewan
            jenishewan_card.text= data.jenisHewan
            usiahewan_card.text= data.usiaHewan
            if (!data.imageUri!!.isEmpty())
                fotohewan_card.setImageURI(Uri.parse(data.imageUri))
            // itemview cardnya di click
            itemView.setOnClickListener {
                cardListener1.onCardClick(adapterPosition)
            }

        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListDataRVAdapter.viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_animal, parent, false)
        return viewHolder(view, cardListener)

    }

    override fun onBindViewHolder(holder: ListDataRVAdapter.viewHolder, position: Int) {
        holder.setData(listAnimal[position])
        //mengisi card viewnya 100/ 10
    }

    override fun getItemCount(): Int {
        //mengambil jumlah
        return listAnimal.size
    }

}