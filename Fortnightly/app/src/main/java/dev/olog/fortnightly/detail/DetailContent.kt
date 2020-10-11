package dev.olog.fortnightly.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.EmphasisAmbient
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.SpanStyleRange
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import dev.olog.fortnightly.R
import dev.olog.fortnightly.composable.CollapsibleToolbar
import dev.olog.fortnightly.composable.FilterImage
import dev.olog.fortnightly.composable.StatusBar
import dev.olog.fortnightly.composable.TagsContent
import dev.olog.fortnightly.ui.FortnightlyTheme
import dev.olog.fortnightly.ui.merryweather

@Preview
@Composable
private fun DetailContentPreview() {
    FortnightlyTheme {
        Surface {
            Column {
                StatusBar()
                DetailContent()
            }
        }
    }
}

@Composable
fun DetailContent() {
    Column {
        CollapsibleToolbar(scrollState = rememberLazyListState())
        ScrollableColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FilterImage(
                asset = imageResource(id = R.drawable.img1),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().aspectRatio(1.35f)
            )
            Column(
                Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TagsContent(tags = listOf("US", "Environment"))
                Text(
                    text = "Llamas Patrol The Central Coast of California",
                    style = MaterialTheme.typography.h3,
                    fontSize = 34.sp
                )
                ProvideEmphasis(emphasis = EmphasisAmbient.current.medium) {
                    Text(
                        text = "How these fuzzy creatures are protecting America's farmland and your salad now",
                        style = MaterialTheme.typography.body1
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        asset = imageResource(R.drawable.img3),
                        modifier = Modifier.clip(CircleShape).size(48.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                val text = "The Salinas vallyes and its rolling farmland produces the majority of leafy greens, vegetables, and fruits grown on the vine consumed in this country"
                Text(
                    text = AnnotatedString(
                        text = text,
                        listOf(
                            SpanStyleRange(SpanStyle(fontFamily = merryweather, fontStyle = FontStyle.Italic, fontSize = 72.sp, fontWeight = FontWeight.Black), 0, 1),

                        )
                    )

                )
            }
        }
    }
}