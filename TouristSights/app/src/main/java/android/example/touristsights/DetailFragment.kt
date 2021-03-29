package android.example.touristsights

import android.example.touristsights.databinding.FragmentDetailBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

const val ROW_POSITION = "ROW_POSITION"

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ROW_POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sights = getSights(resources)
        binding.detailKind.text = sights[position].kind
        binding.detailName.text = sights[position].name
        binding.detailDescription.text = sights[position].description
        var img = resources.getIdentifier(sights[position].imageName,"drawable",context?.packageName)
        binding.detailImage.setImageResource(img)
    }

}