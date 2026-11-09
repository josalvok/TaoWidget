package org.taogadget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.LocalSize
import androidx.glance.action.ActionParameters
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.FontFamily
import androidx.glance.text.FontStyle
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import java.time.LocalDate
import kotlin.math.sqrt

private val KEY_INDEX = intPreferencesKey("quote_index")
private val KEY_DAY = intPreferencesKey("quote_day")

// Colores suaves: tinta verde-gris de día, niebla clara de noche
private val InkText = ColorProvider(R.color.tao_ink)
private val SoftText = ColorProvider(R.color.tao_soft)

class TaoWidget : GlanceAppWidget() {

    // Exact: recibe el tamaño real que el usuario elige al redimensionar
    override val sizeMode = SizeMode.Exact
    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent { TaoContent() }
    }
}

@Composable
private fun TaoContent() {
    val size = LocalSize.current
    val prefs = currentState<Preferences>()

    // Frase del día por defecto; si el usuario toca, se usa la elegida hoy
    val today = LocalDate.now().toEpochDay().toInt()
    val index = if (prefs[KEY_DAY] == today) {
        prefs[KEY_INDEX] ?: 0
    } else {
        today % TaoQuotes.all.size
    }
    val quote = TaoQuotes.all[index.mod(TaoQuotes.all.size)]

    // Ajusta la tipografía al área disponible (menú de redimensionado de Android)
    val usableArea = (size.width.value - 40f).coerceAtLeast(40f) *
            (size.height.value - 40f).coerceAtLeast(20f)
    val fontSp = sqrt(usableArea / (quote.text.length * 1.35f)).coerceIn(11f, 30f)
    val showChapter = size.height.value >= 80f

    Box(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(ImageProvider(R.drawable.glass_bg))
            .padding(horizontal = 20.dp, vertical = 14.dp)
            .clickable(actionRunCallback<NextQuoteAction>()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = quote.text,
                style = TextStyle(
                    color = InkText,
                    fontSize = fontSp.sp,
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center
                )
            )
            if (showChapter) {
                Spacer(GlanceModifier.height(8.dp))
                Text(
                    text = "道德經 · ${quote.chapter}",
                    style = TextStyle(
                        color = SoftText,
                        fontSize = (fontSp * 0.62f).coerceAtLeast(10f).sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}

class NextQuoteAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(context, glanceId) { prefs ->
            val current = prefs[KEY_INDEX] ?: 0
            var next: Int
            do { next = TaoQuotes.all.indices.random() } while (next == current && TaoQuotes.all.size > 1)
            prefs[KEY_INDEX] = next
            prefs[KEY_DAY] = LocalDate.now().toEpochDay().toInt()
        }
        TaoWidget().update(context, glanceId)
    }
}
