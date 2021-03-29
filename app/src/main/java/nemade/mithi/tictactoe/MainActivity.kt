package nemade.mithi.tictactoe

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_get_users_name.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun playGame(view: View) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.activity_get_users_name)
        var player1 = dialog.findViewById(R.id.player1) as TextView
        var player2 = dialog.findViewById(R.id.player2) as TextView
        val typeId = view.id
        val type1vs1 = mainBu1.id
        if(typeId != type1vs1) {
            val player2 = dialog.findViewById(R.id.player2) as TextView
            player2.setText("CPU")
            player2.isEnabled = false
            player2.isVisible = false
        }

        val yesBtn = dialog.findViewById(R.id.start) as Button
        val noBtn = dialog.findViewById(R.id.back) as Button
        yesBtn.setOnClickListener {
            dialog.dismiss()
            val startPlay = Intent(this,playingBoard::class.java)
            startPlay.putExtra("Player1",player1.text.toString())
            startPlay.putExtra("Player2",player2.text.toString())
            startActivity(startPlay)
        }
        noBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
        /*
        val getUsers = Intent(this, getUsersName::class.java)
        val typeId = view.id
        val type1vs1 = mainBu1.id
        if(typeId == type1vs1)
            getUsers.putExtra("Type", "1vs1")
        else
            getUsers.putExtra("Type", "Alone")
        startActivity(getUsers)
        finish();
        */
    }

    private var exit = false
    override fun onBackPressed() {
        if (exit) {
           moveTaskToBack(true);// finish activity
        } else {
            Toast.makeText(
                this, "Press Back again to Exit.",
                Toast.LENGTH_SHORT
            ).show()
            exit = true
            Handler().postDelayed(Runnable { exit = false }, 3 * 1000)
        }
    }
}