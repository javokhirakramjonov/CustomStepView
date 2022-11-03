package uz.gita.customstepview

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myView: MyStepView = findViewById(R.id.steps)

        val editSteps: EditText = findViewById(R.id.input_step)
        val editCurrent: EditText = findViewById(R.id.input_current)

        findViewById<AppCompatButton>(R.id.click).setOnClickListener {
            editSteps.text?.let {
                myView.post {
                    myView.setSteps(it.toString().toInt())
                }
            }
            editCurrent.text?.let {
                myView.post {
                    myView.setCurrentPos(it.toString().toInt())
                }
            }
        }
    }
}