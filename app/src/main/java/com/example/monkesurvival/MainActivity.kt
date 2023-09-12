package com.example.monkesurvival

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Api
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.monkesurvival.ui.theme.MonkeSurvivalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)

        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())

        super.onCreate(savedInstanceState)
        setContent {
            MonkeSurvivalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LeaderboardScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LeaderboardScreenPreview() {
    LeaderboardScreen()
}

data class Player(var position: Int, val name: String, var points: Int)

@Composable
fun LeaderboardScreen() {
    // Auto generated player scores
    val players = List(20) { i ->
        Player(i + 1, "Player Name ${i + 1}", (1000..5000).random())
    }.sortedByDescending { it.points }

    players.forEachIndexed { index, player ->
        player.position = index + 1
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            // Title and settings icon
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "LEADERBOARD", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
                // Settings icon here
            }

            Column (modifier = Modifier
                .fillMaxWidth(0.66f)
                .weight(1f)){
                // Table Headers
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 5.dp)
                ) {
                    Text(text = "Rank", fontSize = 20.sp,fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.5f))
                    Text(text = "Player", fontSize = 20.sp,fontWeight = FontWeight.Bold, modifier = Modifier.weight(1.5f))
                    Text(text = "Total Score", fontSize = 20.sp,fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.6f))
                }


                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    items(players) { player ->
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            if (player.position == 1) {
                                Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Up")
                            } else {
                                if ((0..1).random() == 0) {
                                    Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Down")
                                } else {
                                    Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Up")
                                }
                            }
                            Text(text = " ${player.position}", fontSize = 20.sp, modifier = Modifier.weight(0.5f))
                            Icon(Icons.Filled.AccountCircle, contentDescription = "Profile Avatar")
                            Text(text = " ${player.name}", fontSize = 20.sp, modifier = Modifier.weight(1.5f))
                            Icon(Icons.Filled.Api, contentDescription = "Points Icon")
                            Text(text = " ${player.points}pts", fontSize = 20.sp, modifier = Modifier.weight(0.6f))
                        }
                        
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
            }


            // Bottom buttons
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Button(onClick = { /* Navigate to Store */ }, modifier = Modifier.weight(1f)) {
                    Text(text = "Store")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { /* Navigate to Map */ }, modifier = Modifier.weight(1f)) {
                    Text(text = "Map")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { /* Refresh Leaderboard */ }, modifier = Modifier.weight(1f)) {
                    Text(text = "Leaderboard")
                }
            }
        }
    }
}