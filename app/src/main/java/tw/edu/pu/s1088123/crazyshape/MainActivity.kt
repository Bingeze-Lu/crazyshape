package tw.edu.pu.s1088123.crazyshape

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnLongClickListener
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import kotlinx.android.synthetic.main.activity_main.*
@GlideModule
public final class MyAppGlideModule : AppGlideModule()





class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var res:Int = -1
        var countDrawables:Int = -1
        while (res != 0) {
            countDrawables++;
            res = getResources().getIdentifier("pu" + (countDrawables),
                "drawable", getPackageName());
        }
        var TotalPictures = countDrawables


        Toast.makeText(baseContext, "作者：呂秉澤", Toast.LENGTH_LONG).show()


        imageNext.setOnLongClickListener(object : OnLongClickListener
        {
            override fun onLongClick(p0: View?): Boolean
            {
                intent = Intent(this@MainActivity, GameActivity::class.java)
                startActivity(intent)
                return true

            }
        })
        imageNext.setOnClickListener(object : View.OnClickListener
        {
            override fun onClick(v: View?):Boolean {
                TODO("Not yet implemented")
                var
            }
        })

    }
}
