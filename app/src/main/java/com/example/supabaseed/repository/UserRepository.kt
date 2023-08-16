package com.example.supabaseed.repository

import android.content.Intent
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.providers.IDTokenProvider
import kotlinx.coroutines.flow.StateFlow

interface UserRepository {
    suspend fun signUp(email: String, password: String): Boolean
    suspend fun signInWithEmail(email: String, password: String): Boolean
    suspend fun signInWithTokenID(idToken: String, provider: IDTokenProvider): Boolean
    suspend fun signInWithFacebook(): Boolean
    suspend fun signOut(): Boolean
    fun handleDeeplink(intent: Intent)
    fun getSessionStatus(): StateFlow<SessionStatus>

}