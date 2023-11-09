package com.example.todolist
import android.content.Context
import android.content.res.Resources.NotFoundException
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream


@Suppress("UNCHECKED_CAST")
class FileSaver {
 private  var name =  "listdata.dat"

    fun writeData (item:ArrayList<String>,context:Context){
        val fos:FileOutputStream = context.openFileOutput(name,Context.MODE_PRIVATE)
        val os = ObjectOutputStream(fos)
    os.writeObject(item)
    os.close()
    }

    fun readData (context: Context): ArrayList<String> {

        return try {
            val fis :FileInputStream = context.openFileInput(name)
            val os =ObjectInputStream(fis)
            val  itemlist = os.readObject() as ArrayList<String>
            itemlist
        } catch (e:NotFoundException){
            val itemlist = ArrayList<String>()
            itemlist
        }


    }

    }