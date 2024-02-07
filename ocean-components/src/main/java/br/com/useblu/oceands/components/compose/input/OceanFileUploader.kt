package br.com.useblu.oceands.components.compose.input

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.components.compose.OceanIcon
import br.com.useblu.oceands.components.compose.OceanText
import br.com.useblu.oceands.components.compose.OceanTheme
import br.com.useblu.oceands.model.compose.FileStatus
import br.com.useblu.oceands.model.compose.UploadFileModel
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanSpacing
import br.com.useblu.oceands.ui.compose.OceanTextStyle
import br.com.useblu.oceands.utils.OceanIcons

@Preview
@Composable
fun OceanFileUploaderPreview() {
    val selectedFiles = remember {
        mutableStateListOf(
            UploadFileModel(
                fileName = "123_cnh.pdf",
                status = FileStatus.Loading
            ),
            UploadFileModel(
                fileName = "123_cnh.pdf",
                status = FileStatus.Success
            ),
            UploadFileModel(
                fileName = "123_cnh.pdf",
                status = FileStatus.Error("Erro ao enviar arquivo")
            )
        )
    }

    OceanTheme {
        Column(
            modifier = Modifier
                .background(color = OceanColors.interfaceLightPure)
                .padding(16.dp)
        ) {
            OceanFileUploader(
                title = "Selecione um arquivo do seu celular",
                subtitle = "O arquivo deve estar em formato PDF e ter no máximo 20MB.",
                maxFiles = 3,
                onChooseFile = {
                    selectedFiles.add(
                        UploadFileModel(
                            fileName = "arquivo.pdf",
                            status = FileStatus.Success
                        )
                    )
                },
                selectedFiles = selectedFiles,
                onDeleteFile = {
                    selectedFiles.removeAt(it)
                }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OceanFileUploader(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onChooseFile: (file: Uri) -> Unit,
    selectedFiles: List<UploadFileModel> = emptyList(),
    onDeleteFile: (index: Int) -> Unit,
    maxFiles: Int = 1,
    mimeType: String = "*/*"
) {
    val stroke = Stroke(
        width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 6f), 0f)
    )

    val borderColor = OceanColors.interfaceLightDeep

    val fileChooserLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { fileUri ->
        if (fileUri != null) {
            onChooseFile(fileUri)
        }
    }

    Column {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .drawBehind {
                    drawRoundRect(
                        color = borderColor,
                        style = stroke,
                        cornerRadius = CornerRadius(8.dp.toPx())
                    )
                },
            colors = CardDefaults.cardColors(
                containerColor = OceanColors.interfaceLightPure,
                disabledContainerColor = OceanColors.interfaceLightPure
            ),
            shape = RoundedCornerShape(8.dp),
            enabled = selectedFiles.size < maxFiles,
            onClick = {
                fileChooserLauncher.launch(mimeType)
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OceanIcon(
                    iconType = OceanIcons.UPLOAD_OUTLINE,
                    tint = OceanColors.brandPrimaryPure
                )

                OceanSpacing.StackXXS()
                OceanSpacing.StackXXXS()

                OceanText(
                    text = title,
                    style = OceanTextStyle.description,
                    fontWeight = FontWeight.SemiBold,
                    color = OceanColors.brandPrimaryPure,
                    textAlign = TextAlign.Center
                )

                OceanSpacing.StackXXS()

                OceanText(
                    text = subtitle,
                    style = OceanTextStyle.caption,
                    color = OceanColors.interfaceDarkUp,
                    textAlign = TextAlign.Center
                )
            }
        }

        if (selectedFiles.isNotEmpty()) {
            OceanSpacing.StackXS()

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                selectedFiles.forEachIndexed { index, model ->
                    SelectedFile(
                        model = model,
                        onClickDeleteFile = { onDeleteFile(index) }
                    )
                }
            }
        }
    }
}


@Composable
private fun SelectedFile(
    model: UploadFileModel,
    onClickDeleteFile: () -> Unit
) {
    val borderColor = if (model.status is FileStatus.Error) {
        OceanColors.statusNegativePure
    } else {
        OceanColors.brandPrimaryUp
    }

    Column {
        Row(
            modifier = Modifier
                .background(
                    color = OceanColors.interfaceLightPure,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .fillMaxWidth()
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            when (model.status) {
                FileStatus.Loading -> {
                    CircularProgressIndicator(
                        color = OceanColors.brandPrimaryPure,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .padding(2.dp)
                            .size(16.dp),
                        strokeWidth = 2.dp
                    )
                }

                FileStatus.Success -> {
                    OceanIcon(
                        iconType = OceanIcons.CHECK_CIRCLE_SOLID,
                        tint = OceanColors.statusPositivePure,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(20.dp)
                    )
                }

                is FileStatus.Error -> {
                    OceanIcon(
                        iconType = OceanIcons.X_CIRCLE_SOLID,
                        tint = OceanColors.statusNegativePure,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(20.dp)
                    )
                }
            }

            OceanText(
                text = model.fileName,
                style = OceanTextStyle.description,
                color = OceanColors.interfaceDarkDeep,
                modifier = Modifier.weight(1f)
            )

            Box(modifier = Modifier
                .padding(end = 4.dp)
                .size(40.dp)
                .clickable {
                    onClickDeleteFile()
                }
            ) {
                OceanIcon(
                    iconType = OceanIcons.X_SOLID,
                    tint = OceanColors.interfaceDarkUp,
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Center)
                )
            }
        }

        if (model.status is FileStatus.Error) {
            OceanText(
                text = model.status.error,
                style = OceanTextStyle.caption,
                color = OceanColors.statusNegativePure
            )
        }
    }
}