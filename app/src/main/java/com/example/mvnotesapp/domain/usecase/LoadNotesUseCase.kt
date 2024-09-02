package com.example.mvnotesapp.domain.usecase

import com.example.mvnotesapp.data.DomainRepositoryImpl
import com.example.mvnotesapp.domain.model.NoteModel
import javax.inject.Inject

class LoadNotesUseCase @Inject constructor(
    private val domainRepository: DomainRepositoryImpl
): BaseUseCase<List<NoteModel>>() {

    override suspend fun invoke(): List<NoteModel> = domainRepository.getAllNotes()
}