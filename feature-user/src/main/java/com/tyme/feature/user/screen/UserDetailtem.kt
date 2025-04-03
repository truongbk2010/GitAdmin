package com.tyme.feature.user.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tyme.component.R
import com.tyme.component.theme.GitAdminTheme
import com.tyme.component.ui.HorizontalTextIcon
import com.tyme.core.model.UserDetailModel

@Composable
fun UserDetailItem(
    model: UserDetailModel,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
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

                HorizontalTextIcon(icon = {
                    Icon(
                        Icons.Outlined.LocationOn,
                        modifier = Modifier.size(16.dp),
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }) {
                    Text(
                        text = model.location.ifBlank { stringResource(R.string.str_common_na) },
                        modifier = Modifier.padding(horizontal = 1.dp),
                        maxLines = 1,
                        color = Color.Gray,
                    )
                }
            }

        }
    }
}


@Preview
@Composable
private fun UserDetailItemPreview() {
    GitAdminTheme {
        UserDetailItem(
            model = UserDetailModel(
                id = 1,
                username = "roland",
                avatarUrl = "https://avatars.githubusercontent.com/u/28?v=4",
                htmlUrl = "https://github.com/roland",
                location = "Vietnam",
                following = 10,
                followers = 11,
            ),
        )
    }

}

