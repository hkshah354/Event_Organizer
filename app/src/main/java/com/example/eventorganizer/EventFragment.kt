package com.example.eventorganizer.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.eventorganizer.Event
import com.example.eventorganizer.R
import java.util.*

class EventFragment : Fragment() {

    private var listener: OnEventSavedListener? = null

    interface OnEventSavedListener {
        fun onEventSaved(event: Event)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnEventSavedListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnEventSavedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_event, container, false)

        val eventNameEditText: EditText = view.findViewById(R.id.event_name)
        val eventDatePicker: DatePicker = view.findViewById(R.id.event_date_picker)
        val saveButton: Button = view.findViewById(R.id.save_event)

        saveButton.setOnClickListener {
            val eventName = eventNameEditText.text.toString()

            val day = eventDatePicker.dayOfMonth
            val month = eventDatePicker.month
            val year = eventDatePicker.year

            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            val eventDate = "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}"

            if (eventName.isNotEmpty()) {
                val event = Event(eventName, eventDate)
                listener?.onEventSaved(event)
                Toast.makeText(requireContext(), "Event Saved: $eventName on $eventDate", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Please enter an event name", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = EventFragment()
    }
}