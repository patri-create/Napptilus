package com.project.usecases

import com.project.data.repositories.PreferencesRepository
import javax.inject.Inject

class FindPreferencesUseCase @Inject constructor(private val preferencesRepository: PreferencesRepository) {

    operator fun invoke(key: String): String {
        return preferencesRepository.find(key)
    }
}