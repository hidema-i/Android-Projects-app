package android.example.touristsights

import android.content.res.Resources
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.BufferedReader
import java.io.InputStreamReader

//JSONクラスの生成、逆にJSONデータからこのクラスオブジェクトを作成
@Serializable
class Sight(
    val name: String,
    val imageName: String,
    val description: String,
    val kind: String
)

fun getSights(resources: Resources) : List<Sight>{
    val assetManager = resources.assets
    //assets内にあるJSONデータファイルを開きテキストデータを取得する
    val inputStream =  assetManager.open("sights.json")
    //InputStreamを使い、ファイルの中のデータを取り出すには、BufferedReaderを使用
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    val str:String = bufferedReader.readText()
    //serializerのJSONクラスを使い取得したJSONでデータ形式の文字列をSightクラスに変換
//    val obj = Json(JsonConfiguration.Stable).parse(Sight.serializer().list,str)
    //現在はこれでないと動かないVersion違いのため
    val obj = Json { allowStructuredMapKeys = true }.decodeFromString(ListSerializer(Sight.serializer()), str)
    return  obj
}