package com.gts.sqlitedemo

import android.database.Cursor
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var DB =  DBHelper(this);

        btnInsert.setOnClickListener{

                val nameTXT = name.text.toString()
                val contactTXT = contact.text.toString()
                val dobTXT = dob.text.toString()

                val checkInsertData: Boolean = DB.insertUserData(nameTXT, contactTXT, dobTXT)
                if (checkInsertData) {
                    Toast.makeText(
                        this@MainActivity,
                        "New Entry Inserted",
                        Toast.LENGTH_SHORT
                    ).show()
                }else {
                    Toast.makeText(
                        this@MainActivity,
                        "New Entry Not Inserted",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        btnUpdate.setOnClickListener{

            val nameTXT = name.text.toString()
            val contactTXT = contact.text.toString()
            val dobTXT = dob.text.toString()

            val checkUpdateData: Boolean = DB.updateUserData(nameTXT, contactTXT, dobTXT)
            if (checkUpdateData) {
                Toast.makeText(
                    this@MainActivity,
                    "Entry Updated",
                    Toast.LENGTH_SHORT
                ).show()
            }else {
                Toast.makeText(
                    this@MainActivity,
                    "Entry Not Updated",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnDelete.setOnClickListener{

            val nameTXT = name.text.toString()

            val checkDeleteData: Boolean = DB.deleteUserData(nameTXT)
            if (checkDeleteData) {
                Toast.makeText(
                    this@MainActivity,
                    "Entry Deleted",
                    Toast.LENGTH_SHORT
                ).show()
            }else {
                Toast.makeText(
                    this@MainActivity,
                    "Entry Not Deleted",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnView.setOnClickListener{

                val res: Cursor = DB.getData()
                if (res.count == 0) {
                    Toast.makeText(this@MainActivity, "No Entry Exists", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val buffer = StringBuffer()
                while (res.moveToNext()) {

                    buffer.append("Name: "+ res.getString(0)+ "\n")
                    buffer.append("Contact: "+ res.getString(1)+ "\n")
                    buffer.append("Date Of Birth: "+ res.getString(2)+ "\n\n")
                }

            val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
            builder.setCancelable(true)
            builder.setTitle("User Entries")
            builder.setMessage(buffer.toString())
            builder.show()
            }
    }
}