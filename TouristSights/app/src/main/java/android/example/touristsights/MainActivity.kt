package android.example.touristsights

import android.example.touristsights.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tag = "ListFragment"
            //現在アクティビティに表示されているフラグメントがすでにあれば取得
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null){
            //なければフラグメントを作成
            fragment = ListFragment()
            supportFragmentManager.beginTransaction().apply {
                //作成したフラグメントを画面に表示
                replace(R.id.content,fragment,tag)
            }.commit()
        }
    }
}