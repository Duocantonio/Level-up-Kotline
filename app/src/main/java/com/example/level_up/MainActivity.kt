package com.example.level_up

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.CameraControl
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.lifecycle.awaitInstance
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.level_up.data.local.AppDatabase
import com.example.level_up.data.repository.UserRepository
import com.example.level_up.navigation.NavigationEvent
import com.example.level_up.ui.permission.WithPermission
import com.example.level_up.ui.theme.LevelUpTheme
import com.example.level_up.uiscreen.*
import com.example.level_up.viewmodels.MainViewModel
import com.example.level_up.viewmodels.UserViewModel
import com.example.level_up.viewmodels.UserViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import java.io.File

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = viewModel()
            val navController = rememberNavController()

            val context = LocalContext.current
            val userDao = AppDatabase.getDatabase(context).userDao()
            val repository = UserRepository(userDao)
            val factory = UserViewModelFactory(repository)
            val usuarioViewModel: UserViewModel = viewModel(factory = factory)
            LevelUpTheme {
                LaunchedEffect(key1 = Unit) {
                    viewModel.navigationEvent.collectLatest { event ->
                        when (event) {
                            is NavigationEvent.NavigateTo -> {
                                navController.navigate(event.route.route) {
                                    event.popUpToRoute?.let { popUpToRoute ->
                                        popUpTo(popUpToRoute.route) {
                                            inclusive = event.inclusive
                                        }
                                    }
                                    launchSingleTop = event.singleTop
                                }
                            }

                            is NavigationEvent.PopBackStack -> {
                                navController.popBackStack()
                            }

                            is NavigationEvent.NavigateUp -> {
                                navController.navigateUp()
                            }
                        }
                    }
                }

                // NavHost es el contenedor que dibuja la pantalla actual
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route // Define la pantalla inicial
                ) {
                    composable(route = Screen.Home.route) {
                        HomeScreen(navController = navController, viewModel = viewModel)
                    }
                    composable(route = Screen.Profile.route) {
                        ProfileScreen(
                            navController = navController,
                            mainViewModel = viewModel,         // para navegación
                            usuarioViewModel = usuarioViewModel // para datos del usuario
                        )
                    }
                    composable(route = Screen.Setitings.route) {
                        SettingsScreen(navController = navController, viewModel = viewModel)
                    }
                    composable(route = Screen.Register.route){
                        RegistroScreen(navController, usuarioViewModel = usuarioViewModel)
                    }

                    composable(route = Screen.Product.route) {
                        ProductosScreen()
                    }

                    composable(route = Screen.JuegosDeMesa.route) { JuegosDeMesaScreen() }
                    composable(route = Screen.Perifericos.route) { PerifericosScreen() }
                    composable(route = Screen.Computadores.route) { ComputadoresScreen() }
                    composable(route = Screen.Consolas.route) { ConsolasScreen() }

                    composable(Screen.Camera.route) { WithPermission( modifier = Modifier.fillMaxSize(), permission = Manifest.permission.CAMERA ) { CameraAppScreen() } }

                }
            }
        }
    }
}


@Composable
fun CameraAppScreen() {
    var lensFacing by remember { mutableIntStateOf(CameraSelector.LENS_FACING_FRONT) }
    var zoomLevel by remember { mutableFloatStateOf(0.0f) }
    val imageCaptureUseCase = remember { ImageCapture.Builder().build() }
    val localContext = LocalContext.current

    Box {
        CameraPreview(
            lensFacing = lensFacing,
            zoomLevel = zoomLevel,
            imageCaptureUseCase = imageCaptureUseCase
        )

        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Row {
                Button(onClick = { lensFacing = CameraSelector.LENS_FACING_FRONT }) {
                    Text("Cámara frontal")
                }
                Button(onClick = { lensFacing = CameraSelector.LENS_FACING_BACK }) {
                    Text("Cámara trasera")
                }
            }

            Row {
                Button(onClick = { zoomLevel = 0.0f }) { Text("Zoom 0.0") }
                Button(onClick = { zoomLevel = 0.5f }) { Text("Zoom 0.5") }
                Button(onClick = { zoomLevel = 1.0f }) { Text("Zoom 1.0") }
            }

            Button(onClick = {
                val outputFileOptions =
                    ImageCapture.OutputFileOptions.Builder(File(localContext.externalCacheDir, "image.jpg"))
                        .build()
                val callback = object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        outputFileResults.savedUri?.shareAsImage(localContext)
                    }

                    override fun onError(exception: ImageCaptureException) {}
                }

                imageCaptureUseCase.takePicture(
                    outputFileOptions,
                    ContextCompat.getMainExecutor(localContext),
                    callback
                )
            }) {
                Text("Capturar")
            }
        }
    }
}

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    lensFacing: Int,
    zoomLevel: Float,
    imageCaptureUseCase: ImageCapture
) {
    val previewUseCase = remember { androidx.camera.core.Preview.Builder().build() }
    var cameraProvider: ProcessCameraProvider? by remember { mutableStateOf(null) }
    var cameraControl: CameraControl? by remember { mutableStateOf(null) }
    val localContext = LocalContext.current
    val lifecycleOwner = localContext as LifecycleOwner

    fun rebindCameraProvider() {
        cameraProvider?.let { provider ->
            val selector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
            provider.unbindAll()
            val camera = provider.bindToLifecycle(lifecycleOwner, selector, previewUseCase, imageCaptureUseCase)
            cameraControl = camera.cameraControl
        }
    }

    LaunchedEffect(Unit) {
        cameraProvider = ProcessCameraProvider.awaitInstance(localContext)
        rebindCameraProvider()
    }
    LaunchedEffect(lensFacing) { rebindCameraProvider() }
    LaunchedEffect(zoomLevel) { cameraControl?.setLinearZoom(zoomLevel) }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            PreviewView(context).also {
                previewUseCase.surfaceProvider = it.surfaceProvider
                rebindCameraProvider()
            }
        }
    )
}

fun Uri.shareAsImage(context: Context) {
    val contentUri = FileProvider.getUriForFile(
        context,
        "com.example.level_up.fileprovider",
        toFile()
    )
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_STREAM, contentUri)
        type = "image/jpeg"
    }
    context.startActivity(Intent.createChooser(shareIntent, null))
}
