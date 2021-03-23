package android.example.myscheduler

import android.example.myscheduler.databinding.FragmentFirstBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.kotlin.where

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realm = Realm.getDefaultInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_first, container, false)
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        //LinearLayoutManagerのインスタンスを作成しRecyclerViewのレイアウトマネジャーとして登録
        binding.list.layoutManager = LinearLayoutManager(context)
        //findAllで全てのスケジュールを取得し変数に格納
        val schedules = realm.where<Schedule>().findAll()
        //ScheduleAdapterクラスのインスタンスを生成しRecyclerViewに設定
        val adapter = ScheduleAdapter(schedules)
        binding.list.adapter = adapter
        //adapterに用意したsetOnItemClickListenerメソッドに登録
        adapter.setOnItemClickListener { id ->
            id?.let{
                val action =
                    FirstFragmentDirections.actionToScheduleEditFragment(it)
                findNavController().navigate(action)
            }
        }


        (activity as? MainActivity)?.setFabVisible(View.VISIBLE)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}