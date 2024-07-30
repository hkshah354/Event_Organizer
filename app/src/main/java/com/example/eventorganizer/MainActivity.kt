package com.example.eventorganizer

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.eventorganizer.ui.EventFragment
import com.example.eventorganizer.ui.ViewEventsFragment

class MainActivity : AppCompatActivity(), EventFragment.OnEventSavedListener {

    private val eventsList = mutableListOf<Event>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Directly show the EventFragment initially
        showFragment(EventFragment.newInstance(), false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_event -> {
                showFragment(EventFragment.newInstance(), true)
                true
            }
            R.id.action_view_events -> {
                showFragment(ViewEventsFragment.newInstance(eventsList), true)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    override fun onEventSaved(event: Event) {
        eventsList.add(event)
        showFragment(ViewEventsFragment.newInstance(eventsList), true)
    }
}