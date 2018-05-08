package com.example.dmclaughlin.fortnitetracker

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import com.example.dmclaughlin.fortnitetracker.dao.UserDatabase
import com.example.dmclaughlin.fortnitetracker.dao.UserEntity
import com.example.dmclaughlin.fortnitetracker.vo.FortniteStatsVO
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi

class PlayerResultsActivity : AppCompatActivity() {

    var fortniteStatsDTO: FortniteStatsVO? = null
    var fortniteJson : String? = null
    companion object {
        var userDatabase : UserDatabase? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PlayerResultsActivity.userDatabase = Room.databaseBuilder(this, UserDatabase::class.java, "user-db").allowMainThreadQueries().build()

        setContentView(R.layout.player_stats_layout)

        val spinner: Spinner = findViewById(R.id.spinner)
        val spinnerArray: ArrayAdapter<CharSequence> =
                ArrayAdapter.createFromResource(this, R.array.gametypes_array, android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerArray

        val statsResponse = intent.extras["jsonStats"].toString()

        fortniteJson = statsResponse
        setFortNiteStatsDto(fortniteJson!!)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position)
                {
                    0 ->{
                        setSoloResults()
                    }
                    1 -> {
                        setDuoResults()
                    }
                    2 -> {
                        setSquadResults()
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }




        }

    private fun setFortNiteStatsDto(statsResponse: String) {
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        val fortniteStatsAdapter = moshi.adapter(FortniteStatsVO::class.java)
        fortniteStatsDTO = fortniteStatsAdapter.fromJson(statsResponse)
        setSoloResults()
    }

    private fun setSquadResults() {
        val trnRating : TextView = findViewById(R.id.rating)
        val scoreText : TextView = findViewById(R.id.score)
        val topOneText : TextView = findViewById(R.id.top1)
        val topThreeText : TextView = findViewById(R.id.top3)
        val topTenText : TextView = findViewById(R.id.top10)

        trnRating.text = fortniteStatsDTO!!.stats.squadStats.trnRating.value
        scoreText.text = fortniteStatsDTO!!.stats.squadStats.score.value
        topOneText.text = fortniteStatsDTO!!.stats.squadStats.topOne.value
        topThreeText.text = fortniteStatsDTO!!.stats.squadStats.topThree.value
        topTenText.text = fortniteStatsDTO!!.stats.squadStats.topTen.value        }

    private fun setDuoResults() {
        val trnRating : TextView = findViewById(R.id.rating)
        val scoreText : TextView = findViewById(R.id.score)
        val topOneText : TextView = findViewById(R.id.top1)
        val topThreeText : TextView = findViewById(R.id.top3)
        val topTenText : TextView = findViewById(R.id.top10)

        trnRating.text = fortniteStatsDTO!!.stats.doubleStats.trnRating.value
        scoreText.text = fortniteStatsDTO!!.stats.doubleStats.score.value
        topOneText.text = fortniteStatsDTO!!.stats.doubleStats.topOne.value
        topThreeText.text = fortniteStatsDTO!!.stats.doubleStats.topThree.value
        topTenText.text = fortniteStatsDTO!!.stats.doubleStats.topTen.value
    }


    private fun setSoloResults() {
        val trnRating : TextView = findViewById(R.id.rating)
        val scoreText : TextView = findViewById(R.id.score)
        val topOneText : TextView = findViewById(R.id.top1)
        val topThreeText : TextView = findViewById(R.id.top3)
        val topTenText : TextView = findViewById(R.id.top10)

        trnRating.text = fortniteStatsDTO!!.stats.soloStats.trnRating.value
        scoreText.text = fortniteStatsDTO!!.stats.soloStats.score.value
        topOneText.text = fortniteStatsDTO!!.stats.soloStats.topOne.value
        topThreeText.text = fortniteStatsDTO!!.stats.soloStats.topThree.value
        topTenText.text = fortniteStatsDTO!!.stats.soloStats.topTen.value
    }

    fun saveProfile(view: View)
    {
        val userEntity = UserEntity(fortniteStatsDTO!!.epicUserHandle, fortniteStatsDTO!!.platformName, fortniteJson.toString())
        PlayerResultsActivity.userDatabase!!.userDao().insertUser(userEntity)
    }

    fun loadProfiles(view: View)
    {
        var savedUsers = getSavedUsers()
        val nameList: List<String> = savedUsers.map { it.username }
        val userAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList)
        val userView = findViewById<ListView>(R.id.userList)

        userView.visibility = View.VISIBLE
        userView.isClickable = true
        userView.adapter = userAdapter
        userView.onItemClickListener = AdapterView.OnItemClickListener{
            adapterView, view, position, id ->
            val userToLoad = nameList.get(id.toInt())
            loadUser(userToLoad)
            userView.visibility = View.INVISIBLE
        }


    }

    private fun loadUser(userToLoad: String) {
        val user = PlayerResultsActivity.userDatabase!!.userDao().getUser(userToLoad)
        setFortNiteStatsDto(user.jsonStats)

    }

    private fun getSavedUsers() : List<UserEntity>
    {
        return PlayerResultsActivity.userDatabase!!.userDao().getAllUsers()
    }
}
