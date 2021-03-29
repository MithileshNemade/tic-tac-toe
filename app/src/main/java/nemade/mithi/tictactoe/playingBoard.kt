package nemade.mithi.tictactoe

import android.app.Dialog
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_playing_board.*
import java.util.*
import kotlin.collections.ArrayList

class playingBoard : AppCompatActivity() {

    var totalCnt = 0
    var player1Cnt = 0
    var player2Cnt = 0
    var activerPlayer = 1
    var player1 = "Player1"
    var player2 = "Player2 "
    var player1Marked = ArrayList<Int>()
    var player2Marked = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing_board)
        player1 = intent.getStringExtra("Player1")
        player2 = intent.getStringExtra("Player2")
        if(player1 != "")
            Player1.text = player1 + " :"
        else player1 = "Player1"
        if (player2 != "")
            Player2.text = player2 + " :"
        else player2 = "Player2"
        activerPlayer = 1
        player1Cnt = 0
        player2Cnt = 0
        TotalNum.text = totalCnt.toString()
        Player1Num.text = player1Cnt.toString()
        Player2Num.text = player2Cnt.toString()
        totalCnt++
    }

    fun markedThis(view: View) {
        var clickedButton = view as Button
        var cellId = 0
        when(clickedButton.id){
            R.id.bu11 -> cellId = 11
            R.id.bu12 -> cellId = 12
            R.id.bu13 -> cellId = 13
            R.id.bu21 -> cellId = 21
            R.id.bu22 -> cellId = 22
            R.id.bu23 -> cellId = 23
            R.id.bu31 -> cellId = 31
            R.id.bu32 -> cellId = 32
            R.id.bu33 -> cellId = 33
        }
        if (activerPlayer == 1) {
            clickedButton.text = "X"
            clickedButton.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.blue)))
            player1Marked.add(cellId)
            activerPlayer=2
        }
        else {
            clickedButton.text = "O"
            clickedButton.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.darkGreen)))
            player2Marked.add(cellId)
            activerPlayer=1
        }
        clickedButton.isEnabled = false
        checkWinner()
    }

    fun checkWinner() {
        var winer = -1
        //row1
        if (player1Marked.contains(11) && player1Marked.contains(12) && player1Marked.contains(13)) {
            winer = 1
            //Lrow1.alpha = 1F
            getAnimated(11)
            getAnimated(11)
        }
        if (player2Marked.contains(11) && player2Marked.contains(12) && player2Marked.contains(13)) {
            winer = 2
            //Lrow1.alpha = 1F
            getAnimated(11)
            getAnimated(11)
        }

        // row 2
        if (player1Marked.contains(21) && player1Marked.contains(22) && player1Marked.contains(23)) {
            winer = 1
            //Lrow2.alpha = 1F
            getAnimated(12)
            getAnimated(12)
        }
        if (player2Marked.contains(21) && player2Marked.contains(22) && player2Marked.contains(23)) {
            winer = 2
            //Lrow2.alpha = 1F
            getAnimated(12)
            getAnimated(12)
        }

        // row 3
        if (player1Marked.contains(31) && player1Marked.contains(32) && player1Marked.contains(33)) {
            winer = 1
            //Lrow3.alpha = 1F
            getAnimated(13)
        }
        if (player2Marked.contains(31) && player2Marked.contains(32) && player2Marked.contains(33)) {
            winer = 2
            //Lrow3.alpha = 1F
            getAnimated(13)
        }


        // col 1
        if (player1Marked.contains(11) && player1Marked.contains(21) && player1Marked.contains(31)) {
            winer = 1
            //col1.alpha = 1F
            getAnimated(21)
        }
        if (player2Marked.contains(11) && player2Marked.contains(21) && player2Marked.contains(31)) {
            winer = 2
            //col1.alpha = 1F
            getAnimated(21)
        }


        // col 2
        if (player1Marked.contains(12) && player1Marked.contains(22) && player1Marked.contains(32)) {
            winer = 1
            //col2.alpha = 1F
            getAnimated(22)
        }
        if (player2Marked.contains(12) && player2Marked.contains(22) && player2Marked.contains(32)) {
            winer = 2
            //col2.alpha = 1F
            getAnimated(22)
        }


        // col 3
        if (player1Marked.contains(13) && player1Marked.contains(23) && player1Marked.contains(33)) {
            winer = 1
            //col3.alpha = 1F
            getAnimated(23)
        }
        if (player2Marked.contains(13) && player2Marked.contains(23) && player2Marked.contains(33)) {
            winer = 2
            //col3.alpha = 1F
            getAnimated(23)
        }

        //Diagonal 1
        if (player1Marked.contains(11) && player1Marked.contains(22) && player1Marked.contains(33)) {
            winer = 1
            //dia2.alpha = 1F
            getAnimated(31)

        }
        if (player2Marked.contains(11) && player2Marked.contains(22) && player2Marked.contains(33)) {
            winer = 2
            //dia2.alpha = 1F
            getAnimated(31)
        }

        // Diagonal 2
        if (player1Marked.contains(13) && player1Marked.contains(22) && player1Marked.contains(31)) {
            winer = 1
            //dia1.alpha = 1F
            getAnimated(32)
        }
        if (player2Marked.contains(13) && player2Marked.contains(22) && player2Marked.contains(31)) {
            winer = 2
            //dia1.alpha = 1F
            getAnimated(32)
        }

        TotalNum.text = totalCnt.toString()
        if (winer == 1) {
            player1Cnt += 1
            Player1Num.text = player1Cnt.toString()
            Toast.makeText(this, player1 + " win the game", Toast.LENGTH_LONG).show()
            val handler = Handler()
            handler.postDelayed({ showDialog("!! '" + player1.capitalize() + "' won !!", totalCnt, player1Cnt, player2Cnt, player1, player2)
                                }, 4000)

        } else if (winer == 2) {
            player2Cnt += 1
            Player2Num.text = player2Cnt.toString()
            Toast.makeText(this, player2 + " win the game", Toast.LENGTH_LONG).show()
            val handler = Handler()
            handler.postDelayed({ showDialog("!! '" + player2.capitalize() + "' won !!", totalCnt, player1Cnt, player2Cnt, player1, player2)
                                }, 4000)

        }

        if(winer != -1) {
            for(cellId in 11..33){
                var buSelected:Button? = when(cellId){
                    11 -> bu11
                    12 -> bu12
                    13 -> bu13
                    21 -> bu21
                    22 -> bu22
                    23 -> bu23
                    31 -> bu31
                    32 -> bu32
                    33 -> bu33
                    else ->{ bu11}
                }
                buSelected!!.isEnabled = false
            }
        }
        else if(player1Marked.count() + player2Marked.count() == 9) {
            //Toast.makeText(this, "--: It's DRAW :--", Toast.LENGTH_LONG).show()
            showDialog("It's DRAW !!!", totalCnt, player1Cnt, player2Cnt, player1, player2)
        }
        else if (player2 == "CPU" && activerPlayer == 2) {
            val handler = Handler()
            handler.postDelayed({autoPlay()}, 900)
        }

    }

    private fun getAnimated(i: Int) {
        when(i) {
            11 -> {
                bu11.animate().alpha(0F).setDuration(200)
                bu12.animate().alpha(0F).setDuration(400)
                bu13.animate().alpha(0F).setDuration(600)
                val handler = Handler()
                handler.postDelayed({
                    bu13.animate().alpha(1F).setDuration(200)
                    bu12.animate().alpha(1F).setDuration(400)
                    bu11.animate().alpha(1F).setDuration(600)
                    handler.postDelayed({
                        bu11.animate().alpha(0F).setDuration(200)
                        bu12.animate().alpha(0F).setDuration(400)
                        bu13.animate().alpha(0F).setDuration(600)
                        handler.postDelayed({
                            bu13.animate().alpha(1F).setDuration(200)
                            bu12.animate().alpha(1F).setDuration(400)
                            bu11.animate().alpha(1F).setDuration(600)
                                            }, 600)
                                        }, 600)
                                    }, 600)
            }
            12 -> {
                bu21.animate().alpha(0F).setDuration(200)
                bu22.animate().alpha(0F).setDuration(400)
                bu23.animate().alpha(0F).setDuration(600)
                val handler = Handler()
                handler.postDelayed({
                    bu23.animate().alpha(1F).setDuration(200)
                    bu22.animate().alpha(1F).setDuration(400)
                    bu21.animate().alpha(1F).setDuration(600)
                    handler.postDelayed({
                        bu21.animate().alpha(0F).setDuration(200)
                        bu22.animate().alpha(0F).setDuration(400)
                        bu23.animate().alpha(0F).setDuration(600)
                        handler.postDelayed({
                            bu23.animate().alpha(1F).setDuration(200)
                            bu22.animate().alpha(1F).setDuration(400)
                            bu21.animate().alpha(1F).setDuration(600)
                        }, 600)
                    }, 600)
                }, 600)
            }
            13 -> {
                bu31.animate().alpha(0F).setDuration(200)
                bu32.animate().alpha(0F).setDuration(400)
                bu33.animate().alpha(0F).setDuration(600)
                val handler = Handler()
                handler.postDelayed({
                    bu33.animate().alpha(1F).setDuration(200)
                    bu32.animate().alpha(1F).setDuration(400)
                    bu31.animate().alpha(1F).setDuration(600)
                    handler.postDelayed({
                        bu31.animate().alpha(0F).setDuration(200)
                        bu32.animate().alpha(0F).setDuration(400)
                        bu33.animate().alpha(0F).setDuration(600)
                        handler.postDelayed({
                            bu33.animate().alpha(1F).setDuration(200)
                            bu32.animate().alpha(1F).setDuration(400)
                            bu31.animate().alpha(1F).setDuration(600)
                                            }, 600)
                    }, 600)
                }, 600)
            }
            21 -> {
                bu11.animate().alpha(0F).setDuration(200)
                bu21.animate().alpha(0F).setDuration(400)
                bu31.animate().alpha(0F).setDuration(600)
                val handler = Handler()
                handler.postDelayed({
                    bu31.animate().alpha(1F).setDuration(200)
                    bu21.animate().alpha(1F).setDuration(400)
                    bu11.animate().alpha(1F).setDuration(600)
                    handler.postDelayed({
                        bu11.animate().alpha(0F).setDuration(200)
                        bu21.animate().alpha(0F).setDuration(400)
                        bu31.animate().alpha(0F).setDuration(600)
                        handler.postDelayed({
                            bu31.animate().alpha(1F).setDuration(200)
                            bu21.animate().alpha(1F).setDuration(400)
                            bu11.animate().alpha(1F).setDuration(600)
                        }, 600)
                    }, 600)
                }, 600)
            }
            22 -> {
                bu12.animate().alpha(0F).setDuration(200)
                bu22.animate().alpha(0F).setDuration(400)
                bu32.animate().alpha(0F).setDuration(600)
                val handler = Handler()
                handler.postDelayed({
                    bu32.animate().alpha(1F).setDuration(200)
                    bu22.animate().alpha(1F).setDuration(400)
                    bu12.animate().alpha(1F).setDuration(600)
                    handler.postDelayed({
                        bu12.animate().alpha(0F).setDuration(200)
                        bu22.animate().alpha(0F).setDuration(400)
                        bu32.animate().alpha(0F).setDuration(600)
                        handler.postDelayed({
                            bu32.animate().alpha(1F).setDuration(200)
                            bu22.animate().alpha(1F).setDuration(400)
                            bu12.animate().alpha(1F).setDuration(600)
                        }, 600)
                    }, 600)
                }, 600)
            }
            23 -> {
                bu13.animate().alpha(0F).setDuration(200)
                bu23.animate().alpha(0F).setDuration(400)
                bu33.animate().alpha(0F).setDuration(600)
                val handler = Handler()
                handler.postDelayed({
                    bu33.animate().alpha(1F).setDuration(200)
                    bu23.animate().alpha(1F).setDuration(400)
                    bu13.animate().alpha(1F).setDuration(600)
                    handler.postDelayed({
                        bu13.animate().alpha(0F).setDuration(200)
                        bu23.animate().alpha(0F).setDuration(400)
                        bu33.animate().alpha(0F).setDuration(600)
                        handler.postDelayed({
                            bu33.animate().alpha(1F).setDuration(200)
                            bu23.animate().alpha(1F).setDuration(400)
                            bu13.animate().alpha(1F).setDuration(600)
                        }, 600)
                    }, 600)
                }, 600)
            }
            31 -> {
                bu11.animate().alpha(0F).setDuration(200)
                bu22.animate().alpha(0F).setDuration(400)
                bu33.animate().alpha(0F).setDuration(600)
                val handler = Handler()
                handler.postDelayed({
                    bu33.animate().alpha(1F).setDuration(200)
                    bu22.animate().alpha(1F).setDuration(400)
                    bu11.animate().alpha(1F).setDuration(600)
                    handler.postDelayed({
                        bu11.animate().alpha(0F).setDuration(200)
                        bu22.animate().alpha(0F).setDuration(400)
                        bu33.animate().alpha(0F).setDuration(600)
                        handler.postDelayed({
                            bu33.animate().alpha(1F).setDuration(200)
                            bu22.animate().alpha(1F).setDuration(400)
                            bu11.animate().alpha(1F).setDuration(600)
                        }, 600)
                    }, 600)
                }, 600)
            }
            32 -> {
                bu13.animate().alpha(0F).setDuration(200)
                bu22.animate().alpha(0F).setDuration(400)
                bu31.animate().alpha(0F).setDuration(600)
                val handler = Handler()
                handler.postDelayed({
                    bu31.animate().alpha(1F).setDuration(200)
                    bu22.animate().alpha(1F).setDuration(400)
                    bu13.animate().alpha(1F).setDuration(600)
                    handler.postDelayed({
                        bu13.animate().alpha(0F).setDuration(200)
                        bu22.animate().alpha(0F).setDuration(400)
                        bu31.animate().alpha(0F).setDuration(600)
                        handler.postDelayed({
                            bu31.animate().alpha(1F).setDuration(200)
                            bu22.animate().alpha(1F).setDuration(400)
                            bu13.animate().alpha(1F).setDuration(600)
                        }, 600)
                    }, 600)
                }, 600)
            }
        }

    }

    fun restartGame() {
        totalCnt++
        activerPlayer = 1
        player1Marked.clear()
        player2Marked.clear()
        for(cellId in 11..33){

            var buSelected:Button? = when(cellId){
                11 -> bu11
                12 -> bu12
                13 -> bu13
                21 -> bu21
                22 -> bu22
                23 -> bu23
                31 -> bu31
                32 -> bu32
                33 -> bu33
                else ->{ bu11}
            }
            buSelected!!.text = ""
            buSelected!!.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.whileBu)))
            buSelected!!.isEnabled = true
        }
        /*Lrow1.alpha = 0F
        Lrow2.alpha = 0F
        Lrow3.alpha = 0F
        col1.alpha = 0F
        col2.alpha = 0F
        col3.alpha = 0F
        dia1.alpha = 0F
        dia2.alpha = 0F*/
    }

    fun getToHome() {
        activerPlayer = 1
        player1Marked.clear()
        player2Marked.clear()
        player1Cnt = 0
        player2Cnt = 0
        player1 = "Player1"
        player2 = "Player2"
        /*Lrow1.alpha = 0F
        Lrow2.alpha = 0F
        Lrow3.alpha = 0F
        col1.alpha = 0F
        col2.alpha = 0F
        col3.alpha = 0F
        dia1.alpha = 0F
        dia2.alpha = 0F*/

        var home = Intent(this, MainActivity::class.java)
        startActivity(home)
        finish();
    }

    fun autoPlay() {
        var emptyCells = ArrayList<Int>()
        emptyCells.clear()
        for( cellId in 11..13){

            if( !(player1Marked.contains(cellId) || player2Marked.contains(cellId))){
                emptyCells.add(cellId)
            }
        }
        for( cellId in 21..23){

            if( !(player1Marked.contains(cellId) || player2Marked.contains(cellId))){
                emptyCells.add(cellId)
            }
        }
        for( cellId in 31..33){

            if( !(player1Marked.contains(cellId) || player2Marked.contains(cellId))){
                emptyCells.add(cellId)
            }
        }

        val r = Random()
        val randIndex = r.nextInt(emptyCells.size)
       // Toast.makeText(this,"EmptySize : " + emptyCells.size.toString() + " RandomNumber : "+ randIndex.toString(), Toast.LENGTH_LONG).show()
        var cellId = emptyCells[randIndex]
       // Toast.makeText(this, "CellID : " + cellId.toString(), Toast.LENGTH_LONG).show()
        var flag = 0
        var incremental = 1
        var smartCellId = cellId
        //Log.i("autoPlayRandom", cellId.toString())
        while (flag == 0 && incremental < 9) {
            smartCellId = when (incremental) {
                1 -> {
                    getSmartId(cellId, 11, 12, 13)
                }
                2 -> {
                    getSmartId(cellId, 21, 22, 23)
                }
                3 -> {
                    getSmartId(cellId, 31, 32, 33)
                }
                4 -> {
                    getSmartId(cellId, 11, 21, 31)
                }
                5 -> {
                    getSmartId(cellId, 12, 22, 32)
                }
                6 -> {
                    getSmartId(cellId, 13, 23, 33)
                }
                7 -> {
                    getSmartId(cellId, 11, 22, 33)
                }
                8 -> {
                    getSmartId(cellId, 13, 22, 31)
                }
                else -> cellId
            }
            if (smartCellId != cellId) {
                cellId = smartCellId
                flag = 1
                //Toast.makeText(this, "Incre : " + incremental.toString() + " CellId : " + cellId.toString(), Toast.LENGTH_LONG).show()
            }
            incremental+=1
        }
        var buSelected:Button?
        buSelected =  when(cellId){
            11 -> bu11
            12 -> bu12
            13 -> bu13
            21 -> bu21
            22 -> bu22
            23 -> bu23
            31 -> bu31
            32 -> bu32
            33 -> bu33
            else ->{ bu11}
        }
        //Toast.makeText(this, "CellID : " + cellId.toString(), Toast.LENGTH_SHORT).show()
        Log.i("autoPlayFinal", cellId.toString())
        markedThis(buSelected)
    }

    private fun getSmartId(CellId: Int, id1: Int, id2: Int, id3: Int): Int {
        return when {
            player2Marked.contains(id1) && player2Marked.contains(id2) && !player2Marked.contains(id3) && !player1Marked.contains(id3) -> id3
            player2Marked.contains(id1) && !player2Marked.contains(id2) && player2Marked.contains(id3) && !player1Marked.contains(id2) -> id2
            !player2Marked.contains(id1) && player2Marked.contains(id2) && player2Marked.contains(id3) && !player1Marked.contains(id1) -> id1
            player1Marked.contains(id1) && player1Marked.contains(id2) && !player1Marked.contains(id3) && !player2Marked.contains(id3) -> id3
            player1Marked.contains(id1) && !player1Marked.contains(id2) && player1Marked.contains(id3) && !player2Marked.contains(id2) -> id2
            !player1Marked.contains(id1) && player1Marked.contains(id2) && player1Marked.contains(id3) && !player2Marked.contains(id1) -> id1
            else -> CellId
        }
    }

    private fun showDialog(title: String, total: Int, p1: Int, p2: Int, p1N: String, p2N: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.final_alert)
        val body = dialog.findViewById(R.id.winnerMsg) as TextView
        body.text = title
        val totalCnt = dialog.findViewById(R.id.TotalNum) as TextView
        totalCnt.text = total.toString()
        val p1Name = dialog.findViewById(R.id.Player1) as TextView
        p1Name.text = p1N + " :"
        val p2Name = dialog.findViewById(R.id.Player2) as TextView
        p2Name.text = p2N + " :"
        val p1Cnt = dialog.findViewById(R.id.Player1Num) as TextView
        p1Cnt.text = p1.toString()
        val p2Cnt = dialog.findViewById(R.id.Player2Num) as TextView
        p2Cnt.text = p2.toString()
        val yesBtn = dialog.findViewById(R.id.playAgain) as Button
        val noBtn = dialog.findViewById(R.id.home) as Button
        yesBtn.setOnClickListener {
            dialog.dismiss()
            restartGame()
        }
        noBtn.setOnClickListener {
            dialog.dismiss()
            getToHome()
        }
        dialog.show()
    }

}