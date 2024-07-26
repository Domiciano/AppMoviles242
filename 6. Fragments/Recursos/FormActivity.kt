package icesi.edu.co.testfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import icesi.edu.co.testfragments.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityFormBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }

}