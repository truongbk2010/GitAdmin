package com.tyme.feature.home.screen.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tyme.component.R
import com.tyme.component.theme.GitAdminTheme
import com.tyme.core.model.UserModel

@Composable
fun UserItem(
    model: UserModel,
    modifier: Modifier = Modifier,
    onItemClicked: (model: UserModel) -> Unit,
) {
    Card(
        modifier = modifier.clickable {
            onItemClicked.invoke(model)
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(
                        shape = CircleShape,
                    ),
                contentScale = ContentScale.Crop,
                model = ImageRequest.Builder(LocalContext.current).data(model.avatarUrl)
                    .crossfade(true).build(),
                error = painterResource(R.drawable.ic_user_default),
                contentDescription = null,
            )

            Spacer(Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = model.username,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                HorizontalDivider(thickness = 1.dp)

                Text(
                    text = model.htmlUrl,
                    fontStyle = FontStyle.Italic,
                    color = Color.Blue,
                )

            }

        }
    }
}


@Preview
@Composable
private fun UserItemPreview() {
    GitAdminTheme {
        UserItem(
            model = UserModel(
                id = 1,
                username = "roland",
                avatarUrl = "https://avatars.githubusercontent.com/u/28?v=4",
                htmlUrl = "https://github.com/roland",
            ),
            onItemClicked = {},
        )
    }

}

