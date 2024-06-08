package com.example.takenotes

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.takenotes.model.FakeNotes
import com.example.takenotes.model.Note
import com.example.takenotes.ui.theme.TakeNotesTheme
import com.example.takenotes.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakeNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val noteViewModel = viewModel<NoteViewModel>()
                    FullApp(noteViewModel = noteViewModel)
                }
            }
        }
    }
}

@Composable
fun TopAppBar(modifier: Modifier = Modifier){
    Surface(
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 5.dp,
        modifier = modifier
            .fillMaxWidth()

    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )

        ){
            Text(
                text = "Note",
                modifier = Modifier.weight(1f),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notification"
                )
            }
        }
    }
}


@Composable
fun MainForm(
    noteViewModel: NoteViewModel,
    modifier: Modifier = Modifier) {

    var title by rememberSaveable { mutableStateOf("") }
    var note by rememberSaveable { mutableStateOf("" ) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = title,
            onValueChange = { it ->
                title = it
            },
            label = { Text("Title") },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = note,
            onValueChange = {it ->
                note = it
            },
            label = {Text("Add a Note")},
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                noteViewModel.addNote(
                    Note(title = title, details = note, dateCreated = Date())
                )
            }
        ) {
            Text("Save")
        }
    }
}

@Composable
fun NoteDisplay(note: Note, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .border(
                border = BorderStroke(1.dp, Color.Gray), // Add border here
                shape = RoundedCornerShape(15.dp) // Ensure the border shape matches the clip shape
            ),

    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                text = note.title,
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = note.details,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .format(note.dateCreated)
            )
        }


    }
}

@Composable
fun ListNoteDisplay(listNote: List<Note>, modifier: Modifier = Modifier){
    LazyColumn(
        modifier = modifier
    ) {
        items(listNote.size) {index ->
            NoteDisplay(
                note = listNote[index],
                modifier = Modifier.padding(bottom = 15.dp)
            )
        }
    }
}


@Composable
fun FullApp(noteViewModel: NoteViewModel) {

    val listNote by noteViewModel.noteList.collectAsState()

    val fakeNotes = FakeNotes.list
    Scaffold(
        topBar = {
            TopAppBar()
        },
        content = { paddingValues ->

            Column(modifier = Modifier.padding(paddingValues)) {
                MainForm(
                    noteViewModel,
                    modifier = Modifier.padding(top = 32.dp))

                Divider(modifier = Modifier.padding(20.dp))

                ListNoteDisplay(listNote)
            }


        }
    )
}


@Preview(showBackground = true)
@Composable
fun NoteDisplay() {
    TakeNotesTheme {
        val note: Note = Note(
            title = "Sample Note 1",
            details = "Details of Sample Note 1",
            dateCreated = Date()
        )

        NoteDisplay(note)
    }
}

@Preview(showBackground = true)
@Composable
fun FullListNoteDisplay(){
    TakeNotesTheme {
        ListNoteDisplay(FakeNotes.list)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun FullAppDisplay() {
//    TakeNotesTheme {
//        FullApp()
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun mainForm() {
//    TakeNotesTheme {
//        MainForm()
//    }
//}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TakeNotesTheme {
        TopAppBar()
    }
}