package net.heedstudio.businesscard.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import net.heedstudio.businesscard.App
import net.heedstudio.businesscard.R
import net.heedstudio.businesscard.data.BusinessCard
import net.heedstudio.businesscard.databinding.ActivityAddBusinessCardBinding

class AddBusinessCardActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        insertListener()

    }

    private fun insertListener() {
        binding.btnClose.setOnClickListener(this)
        binding.btnConfirmar.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){

            binding.btnClose.id -> {
                finish()
            }

            binding.btnConfirmar.id -> {
                val businessCard = BusinessCard(
                    nome = binding.tilName.editText?.text.toString(),
                    telefone =  binding.tilPhone.editText?.text.toString(),
                    email =  binding.tilEmail.editText?.text.toString(),
                    empresa =  binding.tilEmpresa.editText?.text.toString(),
                    fundo =  binding.tilCor.editText?.text.toString()
                )
                mainViewModel.insert(businessCard)
                Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_SHORT).show()
                finish()
            }

        }
    }
}