package com.project.usecases

import com.project.data.repositories.PreferencesRepository
import javax.inject.Inject

class SavePreferencesUseCase @Inject constructor(private val preferencesRepository: PreferencesRepository) {

    operator fun invoke(key: String, value: String) {
        preferencesRepository.save(key, value)
    }
}