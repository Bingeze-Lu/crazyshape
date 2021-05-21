package tw.edu.pu.s1088123.crazyshape


import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game2.*
import org.tensorflow.lite.support.image.TensorImage
import tw.edu.pu.s1088123.crazyshape.ml.Shapes

class GameActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game2)

        btnBack.setOnClickListener(object: View.OnClickListener
        {
            override fun onClick(p0: View?)
            {
                finish()
            }
        })

        btn.setOnClickListener(object:View.OnClickListener
        {
            override fun onClick(p0: View?)
            {
                handv.path.reset()
                handv.invalidate()
            }
        })

        handv.setOnTouchListener(object:View.OnTouchListener
        {
            override fun onTouch(p0: View?, event: MotionEvent): Boolean
            {
                var xPos = event.getX()
                var yPos = event.getY()
                when (event.action)
                {
                    MotionEvent.ACTION_DOWN -> handv.path.moveTo(xPos, yPos)
                    MotionEvent.ACTION_MOVE -> handv.path.lineTo(xPos, yPos)
                    MotionEvent.ACTION_UP ->
                    {
                        //將handv轉成Bitmap
                        val b = Bitmap.createBitmap(handv.measuredWidth, handv.measuredHeight,
                            Bitmap.Config.ARGB_8888)
                        val c = Canvas(b)
                        handv.draw(c)
                        classifyDrawing(b)
                    }
                }
                handv.invalidate()
                return true
            }
        })
    }


    fun classifyDrawing(bitmap : Bitmap)
    {
        val model = Shapes.newInstance(this)

// Creates inputs for reference.
        val image = TensorImage.fromBitmap(bitmap)

// Runs model inference and gets result.
        val outputs = model.process(image)
            .probabilityAsCategoryList.apply {
                sortByDescending { it.score }
            }.take(1)
        //val probability = outputs.probabilityAsCategoryList
        var Result:String = ""
        var FlagDraw:Int = 0
        when (outputs[0].label) {
            "circle" -> {
                Result = "您畫的是圓形喔"
                FlagDraw = 1
            }
            "square" -> {
                Result = "您畫的是方形喔"
                FlagDraw = 2
            }
            "star" -> {
                Result = "您畫的是星形喔"
                FlagDraw = 3
            }
            "triangle" -> {
                Result = "您畫的是三角形喔"
                FlagDraw = 4
            }
        }
        Result += ": " + String.format("%.1f%%",outputs[0].score * 100.0f)


// Releases model resources if no longer used.
                    model . close ()


        Toast.makeText(this,Result, Toast.LENGTH_SHORT).show()
    }

}
