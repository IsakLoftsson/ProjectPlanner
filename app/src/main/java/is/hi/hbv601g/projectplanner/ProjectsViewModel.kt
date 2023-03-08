package `is`.hi.hbv601g.projectplanner

import androidx.lifecycle.ViewModel
import `is`.hi.hbv601g.projectplanner.data.ProjectPlannerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProjectsViewModel : ViewModel(){

    private val _uiState = MutableStateFlow(ProjectPlannerUiState())
    val uiState: StateFlow<ProjectPlannerUiState> = _uiState.asStateFlow()
}