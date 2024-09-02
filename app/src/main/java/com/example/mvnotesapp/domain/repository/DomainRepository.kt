package com.example.mvnotesapp.domain.repository

import com.example.mvnotesapp.domain.model.NoteModel

interface DomainRepository {
    suspend fun getAllNotes(): List<NoteModel>
}