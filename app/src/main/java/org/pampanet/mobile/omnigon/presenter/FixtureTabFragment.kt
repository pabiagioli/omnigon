package org.pampanet.mobile.omnigon.presenter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_matcheslist.view.*
import kotlinx.coroutines.*
import org.pampanet.mobile.omnigon.R
import org.pampanet.mobile.omnigon.adapter.MatchListAdapter
import org.pampanet.mobile.omnigon.utils.getFixtures

class FixtureTabFragment : Fragment() {

    private val uiScope = CoroutineScope(
        Dispatchers.Main + Job())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_matcheslist, container, false)

        rootView.search_filter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                rootView.matches_list?.adapter?.let {
                    (it as MatchListAdapter).filterList(s?.toString()?:"")
                }

            }
        })

        getFixturesAsync(rootView)
        return rootView
    }

    fun getFixturesAsync(rootView: View) = uiScope.launch {
        val fixtures = async { getFixtures() }
        rootView.matches_list.adapter = MatchListAdapter(fixtures.await())
        rootView.matches_list.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
    }

}