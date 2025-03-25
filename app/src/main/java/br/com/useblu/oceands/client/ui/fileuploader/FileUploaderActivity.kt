package br.com.useblu.oceands.client.ui.fileuploader

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import br.com.useblu.oceands.client.databinding.ActivityFileUploaderBinding
import br.com.useblu.oceands.components.compose.input.OceanFileUploader

class FileUploaderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFileUploaderBinding
    private lateinit var viewModel: FileUploaderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFileUploaderBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[FileUploaderViewModel::class.java]
        setContentView(binding.root)

        binding.composeView.setContent {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                OceanFileUploader(
                    title = "Selecione até dois arquivos",
                    subtitle = "O arquivo deve estar em formato PDF e ter no máximo 20MB.",
                    onChooseFile = {
                        println("File path: $it")
                        val fileName = getFileName(it)
                        viewModel.onChooseFile(fileName)
                    },
                    selectedFiles = viewModel.selectedFiles,
                    onDeleteFile = {
                        viewModel.onDeleteFile(it)
                    },
                    maxFiles = 2
                )
            }
        }
    }

    private fun getFileName(uri: Uri): String {
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.moveToFirst()
        val name = cursor?.getString(cursor.getColumnIndexOrThrow("_display_name"))
        cursor?.close()
        return name ?: ""
    }
}
