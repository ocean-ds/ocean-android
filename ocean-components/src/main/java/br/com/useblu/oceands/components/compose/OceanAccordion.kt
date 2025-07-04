package br.com.useblu.oceands.components.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.useblu.oceands.R
import br.com.useblu.oceands.ui.compose.OceanColors
import br.com.useblu.oceands.ui.compose.OceanFontSize
import br.com.useblu.oceands.ui.compose.OceanSpacing

@Preview
@Composable
fun PreviewOceanAccordion() {
    Column {
        // Exemplo 1: Texto em negrito
        OceanAccordion(
            title = "Formatação Negrito",
            description = "Este texto contém palavras em <b>negrito</b> para destacar informações importantes.",
            expanded = true
        )

        OceanSpacing.StackXS()

        // Exemplo 2: Texto em itálico
        OceanAccordion(
            title = "Formatação Itálico",
            description = "Aqui temos texto em <i>itálico</i> para dar ênfase suave ao conteúdo.",
            expanded = true
        )

        OceanSpacing.StackXS()

        // Exemplo 3: Texto sublinhado
        OceanAccordion(
            title = "Formatação Sublinhado",
            description = "Texto com <u>sublinhado</u> para destacar termos específicos.",
            expanded = true
        )

        OceanSpacing.StackXS()

        // Exemplo 4: Texto riscado
        OceanAccordion(
            title = "Formatação Riscado",
            description = "Preço antigo: <s>R\$ 99,90</s> - Novo preço: R\$ 79,90",
            expanded = true
        )

        OceanSpacing.StackXS()

        // Exemplo 5: Combinando formatações
        OceanAccordion(
            title = "Formatações Combinadas",
            description = "Texto com <b>negrito</b>, <i>itálico</i> e <u>sublinhado</u> no mesmo parágrafo.",
            expanded = true
        )

        OceanSpacing.StackXS()

        // Exemplo 6: Texto com cores (se suportado)
        OceanAccordion(
            title = "Formatação com Cores",
            description = "Texto normal e <font color='#FF0000'>texto vermelho</font> para alertas.",
            expanded = true
        )

        OceanSpacing.StackXS()

        // Exemplo 7: Links (se suportado)
        OceanAccordion(
            title = "Links",
            description = "Visite nosso site: <a href='https://exemplo.com'>www.exemplo.com</a>",
            expanded = true
        )

        OceanSpacing.StackXS()

        // Exemplo 8: Texto com quebras de linha
        OceanAccordion(
            title = "Quebras de Linha",
            description = "Primeira linha<br/>Segunda linha<br/>Terceira linha",
            expanded = true
        )

        OceanSpacing.StackXS()

        // Exemplo 9: Formatação complexa
        OceanAccordion(
            title = "Formatação Complexa",
            description = "<b>Importante:</b> Este produto está em <i>promoção</i>.<br/><u>Preço original</u>: <s>R\$ 199,90</s><br/><b>Preço atual</b>: R\$ 149,90",
            expanded = true
        )

        OceanSpacing.StackXS()

        // Exemplo 10: Texto com formatação aninhada
        OceanAccordion(
            title = "Formatação Aninhada",
            description = "Texto <b>negrito com <i>itálico dentro</i></b> e <u>sublinhado com <b>negrito</b></u>.",
            expanded = true
        )

        OceanSpacing.StackXS()

        // Exemplo 11: Lista simples (usando quebras de linha)
        OceanAccordion(
            title = "Lista de Recursos",
            description = "<b>Recursos inclusos:</b><br/>• Interface intuitiva<br/>• <i>Suporte 24/7</i><br/>• <u>Atualizações gratuitas</u>",
            expanded = true
        )

        OceanSpacing.StackXS()

        // Exemplo 12: Formatação para FAQ
        OceanAccordion(
            title = "Como cancelar minha assinatura?",
            description = "<b>Passo 1:</b> Acesse suas configurações<br/><b>Passo 2:</b> Clique em <i>Gerenciar Assinatura</i><br/><b>Passo 3:</b> Selecione <u>Cancelar</u>",
            expanded = true
        )

        OceanSpacing.StackXS()

        // Exemplo 13: Formatação para termos
        OceanAccordion(
            title = "Termos de Uso",
            description = "Ao utilizar este serviço, você concorda com nossos <b>termos e condições</b>. Para mais informações, consulte nossa <a href='#'>política de privacidade</a>.",
            expanded = true
        )

        OceanSpacing.StackXS()

        // Exemplo 14: Formatação para alertas
        OceanAccordion(
            title = "Aviso Importante",
            description = "<b>ATENÇÃO:</b> <font color='#FF6B6B'>Este recurso será descontinuado</font> em <u>30 dias</u>. <i>Migre seus dados antes do prazo final.</i>",
            expanded = true
        )

        OceanSpacing.StackXS()

        // Exemplo 15: Formatação para instruções
        OceanAccordion(
            title = "Como configurar?",
            description = "<b>Configuração rápida:</b><br/><i>1. Abra o aplicativo</i><br/><i>2. Vá para <u>Configurações</u></i><br/><i>3. Ative a opção <b>Sincronização</b></i>",
            expanded = true
        )

        OceanSpacing.StackSM()
    }
}

@Composable
fun OceanAccordion(
    title: String,
    description: String,
    expanded: Boolean
) {
    var expandedContent by remember { mutableStateOf(expanded) }

    Column(
        modifier = Modifier.background(OceanColors.interfaceLightPure)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expandedContent = !expandedContent
                }
                .padding(top = if (expandedContent) OceanSpacing.xxsExtra else OceanSpacing.xs)
                .padding(bottom = if (expandedContent) OceanSpacing.xxs else OceanSpacing.xs)
        ) {
            OceanText(
                text = title,
                color = if (expandedContent) OceanColors.brandPrimaryDown else OceanColors.interfaceDarkDown,
                fontSize = OceanFontSize.xxs,
                modifier = Modifier.weight(1f)
            )

            OceanSpacing.StackXS()

            Icon(
                painter = painterResource(id = R.drawable.ocean_icon_chevron_right_solid),
                contentDescription = null,
                tint = if (expandedContent) OceanColors.brandPrimaryDown else OceanColors.interfaceDarkDown,
                modifier = Modifier
                    .size(16.dp)
                    .rotate(if (expandedContent) 270f else 90f)
            )
        }

        AnimatedVisibility(visible = expandedContent) {
            Column {
                OceanText(
                    text = description,
                    color = OceanColors.interfaceDarkDown,
                    fontSize = OceanFontSize.xxs,
                    modifier = Modifier.fillMaxWidth()
                )
                OceanSpacing.StackXS()
            }
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = OceanColors.interfaceLightDown
        )
    }
}
