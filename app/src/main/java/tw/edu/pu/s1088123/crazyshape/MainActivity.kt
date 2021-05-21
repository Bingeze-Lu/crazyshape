package tw.edu.pu.s1088123.crazyshape

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnLongClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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


    }
}
