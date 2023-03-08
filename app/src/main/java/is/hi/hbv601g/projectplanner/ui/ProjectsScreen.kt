package `is`.hi.hbv601g.projectplanner.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import `is`.hi.hbv601g.projectplanner.data.Project

@Composable
fun ProjectsItem(project: Project, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                text = project.id.toString(),
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = project.title,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun ProjectsScreen(projectList: List<Project>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(projectList) { project ->
            ProjectsItem(project)
        }
    }
}