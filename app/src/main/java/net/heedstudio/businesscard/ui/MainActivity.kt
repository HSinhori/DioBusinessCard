package net.heedstudio.businesscard.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import net.heedstudio.businesscard.App
import net.heedstudio.businesscard.databinding.ActivityMainBinding
import net.heedstudio.businesscard.util.Image

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private val adapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvCards.adapter = adapter
        getAllBusinessCard()
        insertListener()

    }

    private fun insertListener(){
        binding.fabAdd.setOnClickListener(this)

        adapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){

            binding.fabAdd.id -> {
                startActivity(Intent(this@MainActivity, AddBusinessCardActivity::class.java))
            }

        }
    }

    private fun getAllBusinessCard() {
        mainViewModel.getAll().observe(this) { businessCard ->
            adapter.submitList(businessCard)
        }
    }

}