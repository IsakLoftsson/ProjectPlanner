package `is`.hi.hbv601g.projectplanner.data

fun loadProjects(): List<Project> {
    return listOf<Project>(
        Project(1,1,"Ameno", "Hópur 7"),
        Project(2,1,"Omenare", "Hópur 5"),
        Project(3,1,"Imperavi", "Hópur 1"),
        Project(4,1,"Ameno", "Hópur 2"),
        Project(5,1,"Dimere", "Hópur 7"),
        Project(6,1,"Dimere", "Óskar"),
        Project(7,1,"Matiro", "Ísak"),
        Project(8,1,"Ameno", "Hópur 7"),
        Project(9,1,"Dori me", "Vigdís"),
        Project(10,2,"Interimo", "Óskar"),
        Project(11,2,"adapare", "Hópur 7"),
        Project(12,2,"Dori me", "Ísak"),
        Project(13,2,"Ameno", "Hópur 4"),
        Project(14,2,"Ameno", "Hópur 3"),
        Project(15,2,"Latire", "Vigdís")
    )
}

fun loadTasks(): List<Task> {
    return listOf<Task> (
        Task(1,1,"Ameno 1"),
        Task(2,1,"Ameno 2"),
        Task(3,1,"Ameno 3"),
        Task(4,1,"Ameno 4"),
        Task(5,1,"Ameno 5"),
        Task(6,2,"Omenare 1")
    )
}