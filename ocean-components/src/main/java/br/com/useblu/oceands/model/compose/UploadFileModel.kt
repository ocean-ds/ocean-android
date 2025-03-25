package br.com.useblu.oceands.model.compose

data class UploadFileModel(
    val fileName: String,
    val status: FileStatus = FileStatus.Success
)

sealed interface FileStatus {
    data object Loading : FileStatus
    data object Success : FileStatus
    data class Error(val error: String) : FileStatus
}
