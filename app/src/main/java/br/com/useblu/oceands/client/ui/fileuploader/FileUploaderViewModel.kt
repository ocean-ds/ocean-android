package br.com.useblu.oceands.client.ui.fileuploader

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.useblu.oceands.model.compose.FileStatus
import br.com.useblu.oceands.model.compose.UploadFileModel

class FileUploaderViewModel: ViewModel() {

    var selectedFiles by mutableStateOf(emptyList<UploadFileModel>())
        private set

    fun onChooseFile(fileName: String) {
        val status = listOf(
            FileStatus.Loading,
            FileStatus.Error("Tente novamente"),
            FileStatus.Success
        ).random()

        selectedFiles = selectedFiles + UploadFileModel(fileName, status)
    }

    fun onDeleteFile(index: Int) {
        selectedFiles = selectedFiles.toMutableList().apply {
            removeAt(index)
        }
    }
}