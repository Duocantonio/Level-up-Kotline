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
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.lifecycle.awaitInstance
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toFile
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.example.level_up.viewmodels.CarritoViewModel
import com.example.level_up.viewmodels.LoginViewModel
import com.example.level_up.viewmodels.LoginViewModelFactory
import com.example.level_up.viewmodels.UserViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel: MainViewModel = viewModel()
            val carritoViewModel: CarritoViewModel = viewModel()
            val navController = rememberNavController()


            val context = LocalContext.current
            val userDao = AppDatabase.getDatabase(context).userDao()
            val userRepository = UserRepository(userDao)

            val loginFactory = LoginViewModelFactory(userRepository)
            val loginViewModel: LoginViewModel = viewModel(factory = loginFactory)

            val userFactory = UserViewModelFactory(userRepository)
            val userViewModel: UserViewModel = viewModel(factory = userFactory)

            LevelUpTheme {
                LaunchedEffect(Unit) {
                    mainViewModel.navigationEvent.collectLatest { event ->
                        when (event) {
                            is NavigationEvent.NavigateTo -> {
                                navController.navigate(event.route.route) {
                                    event.popUpToRoute?.let {
                                        popUpTo(it.route) {
                                            inclusive = event.inclusive
                                        }
                                    }
                                    launchSingleTop = event.singleTop
                                }
                            }

                            is NavigationEvent.PopBackStack -> navController.popBackStack()
                            is NavigationEvent.NavigateUp -> navController.navigateUp()
                        }
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = Screen.Login.route
                ) {
                    composable(route = Screen.Home.route) {
                        HomeScreen(
                            navController = navController,
                            viewModel = mainViewModel
                        )
                    }

                    composable(route = Screen.Login.route) {
                        LoginScreen(
                            loginViewModel = loginViewModel,
                            onLoginSuccess = {
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.Login.route) { inclusive = true }
                                }
                            },
                            onRegisterClick = {
                                navController.navigate(Screen.Register.route)
                            }
                        )
                    }



                    composable(route = Screen.Profile.route) {
                        ProfileScreen(
                            navController = navController,
                            mainViewModel = mainViewModel,
                            usuarioViewModel = userViewModel
                        )
                    }

                    composable(route = Screen.Setitings.route) {
                        SettingsScreen(navController = navController, viewModel = mainViewModel)
                    }

                    composable(route = Screen.Register.route) {
                        RegistroScreen(
                            navController = navController,
                            usuarioViewModel = userViewModel
                        )
                    }

                    composable(route = Screen.Product.route) {
                        ProductosScreen()
                    }

                    composable(route = Screen.JuegosDeMesa.route) {
                        JuegosDeMesaScreen(
                            navController = navController,
                            carritoViewModel = carritoViewModel
                        )
                    }

                    composable(route = Screen.Perifericos.route) {
                        PerifericosScreen(
                            navController = navController,
                            carritoViewModel = carritoViewModel
                        )
                    }

                    composable(route = Screen.Computadores.route) {
                        ComputadoresScreen(
                            navController = navController,
                            carritoViewModel = carritoViewModel
                        )
                    }

                    composable(route = Screen.Consolas.route) {
                        ConsolasScreen(
                            navController = navController,
                            carritoViewModel = carritoViewModel
                        )
                    }

                    composable(Screen.Camera.route) {
                        WithPermission(
                            modifier = Modifier.fillMaxSize(),
                            permission = Manifest.permission.CAMERA
                        ) {
                            CameraAppScreen()
                        }
                    }

                    composable(route = Screen.Cart.route) {
                        CarritoScreen(viewModel = carritoViewModel)
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
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = { lensFacing = CameraSelector.LENS_FACING_FRONT }) {
                        Text("Frontal")
                    }
                    Button(onClick = { lensFacing = CameraSelector.LENS_FACING_BACK }) {
                        Text("Trasera")
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = { zoomLevel = 0.0f }) { Text("Zoom 0") }
                    Button(onClick = { zoomLevel = 0.5f }) { Text("Zoom 0.5") }
                    Button(onClick = { zoomLevel = 1.0f }) { Text("Zoom 1.0") }
                }

                Button(onClick = {
                    val outputFileOptions = ImageCapture.OutputFileOptions.Builder(
                        File(localContext.externalCacheDir, "image.jpg")
                    ).build()
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
        val previewUseCase = remember { Preview.Builder().build() }
        var cameraProvider: ProcessCameraProvider? by remember { mutableStateOf(null) }
        var cameraControl: CameraControl? by remember { mutableStateOf(null) }
        val localContext = LocalContext.current
        val lifecycleOwner = localContext as LifecycleOwner

        fun rebindCameraProvider() {
            cameraProvider?.let { provider ->
                val selector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
                provider.unbindAll()
                val camera = provider.bindToLifecycle(
                    lifecycleOwner,
                    selector,
                    previewUseCase,
                    imageCaptureUseCase
                )
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
}