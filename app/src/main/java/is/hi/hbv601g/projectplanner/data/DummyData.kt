package `is`.hi.hbv601g.projectplanner.data

fun loadProjects(): List<Project> {
    return listOf<Project>(
        Project(1,1,"Ameno", "Verkefnislýsing :)"),
        Project(2,1,"Omenare", "Verkefnislýsing :)"),
        Project(3,1,"Imperavi", "Verkefnislýsing :)"),
        Project(4,1,"Ameno", "Verkefnislýsing :)"),
        Project(5,1,"Dimere", "Verkefnislýsing :)"),
        Project(6,1,"Dimere", "Verkefnislýsing :)"),
        Project(7,1,"Matiro", "Verkefnislýsing :)"),
        Project(8,1,"Ameno", "Verkefnislýsing :)"),
        Project(9,1,"Dori me", "Verkefnislýsing :)"),
        Project(10,2,"Interimo", "Verkefnislýsing :)"),
        Project(11,2,"adapare", "Verkefnislýsing :)"),
        Project(12,2,"Dori me", "Verkefnislýsing :)"),
        Project(13,2,"Ameno", "Verkefnislýsing :)"),
        Project(14,2,"Ameno", "Verkefnislýsing :)"),
        Project(15,2,"Latire", "Verkefnislýsing :)")
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

fun loadGroupMembers(): List<GroupMembers> {
    return listOf<GroupMembers> (
        GroupMembers(1,"Óskar the Great","osa16@hi.is", 1),
        GroupMembers(2,"Ísak CringeLORD", "isl5@hi.is", 1),
        GroupMembers(3,"Vigdís Bossman", "vigdis@hi.is", 1),
    )
}