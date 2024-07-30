package com.example.eventorganizer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.eventorganizer.Event
import com.example.eventorganizer.R

class ViewEventsFragment : Fragment() {

    private var events: List<Event>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            events = it.getParcelableArrayList(ARG_EVENTS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_events, container, false)
        val eventsContainer: LinearLayout = view.findViewById(R.id.events_container)

        events?.forEach { event ->
            val eventView = TextView(requireContext()).apply {
                text = "${event.name} on ${event.date}"
                textSize = 16f
                setPadding(16, 16, 16, 16)
            }
            eventsContainer.addView(eventView)
        }

        return view
    }

    companion object {
        private const val ARG_EVENTS = "events"

        @JvmStatic
        fun newInstance(events: List<Event>) =
            ViewEventsFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_EVENTS, ArrayList(events))
                }
            }
    }
}