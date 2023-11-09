package com.example.todolist
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class MainActivity : AppCompatActivity() {
    private  lateinit var  add: Button
    private lateinit var  todo:EditText
    private lateinit var  todolist:ListView
    private var itemList = ArrayList<String> ()
 private   var fileSaver = FileSaver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add = findViewById(R.id.button_id)
        todo = findViewById(R.id.edittext_id)
        todolist = findViewById(R.id.listview_id)
        itemList = fileSaver.readData(this)
        val arrayAdapter = ArrayAdapter (this,android.R.layout.simple_list_item_1,android.R.id.text1,itemList)

  todolist.adapter  = arrayAdapter

        add.setOnClickListener {
            val itemname = todo.toString()
            itemList.add(itemname)
            todo.setText("")
            fileSaver.writeData(itemList, applicationContext)
            arrayAdapter.notifyDataSetChanged()

        }
        todolist.setOnItemClickListener { adapterView, view, index, ld ->
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Delete")
            alert.setIcon(R.drawable.delete_icon)
            alert.setCancelable(false)
            alert.setMessage("Do wanna delete it .....?")
            alert.setNegativeButton("No",DialogInterface.OnClickListener {
                    dialogInterface,id->
                dialogInterface.cancel()
            })
            alert.setPositiveButton("Yes",DialogInterface.OnClickListener { dialogInterface, i ->
                itemList.removeAt(index)
                fileSaver.writeData(itemList,applicationContext)
            })
      alert.create().show()

        }


    }



}