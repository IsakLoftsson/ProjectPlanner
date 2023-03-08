package `is`.hi.hbv601g.projectplanner

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import `is`.hi.hbv601g.projectplanner.data.DummyData
import `is`.hi.hbv601g.projectplanner.ui.LoginScreen
import `is`.hi.hbv601g.projectplanner.ui.ProjectsScreen
import `is`.hi.hbv601g.projectplanner.ui.RegistrationScreen


enum class ProjectPlannerScreen(@StringRes val title: Int) {
    Login(title = R.string.login),
    Register(title = R.string.register),
    Projects(title = R.string.project_list),
    Project(title = R.string.project),
    Task(title = R.string.task)
}

@Composable
fun ProjectPlannerApp(
    modifier: Modifier = Modifier,
    viewModel: ProjectsViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ProjectPlannerScreen.valueOf(
        backStackEntry?.destination?.route ?: ProjectPlannerScreen.Login.name
    )

    Scaffold { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = ProjectPlannerScreen.Projects.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = ProjectPlannerScreen.Login.name) {
                LoginScreen()
            }
            composable(route = ProjectPlannerScreen.Register.name) {
                RegistrationScreen()
            }
            composable(route = ProjectPlannerScreen.Projects.name) {
                ProjectsScreen(projectList = DummyData().loadProjects())
            }
            composable(route = ProjectPlannerScreen.Project.name) {

            }
            composable(route = ProjectPlannerScreen.Task.name) {

            }
        }

    }
}