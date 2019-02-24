package org.pampanet.mobile.omnigon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.pampanet.mobile.omnigon.adapter.TabAdapter
import org.pampanet.mobile.omnigon.presenter.FixtureTabFragment
import org.pampanet.mobile.omnigon.presenter.ResultsTabFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = TabAdapter(supportFragmentManager)
        adapter.addFragment(FixtureTabFragment(), "Fixtures")
        adapter.addFragment(ResultsTabFragment(), "Results")

        pager.adapter = adapter
        tabLayout.setupWithViewPager(pager)
    }
}
