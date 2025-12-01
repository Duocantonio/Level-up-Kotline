package com.example.level_up

import com.example.level_up.api.model.Usuario
import com.example.level_up.api.remote.ApiService
import com.example.level_up.api.repository.UsuarioRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest

class TestTablePostRepository(private val testApi: ApiService): UsuarioRepository(){
    override suspend fun getUsuario(): List<Usuario> {
        return testApi.getUsuario()
    }

    override suspend fun createUsuario(usuario: Usuario): Usuario{
        return testApi.createUsuario(usuario)
    }

    override suspend fun updateUsuario(id: Int, usuario: Usuario): Usuario {
        return testApi.updateUsuario(id, usuario)
    }
}

class PostRepositoryTest: StringSpec({
    "getUsuario() debe retornar una lista de usuarios simulada"{
        val fakeusuario=listOf<Usuario>(
            Usuario(1,"nombre1",123456789,"alo@gmail.com", "clav1", "clave1", "loas"),
            Usuario(2,"nombre2",1789,"alo1@gmail.com", "clav2", "clave2", "loasd")
        )
        val mockApi = mockk<ApiService>()
        coEvery { mockApi.getUsuario()} returns fakeusuario
        val repo = TestTablePostRepository(mockApi)
        runTest{
            val result=repo.getUsuario()
            result shouldContainExactly fakeusuario
        }
    }

    "CreateUsuario() debe retornar un usuario Creado correctamente"{
        val usuarioNuevo= Usuario(4, "test",12, "fsdfsd@gmail.com", "clave", "clave2", "dsadasd")

        val mockApi= mockk<ApiService>()
        coEvery { mockApi.createUsuario(usuarioNuevo) } returns usuarioNuevo

        val repo= TestTablePostRepository(mockApi)
        runTest{
            val result= repo.createUsuario(usuarioNuevo)
            result shouldBe usuarioNuevo
        }
    }
    "updateUsuario() debe retornar un usuario actualizado simulado" {
        val usuarioActualizado = Usuario(1, "Actualizado", 25, "actualizado@gmail.com", "123", "456", "nuevadireccion")

        val mockApi = mockk<ApiService>()
        coEvery { mockApi.updateUsuario(1, usuarioActualizado) } returns usuarioActualizado
        val repo = TestTablePostRepository(mockApi)
        runTest {
            val result = repo.updateUsuario(1, usuarioActualizado)
            result shouldBe usuarioActualizado
        }
    }



})
