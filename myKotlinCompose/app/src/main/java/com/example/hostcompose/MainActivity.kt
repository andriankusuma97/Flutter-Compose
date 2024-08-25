package com.example.hostcompose

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.example.hostcompose.ui.theme.HostComposeTheme
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat


class MainActivity : FragmentActivity() {

    companion object {
        private const val CHANNEL = "com.example.nativehost/channel"
        private const val ENGINE_ID = "my_engine_id"
    }

    private lateinit var flutterEngine: FlutterEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Flutter engine
        flutterEngine = FlutterEngineCache.getInstance()[ENGINE_ID] ?: FlutterEngine(this).apply {
            dartExecutor.executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
            )
            // Register Method Channel
            MethodChannel(dartExecutor.binaryMessenger, CHANNEL)
                .setMethodCallHandler { call: MethodCall, result: MethodChannel.Result ->
                    if (call.method == "getMessage") {
                        val message = "Hello from Native Android"
                        Log.d("MainActivity", "Sending message: $message")
                        result.success(message)
                    } else {
                        result.notImplemented()
                    }
                }
            // Cache the FlutterEngine to be used by FlutterFragment
            FlutterEngineCache.getInstance().put(ENGINE_ID, this)
        }

        setContent {

            HostComposeTheme {
//                MainContent()
                HomeMainContent( )
            }
        }
    }

   @Composable
    fun HomeMainContent( ) {

        // assuming the width of each tab is fixed
        Column(
            modifier = Modifier
                .background(Color.White)
        ) {
            WondrLogo()
            Spacer(modifier = Modifier.height(12.dp))
            BarMenu()
            TogleTabs()
        }
    }

    @Composable
    fun TogleTabs(){
        var selectedTabIndex by remember { mutableStateOf(0) }
        val tabs = listOf("Insight", "Transaksi","Growth")
        val tabWidthPercentage = 1f / tabs.size

        BoxWithConstraints(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(10.dp))
        {
            val parentWidth = maxWidth
            val tabWidth = parentWidth * tabWidthPercentage
            val animatedOffset by animateDpAsState(
                targetValue = (selectedTabIndex * parentWidth.value / tabs.size).dp,
                animationSpec = tween(durationMillis = 300)
            )
            Box(
                modifier = Modifier
                    .offset(x = animatedOffset)
                    .height(60.dp)
                    .width(tabWidth)
                    .clip(RoundedCornerShape(48.dp))
                    .background(Color(0xFFDEEF5A))
            )
            Row(
                modifier = Modifier

                    .fillMaxWidth()
            )
            {
                tabs.forEachIndexed { index, tab ->
                    val interactionSource = remember { MutableInteractionSource() }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(48.dp))

                            .clickable(
                                interactionSource = interactionSource,
                                indication = null // Remove click effect
                            ) { selectedTabIndex = index }
                            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                            .height(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = tab,
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 16.4.sp,
                                fontWeight = if (selectedTabIndex == index) FontWeight(900) else FontWeight(400) ,
                                color = Color.Black
                            )
                        )
                    }
                }

            }


        }
        Crossfade(targetState = selectedTabIndex) { targetIndex ->
            when (targetIndex) {
                0 -> HomeContent()
                1 -> FlutterContainer()
//                2 -> Tab3Content()
            }
        }
    }

    @Composable
    fun FlutterContainer() {
        val context = LocalContext.current as FragmentActivity
        val fragmentManager = context.supportFragmentManager

        val flutterFragment = remember {
            fragmentManager.findFragmentByTag("flutterFragment") as? FlutterFragment
                ?: FlutterFragment.withCachedEngine(ENGINE_ID).build()
        }

        AndroidView(
            factory = { context ->
                FrameLayout(context).apply {
                    id = ViewCompat.generateViewId()
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { view ->
            fragmentManager.commit {
                if (!flutterFragment.isAdded) {
                    add(view.id, flutterFragment, "flutterFragment")
                }
            }
        }
    }



//    private fun ManageFlutterFragmentVisibility(context: FragmentActivity, selectedTabIndex: Int) {
//        val fragmentManager = context.supportFragmentManager
//        val flutterFragment = fragmentManager.findFragmentByTag("flutterFragment") as? FlutterFragment
//
//        fragmentManager.commit {
//            flutterFragment?.let {
//                if (selectedTabIndex == 1) {
//                    if (it.isDetached) {
//                        attach(it)
//                    }
//                    show(it)
//                } else {
//                    hide(it)
//                }
//            }
//        }
//    }

}
