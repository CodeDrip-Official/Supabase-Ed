package com.example.supabaseed.repository.impl

import android.content.Intent
import android.util.Log
import com.example.supabaseed.repository.UserRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.exceptions.HttpRequestException
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.handleDeeplinks
import io.github.jan.supabase.gotrue.providers.IDTokenProvider
import io.github.jan.supabase.gotrue.providers.OAuthProvider
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.IDToken
import io.github.jan.supabase.gotrue.providers.invoke
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient,
) : UserRepository {

    private val authService = supabaseClient.gotrue
    override suspend fun signUp(email: String, password: String): Boolean {
        return try {
            authService.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } catch (e: HttpRequestException) {
            e.printStackTrace()
            false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun signInWithEmail(email: String, password: String): Boolean {
        return try {
            authService.loginWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } catch (e: HttpRequestException) {
            e.printStackTrace()
            false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun signInWithFacebook(): Boolean {
        return try {
            authService.loginWith(OAuthProvider.Companion.invoke("facebook"))
            true
        } catch (e: HttpRequestException) {
            e.printStackTrace()
            false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun signInWithTokenID(
        idToken: String,
        provider: IDTokenProvider
    ): Boolean {
        return try {
            authService.loginWith(IDToken) {
                this.provider = provider
                this.idToken = idToken
            }
            true
        } catch (e: HttpRequestException) {
            e.printStackTrace()
            false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun signOut(): Boolean {
        return try {
            authService.logout()
            true
        } catch (e: HttpRequestException) {
            e.printStackTrace()
            false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun handleDeeplink(intent: Intent) {
        Log.d("Test", "Handling Deeplink $intent")
        supabaseClient.handleDeeplinks(intent)
    }

    override fun getSessionStatus(): StateFlow<SessionStatus> {
        return supabaseClient.gotrue.sessionStatus
    }
}