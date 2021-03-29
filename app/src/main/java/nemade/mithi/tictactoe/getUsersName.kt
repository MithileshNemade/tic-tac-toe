package nemade.mithi.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_get_users_name.*

class getUsersName : AppCompatActivity() {
    var type = "1vs1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_users_name)
        type = intent.getStringExtra("Type")
        if (type == "Alone") {
            player2.setText("CPU")
            player2.isEnabled = false
            player2.isVisible = false
        }
    }

    fun startGame(view: View) {
        val startPlay = Intent(this,playingBoard::class.java)
        startPlay.putExtra("Player1",player1.text.toString())
        startPlay.putExtra("Player2",player2.text.toString())
        startActivity(startPlay)
        finish();
    }
}