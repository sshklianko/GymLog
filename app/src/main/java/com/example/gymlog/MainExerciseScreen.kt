package com.example.gymlog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.gymlog.data.Exercise
import com.example.gymlog.data.SingleWorkoutHistory
import com.example.gymlog.data.historyOfWorkouts
import com.example.gymlog.ui.theme.GymLogTheme

@Composable
fun MainExerciseScreen() {
    Scaffold(
        topBar = {
            mainScreenTopBar()
    }) {
        LazyColumn(contentPadding = it) {
            items(historyOfWorkouts) {
                SingleWorkoutHistoryView(singleWorkout = it)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mainScreenTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.ic_woof_logo),

                    // Content Description is not needed here - image is decorative, and setting a
                    // null content description allows accessibility services to skip this element
                    // during navigation.

                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = TextStyle(fontStyle = FontStyle.Italic),

                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun SingleWorkoutHistoryView(singleWorkout: SingleWorkoutHistory, modifier: Modifier = Modifier) {
    Card(modifier = Modifier
        .padding(dimensionResource(R.dimen.padding_small))
        .fillMaxWidth()) {
        val leftPadding = Modifier.padding(start = dimensionResource(R.dimen.padding_small));
        Text(
            text = "Duration ${singleWorkout.lengthInMins} mins",
            modifier = leftPadding.padding(top = dimensionResource(R.dimen.padding_small))
        )
        singleWorkout.exercises.forEach { item ->
            ExercisesOfWorkoutView(item, leftPadding)
        }
    }
}

@Composable
fun ExercisesOfWorkoutView(ex: Exercise, modifier: Modifier = Modifier) {
    Row {
        Image(
            modifier = Modifier
                .size(dimensionResource(R.dimen.small_image_size))
                .padding(dimensionResource(R.dimen.padding_small)),
            painter = painterResource(R.drawable.ic_woof_logo),

            contentDescription = null,
        )
        Text(
            text = "${ex.name}",
            modifier = modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
    }
    Row(modifier = modifier) {
        Text(
            text = "Set#",
            modifier = Modifier.weight(1f),
            style = TextStyle(color = Color.Gray),
        )
        Text(
            text = "Weight&Reps",
            modifier = Modifier
                .weight(9f)
                .padding(start = dimensionResource(R.dimen.padding_small)),
            style = TextStyle(color = Color.Gray),
        )
    }
    var counter = 0;
    ex.exerciseSets.forEach { item ->
        Row(modifier = modifier) {
            Text(
                text = "${++counter}",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = TextStyle(fontWeight = FontWeight.Bold),

            )
            Text(
                text = "${item.weight} kg x ${item.numberOfRepetitions}",
                modifier = Modifier
                    .weight(9f)
                    .padding(start = dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    GymLogTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainExerciseScreen()
        }
    }
}