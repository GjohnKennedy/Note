package com.example.mynote

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mynote.Database.NoteDatabase
import com.example.mynote.Model.Note
import com.example.mynote.Model.NoteViewModel
import com.example.mynote.adapter.NotesAdapter
import com.example.mynote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),NotesAdapter.NoteClickListener,PopupMenu.OnMenuItemClickListener{

    lateinit var binding:ActivityMainBinding
    lateinit var database: NoteDatabase
    lateinit var viewModel: NoteViewModel
    lateinit var adapter: NotesAdapter
    lateinit var selectnote:Note

    val updateNote=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode==Activity.RESULT_OK)
        {
            val note=it.data?.getSerializableExtra("note") as Note
            if (note!=null)
            {
                viewModel.updaterep(note)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        viewModel.allNotesRep.observe(this){list->
           list.let {
               adapter.updateList(list)
           }

        }


    }

    private fun initUi() {
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.layoutManager= StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        adapter= NotesAdapter(this,this)
        binding.recyclerview.adapter=adapter


        val getContent=registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {

            if (it.resultCode== RESULT_OK)
            {
                val note=it.data?.getSerializableExtra("note") as Note

                if (note!=null)
                {
                    viewModel.insertrep(note)
                }
            }

        }

         binding.floatingBtn.setOnClickListener {
             val intent=Intent(this@MainActivity,AddNoteActivity::class.java)
            getContent.launch(intent)

         }


         binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(searchsend: String?): Boolean {
                if (searchsend!=null)
                {
                    adapter.filterlist(searchsend!!)

                }
                return true
            }

        })
    }



    override fun onItemClicked(note: Note) {

        val intent=Intent(this,AddNoteActivity::class.java)
        intent.putExtra("current_note",note)
        updateNote.launch(intent)

    }

    override fun onLongItemClicked(note: Note, cardView: CardView) {
        selectnote=note
        popUpdisplay(cardView)

    }

    private fun popUpdisplay(cardView: CardView) {
        val popup=PopupMenu(this,cardView)
         popup.setOnMenuItemClickListener(this@MainActivity)
        popup.inflate(R.menu.popup_menu)
        popup.show()

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if(item?.itemId==R.id.delete)
        {
            viewModel.deleteRep(selectnote)
            return true
        }
        return false
    }


}