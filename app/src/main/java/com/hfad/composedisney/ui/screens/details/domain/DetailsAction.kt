package com.hfad.composedisney.ui.screens.details.domain

sealed class DetailsAction {

    data class Init(val id: Int) : DetailsAction()
}