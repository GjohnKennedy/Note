package com.example.mynote

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mynote.Model.Note
import com.example.mynote.databinding.ActivityAddNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    lateinit var binding:ActivityAddNoteBinding

    lateinit var note:Note
    lateinit var oldNote:Note

    var isUpdated=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        try {
            oldNote=intent.getSerializableExtra("current_note") as Note
            binding.addactivityTitle.setText(oldNote.title)
            binding.addactivityContent.setText(oldNote.content)
            isUpdated=true
        }catch (e:java.lang.Exception)
        {
            e.printStackTrace()
        }

        binding.imageViewCheck.setOnClickListener{

            val updatetitle=binding.addactivityTitle.text.toString()
            val updatecontent=binding.addactivityContent.text.toString()

            if (updatetitle.isNotEmpty() || updatecontent.isNotEmpty())
            {
                val  fomatter=SimpleDateFormat("EEE, d MMM yyyy HH:mm a")

                if (isUpdated)
                {
                    note=Note(oldNote.id,updatetitle,updatecontent,fomatter.format(Date()))
                }
                else{
                    note= Note(null,updatetitle,updatecontent,fomatter.format(Date()))
                }
                val  intent=Intent()
                intent.putExtra("note",note)
                setResult(Activity.RESULT_OK,intent)
                finish()

            }
            else{
                Toast.makeText(this, "Please Enter Something", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //val intent=Intent(this@AddNoteActivity,MainActivity::class.java)

        }

        binding.imageViewBackarrow.setOnClickListener {
            onBackPressed()
        }
    }
}