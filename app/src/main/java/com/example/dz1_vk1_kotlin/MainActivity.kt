package com.example.dz1_vk1_kotlin

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: SqAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val text_views = savedInstanceState?.getIntegerArrayList("views") ?: arrayListOf()
        val deleted = savedInstanceState?.getIntegerArrayList("deleted") ?: arrayListOf()
        val nextSq = savedInstanceState?.getInt("next_sq") ?: 0
        adapter = SqAdapter(text_views, deleted, nextSq)

        val list = findViewById<RecyclerView>(R.id.squares_list)
        list.layoutManager = GridLayoutManager(this, resources.getInteger(R.integer.columns))
        list.adapter = adapter

        findViewById<Button>(R.id.add_button).setOnClickListener {
            adapter.addSq()
            list.scrollToPosition(adapter.itemCount - 1)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntegerArrayList("views", adapter.text_views)
        outState.putIntegerArrayList("deleted", adapter.deleted_views)
        outState.putInt("next_sq", adapter.nextSq)
    }
}