    package com.example.dz1_vk1_kotlin

    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.TextView
    import androidx.core.content.ContextCompat
    import androidx.recyclerview.widget.RecyclerView

    class SqAdapter(
        val text_views: ArrayList<Int>,
        val deleted_views: ArrayList<Int>,
        var nextSq: Int) :
        RecyclerView.Adapter<SqAdapter.SqViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): SqViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.square, parent, false)
            val holder = SqViewHolder(view)
            holder.itemView.setOnClickListener {
                val pos = holder.bindingAdapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    removeSq(pos)
                }
            }
            return holder
        }

        override fun onBindViewHolder(
            holder: SqViewHolder,
            position: Int
        ) {
            val num = text_views[position]
            holder.number.text = num.toString()
            val colorRes = if (num % 2 == 0) R.color.chet_sq else R.color.ne_chet_sq
            holder.number.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, colorRes)
            )
        }

        override fun getItemCount(): Int {
            return text_views.size
        }

        class SqViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val number: TextView = view.findViewById(R.id.sq_id)
        }

        fun addSq() {
            if (deleted_views.isNotEmpty()){
                text_views.add(deleted_views.removeAt(deleted_views.lastIndex))
            }
            else {
                text_views.add(nextSq)
                nextSq++
            }
            notifyItemInserted(text_views.lastIndex)

        }

        private fun removeSq(position: Int) {
            deleted_views.add(text_views.removeAt(position))
            notifyItemRemoved(position)
        }
    }