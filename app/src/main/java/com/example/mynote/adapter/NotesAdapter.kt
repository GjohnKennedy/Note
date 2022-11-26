package com.example.mynote.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynote.Model.Note
import com.example.mynote.R
import kotlin.random.Random

class NotesAdapter(private val context: Context,val listener: NoteClickListener ):RecyclerView.Adapter<NotesAdapter.NoteViewHolder>(){

    private val Notelist=ArrayList<Note>()
    private val AllNotelist=ArrayList<Note>()


    class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        val notes_layout=itemView.findViewById<CardView>(R.id.cardview)
        val title=itemView.findViewById<TextView>(R.id.title)
        val content=itemView.findViewById<TextView>(R.id.content)
        val date=itemView.findViewById<TextView>(R.id.date)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int)
    {
        val currentNote=Notelist[position]
        holder.title.text=currentNote.title
        holder.title.isSelected=true

        holder.content.text=currentNote.content

        holder.date.text=currentNote.date
        holder.date.isSelected=true

        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(),null))


        holder.notes_layout.setOnClickListener {
            listener.onItemClicked(Notelist[holder.adapterPosition])
        }
        holder.notes_layout.setOnLongClickListener {
            listener.onLongItemClicked(Notelist[holder.adapterPosition],holder.notes_layout)
            true
        }

    }

    override fun getItemCount(): Int
    {
        return Notelist.size
    }

    fun updateList(newlist: List<Note>)
    {
        AllNotelist.clear()
        AllNotelist.addAll(newlist)

        Notelist.clear()
        Notelist.addAll(AllNotelist)
        notifyDataSetChanged()
    }

    fun filterlist(search :String)
    {
        Notelist.clear()

        for (item in AllNotelist)
        {
            if(item.title?.lowercase()?.contains(search.lowercase())==true ||
                item.content?.lowercase()?.contains(search.lowercase())==true||
                item.date?.lowercase()?.contains(search.lowercase())==true)
            {
                Notelist.add(item)
            }

        }
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder
    {
        return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_itemlist_layout,parent,false))
    }

    fun randomColor():Int{
        val list=ArrayList<Int>()
        list.add(R.color.MangoTango)
        list.add(R.color.card_violet)
        list.add(R.color.card_purple)
        list.add(R.color.card_green)
        list.add(R.color.card_red)
        list.add(R.color.card_blue)
        list.add(R.color.card_yellow)

        val seed=System.currentTimeMillis().toInt()
        val randomIndex= Random(seed).nextInt(list.size)
        return list[randomIndex]
    }


    interface NoteClickListener{

        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note,cardView: CardView)

    }
}