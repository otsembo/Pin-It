package com.otsembo.pinit.notes_data.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otsembo.pinit.notes_data.data.model.AppNote
import com.otsembo.pinit.notes_data.databinding.NotesCardItemBinding

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

    private val notes = ArrayList<AppNote>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder = NotesHolder.from(parent = parent)

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.bindData(notes[position])
    }

    override fun getItemCount(): Int = notes.size

    fun submitItems(items: List<AppNote>) {
        notes.clear()
        notes.addAll(items)
        notifyItemRangeChanged(0, items.size)
    }

    class NotesHolder(private val binding: NotesCardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(appNote: AppNote) {
            binding.note = appNote
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): NotesHolder {
                val inflater = LayoutInflater.from(parent.context)
                return NotesHolder(NotesCardItemBinding.inflate(inflater, parent, false))
            }
        }
    }
}
