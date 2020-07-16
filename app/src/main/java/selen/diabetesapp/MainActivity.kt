package selen.diabetesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Entry(gramm: String, kal: String, name: String) {
    var Gramm = gramm
    var Kal = kal
    var Name = name
}

class IngredientEntryAdapter : RecyclerView.Adapter<IngredientEntryAdapter.ViewHolder>() {

    private val mEntries: MutableList<Entry> = mutableListOf()

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val ingredientTextView = itemView.findViewById<TextView>(R.id.ingredient_text)
        val deleteButton = itemView.findViewById<Button>(R.id.button_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.ingredient_entry, parent, false)
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return mEntries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry: Entry = mEntries.get(position)

        var gramm = entry.Gramm
        var kal = entry.Kal
        val name = entry.Name

        holder.ingredientTextView.setText("$gramm Gramm von $name, zu $kal / 100 KH")
        val button = holder.deleteButton
        button.setOnClickListener {
            mEntries.removeAt(position)
            notifyDataSetChanged()
        }
    }

    fun addItem(entry: Entry)
    {
        mEntries.add(entry)
        notifyDataSetChanged()
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvContacts = findViewById<View>(R.id.recyclerView) as RecyclerView
        val adapter = IngredientEntryAdapter()

        rvContacts.adapter = adapter
        rvContacts.layoutManager = LinearLayoutManager(this)
    }

    fun buttonadd(view: View) {
        val gramm: EditText = findViewById(R.id.editTextTextGramm)
        val kal: EditText = findViewById(R.id.editTextTextKal)
        val name: EditText = findViewById(R.id.editTextTextName)

        val rvContacts = findViewById<View>(R.id.recyclerView) as RecyclerView

        val new_e = Entry(gramm.text.toString(), kal.text.toString(), name.text.toString())
        (rvContacts.adapter as IngredientEntryAdapter).addItem(new_e)
    }
}