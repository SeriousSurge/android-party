package com.hiremarknolan.android_party_compose.presentation.common

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(name="Night", group="DayNight", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name="Day", group="DayNight", uiMode = Configuration.UI_MODE_NIGHT_NO)
annotation class DayNightPreview