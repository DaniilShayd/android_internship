package com.example.androidinternship.ui.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidinternship.data.User
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.androidinternship.R

@Composable
fun UserListCard(
    user: User,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(R.dimen.padding_medium),
                vertical = dimensionResource(R.dimen.padding_small)
            )
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        UserCardContent(user)
    }
}

@Composable
private fun UserCardContent(user: User) {
    Row(
        modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserAvatar(user.name)
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_medium_small)))
        UserInfo(user)
    }
}

@Composable
private fun UserAvatar(fullName: String) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = getInitials(fullName),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun UserInfo(user: User) {
    Column {
        UserName(user.name)
        UserNickname(user.nickname)
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_smallest)))
        UserAddress(user.address)
        UserPhone(user.phoneNumber)
    }
}

@Composable
private fun UserName(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
private fun UserNickname(nickname: String) {
    Text(
        text = nickname,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
private fun UserAddress(address: String) {
    Text(
        text = "${stringResource(R.string.address)} $address",
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
private fun UserPhone(phoneNumber: String) {
    Text(
        text = "${stringResource(R.string.number)} $phoneNumber",
        style = MaterialTheme.typography.bodyMedium
    )
}

private fun getInitials(fullName: String): String {
    val names = fullName.split(" ").filter { it.isNotBlank() }
    return when {
        names.size >= 2 -> "${names[0].first()}${names[1].first()}".uppercase()
        names.size == 1 -> names[0].take(2).uppercase()
        else -> ""
    }
}