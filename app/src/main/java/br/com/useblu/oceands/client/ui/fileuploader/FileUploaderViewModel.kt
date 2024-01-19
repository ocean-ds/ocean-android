package br.com.useblu.oceands.client.ui.fileuploader

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class FileUploaderViewModel: ViewModel() {

    var selectedFiles by mutableStateOf(emptyList<String>())
        private set

    fun onChooseFile(path: String) {
        selectedFiles = selectedFiles + path.split("/").last()
    }

    fun onDeleteFile(index: Int) {
        selectedFiles = selectedFiles.toMutableList().apply {
            removeAt(index)
        }
    }
}