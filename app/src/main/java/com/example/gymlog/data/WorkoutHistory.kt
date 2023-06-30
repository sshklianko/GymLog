package com.example.gymlog.data

data class SingleWorkoutHistory(
    val lengthInMins: Int,
    val exercises: List<Exercise>
)

data class Exercise(
    val name: String,
    val exerciseSets: List<ExerciseSet>
)

data class ExerciseSet(
    val numberOfRepetitions: Int,
    val weight: Double = 0.0
)

val historyOfWorkouts = mutableListOf(
    SingleWorkoutHistory(60, listOf(
        Exercise("Ex1", listOf(
            ExerciseSet(3, 15.0),
            ExerciseSet(3, 15.0),
            ExerciseSet(3, 15.0),
        )),Exercise("Ex2", listOf(
            ExerciseSet(5, 20.0),
            ExerciseSet(5, 25.0),
            ExerciseSet(5, 25.0),
        )),
    )),

    SingleWorkoutHistory(60, listOf(
        Exercise("Ex3", listOf(
            ExerciseSet(10, 15.0),
            ExerciseSet(10, 15.0),
            ExerciseSet(10, 15.0),
        )),Exercise("Ex4", listOf(
            ExerciseSet(15, 20.0),
            ExerciseSet(15, 25.0),
            ExerciseSet(15, 25.0),
        )),
    )),
)